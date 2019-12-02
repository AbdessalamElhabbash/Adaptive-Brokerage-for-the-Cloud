package terraform.resources;

import java.util.HashMap;
import java.util.Map;

import terraform.provider.Constants;

public class Environment {

	private Map<String,String> environment = new HashMap<>();
	
	public void addVariable(String key, String value){
			environment.put(key,value);		
	}
	
	public boolean isEmpty(){
		return environment.isEmpty();
	}
	
	public String getBlock(int level) { 
		/***for formatting the terraform code**/ 
		String headIndent=""; 
		for(int i=0; i<level; i++){ 
			headIndent += Constants.indent; 
		} String elementIndent= headIndent +  Constants.indent; 
		/*******************************************************/
		
		String block = headIndent + "environment { \n" ;
		for(String key : environment.keySet()){
			block += elementIndent + key + " = " + environment.get(key) +"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
