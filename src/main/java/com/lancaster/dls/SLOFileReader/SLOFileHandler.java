package com.lancaster.dls.SLOFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SLOFileHandler {
	private static JSONParser jsonParser = new JSONParser();
	
	private static HashMap<String, List<String>> units = new HashMap();
	private static HashMap<String, String> valueTypes = new HashMap();
	private static List<String> validSLONames = new ArrayList();
	private static List<String> validOperators = new ArrayList();
	private static List<String> validCloudServices = new ArrayList();
	private static List<String> sloUnits;
	private static List<String> componentIDs = new ArrayList<String>() {{add("internet");}};
	private static List<String> validComponentTypes = new ArrayList();
	private static List<String> validRegions = new ArrayList();

	private static String line;
	private static String sloName;	
	private static int counter;
	
	public SLOFileHandler() {
	}

	public static String readFileContent(File file) {
		StringBuffer content = new StringBuffer();
		try {
			FileReader reader = new FileReader(file);
			JSONObject jsonObj = (JSONObject) jsonParser.parse(reader);
			Iterator itr = jsonObj.entrySet().iterator();
						
			String component;
			String data_flow="\t\"data_flow\": [\n";
			String application="\t\"application\": {\n";

			int lineNumber = 1;
			content.append("{\n");
			
			while (itr.hasNext()) {
				Map.Entry mapElement = (Map.Entry) itr.next();
				component = (String) mapElement.getKey();
								
				if(component.equalsIgnoreCase("data_flow")){
					JSONArray list = (JSONArray) mapElement.getValue();
					Object elements[] = list.toArray();
					for (Object o: elements) {
						JSONObject obj = (JSONObject) o;		
						data_flow += "\t\t{ \"from\": " + "\"" + obj.get("from") + "\",\n";
						data_flow += "\t\t   \"to\": " + "\"" + obj.get("to") + "\",\n";
//						data_flow += "\t\t   \"bw\": " + "\"" + obj.get("bw") + "\",\n";
//						data_flow += "\t\t   \"unit\": " + "\"" + obj.get("unit") + "\"\n";
						data_flow += "\t\t},\n";
					}
					data_flow = data_flow.substring(0, data_flow.length() - 2);
					data_flow += "\n\t] \n";
				}else if(component.equalsIgnoreCase("application")){
					JSONObject componentMap = (JSONObject) mapElement.getValue();
					JSONArray slos = (JSONArray) componentMap.get("SLOs");
					
					Object sloElements[] = slos.toArray();	
					application += "\t\t\"SLOs\": [\n";
					for (Object o : sloElements) {
						JSONObject obj = (JSONObject) o;
						application += "\t\t{ \"name\": " + "\"" + obj.get("name") + "\",\n";
						application += "\t\t   \"unit\": " + "\"" + obj.get("unit") + "\",\n";
						application += "\t\t   \"value\": " + "\"" + obj.get("value") + "\",\n";
						application += "\t\t   \"operator\": " + "\"" + obj.get("operator") + "\"\n";
						application += "\t\t},\n";
					}
					
					application = application.substring(0, application.length() - 2);
					application += "\n\t]\n}, \n";
				} 
				else {
					componentIDs.add(component);
					content.append("\t\"" + component + "\": {\n");
					JSONObject componentMap = (JSONObject) mapElement.getValue();
					JSONArray slos = (JSONArray) componentMap.get("SLOs");
					JSONObject config = (JSONObject) componentMap.get("config");
					
					Object sloElements[] = slos.toArray();	
					content.append("\t\t\"SLOs\": [\n");
					for (Object o : sloElements) {
						JSONObject obj = (JSONObject) o;
							content.append("\t\t{ \"name\": " + "\"" + obj.get("name") + "\",\n");
							content.append("\t\t   \"unit\": " + "\"" + obj.get("unit") + "\",\n");
							content.append("\t\t   \"value\": " + "\"" + obj.get("value") + "\",\n");
							content.append("\t\t   \"operator\": " + "\"" + obj.get("operator") + "\"\n");
							content.append("\t\t},\n");
					}
					content.deleteCharAt(content.length() - 2);
					content.append("\t],\n");
					
					Iterator configItr = config.entrySet().iterator();
					content.append("\t\t\"config\": {\n");
					while (configItr.hasNext()) {
						Map.Entry configElement = (Map.Entry) configItr.next();
						String key = (String) configElement.getKey();
						String value = (String) config.get(key);
						content.append("\t\t	\""+key+"\": " + "\"" + value + "\",\n");
					}
					content.deleteCharAt(content.length() - 2);
					content.append("\t\t}\n},\n");
				}
			}
			if (!application.equals("\t\"application\": {\n")) {
				content.append(application);
			}
			if (!data_flow.equals("\t\"data_flow\": [\n")) {
				content.append(data_flow);
			}
			content.deleteCharAt(content.length()-2);
			content.append("}");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
	
	

	public static void readConfigurationFile() {
		if(!validSLONames.isEmpty()){
			validSLONames.clear();
			units.clear();
		}
		File file = new File("resources/configuration.slo");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			while ((line = buffer.readLine()) != null) {

				counter = 0;
				sloUnits = new ArrayList<>();
				
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
			    
				while (tokenizer.hasMoreElements()) {
			    	String token = tokenizer.nextToken();
			    	token = token.trim();
			    	token = token.substring(1, token.length()-1);
			    	if (counter == 0) {
			    		sloName = token;
			    		validSLONames.add(token.trim());
			    	} else if (counter == 1) {			    				    	
			    		StringTokenizer validUnits = new StringTokenizer(token, ",\" ");			    		
			    		while (validUnits.hasMoreTokens()) {
			    			String unit = validUnits.nextToken();
			    			if (unit.equals("-")) {
			    				unit = "";
			    			}
			    			sloUnits.add(unit.trim());			    						    			
			    			
			    		}			    		
			    	} else if (counter == 2) {
			    		StringTokenizer validValueTypes = new StringTokenizer(token, ",\"");
			    		while (validValueTypes.hasMoreTokens()) {
			    			String validType = validValueTypes.nextToken();
			    			valueTypes.put(sloName, validType);
			    		}
			    	}
			    	counter++;
			    	//validSLONames.add(token);			    	
			    }
				units.put(sloName.trim(), sloUnits);
			    
			}
			/*
			System.out.println("SLO name\tUnits");
			for (String key : units.keySet()) {
			    System.out.println(key + "\t" + units.get(key));			    			 
			}
			
			System.out.println("\n\nSLO name\tValue Types");
			for (String key : valueTypes.keySet()) {
				System.out.println(key + " " + valueTypes.get(key));
			}*/
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public static void readOperatorsFile() {
		File file = new File("resources/operators.slo");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			while ((line = buffer.readLine()) != null) {									
				validOperators.add(line);
			}									
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readComponentTypesFile() {
		File file = new File("resources/componentTypes.slo");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			if(!validComponentTypes.isEmpty()){
				validComponentTypes.clear();
			}
			while ((line = buffer.readLine()) != null) {	
				if(!validComponentTypes.contains(line)){
					validComponentTypes.add(line.trim());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readRegionsFile() {
		File file = new File("resources/regions.slo");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			while ((line = buffer.readLine()) != null) {									
				validRegions.add(line);
			}									
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readCloudServicesFile() {
		File file = new File("resources/cloud-services.slo");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			while ((line = buffer.readLine()) != null) {
				validCloudServices.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, List<String>> getValidUnits() {		
		return units;
	}
	
	public static HashMap<String, String> getValidValueTypes() {
		return valueTypes;
	}
	
	public static List<String> getValidSLONames() {
		return validSLONames;
	}
	
	public static List<String> getValidOperators() {		
		return validOperators;
	}
	
	public static List<String> getValidCloudServices() {		
		return validCloudServices;
	}
	public static List<String> getComponentIDs() {		
		return componentIDs;
	}
	public static List<String> getValidComponentTypes() {		
		return validComponentTypes;
	}
	public static List<String> getValidRegions() {		
		return validRegions;
	}
}