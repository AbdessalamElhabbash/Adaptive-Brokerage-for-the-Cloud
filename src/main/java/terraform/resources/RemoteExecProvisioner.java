package terraform.resources;

import java.util.ArrayList;

import terraform.provider.Constants;

public class RemoteExecProvisioner extends Provisioner{
	private ArrayList<String> inline;
	private String script;
	private ArrayList<String> scripts;
	/**
	 * @return the inline
	 */
	public ArrayList<String> getInline() {
		return inline;
	}
	/**
	 * @param inline the inline to set
	 */
	public void setInline(ArrayList<String> inline) {
		this.inline = inline;
	}
	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}
	/**
	 * @param script the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}
	/**
	 * @return the scripts
	 */
	public ArrayList<String> getScripts() {
		return scripts;
	}
	/**
	 * @param scripts the scripts to set
	 */
	public void setScripts(ArrayList<String> scripts) {
		this.scripts = scripts;
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
		String elementIndent = headIndent +  Constants.indent; 
		/*******************************************************/ 
		
		String block = headIndent + "provisioner \"remote-exec\" { \n";
		if (!inline.isEmpty()) {
			block += elementIndent + "inline = " + inline + "\n";
		}
		if (script != null && !script.equals("")) {
			block += elementIndent + "script = " + script + "\n";
		}
		if (!scripts.isEmpty()) {
			block += elementIndent + "scripts = " + scripts + "\n";
		}		
		block += headIndent + "}\n";
		return block;
	}
}
