package terraform.resources.azure.network;

import terraform.provider.Constants;

public class Delegation {

	private String name; //Required 
	private ServiceDelegation service_delegation; //Required
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(name == null || name.equals("")){
			return false;
		}
		if(service_delegation == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBlock(int level) { 
		/***for formatting the terraform code**/ 
		String headIndent=""; 
		for(int i=0; i<level; i++){ 
			headIndent += Constants.indent; 
		} 
		String elementIndent= headIndent +  Constants.indent;		
		/*******************************************************/
		if(!isValid()){
			return null;
		}
		String block = "\"delegation\" { \n";
		block += elementIndent + "name = " + name + "\n";
		block += service_delegation.getBlock(level+1) + "\n";
		block += headIndent + "}\n";
		return block;
	}
}
