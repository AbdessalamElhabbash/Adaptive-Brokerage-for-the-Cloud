package terraform.resources.aws.vpcresources;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AwsVpc extends Resource{

	private String cidr_block; //required
	private String instance_tenancy;
	private boolean enable_dns_support; //default true
	private boolean enable_dns_hostnames; //default false
	private boolean enable_classiclink; //default false
	private boolean enable_classiclink_dns_support;
	private boolean assign_generated_ipv6_cidr_block; //default false
	private Tags tags;
	
	public AwsVpc(String resourceName) {
		super(resourceName);
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
	 * @return the instance_tenancy
	 */
	public String getInstance_tenancy() {
		return instance_tenancy;
	}



	/**
	 * @param instance_tenancy the instance_tenancy to set
	 */
	public void setInstance_tenancy(String instance_tenancy) {
		this.instance_tenancy = instance_tenancy;
	}



	/**
	 * @return the enable_dns_support
	 */
	public boolean isEnable_dns_support() {
		return enable_dns_support;
	}



	/**
	 * @param enable_dns_support the enable_dns_support to set
	 */
	public void setEnable_dns_support(boolean enable_dns_support) {
		this.enable_dns_support = enable_dns_support;
	}



	/**
	 * @return the enable_dns_hostnames
	 */
	public boolean isEnable_dns_hostnames() {
		return enable_dns_hostnames;
	}



	/**
	 * @param enable_dns_hostnames the enable_dns_hostnames to set
	 */
	public void setEnable_dns_hostnames(boolean enable_dns_hostnames) {
		this.enable_dns_hostnames = enable_dns_hostnames;
	}



	/**
	 * @return the enable_classiclink
	 */
	public boolean isEnable_classiclink() {
		return enable_classiclink;
	}



	/**
	 * @param enable_classiclink the enable_classiclink to set
	 */
	public void setEnable_classiclink(boolean enable_classiclink) {
		this.enable_classiclink = enable_classiclink;
	}



	/**
	 * @return the enable_classiclink_dns_support
	 */
	public boolean isEnable_classiclink_dns_support() {
		return enable_classiclink_dns_support;
	}



	/**
	 * @param enable_classiclink_dns_support the enable_classiclink_dns_support to set
	 */
	public void setEnable_classiclink_dns_support(boolean enable_classiclink_dns_support) {
		this.enable_classiclink_dns_support = enable_classiclink_dns_support;
	}



	/**
	 * @return the assign_generated_ipv6_cidr_block
	 */
	public boolean isAssign_generated_ipv6_cidr_block() {
		return assign_generated_ipv6_cidr_block;
	}



	/**
	 * @param assign_generated_ipv6_cidr_block the assign_generated_ipv6_cidr_block to set
	 */
	public void setAssign_generated_ipv6_cidr_block(boolean assign_generated_ipv6_cidr_block) {
		this.assign_generated_ipv6_cidr_block = assign_generated_ipv6_cidr_block;
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
			throw new RequiredArgumentException("Config cidr_block is required for the aws_vpc object");
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
		
		String block = headIndent + "resource aws_vpc \"" + this.resourceName + "\" { \n";
		block += elementIndent + "cidr_block = \"" + cidr_block + "\"\n";

		if(instance_tenancy != null && !instance_tenancy.equals("")){
			block += elementIndent + "instance_tenancy = \"" + instance_tenancy + "\"\n";
		}
		if(!enable_dns_support){
			block += elementIndent + "enable_dns_support = " + enable_dns_support + "\n";
		}
		if(enable_dns_hostnames){
			block += elementIndent + "enable_dns_hostnames = " + enable_dns_hostnames + "\n";
		}
		if(enable_classiclink){
			block += elementIndent + "enable_classiclink = " + enable_classiclink + "\n";
		}
		if(enable_classiclink_dns_support){
			block += elementIndent + "enable_classiclink_dns_support = " + enable_classiclink_dns_support + "\n";
		}
		if(assign_generated_ipv6_cidr_block){
			block += elementIndent + "assign_generated_ipv6_cidr_block = " + assign_generated_ipv6_cidr_block + "\n";
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
		return "${aws_vpc." + resourceName + ".id}";
	}
}
