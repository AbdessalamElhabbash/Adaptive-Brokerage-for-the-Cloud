package com.lancaster.dsl.SLO_DSL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.lancaster.dsl.SLO_DSL.AbstractWeightedGraph.AbstractGraph;
import com.lancaster.dsl.SLO_DSL.WeightedGraph.Graph;

public class Selector {

	public static List<String> nonDBSlo = new ArrayList<>(Arrays.asList("Monthly_egress_bandwidth"));

	public static ArrayList<Service> select(Connection connection, ArrayList<Component> components) {
		ArrayList<Service> selectedServices = new ArrayList<>();
		ArrayList<ArrayList<Service>> candidateServices = findCandidateServices(connection, components, null);
		for (ArrayList<Service> services : candidateServices) {
			if (!services.isEmpty()) {
				Service selectedService = selectMaxService(services,
						getComponent(services.get(0).getComponentName(), components));
				selectedServices.add(selectedService);
			}
		}
		return selectedServices;
	}

	// assuming appSLOs is not empty
	public static ArrayList<Service> select(Connection connection, ArrayList<Component> components,
			ArrayList<SLO> appSLOs, ArrayList<Map<String, String>> dataFlow) {
		ArrayList<ArrayList<Service>> candidateServices = findCandidateServices(connection, components, dataFlow);
		ArrayList<ArrayList<Service>> combinations = new ArrayList<>();
		generateCombinations(candidateServices, combinations);
		long startTime = System.currentTimeMillis();
		ArrayList<ArrayList<Service>> satisfyingCombinations = new ArrayList<>();

		SLO mup = getSLO("Monthly_uptime_percentage", appSLOs);
		if (mup != null) {
			for (ArrayList<Service> combination : combinations) {
				boolean satsfying = true;
				for (Service service : combination) {
					double sloValue = Double.parseDouble(getSLO("Monthly_uptime_percentage", service.getSlos()).getValue());
					if (!(sloValue >= Double.parseDouble(mup.getValue()))){
						satsfying = false;
					}
				}
				if (satsfying) {
					satisfyingCombinations.add(combination);
				}
			}
		}

		// check other slos if any
		// ....

		// check Monthly_bandwidth_cost

		SLO mbc = getSLO("Monthly_bandwidth_cost", appSLOs);
		ArrayList<Graph> satisfyingGraphs = new ArrayList<>();
		if (mbc != null) {
			ArrayList<Graph> graphs = generateConcreteGraphs(candidateServices, satisfyingCombinations, dataFlow,
					components);
			Graph selectedGraph = null;
			double minCost = 999999999;
			for (Graph graph : graphs) {
				if (graph.getWeightsSum() <= Double.parseDouble(mbc.getValue())) {
					satisfyingGraphs.add(graph);
				}
			}
		}

		// calculate the utility of the combinations and return the highest one
		Map<Graph, Double> utilities = new LinkedHashMap<>();
		if (!satisfyingGraphs.isEmpty()) {
			for (Graph combination : satisfyingGraphs) {
				utilities.put(combination, calculateCombinationUtility(combination, appSLOs));
			}
		}
		if (!utilities.isEmpty()) {
			Map.Entry<Graph, Double> maxEntry = null;
			for (Map.Entry<Graph, Double> entry : utilities.entrySet()) {
				if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
					maxEntry = entry;
				}
			}
			System.out.println(System.currentTimeMillis() + ", " +startTime);
			return maxEntry.getKey().getVertices();
		}
		System.out.println(System.currentTimeMillis() + ", " +startTime);
		return new ArrayList<>();
	}
	
	private static ArrayList<ArrayList<Service>> findCandidateServices(Connection connection,
			ArrayList<Component> components, ArrayList<Map<String, String>> dataFlow) {
		ArrayList<ArrayList<Service>> candidateServices = new ArrayList<>();
		for (Component comp : components) {
			ArrayList<Service> compServices = new ArrayList<>();
			try {

				Statement statement = connection.createStatement();
				// for (SLO slo : comp.getSlos()) {
				// query += " ," + slo.getName();
				// }
				String query = "SELECT * FROM  services WHERE type = '" + comp.getType() + "' ";
				for (SLO slo : comp.getSlos()) {
					if (!Selector.nonDBSlo.contains(slo.getName())) {
						query += " AND " + slo.getName() + slo.getOperator() + slo.getValue();
					}
				}
				query += ";";
				//System.out.println(query);

				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					Service candidateInstance = new Service();
					candidateInstance.setName(resultSet.getString("Name"));
					candidateInstance.setProvider(resultSet.getString("Provider"));
					candidateInstance.setType(resultSet.getString("type"));
					candidateInstance.setInstanceType(resultSet.getString("instance_type"));
					candidateInstance.setContinent(resultSet.getString("continent"));
					candidateInstance.setRegion(resultSet.getString("region_name"));
					candidateInstance.setZone(resultSet.getString("zone"));
					candidateInstance.setComponentName(comp.getName());
					ArrayList<SLO> serviceSLOs = new ArrayList<>();
					for (SLO slo : comp.getSlos()) {
						if (!Selector.nonDBSlo.contains(slo.getName())) {
							SLO serviceSLO = new SLO();
							serviceSLO.setName(slo.getName());
							serviceSLO.setValue(resultSet.getString(slo.getName()));
							serviceSLOs.add(serviceSLO);
						}
					}
					candidateInstance.setSlos(serviceSLOs);
					compServices.add(candidateInstance);
				}
				if (!compServices.isEmpty()) {
					candidateServices.add(compServices);
				} else if(dataFlow != null && !dataFlow.isEmpty()){
					ArrayList<Map<String, String>> mapsToRemove = new ArrayList<>();
					for (Map<String, String> dFlow : dataFlow) {
						if (dFlow.containsKey(comp.getName()) || dFlow.containsValue(comp.getName())) {
							mapsToRemove.add(dFlow);
						}
					}
					for (Map<String, String> map : mapsToRemove) {
						dataFlow.remove(map);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(candidateServices);

		return candidateServices;
	}

	private static Map<String, String> randomlySelect(ArrayList<Map<String, String>> candidateInstances) {
		Random rand = new Random();
		return candidateInstances.get(rand.nextInt(candidateInstances.size()));
	}

	public static ArrayList<Graph> generateConcreteGraphs(ArrayList<ArrayList<Service>> candidateServices,
			ArrayList<ArrayList<Service>> combinations, ArrayList<Map<String, String>> dataFlow,
			ArrayList<Component> components) {
		ArrayList<Graph> graphs = new ArrayList<>();
		for (ArrayList<Service> vertices : combinations) {
			Graph graph = new Graph(vertices);
			for (Map<String, String> dFlow : dataFlow) {
				for (Map.Entry<String, String> entry : dFlow.entrySet()) {
					String from = entry.getKey();
					String to = entry.getValue();

					if (to.equalsIgnoreCase("internet")) {
						Service internet = new Service("internet");
						internet.setRegion("internet");
						internet.setComponentName("internet");
						internet.setProvider("internet");
						graph.addVertex(internet);
					}
					SLO monthly_egress_bandwidth = getSLO("Monthly_egress_bandwidth", getComponent(from, components).getSlos());
					double value = Double.parseDouble(monthly_egress_bandwidth.getValue());
					if(monthly_egress_bandwidth.getUnit().equals("TB")){
						value *= 1000;
					}
					graph.addEgde(graph.getVertex(from), graph.getVertex(to), bwCost(graph.getVertex(from),
							graph.getVertex(to), value));
				}
			}
			graphs.add(graph);
		}
		return graphs;
	}

	private static AbstractGraph generateAbstractGraphs(ArrayList<Component> components, Map<String, String> dataFlow) {
		AbstractGraph graph = new AbstractGraph(components);
		for (String from : dataFlow.keySet()) {
			String to = dataFlow.get("from");
			graph.addEgde(getComponent(from, components), getComponent(to, components), 0);
		}
		return graph;
	}

	private static Service selectMaxService(ArrayList<Service> candidateServices, Component request) {
		Service maxService = null;
		double maxUtility = 0;
		for (Service candidateService : candidateServices) {
			double serviceUtility = serviceUtility(candidateService, request);
			if (serviceUtility >= maxUtility) {
				maxUtility = serviceUtility;
				maxService = candidateService;
			}
		}
		return maxService;
	}

	public static void generateCombinations(ArrayList<ArrayList<Service>> list,
			ArrayList<ArrayList<Service>> combinations) {

		int n = list.size();
		int[] indices = new int[n];
		for (int i = 0; i < n; i++) {
			indices[i] = 0;
		}
		while (true) {
			for (int i = 0; i < n; i = i + list.size()) {
				ArrayList<Service> vertices = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					vertices.add(list.get(j).get(indices[j]));
				}
				combinations.add(vertices);
			}
			int next = n - 1;
			while (next >= 0 && (indices[next] + 1 >= list.get(next).size())) {
				next--;
			}
			if (next < 0) {
				return;
			}
			indices[next]++;

			for (int i = next + 1; i < n; i++) {
				indices[i] = 0;
			}
		}
	}
	
	private static double serviceUtility(Service candidateService, Component request) {
		double serviceUtility = 0;
		for (SLO userSLO : request.getSlos()) {
			SLO serviceSLO = getSLO(userSLO.getName(), candidateService.getSlos());
			serviceUtility += calculateUtility(Double.parseDouble(userSLO.getValue()), Double.parseDouble(serviceSLO.getValue()));
		}
		return serviceUtility;
	}

	private static double calculateUtility(double uSLO, double sSLO) {
		if (sSLO < uSLO) {
			return -1;
		}
		return 1 - Math.exp(uSLO - sSLO);
	}

	private static double calculateCombinationUtility(Graph combination, ArrayList<SLO> appSLOs) {
		double combinationUtility = 0;
		for (SLO slo : appSLOs) {
			if (slo.getName().equals("Monthly_uptime_percentage")) {
				ArrayList<Double> sloValues = new ArrayList<>();
				for (Service vertex : combination.getVertices()) {
					if (!vertex.getName().equalsIgnoreCase("internet")) {
						sloValues.add(Double.parseDouble(getSLO("Monthly_uptime_percentage", vertex.getSlos()).getValue()));
					}
				}
				combinationUtility += calculateUtility(Double.parseDouble(slo.getValue()),
						sloValues.get(sloValues.indexOf(Collections.min(sloValues))));
			}
			if (slo.getName().equals("Monthly_bandwidth_cost")) {
				combinationUtility += calculateUtility(Double.parseDouble(slo.getValue()), combination.getWeightsSum());
			}
		}
		return combinationUtility;
	}

	private static SLO getSLO(String name, ArrayList<SLO> slos) {
		for (SLO slo : slos) {
			if (slo.getName().equalsIgnoreCase(name)) {
				return slo;
			}
		}
		return null;
	}

	private static Component getComponent(String name, ArrayList<Component> components) {
		for (Component comp : components) {
			if (comp.getName().equalsIgnoreCase(name)) {
				return comp;
			}
		}
		return null;
	}

	public static double bwCost(Service from, Service to, double requiredMonthlyBW) {
		if (from.getName().equalsIgnoreCase("azure_cosmos_db") || from.getName().equalsIgnoreCase("azure_compute")) {
			if (to.getProvider().equals("azure") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else {
				if (requiredMonthlyBW <= 5) {
					return 0;
				} else if (requiredMonthlyBW <= 10000) {
					return 0.087 * requiredMonthlyBW;
				} else if (requiredMonthlyBW <= 50000) {
					return 0.083 * requiredMonthlyBW;
				} else if (requiredMonthlyBW <= 150000) {
					return 0.07 * requiredMonthlyBW;
				} else {
					return 0.05 * requiredMonthlyBW;
				}
			}
		} else if (from.getName().equalsIgnoreCase("aws_dynamodb")) {
			if (to.getProvider().equals("aws") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else {
				if (requiredMonthlyBW <= 1) {
					return 0;
				} else if (requiredMonthlyBW <= 9999) {
					return 0.09 * (requiredMonthlyBW - 1);
				} else if (requiredMonthlyBW <= 40000) {
					return (0.09 * 9998) + (0.085 * (requiredMonthlyBW - 9999));
				} else if (requiredMonthlyBW <= 100000) {
					return (0.09 * 99998) + (0.085 * 30002) + (0.07 * (requiredMonthlyBW - 40000));
				} else {
					return (0.09 * 99998) + (0.085 * 30002) + (0.07 * (100000 - 40000))
							+ (0.05 * (requiredMonthlyBW - 100000));
				}
			}
		} else if (from.getName().equalsIgnoreCase("aws_compute")) {
			if (to.getProvider().equals("aws") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else {
				String arr[] = { "N Virginia", "Singapore", "Sydney", "Sao Paulo", "Canada Central", "London", "Paris",
						"Tokyo", "US-West", "Hong Kong", "US-East", "Frankfurt", "Stockholm", "Northern California",
						"Ireland", "Osaka-Local", "Seoul", "Mumbai", "Oregon" };
				Set<String> regions = new HashSet<>(Arrays.asList(arr));
				if (regions.contains(to.getRegion())) {
					return 0.02 * requiredMonthlyBW;
				} else {
					if (requiredMonthlyBW <= 1) {
						return 0;
					} else if (requiredMonthlyBW <= 9999) {
						return 0.09 * (requiredMonthlyBW - 1);
					} else if (requiredMonthlyBW <= 40000) {
						return (0.09 * 9998) + (0.085 * (requiredMonthlyBW - 9999));
					} else if (requiredMonthlyBW <= 100000) {
						return (0.09 * 99998) + (0.085 * 30002) + (0.07 * (requiredMonthlyBW - 40000));
					} else {
						return (0.09 * 99998) + (0.085 * 30002) + (0.07 * (100000 - 40000))
								+ (0.05 * (requiredMonthlyBW - 100000));
					}
				}

			}
		} else if (from.getName().equalsIgnoreCase("google_sql")) {
			if (to.getProvider().equals("google") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else if (to.getRegion().equals("internet")) {
				return 0.19 * requiredMonthlyBW;
			} else {
				return 0.12 * requiredMonthlyBW;
			}

		} else if (from.getName().equalsIgnoreCase("google_compute")) {
			if (to.getProvider().equals("google") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else if (from.getRegion().equals("Oceania3")) {
				return 0.15 * requiredMonthlyBW;
			} else if (from.getRegion().equals(to.getRegion()) && (!from.getZone().equals(to.getZone()))) {
				return 0.01 * requiredMonthlyBW;
			} else if ((from.getContinent().equalsIgnoreCase("US") || from.getContinent().equalsIgnoreCase("canada"))
					&& (to.getContinent().equalsIgnoreCase("US") || to.getContinent().equalsIgnoreCase("canada"))) {
				return 0.01 * requiredMonthlyBW;
			} else if (from.getContinent().equalsIgnoreCase("europe")
					|| from.getContinent().equalsIgnoreCase("europe")) {
				return 0.02 * requiredMonthlyBW;
			} else if ((from.getContinent().equalsIgnoreCase("asia") || from.getContinent().equalsIgnoreCase("asia"))) {
				return 0.05 * requiredMonthlyBW;
			} else if ((from.getContinent().equalsIgnoreCase("South America")
					|| from.getContinent().equalsIgnoreCase("South America"))) {
				return 0.08 * requiredMonthlyBW;
			} else {
				return 0.08 * requiredMonthlyBW;
			}
		} else if (from.getName().equalsIgnoreCase("rackspace_storage")
				|| from.getName().equalsIgnoreCase("rackspace_compute")) {
			if (to.getProvider().equals("rackspace") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else {
				if (requiredMonthlyBW <= 10000) {
					return 0.12 * requiredMonthlyBW;
				} else if (requiredMonthlyBW <= 40000) {
					return 0.12 * (10000) + 0.1 * (requiredMonthlyBW - 10000);
				} else if (requiredMonthlyBW <= 150000) {
					return 0.12 * (10000) + (0.1 * 30000) + 0.08 * (requiredMonthlyBW - 40000);
				} else if (requiredMonthlyBW <= 300000) {
					return (0.12 * 10000) + (0.1 * 30000) + (0.08 * 110000) + (0.07 * (requiredMonthlyBW - 150000));
				} else {
					return (0.12 * 10000) + (0.1 * 30000) + (0.08 * 110000) + (0.07 * 150000)
							+ (0.06 * (requiredMonthlyBW - 300000));
				}
			}

		} else if (from.getName().equalsIgnoreCase("digitalocean_storage")
				|| from.getName().equalsIgnoreCase("digitalocean_compute")) {
			if (to.getProvider().equals("digitalocean") && (to.getRegion().equals(from.getRegion()))) {
				return 0;
			} else {
				if (requiredMonthlyBW <= 10000) {
					return 0;
				} else {
					return 0.01 * requiredMonthlyBW;
				}
			}
		}
		return -1;
	}

	public static void main(String args[]) {

	}
}
