package terraform.resources.azure;

import terraform.provider.Constants;

public class VaultCertificates {
	
	private String certificate_url; //Required
	private String certificate_store; //Required, on windows machines
	/**
	 * @return the certificate_url
	 */
	public String getCertificate_url() {
		return certificate_url;
	}
	/**
	 * @param certificate_url the certificate_url to set
	 */
	public void setCertificate_url(String certificate_url) {
		this.certificate_url = certificate_url;
	}
	/**
	 * @return the certificate_store
	 */
	public String getCertificate_store() {
		return certificate_store;
	}
	/**
	 * @param certificate_store the certificate_store to set
	 */
	public void setCertificate_store(String certificate_store) {
		this.certificate_store = certificate_store;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(certificate_url ==null || certificate_url.equals("")){
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
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		if(!isValid()){
			return null;
		}
		
		String block = headIndent + "vault_certificates { \n";		
		
		block += elementIndent + "certificate_url  = " + certificate_url  + "\n";		
		if(certificate_store == null || certificate_store.equals("")){
			block += elementIndent + "certificate_store  = " + certificate_store  + "\n";	
		}		
		block += headIndent + "}\n";
		return block;
	}

}
