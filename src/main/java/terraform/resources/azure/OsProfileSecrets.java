package terraform.resources.azure;

import java.util.ArrayList;

import terraform.provider.Constants;

public class OsProfileSecrets {
	private String source_vault_id; //Required
	private ArrayList<VaultCertificates> vault_certificates; //Required
	/**
	 * @return the source_vault_id
	 */
	public String getSource_vault_id() {
		return source_vault_id;
	}
	/**
	 * @param source_vault_id the source_vault_id to set
	 */
	public void setSource_vault_id(String source_vault_id) {
		this.source_vault_id = source_vault_id;
	}
	/**
	 * @return the vault_certificates
	 */
	public ArrayList<VaultCertificates> getVault_certificates() {
		return vault_certificates;
	}
	/**
	 * @param vault_certificates the vault_certificates to set
	 */
	public void setVault_certificates(ArrayList<VaultCertificates> vault_certificates) {
		this.vault_certificates = vault_certificates;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(source_vault_id != null && !source_vault_id.equals("")){
			return true;
		}
		if(vault_certificates !=null && !vault_certificates.isEmpty()){
				return true;
		}		
		return false;
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
		if(!isValid()){
			return null;
		}
		String block = headIndent + "os_profile_secrets { \n";		
		
		block += elementIndent + "source_vault_id = " + source_vault_id + "\n";		
		for(VaultCertificates vault_certificate : vault_certificates){
			block += vault_certificate.getBlock(level+1);
		}		
		block += headIndent + "}\n";
		return block;
	}
}
