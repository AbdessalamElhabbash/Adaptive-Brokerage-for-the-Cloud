package terraform.resources.azure.network;

import java.util.ArrayList;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AzurermNetworkInterface extends Resource{
	public AzurermNetworkInterface(String resourceName) {
		super(resourceName);
	}

	private String name; //required
	private String resource_group_name; //required
	private String location;//required
	private String network_security_group_id;
	private String internal_dns_name_label;
	private boolean enable_ip_forwarding; // Defaults to false.
	private boolean enable_accelerated_networking;  //default to false
	private ArrayList<String> dns_servers; 
	private ArrayList<IpConfiguration> ip_configuration; //required
	private Tags tags;
	
	
	
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
	 * @return the resource_group_name
	 */
	public String getResource_group_name() {
		return resource_group_name;
	}

	/**
	 * @param resource_group_name the resource_group_name to set
	 */
	public void setResource_group_name(String resource_group_name) {
		this.resource_group_name = resource_group_name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the network_security_group_id
	 */
	public String getNetwork_security_group_id() {
		return network_security_group_id;
	}

	/**
	 * @param network_security_group_id the network_security_group_id to set
	 */
	public void setNetwork_security_group_id(String network_security_group_id) {
		this.network_security_group_id = network_security_group_id;
	}

	/**
	 * @return the internal_dns_name_label
	 */
	public String getInternal_dns_name_label() {
		return internal_dns_name_label;
	}

	/**
	 * @param internal_dns_name_label the internal_dns_name_label to set
	 */
	public void setInternal_dns_name_label(String internal_dns_name_label) {
		this.internal_dns_name_label = internal_dns_name_label;
	}

	/**
	 * @return the enable_ip_forwarding
	 */
	public boolean isEnable_ip_forwarding() {
		return enable_ip_forwarding;
	}

	/**
	 * @param enable_ip_forwarding the enable_ip_forwarding to set
	 */
	public void setEnable_ip_forwarding(boolean enable_ip_forwarding) {
		this.enable_ip_forwarding = enable_ip_forwarding;
	}

	/**
	 * @return the enable_accelerated_networking
	 */
	public boolean isEnable_accelerated_networking() {
		return enable_accelerated_networking;
	}

	/**
	 * @param enable_accelerated_networking the enable_accelerated_networking to set
	 */
	public void setEnable_accelerated_networking(boolean enable_accelerated_networking) {
		this.enable_accelerated_networking = enable_accelerated_networking;
	}

	/**
	 * @return the dns_servers
	 */
	public ArrayList<String> getDns_servers() {
		return dns_servers;
	}

	/**
	 * @param dns_servers the dns_servers to set
	 */
	public void setDns_servers(ArrayList<String> dns_servers) {
		this.dns_servers = dns_servers;
	}

	/**
	 * @return the ip_configuration
	 */
	public ArrayList<IpConfiguration> getIp_configuration() {
		return ip_configuration;
	}

	/**
	 * @param ip_configuration the ip_configuration to set
	 */
	public void setIp_configuration(ArrayList<IpConfiguration> ip_configuration) {
		this.ip_configuration = ip_configuration;
	}

	/**
	 * @return the tags
	 */
	public Tags getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	
	/**
	 * returns interpolated reference to the ids
	 * @return
	 */
	public String getIDReference(){
		return "${azurerm_network_interface." + resourceName + ".id}";
 
	}

	/**
	 * 
	 * @return
	 */
	private boolean isValid() throws RequiredArgumentException{
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name config is required for the azurerm_network_interface object");
		}
		if(resource_group_name == null || resource_group_name.equals("")){
			throw new RequiredArgumentException("resource_group_name config is required for the azurerm_network_interface object");
		}
		if(location == null || location.equals("")){
			throw new RequiredArgumentException("location config is required for the azurerm_network_interface object");
		}
		if(ip_configuration == null || ip_configuration.isEmpty()){
			throw new RequiredArgumentException("ip_configuration config is required for the azurerm_network_interface object");
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
				
		String block =  headIndent + "resource \"azurerm_network_interface\" \"" + resourceName + "\"{ \n";
		
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "resource_group_name = \"" + resource_group_name + "\"\n";
		block += elementIndent + "location = \"" + location + "\"\n";
		for(IpConfiguration ip_config: ip_configuration){
			block += ip_config.getBlock(level+1);
		}

		if(network_security_group_id != null && !network_security_group_id.equals("")){
			block += elementIndent + "network_security_group_id = \"" + network_security_group_id + "\"\n";
		}
		if(internal_dns_name_label != null && !internal_dns_name_label.equals("")){
			block += elementIndent + "internal_dns_name_label = \"" + internal_dns_name_label + "\"\n";
		}
		if(enable_ip_forwarding){
			block += elementIndent + "enable_ip_forwarding = \"" + enable_ip_forwarding + "\"\n";
		}
		if(enable_accelerated_networking){
			block += elementIndent + "enable_accelerated_networking = \"" + enable_accelerated_networking + "\"\n";
		}
		if(dns_servers != null && !dns_servers.isEmpty()){
			block += elementIndent + "dns_servers = \"" + dns_servers + "\"\n";
		}
		if(tags != null){
			block += tags.getBlock(level+1);
		}
		block += headIndent + "}\n";
		return block;
	}

}
