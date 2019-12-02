package terraform.resources.aws.vpcresources;

import exceptions.RequiredArgumentException;
import terraform.TerraformObject;
import terraform.provider.Constants;

public class Attachment extends TerraformObject {
	private String instance; //required
	private int device_index; //required
	/**
	 * @return the instance
	 */
	public String getInstance() {
		return instance;
	}
	/**
	 * @param instance the instance to set
	 */
	public void setInstance(String instance) {
		this.instance = instance;
	}
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
	 * 
	 * @return
	 * @throws RequiredArgumentException
	 */
	private boolean isValid() throws RequiredArgumentException{
		if(device_index < 0){
			throw new RequiredArgumentException("Valid value is required for the device_index argument of the Attachment object");
		}
		
		if(instance ==null || instance.equals("")){
			throw new RequiredArgumentException("Config instance is required for the Attachment object");
		}
		return true;
	}
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	@Override
	public String getBlock(int level) {
		try {
			isValid();
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		}
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block = headIndent + "attachment \"" + "\" { \n";
		block += elementIndent + "instance = \"" + instance + "\"\n";
		block += elementIndent + "device_index = \"" + device_index + "\"\n";
		
		block += headIndent + "}\n";
		return block;
	}
}
