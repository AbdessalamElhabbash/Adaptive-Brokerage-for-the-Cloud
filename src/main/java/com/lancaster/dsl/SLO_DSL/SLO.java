package com.lancaster.dsl.SLO_DSL;

public class SLO {
	
	private String name;
	private String value;
	private String operator;
	private String unit;	
	private String level;
	
	public SLO() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
			this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
			this.operator = operator;
		
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String toString(){
		return name +": " + operator + " " + value + " " + unit + " " + level;
	}
	
}
