package terraform.resources.azure.network;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;

public class AzurermSubnet extends Resource{

	public AzurermSubnet(String resourceName) {
		super(resourceName);
	}

	private String name; //required
	private String resource_group_name; //required
	private String virtual_network_name; //required
	private String address_prefix; //required
	private String network_security_group_id;
	private String route_table_id;
	private ArrayList<String> service_endpoints; /** Possible values include: Microsoft.AzureActiveDirectory
	 Microsoft.AzureCosmosDB, Microsoft.EventHub, Microsoft.KeyVault, Microsoft.ServiceBus, Microsoft.Sql and 
	 Microsoft.Storage*/
	private ArrayList<Delegation> delegation;

	private static final Set<String> ENDPOINTS;

    static {
    	ENDPOINTS = new HashSet<String>();
    	ENDPOINTS.add("Microsoft.AzureActiveDirectory");
    	ENDPOINTS.add("Microsoft.AzureCosmosDB");
    	ENDPOINTS.add("Microsoft.EventHub");
    	ENDPOINTS.add("Microsoft.KeyVault");
    	ENDPOINTS.add("Microsoft.ServiceBus");
    	ENDPOINTS.add("Microsoft.Sql");
    	ENDPOINTS.add("Microsoft.Storage");
    }
	
	
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
	 * @return the virtual_network_name
	 */
	public String getVirtual_network_name() {
		return virtual_network_name;
	}

	/**
	 * @param virtual_network_name the virtual_network_name to set
	 */
	public void setVirtual_network_name(String virtual_network_name) {
		this.virtual_network_name = virtual_network_name;
	}

	/**
	 * @return the address_prefix
	 */
	public String getAddress_prefix() {
		return address_prefix;
	}

	/**
	 * @param address_prefix the address_prefix to set
	 */
	public void setAddress_prefix(String address_prefix) {
		this.address_prefix = address_prefix;
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
	 * @return the route_table_id
	 */
	public String getRoute_table_id() {
		return route_table_id;
	}

	/**
	 * @param route_table_id the route_table_id to set
	 */
	public void setRoute_table_id(String route_table_id) {
		this.route_table_id = route_table_id;
	}

	/**
	 * @return the service_endpoints
	 */
	public ArrayList<String> getService_endpoints() {
		return service_endpoints;
	}

	/**
	 * @param service_endpoints the service_endpoints to set
	 */
	public void setService_endpoints(ArrayList<String> service_endpoints) throws InvalidTypeException {
		if(ENDPOINTS.contains(service_endpoints)){
			this.service_endpoints = service_endpoints;
		}else{
			throw new InvalidTypeException("Possible values include: Microsoft.AzureActiveDirectory, "
					+ "Microsoft.AzureCosmosDB, Microsoft.EventHub, Microsoft.KeyVault, Microsoft.ServiceBus,"
					+ " Microsoft.Sql and Microsoft.Storage.");
		}
	}

	/**
	 * @return the delegation
	 */
	public ArrayList<Delegation> getDelegation() {
		return delegation;
	}

	/**
	 * @param delegation the delegation to set
	 */
	public void setDelegation(ArrayList<Delegation> delegation) {
		this.delegation = delegation;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isValid() throws RequiredArgumentException{
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name config is required for the azurerm_subnet object");
		}
		if(resource_group_name == null || resource_group_name.equals("")){
			throw new RequiredArgumentException("resource_group_name config is required for the azurerm_subnet object");
		}
		if(virtual_network_name == null || virtual_network_name.equals("")){
			throw new RequiredArgumentException("virtual_network_name config is required for the azurerm_subnet object");
		}
		if(address_prefix == null || address_prefix.equals("")){
			throw new RequiredArgumentException("address_prefix config is required for the azurerm_subnet object");
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
		
		String block = headIndent + "resource \"azurerm_subnet\" \"" + resourceName + "\"{ \n";
		
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "resource_group_name = \"" + resource_group_name + "\"\n";
		block += elementIndent + "virtual_network_name = \"" + virtual_network_name + "\"\n";
		block += elementIndent + "address_prefix = \"" + address_prefix + "\"\n";

		if(network_security_group_id != null && !network_security_group_id.equals("")){
			block += elementIndent + "network_security_group_id = \"" + network_security_group_id + "\"\n";
		}
		if(route_table_id != null && !route_table_id.equals("")){
			block += elementIndent + "route_table_id = \"" + route_table_id + "\"\n";
		}
		if(service_endpoints != null && !service_endpoints.isEmpty()){
			block += elementIndent + "service_endpoints = \"" + service_endpoints + "\"\n";
		}
		if(delegation!=null){
			for(Delegation del : delegation){
				block += del.getBlock(level+1);
			}
		}
		block += headIndent + "}\n";
		return block;
	}

	/**
	 * returns interpolated reference to the id
	 * @return
	 */
	public String getIDReference() {
		return "${azurerm_subnet." + resourceName + ".id}";
	}
}
