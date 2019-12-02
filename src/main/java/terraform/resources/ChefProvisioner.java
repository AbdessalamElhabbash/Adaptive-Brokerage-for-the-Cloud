package terraform.resources;

import java.util.ArrayList;

import terraform.provider.Constants;

public class ChefProvisioner extends Provisioner{
	private String attributes_json; // (Optional)
	private String channel; // (Optional)
	private ArrayList<String> client_options; // (Optional)
	private boolean disable_reporting; // (Optional)
	private String environment; // Optional
	private boolean fetch_chef_certificates; // Optional
	private boolean log_to_file; // (Optional)
	private boolean use_policyfile; // (Optional)
	private String policy_group; // (Optional)
	private String policy_name; // (Optional)
	private String http_proxy; // (Optional)
	private String https_proxy; // (Optional)
	private String named_run_list; // (Optional)
	private ArrayList<String> no_proxy; // (Optional)
	private String node_name; // Required
	private ArrayList<String> ohai_hints; // (Optional)
	private String os_type; // (Optional)
	private boolean prevent_sudo; // (Optional)
	private boolean recreate_client; // (Optional)
	private ArrayList<String> run_list; // (Optional)
	private String secret_key; // (Optional)
	private String server_url; // Required
	private boolean skip_install; // (Optional)
	private boolean skip_register; // Optional
	private String ssl_verify_mode; // Optional
	private String user_name; // Required
	private String user_key; // (Optional)
	private String vault_json; // (Optional)
	private String version; // (Optional)

	/**
	 * @return the attributes_json
	 */
	public String getAttributes_json() {
		return attributes_json;
	}

	/**
	 * @param attributes_json
	 *            the attributes_json to set
	 */
	public void setAttributes_json(String attributes_json) {
		this.attributes_json = attributes_json;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the client_options
	 */
	public ArrayList<String> getClient_options() {
		return client_options;
	}

	/**
	 * @param client_options
	 *            the client_options to set
	 */
	public void setClient_options(ArrayList<String> client_options) {
		this.client_options = client_options;
	}

	/**
	 * @return the disable_reporting
	 */
	public boolean isDisable_reporting() {
		return disable_reporting;
	}

	/**
	 * @param disable_reporting
	 *            the disable_reporting to set
	 */
	public void setDisable_reporting(boolean disable_reporting) {
		this.disable_reporting = disable_reporting;
	}

	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment
	 *            the environment to set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * @return the fetch_chef_certificates
	 */
	public boolean isFetch_chef_certificates() {
		return fetch_chef_certificates;
	}

	/**
	 * @param fetch_chef_certificates
	 *            the fetch_chef_certificates to set
	 */
	public void setFetch_chef_certificates(boolean fetch_chef_certificates) {
		this.fetch_chef_certificates = fetch_chef_certificates;
	}

	/**
	 * @return the log_to_file
	 */
	public boolean isLog_to_file() {
		return log_to_file;
	}

	/**
	 * @param log_to_file
	 *            the log_to_file to set
	 */
	public void setLog_to_file(boolean log_to_file) {
		this.log_to_file = log_to_file;
	}

	/**
	 * @return the use_policyfile
	 */
	public boolean isUse_policyfile() {
		return use_policyfile;
	}

	/**
	 * @param use_policyfile
	 *            the use_policyfile to set
	 */
	public void setUse_policyfile(boolean use_policyfile) {
		this.use_policyfile = use_policyfile;
	}

	/**
	 * @return the policy_group
	 */
	public String getPolicy_group() {
		return policy_group;
	}

	/**
	 * @param policy_group
	 *            the policy_group to set
	 */
	public void setPolicy_group(String policy_group) {
		this.policy_group = policy_group;
	}

	/**
	 * @return the policy_name
	 */
	public String getPolicy_name() {
		return policy_name;
	}

	/**
	 * @param policy_name
	 *            the policy_name to set
	 */
	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}

	/**
	 * @return the http_proxy
	 */
	public String getHttp_proxy() {
		return http_proxy;
	}

	/**
	 * @param http_proxy
	 *            the http_proxy to set
	 */
	public void setHttp_proxy(String http_proxy) {
		this.http_proxy = http_proxy;
	}

	/**
	 * @return the https_proxy
	 */
	public String getHttps_proxy() {
		return https_proxy;
	}

