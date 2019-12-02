package com.lancaster.dsl.SLO_DSL;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSOONGenerator {
	private static JSOONGenerator instance;
	private static final Logger LOGGER = Logger.getLogger(JSOONGenerator.class.getName());
	
	private final List<Component> components = new ArrayList<>();
	private final List<SLO> applicationSLOs = new ArrayList<>();
	private final JSONArray dataFlowArray = new JSONArray();;

	public JSOONGenerator() {
		
	}
	
	/**
	 * @return a reference to an example for Component objects.
	 */
	
	/*public static JSOONGenerator getInstance() {
		if (instance == null) {
			instance = new JSOONGenerator();
			//instance.ensureTestData();
		}
		
		return instance;
	}/*
	
	/**
	 * @return all available Component objects.
	 */
	
	public synchronized List<Component> getComponents() {
		return components;
	}
	
	public synchronized List<SLO> getApplicationSLOs() {
		return applicationSLOs;
	}
	
	public synchronized JSONArray getDataFlowArray() {
		return dataFlowArray;
	}
	
	public synchronized void save(Component entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE, "Component is null.");
			return;
		}
		components.add(entry);
	}
	
	public synchronized void save(SLO entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE, "SLO is null.");
			return;
		}
		applicationSLOs.add(entry);
	}
	
	public synchronized void save(JSONObject entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE, "Dataflow is null.");
			return;
		}
		dataFlowArray.add(entry);
	}
	
	public synchronized void delete(Component component) {
		components.remove(component);
	}
	
}
