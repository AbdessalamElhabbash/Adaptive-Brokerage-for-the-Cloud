package terraform.resources.aws;

import java.util.ArrayList;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Provisioner;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AwsInstance extends Resource{
	
	public AwsInstance(String resourceName) {
		super(resourceName);
	}

	private String ami; //required
	private String availability_zone;
	private String placement_group;
	private String tenancy;
	private String host_id;
	private int cpu_core_count; 
	private int cpu_threads_per_core;
	private boolean ebs_optimized; 
	private boolean disable_api_termination; 
	private String instance_initiated_shutdown_behavior;
	private String instance_type; //required
	private String key_name;
	private String get_password_data;
	private boolean monitoring;
	private ArrayList<String> security_groups;
	private ArrayList<String> vpc_security_group_ids;
	private String subnet_id;
	private boolean associate_public_ip_address; 
	private String private_ip;
	private boolean source_dest_check = true; //default true
	private String user_data;
	private String user_data_base64;
	private String iam_instance_profile;
	private int ipv6_address_count;
	private ArrayList<String> ipv6_addresses; 
	private Tags tags; 
	private VolumeTags volumeTags;
	private RootBlockDevice rootBlockDevice;
	private EbsBlockDevice ebsBlockDevice;
	private EphemeralBlockDevice ephemeralBlockDevice;
	private NetworkInterface network_interface;
	private CreditSpecification credit_specification;
	
	
	
	/**
	 * @return the ami
	 */
	public String getAmi() {
		return ami;
	}

	/**
	 * @param ami the ami to set
	 */
	public void setAmi(String ami) {
		this.ami = ami;
	}

	/**
	 * @return the availability_zone
	 */
	public String getAvailability_zone() {
		return availability_zone;
	}

	/**
	 * @param availability_zone the availability_zone to set
	 */
	public void setAvailability_zone(String availability_zone) {
		this.availability_zone = availability_zone;
	}

	/**
	 * @return the placement_group
	 */
	public String getPlacement_group() {
		return placement_group;
	}

	/**
	 * @param placement_group the placement_group to set
	 */
	public void setPlacement_group(String placement_group) {
		this.placement_group = placement_group;
	}

	/**
	 * @return the tenancy
	 */
	public String getTenancy() {
		return tenancy;
	}

	/**
	 * @param tenancy the tenancy to set
	 */
	public void setTenancy(String tenancy) {
		this.tenancy = tenancy;
	}

	/**
	 * @return the host_id
	 */
	public String getHost_id() {
		return host_id;
	}

	/**
	 * @param host_id the host_id to set
	 */
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	/**
	 * @return the cpu_core_count
	 */
	public int getCpu_core_count() {
		return cpu_core_count;
	}

	/**
	 * @param cpu_core_count the cpu_core_count to set
	 */
	public void setCpu_core_count(int cpu_core_count) {
		this.cpu_core_count = cpu_core_count;
	}

	/**
	 * @return the cpu_threads_per_core
	 */
	public int getCpu_threads_per_core() {
		return cpu_threads_per_core;
	}

	/**
	 * @param cpu_threads_per_core the cpu_threads_per_core to set
	 */
	public void setCpu_threads_per_core(int cpu_threads_per_core) {
		this.cpu_threads_per_core = cpu_threads_per_core;
	}

	/**
	 * @return the ebs_optimized
	 */
	public boolean isEbs_optimized() {
		return ebs_optimized;
	}

	/**
	 * @param ebs_optimized the ebs_optimized to set
	 */
	public void setEbs_optimized(boolean ebs_optimized) {
		this.ebs_optimized = ebs_optimized;
	}

	/**
	 * @return the disable_api_termination
	 */
	public boolean isDisable_api_termination() {
		return disable_api_termination;
	}

	/**
	 * @param disable_api_termination the disable_api_termination to set
	 */
	public void setDisable_api_termination(boolean disable_api_termination) {
		this.disable_api_termination = disable_api_termination;
	}

	/**
	 * @return the instance_initiated_shutdown_behavior
	 */
	public String getInstance_initiated_shutdown_behavior() {
		return instance_initiated_shutdown_behavior;
	}

	/**
	 * @param instance_initiated_shutdown_behavior the instance_initiated_shutdown_behavior to set
	 */
	public void setInstance_initiated_shutdown_behavior(String instance_initiated_shutdown_behavior) {
		this.instance_initiated_shutdown_behavior = instance_initiated_shutdown_behavior;
	}

	/**
	 * @return the instance_type
	 */
	public String getInstance_type() {
		return instance_type;
	}

	/**
	 * @param instance_type the instance_type to set
	 */
	public void setInstance_type(String instance_type) {
		this.instance_type = instance_type;
	}

	/**
	 * @return the key_name
	 */
	public String getKey_name() {
		return key_name;
	}

	/**
	 * @param key_name the key_name to set
	 */
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	/**
	 * @return the get_password_data
	 */
	public String getGet_password_data() {
		return get_password_data;
	}

	/**
	 * @param get_password_data the get_password_data to set
	 */
	public void setGet_password_data(String get_password_data) {
		this.get_password_data = get_password_data;
	}

	/**
	 * @return the monitoring
	 */
	public boolean isMonitoring() {
		return monitoring;
	}

	/**
	 * @param monitoring the monitoring to set
	 */
	public void setMonitoring(boolean monitoring) {
		this.monitoring = monitoring;
	}

	/**
	 * @return the security_groups
	 */
	public ArrayList<String> getSecurity_groups() {
		return security_groups;
	}

	/**
	 * @param security_groups the security_groups to set
	 */
	public void setSecurity_groups(ArrayList<String> security_groups) {
		this.security_groups = security_groups;
	}

	/**
	 * @return the vpc_security_group_ids
	 */
	public ArrayList<String> getVpc_security_group_ids() {
		return vpc_security_group_ids;
	}

	/**
	 * @param vpc_security_group_ids the vpc_security_group_ids to set
	 */
	public void setVpc_security_group_ids(ArrayList<String> vpc_security_group_ids) {
		this.vpc_security_group_ids = vpc_security_group_ids;
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
	 * @return the associate_public_ip_address
	 */
	public boolean isAssociate_public_ip_address() {
		return associate_public_ip_address;
	}

	/**
	 * @param associate_public_ip_address the associate_public_ip_address to set
	 */
	public void setAssociate_public_ip_address(boolean associate_public_ip_address) {
		this.associate_public_ip_address = associate_public_ip_address;
	}

	/**
	 * @return the private_ip
	 */
	public String getPrivate_ip() {
		return private_ip;
	}

	/**
	 * @param private_ip the private_ip to set
	 */
	public void setPrivate_ip(String private_ip) {
		this.private_ip = private_ip;
	}

	/**
	 * @return the source_dest_check
	 */
	public boolean isSource_dest_check() {
		return source_dest_check;
	}

	/**
	 * @param source_dest_check the source_dest_check to set
	 */
	public void setSource_dest_check(boolean source_dest_check) {
		this.source_dest_check = source_dest_check;
	}

	/**
	 * @return the user_data
	 */
	public String getUser_data() {
		return user_data;
	}

	/**
	 * @param user_data the user_data to set
	 */
	public void setUser_data(String user_data) {
		this.user_data = user_data;
	}

	/**
	 * @return the user_data_base64
	 */
	public String getUser_data_base64() {
		return user_data_base64;
	}

	/**
	 * @param user_data_base64 the user_data_base64 to set
	 */
	public void setUser_data_base64(String user_data_base64) {
		this.user_data_base64 = user_data_base64;
	}

	/**
	 * @return the iam_instance_profile
	 */
	public String getIam_instance_profile() {
		return iam_instance_profile;
	}

	/**
	 * @param iam_instance_profile the iam_instance_profile to set
	 */
	public void setIam_instance_profile(String iam_instance_profile) {
		this.iam_instance_profile = iam_instance_profile;
	}

	/**
	 * @return the ipv6_address_count
	 */
	public int getIpv6_address_count() {
		return ipv6_address_count;
	}

	/**
	 * @param ipv6_address_count the ipv6_address_count to set
	 */
	public void setIpv6_address_count(int ipv6_address_count) {
		this.ipv6_address_count = ipv6_address_count;
	}

	/**
	 * @return the ipv6_addresses
	 */
	public ArrayList<String> getIpv6_addresses() {
		return ipv6_addresses;
	}

	/**
	 * @param ipv6_addresses the ipv6_addresses to set
	 */
	public void setIpv6_addresses(ArrayList<String> ipv6_addresses) {
		this.ipv6_addresses = ipv6_addresses;
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
	 * @return the volumeTags
	 */
	public VolumeTags getVolumeTags() {
		return volumeTags;
	}

	/**
	 * @param volumeTags the volumeTags to set
	 */
	public void setVolumeTags(VolumeTags volumeTags) {
		this.volumeTags = volumeTags;
	}

	/**
	 * @return the rootBlockDevice
	 */
	public RootBlockDevice getRootBlockDevice() {
		return rootBlockDevice;
	}

	/**
	 * @param rootBlockDevice the rootBlockDevice to set
	 */
	public void setRootBlockDevice(RootBlockDevice rootBlockDevice) {
		this.rootBlockDevice = rootBlockDevice;
	}

	/**
	 * @return the ebsBlockDevice
	 */
	public EbsBlockDevice getEbsBlockDevice() {
		return ebsBlockDevice;
	}

	/**
	 * @param ebsBlockDevice the ebsBlockDevice to set
	 */
	public void setEbsBlockDevice(EbsBlockDevice ebsBlockDevice) {
		this.ebsBlockDevice = ebsBlockDevice;
	}

	/**
	 * @return the ephemeralBlockDevice
	 */
	public EphemeralBlockDevice getEphemeralBlockDevice() {
		return ephemeralBlockDevice;
	}

	/**
	 * @param ephemeralBlockDevice the ephemeralBlockDevice to set
	 */
	public void setEphemeralBlockDevice(EphemeralBlockDevice ephemeralBlockDevice) {
		this.ephemeralBlockDevice = ephemeralBlockDevice;
	}

	/**
	 * @return the network_interface
	 */
	public NetworkInterface getNetwork_interface() {
		return network_interface;
	}

	/**
	 * @param network_interface the network_interface to set
	 */
	public void setNetwork_interface(NetworkInterface network_interface) {
		this.network_interface = network_interface;
	}

	/**
	 * @return the credit_specification
	 */
	public CreditSpecification getCredit_specification() {
		return credit_specification;
	}

	/**
	 * @param credit_specification the credit_specification to set
	 */
	public void setCredit_specification(CreditSpecification credit_specification) {
		this.credit_specification = credit_specification;
	}

	/**
	 * Checks if the required attributes are set
	 * @return
	 */
	public boolean isValid() throws RequiredArgumentException{
		if(ami == null || ami.equals("")){
			throw new RequiredArgumentException("ami config is required for aws_instance");
		}
		if(instance_type == null || instance_type.equals("")){
			throw new RequiredArgumentException("instance_type config is required for aws_instance");
		}
		if(resourceName == null || resourceName.equals("")){
			throw new RequiredArgumentException("resourceName config is required for aws_instance");
		}
		return true;
	}
	
	@Override
	public String getBlock(int level) {
		try {
			isValid();
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		}
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block = headIndent + "resource aws_instance \"" + this.resourceName + "\" { \n";
		
		block += elementIndent + "ami = \"" + ami + "\"\n";
		if (count > 0) {
			block += elementIndent + "count = " + count + "\n";
		}
		if (cpu_core_count > 0) {
			block += elementIndent + "cpu_core_count = " + cpu_core_count + "\n";
		}
		if (cpu_threads_per_core > 0) {
			block += elementIndent + "cpu_threads_per_core = " + cpu_threads_per_core + "\n";
		}
		
		if (provider != null && !provider.equals("")) {
			block += elementIndent + "provider = \"" + provider + "\"\n";
		}
		if (availability_zone != null && !availability_zone.equals("")) {
			block += elementIndent + "availability_zone = \"" + availability_zone + "\"\n";
		}
		if (placement_group != null && !placement_group.equals("")) {
			block += elementIndent + "placement_group = \"" + placement_group + "\"\n";
		}
		if (tenancy != null && !tenancy.equals("")) {
			block += elementIndent + "tenancy = \"" + tenancy + "\"\n";
		}
		if (host_id != null && !host_id.equals("")) {
			block += elementIndent + "host_id = \"" + host_id + "\"\n";
		}
		if (instance_initiated_shutdown_behavior != null && !instance_initiated_shutdown_behavior.equals("")) {
			block += elementIndent + "instance_initiated_shutdown_behavior = \"" + instance_initiated_shutdown_behavior + "\"\n";
		}
		if (instance_type != null && !instance_type.equals("")) {
			block += elementIndent + "instance_type = \"" + instance_type + "\"\n";
		}
		if (key_name != null && !key_name.equals("")) {
			block += elementIndent + "key_name = \"" + key_name + "\"\n";
		}
		if (get_password_data != null && !get_password_data.equals("")) {
			block += elementIndent + "get_password_data = \"" + get_password_data + "\"\n";
		}
		if (subnet_id != null && !subnet_id.equals("")) {
			block += elementIndent + "subnet_id = \"" + subnet_id + "\"\n";
		}
		if (private_ip != null && !private_ip.equals("")) {
			block += elementIndent + "private_ip = \"" + private_ip + "\"\n";
		}
		if (user_data != null && !user_data.equals("")) {
			block += elementIndent + "suuser_databnet_id = \"" + user_data + "\"\n";
		}
		if (user_data_base64 != null && !user_data_base64.equals("")) {
			block += elementIndent + "user_data_base64 = \"" + user_data_base64 + "\"\n";
		}
		if (iam_instance_profile != null && !iam_instance_profile.equals("")) {
			block += elementIndent + "iam_instance_profile = \"" + iam_instance_profile + "\"\n";
		}		
		if(ipv6_address_count > 0){
			block += elementIndent + "ipv6_address_count = " + ipv6_address_count + "\n";
		}
		if(depends_on!= null && !depends_on.isEmpty()){
			block += elementIndent + "depends_on = " + appendDoubleQuotes(depends_on) + "\n";
		}
		if(provisioners != null && !provisioners.isEmpty()){
			for(Provisioner provisioner : provisioners){
				block += provisioner.getBlock(level+1) + "\n";
			}
		}
		if(security_groups != null && !security_groups.isEmpty()){
			block += elementIndent + "security_groups = " + appendDoubleQuotes(security_groups) + "\n";
		}
		if(vpc_security_group_ids != null && !vpc_security_group_ids.isEmpty()){
			block += elementIndent + "vpc_security_group_ids = " + appendDoubleQuotes(vpc_security_group_ids) + "\n";
		}
		if(ipv6_addresses != null && !ipv6_addresses.isEmpty()){
			block += elementIndent + "ipv6_addresses = " + appendDoubleQuotes(ipv6_addresses) + "\n";
		}
		
		if(ebs_optimized){
			block += elementIndent + "ebs_optimized = " + ebs_optimized + "\n";
		}
		if(disable_api_termination){
			block += elementIndent + "disable_api_termination = " + disable_api_termination + "\n";
		}
		if(monitoring){
			block += elementIndent + "monitoring = " + monitoring + "\n";
		}
		if(associate_public_ip_address){
			block += elementIndent + "associate_public_ip_address = " + associate_public_ip_address + "\n";
		}
		if(!source_dest_check){
			block += elementIndent + "source_dest_check = " + source_dest_check + "\n";
		}
		if(lifecycle != null){
			block += lifecycle.getBlock(level+1) + "\n";
		}
		if(timeouts != null){
			block += timeouts.getBlock(level+1) + "\n";
		}
		if(connection != null){
			block += connection.getBlock(level+1) + "\n";
		}
		if(tags != null){
			block += tags.getBlock(level+1) + "\n";
		}
		if(volumeTags != null){
			block += volumeTags.getBlock(level+1) + "\n";
		}
		if(rootBlockDevice != null){
			block += rootBlockDevice.getBlock(level+1) + "\n";
		}
		if(ebsBlockDevice != null){
			block += ebsBlockDevice.getBlock(level+1) + "\n";
		}
		if(ephemeralBlockDevice != null){
			block += ephemeralBlockDevice.getBlock(level+1) + "\n";
		}
		if(network_interface != null){
			block += network_interface.getBlock(level+1) + "\n";
		}
		if(credit_specification != null){
			block += credit_specification.getBlock(level+1) + "\n";
		}
		block += headIndent + "}\n";
		return block;
	}
	
}
