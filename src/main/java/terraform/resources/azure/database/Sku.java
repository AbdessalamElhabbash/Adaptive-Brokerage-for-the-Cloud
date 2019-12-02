package terraform.resources.azure.database;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.TerraformObject;
import terraform.provider.Constants;

public class Sku extends TerraformObject{
	private String name; //required
	private int capacity;//required
	private String tier;//required. Possible values are Basic, GeneralPurpose, and MemoryOptimized. 
	private String family; //required. Possible values are Gen4 and Gen5
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the tier
	 */
	public String getTier() {
		return tier;
	}

	/**
	 * @param tier the tier to set
	 */
	public void setTier(String tier) {
		this.tier = tier;
	}

	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	private boolean isValid() throws InvalidTypeException, RequiredArgumentException{
		if(capacity < 0){
			throw new InvalidTypeException("Invalid value for the capacity config"); 
		}
		if(tier == null || tier.equals("")){
			throw new RequiredArgumentException("tier is a required config for the azurerm_postgresql_server"); 
		}else if(!tier.equalsIgnoreCase("Basic") && !tier.equalsIgnoreCase("GeneralPurpose") && !tier.equalsIgnoreCase("MemoryOptimized")){
			throw new InvalidTypeException("Invalid tier type. Possible values are Basic, GeneralPurpose, and 10.0."); 
		}
		if(family == null || family.equals("")){
			throw new RequiredArgumentException("family is a required config for the azurerm_postgresql_server"); 
		}else if(!family.equalsIgnoreCase("Gen4") && !family.equalsIgnoreCase("Gen5")){
			throw new InvalidTypeException("Invalid family type. Possible values are Gen4 and Gen5."); 
		}
		String n = "";
		if(tier.equalsIgnoreCase("Basic")){
			n += "B";
		}else if(tier.equalsIgnoreCase("GeneralPurpose")){
			n += "GP";
		}else if(tier.equalsIgnoreCase("MemoryOptimized")){
			n += "MO";
		}
		name = n + family + capacity;
		return true;
	}
	
	@Override
	public String getBlock(int level) {
		
		try {
			isValid();
		} catch (InvalidTypeException | RequiredArgumentException e) {
			e.printStackTrace();
		}
		
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block = headIndent + "sku { \n";
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "capacity = " + capacity + "\n";
		block += elementIndent + "tier = \"" + tier + "\"\n";
		block += elementIndent + "family = \"" + family + "\"\n";

		block += headIndent + "}\n";
		return block;
	}	
}
