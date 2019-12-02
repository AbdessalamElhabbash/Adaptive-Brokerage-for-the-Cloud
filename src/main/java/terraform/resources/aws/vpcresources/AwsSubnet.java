package terraform.resources.aws.vpcresources;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AwsSubnet extends Resource{

	private String availability_zone;
	private String availability_zone_id;
	private String cidr_block; //required
	private String ipv6_cidr_block;
	private boolean map_public_ip_on_launch; //default false
	private boolean assign_ipv6_address_on_creation; //default false
	private String vpc_id; //required
	private Tags tags;
	
	public AwsSubnet(String resourceName) {
		super(resourceName);
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
	 * @return the availability_zone_id
	 */
	public String getAvailability_zone_id() {
		return availability_zone_id;
	}


	/**
	 * @param availability_zone_id the availability_zone_id to set
	 */
	public void setAvailability_zone_id(String availability_zone_id) {
		this.availability_zone_id = availability_zone_id;
	}


	/**
	 * @return the cidr_block
	 */
	public String getCidr_block() {
		return cidr_block;
	}


	/**
	 * @param cidr_block the cidr_block to set
	 */
	public void setCidr_block(String cidr_block) {
		this.cidr_block = cidr_block;
	}


	/**
	 * @return the ipv6_cidr_block
	 */
	public String getIpv6_cidr_block() {
		return ipv6_cidr_block;
	}


	/**
	 * @param ipv6_cidr_block the ipv6_cidr_block to set
	 */
	public void setIpv6_cidr_block(String ipv6_cidr_block) {
		this.ipv6_cidr_block = ipv6_cidr_block;
	}


	/**
	 * @return the map_public_ip_on_launch
	 */
	public boolean isMap_public_ip_on_launch() {
		return map_public_ip_on_launch;
	}


	/**
	 * @param map_public_ip_on_launch the map_public_ip_on_launch to set
	 */
	public void setMap_public_ip_on_launch(boolean map_public_ip_on_launch) {
		this.map_public_ip_on_launch = map_public_ip_on_launch;
	}


	/**
	 * @return the assign_ipv6_address_on_creation
	 */
	public boolean isAssign_ipv6_address_on_creation() {
		return assign_ipv6_address_on_creation;
	}


	/**
	 * @param assign_ipv6_address_on_creation the assign_ipv6_address_on_creation to set
	 */
	public void setAssign_ipv6_address_on_creation(boolean assign_ipv6_address_on_creation) {
		this.assign_ipv6_address_on_creation = assign_ipv6_address_on_creation;
	}


	/**
	 * @return the vpc_id
	 */
	public String getVpc_id() {
		return vpc_id;
	}


	/**
	 * @param vpc_id the vpc_id to set
	 */
	public void setVpc_id(String vpc_id) {
		this.vpc_id = vpc_id;
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


	private boolean isValid() throws RequiredArgumentException{
		if(cidr_block == null || cidr_block.equals("")){
			throw new RequiredArgumentException("Config cidr_block is required for the aws_subnet object");
		}
		if(vpc_id == null || vpc_id.equals("")){
			throw new RequiredArgumentException("Config vpc_id is required for the aws_subnet object");
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
		
		String block = headIndent + "resource aws_subnet \"" + this.resourceName + "\" { \n";
		block += elementIndent + "cidr_block = \"" + cidr_block + "\"\n";
		block += elementIndent + "vpc_id = \"" + vpc_id + "\"\n";

		if(availability_zone != null && !availability_zone.equals("")){
			block += elementIndent + "availability_zone = \"" + availability_zone + "\"\n";
		}
		if(availability_zone_id != null && !availability_zone_id.equals("")){
			block += elementIndent + "availability_zone_id = \"" + availability_zone_id + "\"\n";
		}
		if(ipv6_cidr_block != null && !ipv6_cidr_block.equals("")){
			block += elementIndent + "ipv6_cidr_block = \"" + ipv6_cidr_block + "\"\n";
		}
		if(map_public_ip_on_launch){
			block += elementIndent + "map_public_ip_on_launch = " + map_public_ip_on_launch + "\n";
		}
		if(assign_ipv6_address_on_creation){
			block += elementIndent + "assign_ipv6_address_on_creation = " + assign_ipv6_address_on_creation + "\n";
		}
		if (tags != null) {
			block += tags.getBlock(level+1);
		}
		block += headIndent + "}\n";
		return block;
	}	
	
	/**
	 * returns interpolated reference to the id
	 * @return
	 */
	public String getIDReference() {
		return "${aws_subnet." + resourceName + ".id}";
	}
}
