package terraform.resources.aws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.TerraformObject;
import terraform.provider.Constants;

public class Egress extends TerraformObject{
	private ArrayList<String> cidr_blocks;
	private ArrayList<String> ipv6_cidr_blocks;
	private ArrayList<String> prefix_list_ids;
	private int from_port = -1; //required
	private String protocol; //(Required) The protocol. If you select a protocol of "-1" (semantically equivalent to "all", which is not a valid value here), you must specify a "from_port" and "to_port" equal to 0. If not icmp, tcp, udp, or "-1" use the protocol number
	private ArrayList<String> security_groups; 
	private boolean self = false;
	private int to_port = -1; //required
	private String description;
	public static final Set<String> PROTOCOLS;

	static {
		PROTOCOLS = new HashSet<String>();
		PROTOCOLS.add("tcp");	
		PROTOCOLS.add("udp");	
		PROTOCOLS.add("icmp");	
		PROTOCOLS.add("all");	
	}
	
	/**
	 * @return the cidr_blocks
	 */
	public ArrayList<String> getCidr_blocks() {
		return cidr_blocks;
	}

	/**
	 * @param cidr_blocks the cidr_blocks to set
	 */
	public void setCidr_blocks(ArrayList<String> cidr_blocks) {
		this.cidr_blocks = cidr_blocks;
	}

	/**
	 * @return the ipv6_cidr_blocks
	 */
	public ArrayList<String> getIpv6_cidr_blocks() {
		return ipv6_cidr_blocks;
	}

	/**
	 * @param ipv6_cidr_blocks the ipv6_cidr_blocks to set
	 */
	public void setIpv6_cidr_blocks(ArrayList<String> ipv6_cidr_blocks) {
		this.ipv6_cidr_blocks = ipv6_cidr_blocks;
	}

	/**
	 * @return the prefix_list_ids
	 */
	public ArrayList<String> getPrefix_list_ids() {
		return prefix_list_ids;
	}

	/**
	 * @param prefix_list_ids the prefix_list_ids to set
	 */
	public void setPrefix_list_ids(ArrayList<String> prefix_list_ids) {
		this.prefix_list_ids = prefix_list_ids;
	}

	/**
	 * @return the from_port
	 */
	public int getFrom_port() {
		return from_port;
	}

	/**
	 * @param from_port the from_port to set
	 */
	public void setFrom_port(int from_port) {
		this.from_port = from_port;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 * @throws InvalidTypeException 
	 */
	public void setProtocol(String protocol) throws InvalidTypeException {
		if(!PROTOCOLS.contains(protocol)){
			try{
				int  protcolNum = Integer.parseInt(protocol);
				if(protcolNum <1 || protcolNum >255){
					throw new InvalidTypeException("Possible values are tcp,udp,icmp,all, or protocol number (1-255)");
				}
			}catch(NumberFormatException ex){
				throw new InvalidTypeException("Possible values are tcp,udp,icmp,all, or protocol number (1-255)");
			}
		}
		if(protocol.equals("all")){
			from_port = 0;
			to_port = 0;
			this.protocol = "-1"; 
		}else{
			this.protocol = protocol;
		}
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
	 * @return the self
	 */
	public boolean isSelf() {
		return self;
	}

	/**
	 * @param self the self to set
	 */
	public void setSelf(boolean self) {
		this.self = self;
	}

	/**
	 * @return the to_port
	 */
	public int getTo_port() {
		return to_port;
	}

	/**
	 * @param to_port the to_port to set
	 */
	public void setTo_port(int to_port) {
		this.to_port = to_port;
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
	 * @return the protocols
	 */
	public static Set<String> getProtocols() {
		return PROTOCOLS;
	}

	private boolean isValid() throws RequiredArgumentException{
		if(from_port < 0){
			throw new RequiredArgumentException("from_port must be specified for the Ingress object");
		}
		if(to_port < 0){
			throw new RequiredArgumentException("to_port must be specified for the Ingress object");
		}
		if(protocol ==null || protocol.equals("")){
			throw new RequiredArgumentException("protocol must be specified for the Ingress object");
		}
		return true;
	}
	
	public String getBlock(int level){
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
		
		String block = headIndent + "egress  { \n";
		if(cidr_blocks != null && !cidr_blocks.equals("")){
			block += elementIndent + "cidr_blocks = " + this.appendDoubleQuotes(cidr_blocks) + "\n";
		}
		if(ipv6_cidr_blocks != null && !ipv6_cidr_blocks.equals("")){
			block += elementIndent + "ipv6_cidr_blocks = " + this.appendDoubleQuotes(ipv6_cidr_blocks);
		}
		if(prefix_list_ids != null && !prefix_list_ids.equals("")){
			block += elementIndent + "prefix_list_ids = " + this.appendDoubleQuotes(prefix_list_ids);
		}
		block += elementIndent + "from_port = " + from_port + "\n";
		block += elementIndent + "to_port  = " + to_port + "\n";
		block += elementIndent + "protocol = \"" + protocol + "\"\n";
		
		if(protocol != null && protocol.equals("")){
			block += elementIndent + "protocol = \"" + protocol + "\"\n";
		}
		if(security_groups != null && !security_groups.equals("")){
			block += elementIndent + security_groups + this.appendDoubleQuotes(security_groups) + "\n";
		}
		if(self){
			block += elementIndent + "self  = " + self + "\n";
		}
		if(description != null && !description.equals("")){
			block += elementIndent + "description = \"" + description + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}