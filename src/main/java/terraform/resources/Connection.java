package terraform.resources;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class Connection {

	private String type; // Valid types are ssh and winrm Defaults to ssh.
	private String user;
	private String password;
	private String host;
	private int port;
	private String timeout;
	private String script_path;
	// SSH only
	private String private_key; // SSH key, file()
	private String agent; // SSH
	private String agent_identity; // SSH
	private String host_key; // SSH
	private boolean bastion_host; // SSH
	private String bastion_host_key; // SSH
	private int bastion_port; // SSH
	private String bastion_user; // SSH
	private String bastion_password; // SSH
	private String bastion_private_key; // SSH
	// winrm only
	private boolean https; // winrm
	private boolean insecure; // winrm
	private boolean use_ntlm; // winrm
	private String cacert; // winrm

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) throws InvalidTypeException {

		if (type.equalsIgnoreCase("ssh") || type.equalsIgnoreCase("winrm")) {
			this.type = type;
		} else {
			throw new InvalidTypeException("Connection type can be ssh or winrm only");
		}
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the timeout
	 */
	public String getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            the timeout to set
	 */
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the script_path
	 */
	public String getScript_path() {
		return script_path;
	}

	/**
	 * @param script_path
	 *            the script_path to set
	 */
	public void setScript_path(String script_path) {
		this.script_path = script_path;
	}

	/**
	 * @return the private_key
	 */
	public String getPrivate_key() {
		return private_key;
	}

	/**
	 * @param private_key
	 *            the private_key to set
	 */
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	/**
	 * @return the agent
	 */
	public String getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * @return the agent_identity
	 */
	public String getAgent_identity() {
		return agent_identity;
	}

	/**
	 * @param agent_identity
	 *            the agent_identity to set
	 */
	public void setAgent_identity(String agent_identity) {
		this.agent_identity = agent_identity;
	}

	/**
	 * @return the host_key
	 */
	public String getHost_key() {
		return host_key;
	}

	/**
	 * @param host_key
	 *            the host_key to set
	 */
	public void setHost_key(String host_key) {
		this.host_key = host_key;
	}

	/**
	 * @return the bastion_host
	 */
	public boolean isBastion_host() {
		return bastion_host;
	}

	/**
	 * @param bastion_host
	 *            the bastion_host to set
	 */
	public void setBastion_host(boolean bastion_host) {
		this.bastion_host = bastion_host;
	}

	/**
	 * @return the bastion_host_key
	 */
	public String getBastion_host_key() {
		return bastion_host_key;
	}

	/**
	 * @param bastion_host_key
	 *            the bastion_host_key to set
	 */
	public void setBastion_host_key(String bastion_host_key) {
		this.bastion_host_key = bastion_host_key;
	}

	/**
	 * @return the bastion_port
	 */
	public int getBastion_port() {
		return bastion_port;
	}

	/**
	 * @param bastion_port
	 *            the bastion_port to set
	 */
	public void setBastion_port(int bastion_port) {
		this.bastion_port = bastion_port;
	}

	/**
	 * @return the bastion_user
	 */
	public String getBastion_user() {
		return bastion_user;
	}

	/**
	 * @param bastion_user
	 *            the bastion_user to set
	 */
	public void setBastion_user(String bastion_user) {
		this.bastion_user = bastion_user;
	}

	/**
	 * @return the bastion_password
	 */
	public String getBastion_password() {
		return bastion_password;
	}

	/**
	 * @param bastion_password
	 *            the bastion_password to set
	 */
	public void setBastion_password(String bastion_password) {
		this.bastion_password = bastion_password;
	}

	/**
	 * @return the bastion_private_key
	 */
	public String getBastion_private_key() {
		return bastion_private_key;
	}

	/**
	 * @param bastion_private_key
	 *            the bastion_private_key to set
	 */
	public void setBastion_private_key(String bastion_private_key) {
		this.bastion_private_key = bastion_private_key;
	}

	/**
	 * @return the https
	 */
	public boolean isHttps() {
		return https;
	}

	/**
	 * @param https
	 *            the https to set
	 */
	public void setHttps(boolean https) {
		this.https = https;
	}

	/**
	 * @return the insecure
	 */
	public boolean isInsecure() {
		return insecure;
	}

	/**
	 * @param insecure
	 *            the insecure to set
	 */
	public void setInsecure(boolean insecure) {
		this.insecure = insecure;
	}

	/**
	 * @return the use_ntlm
	 */
	public boolean isUse_ntlm() {
		return use_ntlm;
	}

	/**
	 * @param use_ntlm
	 *            the use_ntlm to set
	 */
	public void setUse_ntlm(boolean use_ntlm) {
		this.use_ntlm = use_ntlm;
	}

	/**
	 * @return the cacert
	 */
	public String getCacert() {
		return cacert;
	}

	/**
	 * @param cacert
	 *            the cacert to set
	 */
	public void setCacert(String cacert) {
		this.cacert = cacert;
	}

	/**
	 * Returns the connection block
	 * @return
	 */
	public String getBlock(int level) { 
		/***for formatting the terraform code**/ 
		String headIndent=""; 
		for(int i=0; i<level; i++){ 
			headIndent += Constants.indent; 
		} String elementIndent= headIndent +  Constants.indent; 
		/*******************************************************/
		
		String block = headIndent + "connection { \n" ;
		
		if(type != null && !type.equals("")){
			block += elementIndent + "type = " + type +"\n";
		}
		if(user != null && !user.equals("")){
			block += elementIndent + "user = " + user +"\n";
		}
		if(password != null && !password.equals("")){
			block += elementIndent + "password = " + password +"\n";
		}
		if(host != null && !host.equals("")){
			block += elementIndent + "host = " + host +"\n";
		}
		if(port >0){
			block += elementIndent + "port = " + port +"\n";
		}
		if(timeout != null && !timeout.equals("")){
			block += elementIndent + "timeout = " + timeout +"\n";
		}
		if(script_path != null && !script_path.equals("")){
			block += elementIndent + "script_path = " + script_path +"\n";
		}
		
		
		if(type.equalsIgnoreCase("\"ssh\"")){
			if(private_key != null && !private_key.equals("")){
				block += elementIndent + "private_key = " + private_key +"\n";
			}
			if(agent != null && !agent.equals("")){
				block += elementIndent + "agent = " + agent +"\n";
			}
			if(agent_identity != null && !agent_identity.equals("")){
				block += elementIndent + "agent_identity = " + agent_identity +"\n";
			}
			if(host_key != null && !host_key.equals("")){
				block += elementIndent + "host_key = " + host_key +"\n";
			}
			if(bastion_host){
				block += elementIndent + "bastion_host = " + bastion_host +"\n";
			}
			if(bastion_host_key != null && !bastion_host_key.equals("")){
				block += elementIndent + "bastion_host_key = " + bastion_host_key +"\n";
			}
			if(bastion_port > 0){
				block += elementIndent + "bastion_port = " + bastion_port +"\n";
			}
			if(bastion_user != null && !bastion_user.equals("")){
				block += elementIndent + "bastion_user = " + bastion_user +"\n";
			}
			if(bastion_password != null && !bastion_password.equals("")){
				block += elementIndent + "bastion_password = " + bastion_password +"\n";
			}
			if(bastion_private_key != null && !bastion_private_key.equals("")){
				block += elementIndent + "bastion_private_key = " + bastion_private_key +"\n";
			}
		}else if(type.equalsIgnoreCase("\"ssh\"")){
			if(https){
				block += elementIndent + "https = " + https +"\n";
			}
			if(insecure){
				block += elementIndent + "insecure = " + insecure +"\n";
			}
			if(use_ntlm){
				block += elementIndent + "use_ntlm = " + use_ntlm +"\n";
			}
			if(cacert != null && !cacert.equals("")){
				block += elementIndent + "cacert = " + cacert +"\n";
			}
		}		
		block += headIndent + "}\n";
		return block; 
	}
	
}
