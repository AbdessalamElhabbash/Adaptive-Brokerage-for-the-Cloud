package terraform.provider;

import exceptions.RequiredArgumentException;
import terraform.TerraformObject;

public class Assume_role extends TerraformObject{
	private String role_arn; //Required
	private String session_name; //Optional
	private String external_id; //Optional
	private String policy; //optional
	/**
	 * @return the role_arn
	 */
	public String getRole_arn() {
		return role_arn;
	}
	/**
	 * @param role_arn the role_arn to set
	 */
	public void setRole_arn(String role_arn) {
		this.role_arn = role_arn;
	}
	/**
	 * @return the session_name
	 */
	public String getSession_name() {
		return session_name;
	}
	/**
	 * @param session_name the session_name to set
	 */
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	/**
	 * @return the external_id
	 */
	public String getExternal_id() {
		return external_id;
	}
	/**
	 * @param external_id the external_id to set
	 */
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	/**
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}
	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
	/**
	 * Checks if the required attributes are set
	 * @return
	 * @throws RequiredArgumentException 
	 */
	public boolean isValid() throws RequiredArgumentException{
		if(role_arn == null || role_arn.equals("")){
			throw new RequiredArgumentException("role_arn is a required config for the aws assume_role object");
		}
		return true;
	}
	
	/**
	 * Returns the assume_role block if valid. If not, returns an error message.
	 * 
	 * @return
	 */
	@Override
	public String getBlock(int level) {
		try {
			isValid();
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		}
		/*** for formatting the terraform code **/
		String headIndent = "";
		for (int i = 0; i < level; i++) {
			headIndent += Constants.indent;
		}
		String elementIndent = headIndent + Constants.indent;
		/*******************************************************/

		String block =  "assume_role { \n" ;
		block += elementIndent + "role_arn = \"" + role_arn + "\"\n";
		if(session_name != null && !session_name.equals("")){
			block += elementIndent + "session_name = \"" + session_name + "\"\n"; 
		} 
		if(external_id != null && !external_id.equals("")){
			block += elementIndent + "external_id = \"" + external_id + "\"\n"; 
		}
		if(policy != null && !policy.equals("")){
			block += elementIndent + "policy = \"" + policy + "\"\n"; 
		}    
		block += headIndent + "}\n";		
		return block;
	}
}
