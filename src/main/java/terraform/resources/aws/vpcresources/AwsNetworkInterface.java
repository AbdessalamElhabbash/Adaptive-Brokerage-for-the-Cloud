package terraform.resources.aws.vpcresources;

import java.util.ArrayList;

import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AwsNetworkInterface extends Resource{

	private String subnet_id; //Required
	private String description; 
	private ArrayList<String> private_ips; 
	private int private_ips_count;
	private ArrayList<String> security_groups;
	private Attachment attachment;
	private boolean source_dest_check;//Default true.
	private Tags tags;
	
	public AwsNetworkInterface(String resourceName) {
		super(resourceName);
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the private_ips
	 */
	public ArrayList<String> getPrivate_ips() {
		return private_ips;
	}



	/**
	 * @param private_ips the private_ips to set
	 */
	public void setPrivate_ips(ArrayList<String> private_ips) {
		this.private_ips = private_ips;
	}



	/**
	 * @return the private_ips_count
	 */
	public int getPrivate_ips_count() {
		return private_ips_count;
	}



	/**
	 * @param private_ips_count the private_ips_count to set
	 */
	public void setPrivate_ips_count(int private_ips_count) {
		this.private_ips_count = private_ips_count;
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
	 * @return the attachment
	 */
	public Attachment getAttachment() {
		return attachment;
	}



	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
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
		if(subnet_id == null || subnet_id.equals("")){
			throw new RequiredArgumentException("Config subnet_id is required for the aws_network_interface object");
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
		
		String block = headIndent + "resource aws_network_interface \"" + this.resourceName + "\" { \n";
		block += elementIndent + "subnet_id = \"" + subnet_id + "\"\n";

		if(description != null && !description.equals("")){
			block += elementIndent + "description = \"" + description + "\"\n";
		}
		if(private_ips_count > 0){
			block += elementIndent + "private_ips_count = \"" + private_ips_count + "\"\n";
		}
		if(source_dest_check){
			block += elementIndent + "source_dest_check = " + source_dest_check + "\n";
		}
		if(private_ips != null && !private_ips.isEmpty()){
			block += elementIndent + "private_ips = " + appendDoubleQuotes(private_ips) + "\n";
		}
		if(security_groups != null && !security_groups.isEmpty()){
			block += elementIndent + "security_groups = " + appendDoubleQuotes(security_groups) + "\n";
		}
		if (attachment != null) {
			block += attachment.getBlock(level+1);
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
		return "${aws_network_interface." + resourceName + ".id}";
	}
}
