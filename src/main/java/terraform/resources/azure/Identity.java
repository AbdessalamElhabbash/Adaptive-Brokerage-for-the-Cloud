package terraform.resources.azure;

import java.util.ArrayList;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class Identity {
	
	private String type; //required Possible types are SystemAssigned, UserAssigned.  
	private ArrayList<String> identity_ids; // Required if type is UserAssigned
	
	/**
	 * @return the type
	 */
	public String getType(){
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)  throws InvalidTypeException{
		if(type.equals("\"SystemAssigned\"") || type.equals("\"UserAssigned\"")){
			this.type = type;
		}else{
			throw new InvalidTypeException("Possible types are SystemAssigned, UserAssigned");
		}
	}
	/**
	 * @return the identity_ids
	 */
	public ArrayList<String> getIdentity_ids() {
		return identity_ids;
	}
	/**
	 * @param identity_ids the identity_ids to set
	 */
	public void setIdentity_ids(ArrayList<String> identity_ids) {
		this.identity_ids = identity_ids;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(type == null || type.equals("\"\"")){
			return false;
		}
		if(type.equalsIgnoreCase("\"UserAssigned\"") && identity_ids.isEmpty()){
			return false;
		}
		return true;
	}
	
	public String getBlock(int level) {
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		if(!isValid()){
			return null;
		}
		String block = "identity { \n";
		if (type != null && !type.equals("")) {
			block += elementIndent + "type = " + type + "\n";
		}
		if (identity_ids != null && !identity_ids.isEmpty()) {
			block += elementIndent + "identity_ids = " + identity_ids + "\n";
		}
		block += headIndent + "}\n";
		return block;
	}
	
}
