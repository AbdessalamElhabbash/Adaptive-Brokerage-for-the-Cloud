package terraform.resources.aws;

import java.util.ArrayList;

import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;
import terraform.resources.Timeouts;

public class AwsSecurityGroup extends Resource{
	public AwsSecurityGroup(String resourceName) {
		super(resourceName);
	}
	private String name;
	private String name_prefix;
	private String description;
	private ArrayList<Ingress> ingress;
	private ArrayList<Egress> egress;
	private boolean revoke_rules_on_delete = false;
	private String vpc_id;
	private Tags tags;
	private Timeouts timeouts;
	
	
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
	 * @return the name_prefix
	 */
	public String getName_prefix() {
		return name_prefix;
	}
	/**
	 * @param name_prefix the name_prefix to set
	 */
	public void setName_prefix(String name_prefix) {
		this.name_prefix = name_prefix;
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
	 * @return the ingress
	 */
	public ArrayList<Ingress> getIngress() {
		return ingress;
	}
	/**
	 * @param ingress the ingress to set
	 */
	public void setIngress(ArrayList<Ingress> ingress) {
		this.ingress = ingress;
	}
	/**
	 * @return the egress
	 */
	public ArrayList<Egress> getEgress() {
		return egress;
	}
	/**
	 * @param egress the egress to set
	 */
	public void setEgress(ArrayList<Egress> egress) {
		this.egress = egress;
	}
	/**
	 * @return the revoke_rules_on_delete
	 */
	public boolean isRevoke_rules_on_delete() {
		return revoke_rules_on_delete;
	}
	/**
	 * @param revoke_rules_on_delete the revoke_rules_on_delete to set
	 */
	public void setRevoke_rules_on_delete(boolean revoke_rules_on_delete) {
		this.revoke_rules_on_delete = revoke_rules_on_delete;
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
	/**
	 * @return the timeouts
	 */
	public Timeouts getTimeouts() {
		return timeouts;
	}
	/**
	 * @param timeouts the timeouts to set
	 */
	public void setTimeouts(Timeouts timeouts) {
		this.timeouts = timeouts;
	}
	@Override
	public String getBlock(int level) {
		
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block = headIndent + "resource \"aws_security_group\" \"" + this.resourceName + "\" { \n";
		
		if(name != null && !name.equals("")){
			block += elementIndent + "name = \"" + name + "\"\n";
		}
		if(name_prefix != null && !name_prefix.equals("")){
			block += elementIndent + "name_prefix = \"" + name_prefix + "\"\n";
		}
		if(description != null && !description.equals("")){
			block += elementIndent + "description = \"" + description + "\"\n";
		}
		if(revoke_rules_on_delete){
			block += elementIndent + "revoke_rules_on_delete = " + revoke_rules_on_delete + "\n";
		}
		if(vpc_id != null && !vpc_id.equals("")){
			block += elementIndent + "vpc_id = \"" + vpc_id + "\"\n";
		}
		if(ingress != null){
			for(Ingress ing : ingress){
				block += ing.getBlock(level+1);
			}
		}
		if(egress != null){
			for(Egress eg : egress){
				block += eg.getBlock(level+1);
			}
		}
		if(tags != null){
			block += tags.getBlock(level+1);
		}
		if(timeouts != null){
			block += timeouts.getBlock(level+1);
		}
		block += headIndent + "}\n";
		return block;
	}
	/**
	 * returns interpolated reference to the id
	 * @return
	 */
	public String getIDReference() {
		return "${aws_security_group." + resourceName + ".id}";
	}
}
