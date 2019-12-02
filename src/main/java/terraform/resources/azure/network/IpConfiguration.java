package terraform.resources.azure.network;

import java.util.ArrayList;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;

public class IpConfiguration {

	private String name; //required
	private String subnet_id; //Required when private ip_address_version is IPv4.
	private String private_ip_address;
	private String private_ip_address_allocation; //Required. Options are Static or Dynamic.
	private String private_ip_address_version; //Possible values are IPv4 or IPv6. Defaults to IPv4.
	private String public_ip_address_id;
	private ArrayList<String> application_gateway_backend_address_pools_ids; 
	private ArrayList<String> load_balancer_backend_address_pools_ids;
	private ArrayList<String> load_balancer_inbound_nat_rules_ids;
	private ArrayList<String> application_security_group_ids;
	private boolean primary;
	
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
	 * @return the subnet_id
	 */
	public String getSubnet_id() {
		return subnet_id;
	}

	/**
	 * @param subnet_id the subnet_id to set
	 */
	public void setSubnet_id(String subnet_id) {
		this.subnet_id = subnet_id;
	}

	/**
	 * @return the private_ip_address
	 */
	public String getPrivate_ip_address() {
		return private_ip_address;
	}

	/**
	 * @param private_ip_address the private_ip_address to set
	 */
	public void setPrivate_ip_address(String private_ip_address) {
		this.private_ip_address = private_ip_address;
	}

	/**
	 * @return the private_ip_address_allocation
	 */
	public String getPrivate_ip_address_allocation() {
		return private_ip_address_allocation;
	}

	/**
	 * @param private_ip_address_allocation the private_ip_address_allocation to set
	 */
	public void setPrivate_ip_address_allocation(String private_ip_address_allocation) throws InvalidTypeException {
		if(!private_ip_address_allocation.equals("Static") && !private_ip_address_allocation.equals("Dynamic")){
			 throw new InvalidTypeException("Possible values are Static or Dynamic");
		}
		this.private_ip_address_allocation = private_ip_address_allocation;
	}

	/**
	 * @return the private_ip_address_version
	 */
	public String getPrivate_ip_address_version() {
		return private_ip_address_version;
	}

	/**
	 * @param private_ip_address_version the private_ip_address_version to set
	 */
	public void setPrivate_ip_address_version(String private_ip_address_version) throws InvalidTypeException {
		if(!private_ip_address_version.equals("IPv4") || !private_ip_address_version.equals("IPv4")){
			 throw new InvalidTypeException("Possible values are IPv4 or IPv6");
		}
		this.private_ip_address_version = private_ip_address_version;
	}

	/**
	 * @return the public_ip_address_id
	 */
	public String getPublic_ip_address_id() {
		return public_ip_address_id;
	}

	/**
	 * @param public_ip_address_id the public_ip_address_id to set
	 */
	public void setPublic_ip_address_id(String public_ip_address_id) {
		this.public_ip_address_id = public_ip_address_id;
	}

	/**
	 * @return the application_gateway_backend_address_pools_ids
	 */
	public ArrayList<String> getApplication_gateway_backend_address_pools_ids() {
		return application_gateway_backend_address_pools_ids;
	}

	/**
	 * @param application_gateway_backend_address_pools_ids the application_gateway_backend_address_pools_ids to set
	 */
	public void setApplication_gateway_backend_address_pools_ids(
			ArrayList<String> application_gateway_backend_address_pools_ids) {
		this.application_gateway_backend_address_pools_ids = application_gateway_backend_address_pools_ids;
	}

	/**
	 * @return the load_balancer_backend_address_pools_ids
	 */
	public ArrayList<String> getLoad_balancer_backend_address_pools_ids() {
		return load_balancer_backend_address_pools_ids;
	}

