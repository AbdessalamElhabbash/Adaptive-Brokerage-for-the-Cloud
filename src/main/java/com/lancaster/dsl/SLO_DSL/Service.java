package com.lancaster.dsl.SLO_DSL;

import java.util.ArrayList;

public class Service {
	private String name;
	private String type;	
	private ArrayList<SLO> slos;
	private String region;
	private String zone;
	private String continent;
	private String componentName;
	private String provider;
	private String instanceType; //t2.micro, .....
	
	Service(String name){
		this.name = name;
	}
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Service(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public ArrayList<SLO> getSlos() {
		return slos;
	}

	public void setSlos(ArrayList<SLO> slos) {
		this.slos = slos;
	}

	public String getComponentName() {
		return componentName;
	}
	
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public String toString(){
		return name + ":" + type + ":" + provider;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

}
