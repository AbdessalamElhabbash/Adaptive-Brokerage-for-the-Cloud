package terraform.resources.aws;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;

public class NetworkInterface {
	
	private int device_index; //required
	private String network_interface_id; //required
	private boolean delete_on_termination; //default false
	/**
	 * @return the device_index
	 */
	public int getDevice_index() {
		return device_index;
	}
	/**
	 * @param device_index the device_index to set
	 */
	public void setDevice_index(int device_index) {
		this.device_index = device_index;
	}
	/**
	 * @return the network_interface_id
	 */
	public String getNetwork_interface_id() {
		return network_interface_id;
	}
	/**
	 * @param network_interface_id the network_interface_id to set
	 */
	public void setNetwork_interface_id(String network_interface_id) {
		this.network_interface_id = network_interface_id;
	}
	/**
	 * @return the delete_on_termination
	 */
	public boolean isDelete_on_termination() {
		return delete_on_termination;
	}
	/**
	 * @param delete_on_termination the delete_on_termination to set
	 */
	public void setDelete_on_termination(boolean delete_on_termination) {
		this.delete_on_termination = delete_on_termination;
	}

	/**
	 * Checks if the required attributes are set
	 * @return
	 * @throws InvalidTypeException 
	 * @throws RequiredArgumentException 
	 */
	private boolean isValid() throws InvalidTypeException, RequiredArgumentException{
		if(device_index < 0){
			throw new InvalidTypeException("Invalid value for device_index of the network_interface object");
		}
		if(network_interface_id == null || network_interface_id.equals("")){
			throw new RequiredArgumentException("network_interface_id is required for the network_interface object");
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
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		try {
			isValid();
		} catch (InvalidTypeException | RequiredArgumentException e) {
			e.printStackTrace();
		}
			
		String block = headIndent + "network_interface { \n";
		
		block += elementIndent + "device_index = " + device_index + "\n";
		block += elementIndent + "network_interface_id = \"" + network_interface_id + "\"\n";
		
		if (delete_on_termination) {
			block += elementIndent + "delete_on_termination = " + delete_on_termination + "\n";
		}		
		block += headIndent + "}\n";
		return block;
	}
}
