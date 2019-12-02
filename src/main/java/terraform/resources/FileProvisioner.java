package terraform.resources;

import terraform.provider.Constants;

public class FileProvisioner extends Provisioner{
	private String source;
	private String content;
	private String destination; //required
	private Connection connection;
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(destination == null || destination.equals("")){
			return false;
		}
		return true;
	}
	
	public String getBlock(int level) { 
		/***for formatting the terraform code**/ 
		String headIndent=""; 
		for(int i=0; i<level; i++){ 
			headIndent += Constants.indent; 
			} 
		String elementIndent = headIndent +  Constants.indent; 
		/*******************************************************/
		if(!isValid()){
			return null;
		}
		String block = headIndent + "provisioner \"file\" { \n";
		if (source != null && !source.equals("")) {
			block += elementIndent + "source = " + source + "\n";
		}
		if (content != null && !content.equals("")) {
			block += elementIndent + "content = " + content + "\n";
		}
		if (destination != null && !destination.equals("")) {
			block += elementIndent + "destination = " + destination + "\n";
		}
		if (connection != null) {
			block += connection.getBlock(level+1) + "\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
