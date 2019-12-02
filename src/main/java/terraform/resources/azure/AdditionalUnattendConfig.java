package terraform.resources.azure;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class AdditionalUnattendConfig {
	private String pass; //required The only allowable value is oobeSystem.
	private String component; //The only allowable value is Microsoft-Windows-Shell-Setup.
	private String setting_name; //Possible values are: FirstLogonCommands and AutoLogon.
	private String content;
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) throws InvalidTypeException{
		if(pass.equalsIgnoreCase("\"oobeSystem\"")){
			this.pass = pass;
		}else{
			throw new InvalidTypeException("The only allowable value is oobeSystem.");
		}
	}
	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}
	/**
	 * @param component the component to set
	 */
	public void setComponent(String component) throws InvalidTypeException{
		if(component.equalsIgnoreCase("\"Microsoft-Windows-Shell-Setup\"")){
			this.component = component;
		}else{
			throw new InvalidTypeException("The only allowable value is Microsoft-Windows-Shell-Setup.");
		}
	}
	/**
	 * @return the setting_name
	 */
	public String getSetting_name() {
		return setting_name;
	}
	/**
	 * @param setting_name the setting_name to set
	 */
	public void setSetting_name(String setting_name) throws InvalidTypeException{
		if(setting_name.equalsIgnoreCase("\"FirstLogonCommands\"") || setting_name.equalsIgnoreCase("\"AutoLogon\"")){
			this.setting_name = setting_name;
		}else{
			throw new InvalidTypeException("Possible values are: FirstLogonCommands and AutoLogon");
		}
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(pass.equals("") || pass == null){
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
		
		String block = "additional_unattend_config { \n";
		if (pass != null && !pass.equals("")) {
			block += elementIndent + "pass = " + pass + "\n";
		}
		if (component != null && !component.equals("")) {
			block += elementIndent + "component = " + component + "\n";
		}
		if (setting_name != null && !setting_name.equals("")) {
			block += elementIndent + "setting_name = " + setting_name + "\n";
		}
		if (content != null && !content.equals("")) {
			block += elementIndent + "content = " + content + "\n";
		}
		block += headIndent + "}\n";
		return block;		
	}

}
