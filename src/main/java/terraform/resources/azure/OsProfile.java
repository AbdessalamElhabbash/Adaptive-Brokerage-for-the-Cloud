package terraform.resources.azure;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;

public class OsProfile {

	private String computer_name; //required
	private String admin_username; //required
	private String admin_password; //required
	private String custom_data;
	/**
	 * @return the computer_name
	 */
	public String getComputer_name() {
		return computer_name;
	}
	/**
	 * @param computer_name the computer_name to set
	 */
	public void setComputer_name(String computer_name) {
		this.computer_name = computer_name;
	}
	/**
	 * @return the admin_username
	 */
	public String getAdmin_username() {
		return admin_username;
	}
	/**
	 * @param admin_username the admin_username to set
	 */
	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}
	/**
	 * @return the admin_password
	 */
	public String getAdmin_password() {
		return admin_password;
	}
	/**
	 * @param admin_password the admin_password to set
	 */
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	/**
	 * @return the custom_data
	 */
	public String getCustom_data() {
		return custom_data;
	}
	/**
	 * @param custom_data the custom_data to set
	 */
	public void setCustom_data(String custom_data) {
		this.custom_data = custom_data;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid() throws RequiredArgumentException{
		if(computer_name == null || computer_name.equals("")){
			throw new RequiredArgumentException("name conifig is required for the os_profile object");		
		}
		if(admin_username == null || admin_username.equals("")){
			throw new RequiredArgumentException("admin_username conifig is required for the os_profile object");		
		}
		if(admin_password == null || admin_password.equals("")){
			throw new RequiredArgumentException("admin_password conifig is required for the os_profile object");		
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
		
		String block = headIndent + "os_profile { \n";
		
		block += elementIndent + "computer_name  = \"" + computer_name + "\"\n";
		block += elementIndent + "admin_username  = \"" + admin_username + "\"\n";
		block += elementIndent + "admin_password  = \"" + admin_password + "\"\n";
		
		if (custom_data  != null && !custom_data.equals("")) {
			block += elementIndent + "custom_data = \"" + custom_data + "\"\n";
		}	
		block += headIndent + "}\n";
		return block;
	}
}
