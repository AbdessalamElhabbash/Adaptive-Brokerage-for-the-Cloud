package terraform.resources;

import terraform.provider.Constants;

public class HabitatProvisioner extends Provisioner{
	private String version;
	private boolean use_sudo;
	private String service_type;
	private String service_name;
	private String peer;
	private boolean permanent_peer; 
	private String listen_gossip;
	private String listen_http;
	private String ring_key;
	private String ring_key_content;
	private String url;
	private String channel;
	private String events;
	private String override_name;
	private String organization;
	private String builder_auth_token;
		
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}



	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}



	/**
	 * @return the use_sudo
	 */
	public boolean isUse_sudo() {
		return use_sudo;
	}



	/**
	 * @param use_sudo the use_sudo to set
	 */
	public void setUse_sudo(boolean use_sudo) {
		this.use_sudo = use_sudo;
	}



	/**
	 * @return the service_type
	 */
	public String getService_type() {
		return service_type;
	}



	/**
	 * @param service_type the service_type to set
	 */
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}



	/**
	 * @return the service_name
	 */
	public String getService_name() {
		return service_name;
	}



	/**
	 * @param service_name the service_name to set
	 */
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}



	/**
	 * @return the peer
	 */
	public String getPeer() {
		return peer;
	}



	/**
	 * @param peer the peer to set
	 */
	public void setPeer(String peer) {
		this.peer = peer;
	}



	/**
	 * @return the permanent_peer
	 */
	public boolean isPermanent_peer() {
		return permanent_peer;
	}



	/**
	 * @param permanent_peer the permanent_peer to set
	 */
	public void setPermanent_peer(boolean permanent_peer) {
		this.permanent_peer = permanent_peer;
	}



	/**
	 * @return the listen_gossip
	 */
	public String getListen_gossip() {
		return listen_gossip;
	}



	/**
	 * @param listen_gossip the listen_gossip to set
	 */
	public void setListen_gossip(String listen_gossip) {
		this.listen_gossip = listen_gossip;
	}



	/**
	 * @return the listen_http
	 */
	public String getListen_http() {
		return listen_http;
	}



	/**
	 * @param listen_http the listen_http to set
	 */
	public void setListen_http(String listen_http) {
		this.listen_http = listen_http;
	}



	/**
	 * @return the ring_key
	 */
	public String getRing_key() {
		return ring_key;
	}



	/**
	 * @param ring_key the ring_key to set
	 */
	public void setRing_key(String ring_key) {
		this.ring_key = ring_key;
	}



	/**
	 * @return the ring_key_content
	 */
	public String getRing_key_content() {
		return ring_key_content;
	}



	/**
	 * @param ring_key_content the ring_key_content to set
	 */
	public void setRing_key_content(String ring_key_content) {
		this.ring_key_content = ring_key_content;
	}



	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}



	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}



	/**
	 * @return the events
	 */
	public String getEvents() {
		return events;
	}



	/**
	 * @param events the events to set
	 */
	public void setEvents(String events) {
		this.events = events;
	}



	/**
	 * @return the override_name
	 */
	public String getOverride_name() {
		return override_name;
	}



	/**
	 * @param override_name the override_name to set
	 */
	public void setOverride_name(String override_name) {
		this.override_name = override_name;
	}



	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}



	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}



	/**
	 * @return the builder_auth_token
	 */
	public String getBuilder_auth_token() {
		return builder_auth_token;
	}



	/**
	 * @param builder_auth_token the builder_auth_token to set
	 */
	public void setBuilder_auth_token(String builder_auth_token) {
		this.builder_auth_token = builder_auth_token;
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
		String elementIndent= headIndent +  Constants.indent; 
		/*******************************************************/
	
		String block =   headIndent + "provisioner \"habitat\" { \n";
		if (version != null && !version.equals("")) {
			block += elementIndent + "version = " + version + "\n";
		}
		if (use_sudo) {
			block += elementIndent + "use_sudo = " + use_sudo + "\n";
		}
		
		if (service_type != null && !service_type.equals("")) {
			block += elementIndent + "service_type = " + service_type + "\n";
		}
		if (service_name != null && !service_name.equals("")) {
			block += elementIndent + "service_name = " + service_name + "\n";
		}
		if (peer != null && !peer.equals("")) {
			block += elementIndent + "peer = " + peer + "\n";
		}
		if (permanent_peer) {
			block += elementIndent + "permanent_peer = " + permanent_peer + "\n";
		}
		if (peer != null && !peer.equals("")) {
			block += elementIndent + "peer = " + peer + "\n";
		}
		if (listen_gossip != null && !listen_gossip.equals("")) {
			block += elementIndent + "listen_gossip" + listen_gossip + "\n";
		}
		if (listen_http != null && !listen_http.equals("")) {
			block += elementIndent + "listen_http = " + listen_http + "\n";
		}
		if (ring_key != null && !ring_key.equals("")) {
			block += elementIndent + "ring_key = " + ring_key + "\n";
		}
		if (ring_key_content != null && !ring_key_content.equals("")) {
			block += elementIndent + "ring_key_content = " + ring_key_content + "\n";
		}
		if (url != null && !url.equals("")) {
			block += elementIndent + "url = " + url + "\n";
		}
		if (channel != null && !channel.equals("")) {
			block += elementIndent + "channel" + channel + "\n";
		}
		if (events != null && !events.equals("")) {
			block += elementIndent + "events = " + events + "\n";
		}
		if (override_name != null && !override_name.equals("")) {
			block += elementIndent + "override_name = " + override_name + "\n";
		}
		if (organization != null && !organization.equals("")) {
			block += elementIndent + "organization = " + organization + "\n";
		}
		if (builder_auth_token != null && !builder_auth_token.equals("")) {
			block += elementIndent + "builder_auth_token = " + builder_auth_token + "\n";
		}		
		block += headIndent + "}\n";
		return block;
	}
}
