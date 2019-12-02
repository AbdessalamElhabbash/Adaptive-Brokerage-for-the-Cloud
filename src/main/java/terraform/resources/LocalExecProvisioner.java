package terraform.resources;

import java.util.ArrayList;

import terraform.provider.Constants;

public class LocalExecProvisioner extends Provisioner{
	private String command; //required
	private String working_dir;
	private ArrayList<String> interpreter;
	private Environment environment;
	
	public LocalExecProvisioner() {
		environment = new Environment();
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @return the working_dir
	 */
	public String getWorking_dir() {
		return working_dir;
	}

	/**
	 * @param working_dir the working_dir to set
	 */
	public void setWorking_dir(String working_dir) {
		this.working_dir = working_dir;
	}

	/**
	 * @return the interpreter
	 */
	public ArrayList<String> getInterpreter() {
		return interpreter;
	}

	/**
	 * @param interpreter the interpreter to set
	 */
	public void setInterpreter(ArrayList<String> interpreter) {
		this.interpreter = interpreter;
	}

	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if (command == null && command.equals("")) {
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
		String elementIndent = headIndent +  Constants.indent; 
		/*******************************************************/ 
		
		if(!isValid()){
			return null;
		}
		String block = headIndent + "provisioner \"local-exec\" { \n";
		if (command != null && !command.equals("")) {
			block += elementIndent + "command = " + command + "\n";
		}
		if (working_dir != null && !working_dir.equals("")) {
			block += elementIndent + "working_dir = " + working_dir + "\n";
		}
		if (!interpreter.isEmpty()) {
			block += elementIndent + "interpreter = " + interpreter + "\n";
		}
		if (!environment.isEmpty()) {
			block += environment.getBlock(level+1);
		}
		block += headIndent + "}\n";
		return block;
	}
}
