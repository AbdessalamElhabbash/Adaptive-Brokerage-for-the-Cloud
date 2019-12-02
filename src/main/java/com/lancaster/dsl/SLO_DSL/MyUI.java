package com.lancaster.dsl.SLO_DSL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lancaster.dls.SLOFileReader.SLOFileHandler;
import com.lancaster.dsl.JDBC.StorageContext;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;

import com.vaadin.ui.VerticalLayout;

import engin.CodeGenerator;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	private RichTextArea editor = new RichTextArea();
	private TextArea errors = new TextArea();
	private TextArea results = new TextArea();
	private ComponentForm componentForm;// = new ComponentForm(this);

	private Button saveButton = new Button("Save");
	private Button findButton = new Button("Find Services");
	private Button addComponentButton = new Button("Add component");
	private Button addAppSLOsButton = new Button("Add application SLOs");
	private Button addDataFLowButton = new Button("Add dataFlow");

	private File file;
	private String fileContent;

	private HashMap<String, List<String>> validUnits = new HashMap();
	private HashMap<String, String> validValueTypes = new HashMap();
	private List<String> validSLONames = new ArrayList();
	private List<String> validOperators = new ArrayList();
	private List<String> validCloudServices = new ArrayList();
	private List<String> validComponentTypes = new ArrayList();

	private List<String> validRegions = new ArrayList();

	private List<String> errorMessages = new ArrayList();
	private static List<String> componentIDs = new ArrayList<String>() {
		{
			add("internet");
		}
	};
	private Map<String, Object> allApplicationSLOs = new LinkedHashMap<>();
	private FormLayout appSloFormLayout = new FormLayout();
	private FormLayout dataFlowFormLayout = new FormLayout();

	private JSONParser jsonParser = new JSONParser();
	//private StorageContext context = new StorageContext();

	private JSOONGenerator componentService = new JSOONGenerator();

	private boolean validated = false;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		// Read configuration files
		SLOFileHandler.readConfigurationFile();
		SLOFileHandler.readOperatorsFile();
		SLOFileHandler.readCloudServicesFile();
		SLOFileHandler.readComponentTypesFile();
		SLOFileHandler.readRegionsFile();
		validSLONames = SLOFileHandler.getValidSLONames();
		validUnits = SLOFileHandler.getValidUnits();
		validValueTypes = SLOFileHandler.getValidValueTypes();
		validOperators = SLOFileHandler.getValidOperators();
		validCloudServices = SLOFileHandler.getValidCloudServices();
		// componentIDs = SLOFileHandler.getComponentIDs();
		validComponentTypes = SLOFileHandler.getValidComponentTypes();
		validRegions = SLOFileHandler.getValidRegions();

		// System.out.println(validOperators);

		editor.setCaption("SLO Editor");
		editor.setSizeFull();
		// editor.setEnabled(false);

		// updateComponents();

		errors.setCaption("Errors");
		errors.setWidth("400");
		errors.setRows(20);
		errors.setReadOnly(true);

		results.setCaption("Results");
		results.setWidth("400");
		results.setRows(20);
		results.setReadOnly(true);

		// editor.setValue("<p style=\"text-decoration: underline;
		// text-decoration-color: red;\">The color of the underline should now
		// be red!</p>");

		Button validateButton = new Button("Validate");

		validateButton.addClickListener(e -> {
			String content = editor.getValue();
			if (content != null) {
				content = validateSLOs(content);
			}
			editor.clear();
			editor.setValue(content);

			StringBuffer errorMsgs = new StringBuffer();
			for (String error : errorMessages) {
				errorMsgs.append(error);
			}
			errorMessages.clear();

			errors.setValue("");

			if (errorMsgs.length() == 0 && validated) {
				errors.setValue("No errors found.");
				findButton.setEnabled(true);
			} else
				errors.setValue(errorMsgs.toString());
			// layout.addComponent(new Label("Thanks " + content
			// + ", it works!"));
		});

		saveButton.addClickListener(e -> {
			saveSLOs();
		});

		findButton.addClickListener(e -> {
			selectServices();
		});

		addComponentButton.addClickListener(e -> {
			addComponent();
		});

		addAppSLOsButton.addClickListener(e -> {
			addAppSLOForm();
		});

		addDataFLowButton.addClickListener(e -> {
			addDataFlowForm();
		});

		Upload uploadButton = new Upload("Upload an SLO file", new Upload.Receiver() {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				try {
					file = File.createTempFile("temp", ".json");
					return new FileOutputStream(file);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}

		});

		uploadButton.addFinishedListener(new Upload.FinishedListener() {

			@Override
			public void uploadFinished(FinishedEvent event) {
				fileContent = SLOFileHandler.readFileContent(file);
				fileContent = fileContent.replaceAll("\n", "<br>");
				fileContent = fileContent.replaceAll("\t", "&nbsp&nbsp&nbsp&nbsp");
				editor.setValue(fileContent);

			}
		});

		final HorizontalLayout buttonsLayout = new HorizontalLayout();
		findButton.setEnabled(false);
		buttonsLayout.addComponents(validateButton, saveButton, findButton);
		final HorizontalLayout slosLayout = new HorizontalLayout();
		slosLayout.addComponents(addComponentButton, addAppSLOsButton, addDataFLowButton);

		componentForm = new ComponentForm(this);

		final HorizontalLayout textAreaLayout = new HorizontalLayout();
		textAreaLayout.addComponents(componentForm, appSloFormLayout, dataFlowFormLayout, editor, errors, results);

		layout.addComponents(uploadButton, buttonsLayout, slosLayout, textAreaLayout);

		setContent(layout);
	}

	public void addComponent() {
		Component component = new Component();
		componentForm.setComponent(component);
	}

	public void addAppSLOForm() {
		AppSLOForm appSloForm = new AppSLOForm(this);
		appSloFormLayout.addComponent(appSloForm);
		appSloForm.setVisible(true);
	}

	public void addDataFlowForm() {
		DataFlowForm dataFlowForm = new DataFlowForm(this);
		dataFlowFormLayout.addComponent(dataFlowForm);
		dataFlowForm.setVisible(true);
	}

	public void updateScript() {
		for (Component component : componentService.getComponents()) {
			// *************************************************
			JSONObject comp = new JSONObject();
			JSONObject compConfig = new JSONObject();
			Map<String, JSONArray> compSLOs = new LinkedHashMap<>();

			if (component.getType() != null) {
				compConfig.put("type", component.getType());
			}
			if (component.getRegion() != null) {
				compConfig.put("region", component.getRegion());
			}

			JSONArray compSLOsArray = new JSONArray();
			for (SLO componentSLO : component.getSlos()) {
				JSONObject compSLO = new JSONObject();
				compSLO.put("name", componentSLO.getName());
				compSLO.put("operator", componentSLO.getOperator());
				compSLO.put("value", componentSLO.getValue());
				if (componentSLO.getUnit() != null) {
					compSLO.put("unit", componentSLO.getUnit());
				} else {
					compSLO.put("unit", "");
				}
				compSLOsArray.add(compSLO);
			}
			comp.put("SLOs", compSLOsArray);
			comp.put("config", compConfig);
			allApplicationSLOs.put(component.getName(), comp);
			componentIDs.add(component.getName());
		}

		Map<String, JSONArray> appSLOs = new LinkedHashMap<>();
		JSONArray appSLOsArray = new JSONArray();
		for (SLO slo : componentService.getApplicationSLOs()) {
			JSONObject appSLO = new JSONObject();
			appSLO.put("name", slo.getName());
			appSLO.put("operator", slo.getOperator());
			appSLO.put("value", slo.getValue());
			if (slo.getUnit() != null) {
				appSLO.put("unit", slo.getUnit());
			} else {
				appSLO.put("unit", "");
			}
			appSLOsArray.add(appSLO);
		}

		appSLOs.put("SLOs", appSLOsArray);

		if (!appSLOs.isEmpty()) {
			allApplicationSLOs.put("application", appSLOs);
		}
		if (!componentService.getDataFlowArray().isEmpty()) {
			allApplicationSLOs.put("data_flow", componentService.getDataFlowArray());
		}

		JSONObject json = new JSONObject(allApplicationSLOs);
		StringWriter writer = new StringWriter();
		try {
			json.writeJSONString(writer);
			editor.setValue(stringToHtml(writer.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	public String validateSLOs(String sloContent) {
		StringBuffer newContent = new StringBuffer();
		JSONObject jsonObj;
		int errorCounter = 0;
		try {
			sloContent = formatSLO(sloContent);
			jsonObj = (JSONObject) jsonParser.parse(sloContent);

			Iterator itr = jsonObj.entrySet().iterator();
			String component;
			newContent.append("{\n");
			String data_flow = "\t\"data_flow\": [\n";
			String application = "\t\"application\": {\n";

			while (itr.hasNext()) {
				Map.Entry mapElement = (Map.Entry) itr.next();
				component = (String) mapElement.getKey();

				if (component.equalsIgnoreCase("data_flow")) {
					JSONArray list = (JSONArray) mapElement.getValue();
					Object elements[] = list.toArray();
					for (Object o : elements) {
						JSONObject obj = (JSONObject) o;
						String from = (String) obj.get("from");
						String to = (String) obj.get("to");
						if (!componentIDs.contains(from)) {
							data_flow += "\t\t{ <span style=\"text-decoration: underline; text-decoration-color: red;\">\"from\": "
									+ "\"" + obj.get("from") + "\",</span>\n";
							errorMessages.add("'" + obj.get("from") + "' is an invalid component ID .\n");
							errorCounter++;
						} else {
							data_flow += "\t\t { \"from\": " + "\"" + obj.get("from") + "\",\n";
						}

						if (!componentIDs.contains(to)) {
							data_flow += "\t\t <span style=\"text-decoration: underline; text-decoration-color: red;\">\"to\": "
									+ "\"" + obj.get("to") + "\"</span>\n\t\t},\n";
							errorMessages.add("'" + obj.get("to") + "' is an invalid component ID .\n");
							errorCounter++;
						} else {
							data_flow += "\t\t   \"to\": " + "\"" + obj.get("to") + "\"\n\t\t},\n";
						}
					}
				} else if (component.equalsIgnoreCase("application")) {
					JSONObject componentMap = (JSONObject) mapElement.getValue();
					JSONArray slos = (JSONArray) componentMap.get("SLOs");
					if (!slos.isEmpty()) {
						Object sloElements[] = slos.toArray();
						application += "\t\t\"SLOs\": [\n";

						for (Object o : sloElements) {
							JSONObject obj = (JSONObject) o;
							String name = (String) obj.get("name");
							String unit = (String) obj.get("unit");
							String value = (String) obj.get("value");
							String operator = (String) obj.get("operator");

							if (!validSLONames.contains(name)) {
								application += "\t\t{ <span style=\"text-decoration: underline; text-decoration-color: red;\">\"name\": "
										+ "\"" + obj.get("name") + "\",</span>\n";
								errorMessages.add("'" + obj.get("name") + "' is an invalid SLO name.\n");
								application += "\t\t   \"unit\": " + "\"" + obj.get("unit") + "\",\n";
								application += "\t\t   \"value\": " + "\"" + obj.get("value") + "\",\n";
								application += "\t\t   \"operator\": " + "\"" + obj.get("operator") + "\"\n";
								errorCounter++;
							} else {
								application += "\t\t{ \"name\": " + "\"" + obj.get("name") + "\",\n";
								if (!validUnits.get(name).contains(unit)) {
									application += "\t\t   <span style=\"text-decoration: underline; text-decoration-color: red;\">\"unit\": "
											+ "\"" + obj.get("unit") + "\",</span>\n";
									errorMessages.add("'" + obj.get("unit") + "' is an invalid unit for '"
											+ obj.get("name") + "'. Please set one of the following units: "
											+ validUnits.get(name) + ".\n");
									errorCounter++;
								} else {
									application += "\t\t   \"unit\": " + "\"" + obj.get("unit") + "\",\n";
								}

								boolean numeric = true;
								try {
									Double.parseDouble((String) obj.get("value"));
								} catch (NumberFormatException e) {
									numeric = false;
								}
								if (numeric) {
									application += "\t\t   \"value\": " + "\"" + obj.get("value") + "\",\n";
								} else {
									application += "\t\t   <span style=\"text-decoration: underline; text-decoration-color: red;\">\"value\": "
											+ "\"" + obj.get("value") + "\",</span>\n";
									errorMessages.add("'" + obj.get("value") + "' is an invalid value for '"
											+ obj.get("name") + "'. Please check.\n");
									errorCounter++;
								}

								if (validOperators.indexOf(operator) == -1) {
									application += "\t\t<span style=\"text-decoration: underline; text-decoration-color: red;\">\"operator\": "
											+ "\"" + obj.get("operator") + "\"</span>\n";
									errorMessages.add("'" + obj.get("operator") + "' is an invalid operator for '"
											+ obj.get("name") + "'. Please set one of the following operators: "
											+ validOperators + ".\n");
									errorCounter++;
								} else {
									application += "\t\t   \"operator\": " + "\"" + obj.get("operator") + "\"\n";
								}
							}
							application += "\t\t},\n";
						}
						application = application.substring(0, application.length() - 2);
						application += "\n\t]\n}, \n";
					}
				} else {
					newContent.append("\t\"" + component + "\": {\n");
					JSONObject componentMap = (JSONObject) mapElement.getValue();
					JSONArray slos = (JSONArray) componentMap.get("SLOs");
					JSONObject config = (JSONObject) componentMap.get("config");
					if (slos != null && !slos.isEmpty()) {
						Object sloElements[] = slos.toArray();
						newContent.append("\t\t\"SLOs\": [\n");
						for (Object o : sloElements) {
							JSONObject obj = (JSONObject) o;
							String name = (String) obj.get("name");
							String unit = (String) obj.get("unit");
							String value = (String) obj.get("value");
							String operator = (String) obj.get("operator");

							if (!validSLONames.contains(name)) {
								newContent
										.append("\t\t{ <span style=\"text-decoration: underline; text-decoration-color: red;\">\"name\": "
												+ "\"" + obj.get("name") + "\",</span>\n");
								errorMessages.add("'" + obj.get("name") + "' is an invalid SLO name.\n");
								newContent.append("\t\t   \"unit\": " + "\"" + obj.get("unit") + "\",\n");
								newContent.append("\t\t   \"value\": " + "\"" + obj.get("value") + "\",\n");
								newContent.append("\t\t   \"operator\": " + "\"" + obj.get("operator") + "\"\n},\n");
								errorCounter++;
							} else {
								newContent.append("\t\t{ \"name\": " + "\"" + obj.get("name") + "\",\n");

								if (!validUnits.get(name).contains(unit)) {
									newContent
											.append("\t\t   <span style=\"text-decoration: underline; text-decoration-color: red;\">\"unit\": "
													+ "\"" + obj.get("unit") + "\",</span>\n");
									errorMessages.add("'" + obj.get("unit") + "' is an invalid unit for '"
											+ obj.get("name") + "'. Please set one of the following units: "
											+ validUnits.get(name) + ".\n");
									errorCounter++;
								} else {
									newContent.append("\t\t   \"unit\": " + "\"" + obj.get("unit") + "\",\n");
								}
								boolean numeric = true;
								try {
									Double.parseDouble((String) obj.get("value"));
								} catch (NumberFormatException e) {
									numeric = false;
								}
								if (numeric) {
									newContent.append("\t\t   \"value\": " + "\"" + obj.get("value") + "\",\n");
								} else {
									newContent
											.append("\t\t   <span style=\"text-decoration: underline; text-decoration-color: red;\">\"value\": "
													+ "\"" + obj.get("value") + "\",</span>\n");
									errorMessages.add("'" + obj.get("value") + "' is an invalid value for '"
											+ obj.get("name") + "'. Please check.\n");
									errorCounter++;
								}

								if (validOperators.indexOf(operator) == -1) {
									newContent
											.append("\t\t<span style=\"text-decoration: underline; text-decoration-color: red;\">\"operator\": "
													+ "\"" + obj.get("operator") + "\"</span>\n");
									errorMessages.add("'" + obj.get("operator") + "' is an invalid operator for '"
											+ obj.get("name") + "'. Please set one of the following operators: "
											+ validOperators + ".\n");
									errorCounter++;
								} else {
									newContent.append("\t\t   \"operator\": " + "\"" + obj.get("operator") + "\"\n");
								}
								newContent.append("\t\t},\n");
							}
						}
						newContent.deleteCharAt(newContent.length() - 2);
						newContent.append("\t],\n");
					}
					if (!config.containsKey("type")) {
						errorMessages.add("component '" + component + "' must have a valid component type.\n");
						errorCounter++;
					}
					Iterator configItr = config.entrySet().iterator();
					newContent.append("\t\t\"config\": {\n");
					while (configItr.hasNext()) {
						Map.Entry configElement = (Map.Entry) configItr.next();
						String key = (String) configElement.getKey();
						if (key.trim().equalsIgnoreCase("type")) {
							String type = (String) config.get(key);
							if (validComponentTypes.indexOf(type) == -1) {
								newContent
										.append("\t\t\t <span style=\"text-decoration: underline; text-decoration-color: red;\">\"type\": "
												+ "\"" + type + "\"</span>,\n");
								errorMessages.add("'" + type + "' is an invalid component type.\n");
								errorCounter++;
							} else {
								newContent.append("\t\t\t \"type\": " + "\"" + type + "\",\n");
							}
						}
						if (key.trim().equalsIgnoreCase("region")) {
							String region = (String) config.get(key);
							if (validRegions.indexOf(region) == -1) {
								newContent
										.append("\t\t\t <span style=\"text-decoration: underline; text-decoration-color: red;\">\"region\": "
												+ "\"" + region + "\"</span>,\n");
								errorMessages.add("'" + region + "' is an invalid component region.\n");
								errorCounter++;
							} else {
								newContent.append("\t\t\t \"region\": " + "\"" + region + "\",\n");
							}
						}
					}
					newContent.deleteCharAt(newContent.length() - 2);
					newContent.append("\t\t}\n},\n");
				}
			}

			if (errorCounter == 1)
				errorMessages.add("Found " + errorCounter + " error.");
			else if (errorCounter != 0 && errorCounter != 1)
				errorMessages.add("Found " + errorCounter + " errors.");
			else if (errorCounter == 0)
				validated = true;

			if (!application.equals("\t\"application\": {\n")) {
				newContent.append(application);
			}
			if (!data_flow.equals("\t\"data_flow\": [\n")) {
				data_flow = data_flow.substring(0, data_flow.length() - 2);
				data_flow += "\n\t] \n";
				newContent.append(data_flow);
			}
			newContent.deleteCharAt(newContent.length() - 2);
			newContent.append("}");

		} catch (ParseException e) {
			errorMessages.add("Invalid json script. \n");
			e.printStackTrace();
		}

		String content = newContent.toString();
		content = content.replace("\t", "&nbsp&nbsp&nbsp&nbsp");
		content = content.replace("\n", "<br>");

		return content;

	}

	private void selectServices() {
		String content = editor.getValue();

		String contentStr = "";
		if (content != null) {
			JSONObject jsonObj;
			try {
				content = formatSLO(content);
				jsonObj = (JSONObject) jsonParser.parse(content);
				ArrayList<Component> components = new ArrayList<>();
				ArrayList<SLO> appSLOs = new ArrayList<>();
				ArrayList<Map<String, String>> dataFlow = new ArrayList<>();

				Iterator itr = jsonObj.entrySet().iterator();
				String component;

				while (itr.hasNext()) {
					Map.Entry mapElement = (Map.Entry) itr.next();
					component = (String) mapElement.getKey();
					if (component.equalsIgnoreCase("data_flow")) {
						JSONArray list = (JSONArray) mapElement.getValue();
						Object elements[] = list.toArray();
						for (Object o : elements) {
							JSONObject obj = (JSONObject) o;
							String from = (String) obj.get("from");
							String to = (String) obj.get("to");
							Map<String, String> dFlow = new LinkedHashMap<>();
							dFlow.put(from, to);
							dataFlow.add(dFlow);
						}
					} else if (component.equalsIgnoreCase("application")) {
						JSONObject componentMap = (JSONObject) mapElement.getValue();
						JSONArray slos = (JSONArray) componentMap.get("SLOs");

						Object sloElements[] = slos.toArray();

						for (Object o : sloElements) {
							JSONObject obj = (JSONObject) o;
							SLO appSLO = new SLO();
							appSLO.setName((String) obj.get("name"));
							appSLO.setUnit((String) obj.get("unit"));
							appSLO.setValue((String) obj.get("value"));
							appSLO.setOperator((String) obj.get("operator"));
							appSLOs.add(appSLO);
						}
					} else {
						JSONObject componentMap = (JSONObject) mapElement.getValue();
						JSONArray slos = (JSONArray) componentMap.get("SLOs");
						JSONObject config = (JSONObject) componentMap.get("config");
						Component comp = new Component();
						comp.setName(component);
						ArrayList<SLO> comSlos = new ArrayList<>();
						Object sloElements[] = slos.toArray();
						for (Object o : sloElements) {
							JSONObject obj = (JSONObject) o;
							SLO slo = new SLO();
							slo.setName((String) obj.get("name"));
							slo.setUnit((String) obj.get("unit"));
							slo.setValue((String) obj.get("value"));
							slo.setOperator((String) obj.get("operator"));
							comSlos.add(slo);
						}
						comp.setSlos(comSlos);

						Iterator configItr = config.entrySet().iterator();
						while (configItr.hasNext()) {
							Map.Entry configElement = (Map.Entry) configItr.next();
							String key = (String) configElement.getKey();
							if (key.trim().equalsIgnoreCase("type")) {
								comp.setType((String) config.get("type"));
							}
							if (key.trim().equalsIgnoreCase("region")) {
								comp.setRegion((String) config.get("region"));
							}
						}
						components.add(comp);
					}
				}

				ArrayList<Service> services = null;
				if (appSLOs.isEmpty()) {
					services = Selector.select(StorageContext.getMysqlConnection(), components);
				} else {
					services = Selector.select(StorageContext.getMysqlConnection(), components, appSLOs, dataFlow);
				}
				for (Component comp : components) {
					boolean found = false;
					for (Service service : services) {
						if (service != null && !service.getName().equals("internet")) {
							if (comp.getName().equals(service.getComponentName())) {
								contentStr += "#'" + service.getComponentName() + "' --> '" + service.getName() + "'\n";
								contentStr += CodeGenerator.generateTerraFrom(service) + "\n";
								found = true;
							}
						}
					}
					if (!found) {
						contentStr += "No service found for '" + comp.getName() + "'\n";
					}
				}
				if (services.size() < components.size()) {
					contentStr += "Global constraints cannot be satisfied.\n";
				}
				results.setValue(contentStr);
			} catch (ParseException e) {
				errorMessages.add("Invalid json script. \n");
			}
		}
	}

	public String formatSLO(String sloContent) {
		sloContent = sloContent.replaceAll("<br>", "\n");
		sloContent = sloContent.replaceAll("&nbsp", "\t");
		sloContent = sloContent.replace("<span style=\"text-decoration: underline; text-decoration-color: red;\">", "");
		sloContent = sloContent.replace("<span style=\"white-space:pre\">", "\t");
		sloContent = sloContent.replaceAll("</span>", "");
		sloContent = sloContent.replace("&gt", ">");
		sloContent = sloContent.replace("&lt", "<");
		sloContent = sloContent.replace(";", "");

		return sloContent;
	}

	public String stringToHtml(String sloContent) {
		sloContent = sloContent.replaceAll("\n", "<br>");
		sloContent = sloContent.replaceAll("\t", "&nbsp");
		// sloContent = sloContent.replace("<span style=\"text-decoration:
		// underline; text-decoration-color: red;\">", "");
		// sloContent = sloContent.replace("<span style=\"white-space:pre\">",
		// "\t");
		// sloContent = sloContent.replaceAll("</span>", "");
		// sloContent = sloContent.replace("&gt", ">");
		// sloContent = sloContent.replace("&lt", "<");
		// sloContent = sloContent.replace(";", "");

		return sloContent;
	}

	public void saveSLOs() {

		File jsonFile;
		String content = formatSLO(editor.getValue());
		content = content.replaceAll("\t\t\t\t", "\t");

		try {
			jsonFile = File.createTempFile("slo", ".json");
			FileWriter writer = new FileWriter(jsonFile);
			writer.write(content);
			writer.close();
			StreamResource resource = new StreamResource(new StreamResource.StreamSource() {
				@Override
				public InputStream getStream() {
					try {
						return new FileInputStream(jsonFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			}, jsonFile.getName());
			resource.setCacheTime(-1);
			;
			resource.setMIMEType("application/json");

			FileDownloader downloader = new FileDownloader(resource);
			downloader.extend(this.saveButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getValidComponentTypes() {
		return validComponentTypes;
	}

	public HashMap<String, List<String>> getValidUnits() {
		return validUnits;
	}

	public List<String> getValidSLONames() {
		return validSLONames;
	}

	public List<String> getValidOperators() {
		return validOperators;
	}

	public JSOONGenerator getComponentService() {
		return componentService;
	}
}
