package terraform.resources;

import terraform.provider.Constants;

public class Service {

	private String name; //required
	private String bindAlias;	
	private String bindService;
	private String bindGroup;
	private String topology;
	private String strategy;
	private String user_toml;
	private String channel;
	private String group;
	private String url;
	private String application;
	private String environment;
	private String override_name;
	private String service_key;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the bindAlias
	 */
	public String getBindAlias() {
		return bindAlias;
	}
	/**
	 * @param bindAlias the bindAlias to set
	 */
	public void setBindAlias(String bindAlias) {
		this.bindAlias = bindAlias;
	}
	/**
	 * @return the bindService
	 */
	public String getBindService() {
		return bindService;
	}
	/**
	 * @param bindService the bindService to set
	 */
	public void setBindService(String bindService) {
		this.bindService = bindService;
	}
	/**
	 * @return the bindGroup
	 */
	public String getBindGroup() {
		return bindGroup;
	}
	/**
	 * @param bindGroup the bindGroup to set
	 */
	public void setBindGroup(String bindGroup) {
		this.bindGroup = bindGroup;
	}
	/**
	 * @return the topology
	 */
	public String getTopology() {
		return topology;
	}
	/**
	 * @param topology the topology to set
	 */
	public void setTopology(String topology) {
		this.topology = topology;
	}
	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}
	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	/**
	 * @return the user_toml
	 */
	public String getUser_toml() {
		return user_toml;
	}
	/**
	 * @param user_toml the user_toml to set
	 */
	public void setUser_toml(String user_toml) {
		this.user_toml = user_toml;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}
	/**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}
	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}
	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	/**
	 * @return the override_name
	 */
	public String getOverride_name() {
		return override_name;
	}
	/**
	 * @param override_name the override_name to set
	 */
	public void setOverride_name(String override_name) {
		this.override_name = override_name;
	}
	/**
	 * @return the service_key
	 */
	public String getService_key() {
		return service_key;
	}
	/**
	 * @param service_key the service_key to set
	 */
	public void setService_key(String service_key) {
		this.service_key = service_key;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(name ==null || name.equals("")){
			return false;
		}
		return true;
	}
	
	public String getBlock(int level) { 
		/***for formatting the terraform code**/ 
		String headIndent=""; 
		for(int i=0; i<level; i++){ 
			headIndent += Constants.indent; 
		} String elementIndent= headIndent +  Constants.indent;		
		/*******************************************************/
		if(!isValid()){
			return null;
		}
		String block = headIndent + "service { \n";
		if (name != null && !name.equals("")) {
			block += elementIndent + "name = " + name + "\n";
		}
		if (bindAlias != null && !bindAlias.equals("")) {
			block += elementIndent + "bindAlias = " + bindAlias + "\n";
		}
		if (bindService != null && !bindService.equals("")) {
			block += elementIndent + "bindService = " + bindService + "\n";
		}
		if (bindGroup != null && !bindGroup.equals("")) {
			block += elementIndent + "bindGroup = " + bindGroup + "\n";
		}
		if (topology != null && !topology.equals("")) {
			block += elementIndent + "topology" + topology + "\n";
		}
		if (strategy != null && !strategy.equals("")) {
			block += elementIndent + "strategy = " + strategy + "\n";
		}
		if (user_toml != null && !user_toml.equals("")) {
			block += elementIndent + "user_toml" + user_toml + "\n";
		}
		if (channel != null && !channel.equals("")) {
			block += elementIndent + "channel = " + channel + "\n";
		}
		if (group != null && !group.equals("")) {
			block += elementIndent + "group = " + group + "\n";
		}
		if (url != null && !url.equals("")) {
			block += elementIndent + "url = " + url + "\n";
		}
		if (application != null && !application.equals("")) {
			block += elementIndent + "application" + application + "\n";
		}
		if (environment != null && !environment.equals("")) {
			block += elementIndent + "environment = " + environment + "\n";
		}
		if (override_name != null && !override_name.equals("")) {
			block += elementIndent + "override_name" + override_name + "\n";
		}
		if (service_key != null && !service_key.equals("")) {
			block += elementIndent + "service_key = " + service_key + "\n";
		}
		block += headIndent + "}\n";
		return block;
	}	
}
