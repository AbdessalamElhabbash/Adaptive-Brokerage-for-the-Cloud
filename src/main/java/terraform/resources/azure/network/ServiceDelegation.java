package terraform.resources.azure.network;

import java.util.ArrayList;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class ServiceDelegation {

	private String name; //Required. Possible values include: Microsoft.ContainerInstance/containerGroups. 
	private ArrayList<String> actions; //Possible values include: Microsoft.Network/virtualNetworks/subnets/action

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) throws InvalidTypeException{
		if(!name.equals("\"Microsoft.ContainerInstance/containerGroups\"")){
			throw new InvalidTypeException("Possible values include: Microsoft.ContainerInstance/containerGroups");
		}
		this.name = name;
	}

	/**
	 * @return the actions
	 */
	public ArrayList<String> getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(ArrayList<String> actions) throws InvalidTypeException{
		for(String action  : actions){
			if(!action.equals("\"Microsoft.Network/virtualNetworks/subnets/action\"")){
				throw new InvalidTypeException("Possible values include: Microsoft.Network/virtualNetworks/subnets/action");
			}
		}
		this.actions = actions;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(name == null || name.equals("")){
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
		String block = "\"service_delegation\" { \n";
		block += elementIndent + "name = " + name + "\n";
		if(actions != null && !actions.equals("")){
			block += elementIndent + "actions = " + actions + "\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
