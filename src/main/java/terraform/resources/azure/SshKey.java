package terraform.resources.azure;

import terraform.provider.Constants;

public class SshKey {

	private String key_data; //required
	private String path; //required
	/**
	 * @return the key_data
	 */
	public String getKey_data() {
		return key_data;
	}
	/**
	 * @param key_data the key_data to set
	 */
	public void setKey_data(String key_data) {
		this.key_data = key_data;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(key_data ==null || key_data.equals("")){
			return false;
		}
		if(path ==null || path.equals("")){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBlock(int level) {
		System.out.println(level);

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
		String block = headIndent + "ssh_keys { \n";
		
		block += elementIndent + "key_data = " + key_data + "\n";
		block += elementIndent + "path = " + path + "\n";
		
		block += headIndent + "}\n";
		return block;
	}
}