	/**
	 * @param https_proxy
	 *            the https_proxy to set
	 */
	public void setHttps_proxy(String https_proxy) {
		this.https_proxy = https_proxy;
	}

	/**
	 * @return the named_run_list
	 */
	public String getNamed_run_list() {
		return named_run_list;
	}

	/**
	 * @param named_run_list
	 *            the named_run_list to set
	 */
	public void setNamed_run_list(String named_run_list) {
		this.named_run_list = named_run_list;
	}

	/**
	 * @return the no_proxy
	 */
	public ArrayList<String> getNo_proxy() {
		return no_proxy;
	}

	/**
	 * @param no_proxy
	 *            the no_proxy to set
	 */
	public void setNo_proxy(ArrayList<String> no_proxy) {
		this.no_proxy = no_proxy;
	}

	/**
	 * @return the node_name
	 */
	public String getNode_name() {
		return node_name;
	}

	/**
	 * @param node_name
	 *            the node_name to set
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	/**
	 * @return the ohai_hints
	 */
	public ArrayList<String> getOhai_hints() {
		return ohai_hints;
	}

	/**
	 * @param ohai_hints
	 *            the ohai_hints to set
	 */
	public void setOhai_hints(ArrayList<String> ohai_hints) {
		this.ohai_hints = ohai_hints;
	}

	/**
	 * @return the os_type
	 */
	public String getOs_type() {
		return os_type;
	}

	/**
	 * @param os_type
	 *            the os_type to set
	 */
	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	/**
	 * @return the prevent_sudo
	 */
	public boolean isPrevent_sudo() {
		return prevent_sudo;
	}

	/**
	 * @param prevent_sudo
	 *            the prevent_sudo to set
	 */
	public void setPrevent_sudo(boolean prevent_sudo) {
		this.prevent_sudo = prevent_sudo;
	}

	/**
	 * @return the recreate_client
	 */
	public boolean isRecreate_client() {
		return recreate_client;
	}

	/**
	 * @param recreate_client
	 *            the recreate_client to set
	 */
	public void setRecreate_client(boolean recreate_client) {
		this.recreate_client = recreate_client;
	}

	/**
	 * @return the run_list
	 */
	public ArrayList<String> getRun_list() {
		return run_list;
	}

	/**
	 * @param run_list
	 *            the run_list to set
	 */
	public void setRun_list(ArrayList<String> run_list) {
		this.run_list = run_list;
	}

	/**
	 * @return the secret_key
	 */
	public String getSecret_key() {
		return secret_key;
	}

	/**
	 * @param secret_key
	 *            the secret_key to set
	 */
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	/**
	 * @return the server_url
	 */
	public String getServer_url() {
		return server_url;
	}

