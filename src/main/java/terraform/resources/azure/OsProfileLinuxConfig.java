package terraform.resources.azure;

import java.util.ArrayList;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;

public class OsProfileLinuxConfig {
	private boolean disable_password_authentication; //Required
	private ArrayList<SshKey> ssh_keys; // required if disable_password_authentication is set to true.
	/**
	 * @return the disable_password_authentication
	 */
	public boolean isDisable_password_authentication() {
		return disable_password_authentication;
	}
	/**
	 * @param disable_password_authentication the disable_password_authentication to set
	 */
	public void setDisable_password_authentication(boolean disable_password_authentication) {
		this.disable_password_authentication = disable_password_authentication;
	}
	/**
	 * @return the ssh_keys
	 */
	public ArrayList<SshKey> getSsh_keys() {
		return ssh_keys;
	}
	/**
	 * @param ssh_keys the ssh_keys to set
	 */
	public void setSsh_keys(ArrayList<SshKey> ssh_keys) {
		this.ssh_keys = ssh_keys;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isValid() throws RequiredArgumentException{
		if(disable_password_authentication){
			if(ssh_keys ==null && ssh_keys.isEmpty()){
				throw new RequiredArgumentException("ssh_keys conifig is required for the os_profile object");		
			}
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
		
		String block = headIndent + "os_profile_linux_config { \n";		
		block += elementIndent + "disable_password_authentication = " + disable_password_authentication + "\n";		
		if(disable_password_authentication){
			for(SshKey ssh_key : ssh_keys){
				block += ssh_key.getBlock(level+1);
			}
		}
		block += headIndent + "}\n";
		return block;
	}
}
