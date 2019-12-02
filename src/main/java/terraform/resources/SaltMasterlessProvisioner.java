package terraform.resources;

import terraform.provider.Constants;

public class SaltMasterlessProvisioner extends Provisioner{
	
	private String bootstrap_args;
	private boolean disable_sudo;
	private String remote_pillar_roots;
	private String remote_state_tree;
	private String local_pillar_roots;
	private String local_state_tree; //required
	private String custom_state;
	private String minion_config_file;
	private boolean skip_bootstrap;
	private String temp_config_dir;
	private boolean no_exit_on_failure;
	private String log_level;
	private String salt_call_args;
	private String salt_bin_dir;
	/**
	 * @return the bootstrap_args
	 */
	public String getBootstrap_args() {
		return bootstrap_args;
	}
	/**
	 * @param bootstrap_args the bootstrap_args to set
	 */
	public void setBootstrap_args(String bootstrap_args) {
		this.bootstrap_args = bootstrap_args;
	}
	/**
	 * @return the disable_sudo
	 */
	public boolean isDisable_sudo() {
		return disable_sudo;
	}
	/**
	 * @param disable_sudo the disable_sudo to set
	 */
	public void setDisable_sudo(boolean disable_sudo) {
		this.disable_sudo = disable_sudo;
	}
	/**
	 * @return the remote_pillar_roots
	 */
	public String getRemote_pillar_roots() {
		return remote_pillar_roots;
	}
	/**
	 * @param remote_pillar_roots the remote_pillar_roots to set
	 */
	public void setRemote_pillar_roots(String remote_pillar_roots) {
		this.remote_pillar_roots = remote_pillar_roots;
	}
	/**
	 * @return the remote_state_tree
	 */
	public String getRemote_state_tree() {
		return remote_state_tree;
	}
	/**
	 * @param remote_state_tree the remote_state_tree to set
	 */
	public void setRemote_state_tree(String remote_state_tree) {
		this.remote_state_tree = remote_state_tree;
	}
	/**
	 * @return the local_pillar_roots
	 */
	public String getLocal_pillar_roots() {
		return local_pillar_roots;
	}
	/**
	 * @param local_pillar_roots the local_pillar_roots to set
	 */
	public void setLocal_pillar_roots(String local_pillar_roots) {
		this.local_pillar_roots = local_pillar_roots;
	}
	/**
	 * @return the local_state_tree
	 */
	public String getLocal_state_tree() {
		return local_state_tree;
	}
	/**
	 * @param local_state_tree the local_state_tree to set
	 */
	public void setLocal_state_tree(String local_state_tree) {
		this.local_state_tree = local_state_tree;
	}
	/**
	 * @return the custom_state
	 */
	public String getCustom_state() {
		return custom_state;
	}
	/**
	 * @param custom_state the custom_state to set
	 */
	public void setCustom_state(String custom_state) {
		this.custom_state = custom_state;
	}
	/**
	 * @return the minion_config_file
	 */
	public String getMinion_config_file() {
		return minion_config_file;
	}
	/**
	 * @param minion_config_file the minion_config_file to set
	 */
	public void setMinion_config_file(String minion_config_file) {
		this.minion_config_file = minion_config_file;
	}
	/**
	 * @return the skip_bootstrap
	 */
	public boolean isSkip_bootstrap() {
		return skip_bootstrap;
	}
	/**
	 * @param skip_bootstrap the skip_bootstrap to set
	 */
	public void setSkip_bootstrap(boolean skip_bootstrap) {
		this.skip_bootstrap = skip_bootstrap;
	}
	/**
	 * @return the temp_config_dir
	 */
	public String getTemp_config_dir() {
		return temp_config_dir;
	}
	/**
	 * @param temp_config_dir the temp_config_dir to set
	 */
	public void setTemp_config_dir(String temp_config_dir) {
		this.temp_config_dir = temp_config_dir;
	}
	/**
	 * @return the no_exit_on_failure
	 */
	public boolean isNo_exit_on_failure() {
		return no_exit_on_failure;
	}
	/**
	 * @param no_exit_on_failure the no_exit_on_failure to set
	 */
	public void setNo_exit_on_failure(boolean no_exit_on_failure) {
		this.no_exit_on_failure = no_exit_on_failure;
	}
	/**
	 * @return the log_level
	 */
	public String getLog_level() {
		return log_level;
	}
	/**
	 * @param log_level the log_level to set
	 */
	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}
	/**
	 * @return the salt_call_args
	 */
	public String getSalt_call_args() {
		return salt_call_args;
	}
	/**
	 * @param salt_call_args the salt_call_args to set
	 */
	public void setSalt_call_args(String salt_call_args) {
		this.salt_call_args = salt_call_args;
	}
	/**
	 * @return the salt_bin_dir
	 */
	public String getSalt_bin_dir() {
		return salt_bin_dir;
	}
	/**
	 * @param salt_bin_dir the salt_bin_dir to set
	 */
	public void setSalt_bin_dir(String salt_bin_dir) {
		this.salt_bin_dir = salt_bin_dir;
	}
	
	/**
	 * 
	 */
	private boolean isValid(){
		if(local_state_tree == null || local_state_tree.equals("")){
			return false;
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
	
		if(!isValid()){
			return null;
		}
		String block = headIndent + "provisioner \"salt-masterless\" { \n";
		if (bootstrap_args != null && !bootstrap_args.equals("")) {
			block += elementIndent + "bootstrap_args = " + bootstrap_args + "\n";
		}
		if(disable_sudo){
			block += elementIndent + "disable_sudo = " + disable_sudo + "\n";
		}		
		if (remote_pillar_roots != null && !remote_pillar_roots.equals("")) {
			block += elementIndent + "remote_pillar_roots = " + remote_pillar_roots + "\n";
		}
		if (remote_state_tree != null && !remote_state_tree.equals("")) {
			block += elementIndent + "remote_state_tree = " + remote_state_tree + "\n";
		}
		if (local_pillar_roots != null && !local_pillar_roots.equals("")) {
			block += elementIndent + "local_pillar_roots = " + local_pillar_roots + "\n";
		}
		if (local_state_tree != null && !local_state_tree.equals("")) {
			block += elementIndent + "local_state_tree = " + local_state_tree + "\n";
		}
		if (custom_state != null && !custom_state.equals("")) {
			block += elementIndent + "custom_state = " + custom_state + "\n";
		}
		if (minion_config_file != null && !minion_config_file.equals("")) {
			block += elementIndent + "minion_config_file = " + minion_config_file + "\n";
		}
		if (skip_bootstrap) {
			block += elementIndent + "skip_bootstrap = " + skip_bootstrap + "\n";
		}
		if (temp_config_dir != null && !temp_config_dir.equals("")) {
			block += elementIndent + "temp_config_dir = " + temp_config_dir + "\n";
		}
		if (no_exit_on_failure) {
			block += elementIndent + "no_exit_on_failure = " + no_exit_on_failure + "\n";
		}
		if (log_level != null && !log_level.equals("")) {
			block += elementIndent + "log_level = " + log_level + "\n";
		}
		if (salt_call_args != null && !salt_call_args.equals("")) {
			block += elementIndent + "salt_call_args = " + salt_call_args + "\n";
		}
		if (salt_bin_dir != null && !salt_bin_dir.equals("")) {
			block += elementIndent + "salt_bin_dir = " + salt_bin_dir + "\n";
		}				
		block += headIndent + "}\n";
		return block;
	}
}