	/**
	 * @param server_url
	 *            the server_url to set
	 */
	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}

	/**
	 * @return the skip_install
	 */
	public boolean isSkip_install() {
		return skip_install;
	}

	/**
	 * @param skip_install
	 *            the skip_install to set
	 */
	public void setSkip_install(boolean skip_install) {
		this.skip_install = skip_install;
	}

	/**
	 * @return the skip_register
	 */
	public boolean isSkip_register() {
		return skip_register;
	}

	/**
	 * @param skip_register
	 *            the skip_register to set
	 */
	public void setSkip_register(boolean skip_register) {
		this.skip_register = skip_register;
	}

	/**
	 * @return the ssl_verify_mode
	 */
	public String getSsl_verify_mode() {
		return ssl_verify_mode;
	}

	/**
	 * @param ssl_verify_mode
	 *            the ssl_verify_mode to set
	 */
	public void setSsl_verify_mode(String ssl_verify_mode) {
		this.ssl_verify_mode = ssl_verify_mode;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the user_key
	 */
	public String getUser_key() {
		return user_key;
	}

	/**
	 * @param user_key
	 *            the user_key to set
	 */
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	/**
	 * @return the vault_json
	 */
	public String getVault_json() {
		return vault_json;
	}

	/**
	 * @param vault_json
	 *            the vault_json to set
	 */
	public void setVault_json(String vault_json) {
		this.vault_json = vault_json;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Checks if the required fields are set
	 * 
	 * @return
	 */
	private boolean isValid() {
		if (user_key == null || user_key.equals("")) {
			return false;
		}
		if (user_name == null || user_name.equals("")) {
			return false;
		}
		if (server_url == null || server_url.equals("")) {
			return false;
		}
		if (node_name == null || node_name.equals("")) {
			return false;
		}
		if (use_policyfile) {
			if (policy_group == null || policy_group.equals("")) {
				return false;
			}
			if (policy_name == null || policy_name.equals("")) {
				return false;
			}
		} else {
			if (run_list.isEmpty()) {
				return false;
			}
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
		String elementIndent= headIndent +  Constants.indent;		
		/*******************************************************/
		
		if (!isValid()) {
			return null;
		}
		String block = headIndent + "provisioner \"chef\" { \n";
		
		if (attributes_json != null && !attributes_json.equals("")) {
			block += elementIndent + "attributes_json = " + attributes_json + "\n";
		}
		if (channel != null && !channel.equals("")) {
			block += elementIndent + "channel = " + channel + "\n";
		}
		if (client_options != null && !client_options.equals("")) {
			block += elementIndent + "client_options = " + client_options + "\n";
		}
		if (disable_reporting) {
			block += elementIndent + "disable_reporting = " + disable_reporting + "\n";
		}
		if (environment != null && !environment.equals("")) {
			block += elementIndent + "environment = " + environment + "\n";
		}
		if (fetch_chef_certificates) {
			block += elementIndent + "fetch_chef_certificates = " + fetch_chef_certificates + "\n";
		}
		if (log_to_file) {
			block += elementIndent + "log_to_file = " + log_to_file + "\n";
		}
		if (use_policyfile) {
			block += elementIndent + "use_policyfile = " + use_policyfile + "\n";
		}
		if (policy_group != null && !policy_group.equals("")) {
			block += elementIndent + "policy_group = " + policy_group + "\n";
		}
		if (policy_name != null && !policy_name.equals("")) {
			block += elementIndent + "policy_name = " + policy_name + "\n";
		}
		if (http_proxy != null && !http_proxy.equals("")) {
			block += elementIndent + "http_proxy = " + http_proxy + "\n";
		}
		if (https_proxy != null && !https_proxy.equals("")) {
			block += elementIndent + "https_proxy = " + https_proxy + "\n";
		}
		if (named_run_list != null && !named_run_list.equals("")) {
			block += elementIndent + "named_run_list = " + named_run_list + "\n";
		}
		if (no_proxy != null && !no_proxy.equals("")) {
			block += elementIndent + "no_proxy = " + no_proxy + "\n";
		}
		if (node_name != null && !node_name.equals("")) {
			block += elementIndent + "node_name = " + node_name + "\n";
		}
		if (ohai_hints != null && !ohai_hints.equals("")) {
			block += elementIndent + "ohai_hints = " + ohai_hints + "\n";
		}
		if (os_type != null && !os_type.equals("")) {
			block += elementIndent + "os_type = " + os_type + "\n";
		}
		if (prevent_sudo) {
			block += elementIndent + "prevent_sudo = " + prevent_sudo + "\n";
		}
		if (recreate_client) {
			block += elementIndent + "recreate_client = " + recreate_client + "\n";
		}
		if (!run_list.isEmpty()) {
			block += elementIndent + "run_list = " + run_list + "\n";
		}
		if (secret_key != null && !secret_key.equals("")) {
			block += elementIndent + "secret_key = " + secret_key + "\n";
		}
		if (server_url != null && !server_url.equals("")) {
			block += elementIndent + "server_url = " + server_url + "\n";
		}
		if (skip_install) {
			block += elementIndent + "skip_install = " + skip_install + "\n";
		}
		if (skip_register) {
			block += elementIndent + "skip_register = " + skip_register + "\n";
		}
		if (ssl_verify_mode != null && !ssl_verify_mode.equals("")) {
			block += elementIndent + "ssl_verify_mode = " + ssl_verify_mode + "\n";
		}
		if (user_name != null && !user_name.equals("")) {
			block += elementIndent + "user_name = " + user_name + "\n";
		}
		if (user_key != null && !user_key.equals("")) {
			block += elementIndent + "user_key = " + user_key + "\n";
		}
		if (vault_json != null && !vault_json.equals("")) {
			block += elementIndent + "vault_json = " + vault_json + "\n";
		}
		if (version != null && !version.equals("")) {
			block += elementIndent + "version = " + version + "\n";
		}
		block += headIndent + "}\n";
		return block;		 
	}
}
