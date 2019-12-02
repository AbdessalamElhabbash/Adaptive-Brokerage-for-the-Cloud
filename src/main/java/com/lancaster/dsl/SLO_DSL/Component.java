package com.lancaster.dsl.SLO_DSL;

import java.util.ArrayList;

public class Component {
	private String name;
	private String type;	
	private ArrayList<SLO> slos;
	private String region;
	
	public Component(){
	}
	/**
	 * @return the id
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	/**
	 * @return the slos
	 */
	public ArrayList<SLO> getSlos() {
		return slos;
	}
	/**
	 * @param slos the slos to set
	 */
	public void setSlos(ArrayList<SLO> slos) {
		this.slos = slos;
	}
	@Override
	public String toString() {
		return "Component [name=" + name + ", type=" + type + ", region=" + region + ", slos = " + slos + "]";
	}
	
	@Override
	public Component clone() throws CloneNotSupportedException {
		return (Component) super.clone();
	}

}
