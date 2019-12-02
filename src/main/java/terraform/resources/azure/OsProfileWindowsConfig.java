package terraform.resources.azure;

import java.util.ArrayList;

import terraform.provider.Constants;

public class OsProfileWindowsConfig {
	private boolean provision_vm_agent; //default false
	private boolean enable_automatic_upgrades; //default false
	private String timezone;
	private ArrayList<Winrm> winrm;
	private AdditionalUnattendConfig additional_unattend_config;
	/**
	 * @return the provision_vm_agent
	 */
	public boolean isProvision_vm_agent() {
		return provision_vm_agent;
	}
	/**
	 * @param provision_vm_agent the provision_vm_agent to set
	 */
	public void setProvision_vm_agent(boolean provision_vm_agent) {
		this.provision_vm_agent = provision_vm_agent;
	}
	/**
	 * @return the enable_automatic_upgrades
	 */
	public boolean isEnable_automatic_upgrades() {
		return enable_automatic_upgrades;
	}
	/**
	 * @param enable_automatic_upgrades the enable_automatic_upgrades to set
	 */
	public void setEnable_automatic_upgrades(boolean enable_automatic_upgrades) {
		this.enable_automatic_upgrades = enable_automatic_upgrades;
	}
	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}
	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	/**
	 * @return the winrm
	 */
	public ArrayList<Winrm> getWinrm() {
		return winrm;
	}
	/**
	 * @param winrm the winrm to set
	 */
	public void setWinrm(ArrayList<Winrm> winrm) {
		this.winrm = winrm;
	}
	/**
	 * @return the additional_unattend_config
	 */
	public AdditionalUnattendConfig getAdditional_unattend_config() {
		return additional_unattend_config;
	}
	/**
	 * @param additional_unattend_config the additional_unattend_config to set
	 */
	public void setAdditional_unattend_config(AdditionalUnattendConfig additional_unattend_config) {
		this.additional_unattend_config = additional_unattend_config;
	}

	
	public String getBlock(int level) {
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		String block = headIndent + "os_profile_windows_config { \n";

		if(provision_vm_agent){
			block += elementIndent + "provision_vm_agent = " + provision_vm_agent + "\n";
		}
		if(enable_automatic_upgrades){
			block += elementIndent + "enable_automatic_upgrades = " + enable_automatic_upgrades + "\n";
		}
		if(timezone != null && !timezone.equals("")){
			block += elementIndent + "timezone = " + timezone + "\n";
		}
		if(!winrm.isEmpty()){
			for(Winrm w : winrm){
				block += w.getBlock(level+1);
			}
		}	
		if(additional_unattend_config != null){
			block += additional_unattend_config.getBlock(level+1);
		}
		block += headIndent + "}\n";
		return block;
	}
}
