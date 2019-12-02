package terraform.resources.azure;

import terraform.provider.Constants;

public class BootDiagnostics {

	private boolean enabled; //Required
	private String storage_uri; //Required
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the storage_uri
	 */
	public String getStorage_uri() {
		return storage_uri;
	}
	/**
	 * @param storage_uri the storage_uri to set
	 */
	public void setStorage_uri(String storage_uri) {
		this.storage_uri = storage_uri;
	}
	
	/***
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(storage_uri.equals("") || storage_uri == null){
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
		String block = headIndent + "boot_diagnostics { \n";
		
		block += elementIndent + "enabled = " + enabled + "\n";
		if (storage_uri != null && !storage_uri.equals("")) {
			block += elementIndent + "storage_uri = " + storage_uri + "\n";
		}	
		block += headIndent + "}\n";
		return block;
	}
}