	/**
	 * @param load_balancer_backend_address_pools_ids the load_balancer_backend_address_pools_ids to set
	 */
	public void setLoad_balancer_backend_address_pools_ids(ArrayList<String> load_balancer_backend_address_pools_ids) {
		this.load_balancer_backend_address_pools_ids = load_balancer_backend_address_pools_ids;
	}

	/**
	 * @return the load_balancer_inbound_nat_rules_ids
	 */
	public ArrayList<String> getLoad_balancer_inbound_nat_rules_ids() {
		return load_balancer_inbound_nat_rules_ids;
	}

	/**
	 * @param load_balancer_inbound_nat_rules_ids the load_balancer_inbound_nat_rules_ids to set
	 */
	public void setLoad_balancer_inbound_nat_rules_ids(ArrayList<String> load_balancer_inbound_nat_rules_ids) {
		this.load_balancer_inbound_nat_rules_ids = load_balancer_inbound_nat_rules_ids;
	}

	/**
	 * @return the application_security_group_ids
	 */
	public ArrayList<String> getApplication_security_group_ids() {
		return application_security_group_ids;
	}

	/**
	 * @param application_security_group_ids the application_security_group_ids to set
	 */
	public void setApplication_security_group_ids(ArrayList<String> application_security_group_ids) {
		this.application_security_group_ids = application_security_group_ids;
	}

	/**
	 * @return the primary
	 */
	public boolean isPrimary() {
		return primary;
	}

	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	private boolean isValid() throws RequiredArgumentException{
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name config is required for the ip_configuration object");
		}
		if(private_ip_address_version != null && private_ip_address_version.equals("IPv4")){
			if(subnet_id == null || subnet_id.equals("")){
				throw new RequiredArgumentException("subnet_id config is required for the ip_configuration object when private_ip_address_version is IPv4");
			}
		}
		if(private_ip_address_allocation == null || private_ip_address_allocation.equals("")){
			throw new RequiredArgumentException("private_ip_address_allocation config is required for the ip_configuration object");
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
			System.out.println(headIndent+"hi");
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		try {
			isValid();
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		}
	
		String block = headIndent + "ip_configuration { \n";
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "private_ip_address_allocation = \"" + private_ip_address_allocation + "\"\n";
		
		if(subnet_id != null && !subnet_id.equals("")){
			block += elementIndent + "subnet_id = \"" + subnet_id + "\"\n";
		}
		if(private_ip_address != null && !private_ip_address.equals("")){
			block += elementIndent + "private_ip_address = \"" + private_ip_address + "\"\n";
		}
		if(private_ip_address_version != null && !private_ip_address_version.isEmpty()){
			block += elementIndent + "private_ip_address_version = \"" + private_ip_address_version + "\"\n";
		}
		if(public_ip_address_id != null && !public_ip_address_id.isEmpty()){
			block += elementIndent + "public_ip_address_id = \"" + public_ip_address_id + "\"\n";
		}
		if(application_gateway_backend_address_pools_ids != null && !application_gateway_backend_address_pools_ids.isEmpty()){
			block += elementIndent + "application_gateway_backend_address_pools_ids = \"" + application_gateway_backend_address_pools_ids + "\"\n";
		}
		if(load_balancer_backend_address_pools_ids != null && !load_balancer_backend_address_pools_ids.isEmpty()){
			block += elementIndent + "load_balancer_backend_address_pools_ids = \"" + load_balancer_backend_address_pools_ids + "\"\n";
		}
		if(load_balancer_inbound_nat_rules_ids != null && !load_balancer_inbound_nat_rules_ids.isEmpty()){
			block += elementIndent + "load_balancer_inbound_nat_rules_ids = \"" + load_balancer_inbound_nat_rules_ids + "\"\n";
		}
		if(application_security_group_ids != null && !application_security_group_ids.isEmpty()){
			block += elementIndent + "application_security_group_ids = \"" + application_security_group_ids + "\"\n";
		}
		if(primary){
			block += elementIndent + "primary =\" " + primary + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
