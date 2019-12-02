package terraform.resources.azure;

import terraform.provider.Constants;

public class Winrm {
	private String protocol; // required
	private String certificate_url;
	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}
	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
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
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(protocol ==null || protocol.equals("")){
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
		String block = headIndent + "winrm { \n";
		block += elementIndent + "protocol = " + protocol + "\n";
		
		if (certificate_url != null && !certificate_url.equals("")) {
			block += elementIndent + "certificate_url = " + certificate_url + "\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}