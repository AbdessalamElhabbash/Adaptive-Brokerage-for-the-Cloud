package terraform.resources.azure.network;

import java.util.ArrayList;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AzurermVirtualNetwork extends Resource{
		
	public AzurermVirtualNetwork(String resourceName) {
		super(resourceName);
	}

	private String name; //required
	private String resource_group_name; //required
	private ArrayList<String> address_space; //required
	private String location; //required
	private ArrayList<String> dns_servers;
	private ArrayList<Subnet> subnet;
	private Tags tags;
	
	
	/**
	 * @return the name
	 */
	public String getName()  {
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
	 * @return the address_space
	 */
	public ArrayList<String> getAddress_space() {
		return address_space;
	}

	/**
	 * @param address_space the address_space to set
	 */
	public void setAddress_space(ArrayList<String> address_space) {
		this.address_space = address_space;
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
	 * @return the subnet
	 */
	public ArrayList<Subnet> getSubnet() {
		return subnet;
	}

	/**
	 * @param subnet the subnet to set
	 */
	public void setSubnet(ArrayList<Subnet> subnet) {
		this.subnet = subnet;
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

	private boolean isValid() throws RequiredArgumentException {
		if(name == null || name.equals("")) {
			throw new RequiredArgumentException("name config is required for azurerm_virtual_network");
		}
		if(resource_group_name == null || resource_group_name.equals("")){
			throw new RequiredArgumentException("resource_group_name config is required for azurerm_virtual_network");
		}
		if(location == null || location.equals("")){
			throw new RequiredArgumentException("location config is required for azurerm_virtual_network");
		}
		if(address_space == null || address_space.isEmpty()){
			throw new RequiredArgumentException("address_space config is required for azurerm_virtual_network");
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
		String block = headIndent + "resource \"azurerm_virtual_network\" \"" + resourceName + "\" { \n";
		
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "location = \"" + location + "\"\n";
		block += elementIndent + "resource_group_name = \"" + resource_group_name + "\"\n";
		block += elementIndent + "address_space = \"" + address_space + "\"\n";

		if(dns_servers != null && !dns_servers.isEmpty()){
			block += elementIndent + "dns_servers = \"" + dns_servers + "\"\n";
		}
		
		if(subnet != null){
			for(Subnet net : subnet){
				block += net.getBlock(level+1) + "\n";
			}
		}
		
		if(tags != null){
			block += tags.getBlock(level+1) + "\n";
		}
		
		block += headIndent + "}\n";
		return block;
	}

}
