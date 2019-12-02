package terraform.resources.aws;

import terraform.provider.Constants;

public class EphemeralBlockDevice {
	private String device_name;
	private String virtual_name;
	private boolean no_device;
	/**
	 * @return the device_name
	 */
	public String getDevice_name() {
		return device_name;
	}
	/**
	 * @param device_name the device_name to set
	 */
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	/**
	 * @return the virtual_name
	 */
	public String getVirtual_name() {
		return virtual_name;
	}
	/**
	 * @param virtual_name the virtual_name to set
	 */
	public void setVirtual_name(String virtual_name) {
		this.virtual_name = virtual_name;
	}
	/**
	 * @return the no_device
	 */
	public boolean isNo_device() {
		return no_device;
	}
	/**
	 * @param no_device the no_device to set
	 */
	public void setNo_device(boolean no_device) {
		this.no_device = no_device;
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
		String block = "ephemeral_block_device { \n" ;
		
		if(device_name != null && !device_name.equals("")){
			block += elementIndent + "device_name = " + device_name +"\n";
		}
		if(virtual_name != null && !virtual_name.equals("")){
			block += elementIndent + "virtual_name = " + virtual_name +"\n";
		}
		
		block += elementIndent + "no_device = " + no_device +"\n";
		
		block += headIndent + "}\n";
		return block; 
	}
}
