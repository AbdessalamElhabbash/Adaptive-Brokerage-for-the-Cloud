package terraform.resources.azure.network;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;

public class Subnet {

	private String name; // Required
	private String address_prefix; //required
	private String security_group;
	
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
	 * @return the address_prefix
	 */
	public String getAddress_prefix() {
		return address_prefix;
	}

	/**
	 * @param address_prefix the address_prefix to set
	 */
	public void setAddress_prefix(String address_prefix) {
		this.address_prefix = address_prefix;
	}

	/**
	 * @return the security_group
	 */
	public String getSecurity_group() {
		return security_group;
	}

	/**
	 * @param security_group the security_group to set
	 */
	public void setSecurity_group(String security_group) {
		this.security_group = security_group;
	}

	private boolean isValid() throws RequiredArgumentException{
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name config is required for the subnet object"); 
		}
		if(address_prefix == null || address_prefix.equals("")){
			throw new RequiredArgumentException("address_prefix config is required for the subnet object"); 
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
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		}
		String block = headIndent + "\"subnet\" { \n";
		
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "address_prefix = \"" + address_prefix + "\"\n";
		
		if(security_group != null && !security_group.equals("")){
			block += elementIndent + "security_group = \"" + security_group + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
