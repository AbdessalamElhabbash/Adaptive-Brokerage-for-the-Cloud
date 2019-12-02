package terraform.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class Timeouts {

	private Map<String,String> timeouts = new HashMap<>();
	public static final Set<String> NAMES;

    static {
    	NAMES = new HashSet<String>();
    	NAMES.add("create");
    	NAMES.add("update");
    	NAMES.add("delete");
    }
	
	public boolean addTimeout(String name, String value) throws InvalidTypeException{
		if(!NAMES.contains(name)){
			throw new InvalidTypeException("Invalid config type " + name + ". Possible values are create, update, or delete.");
		}
		timeouts.put(name,value);
		return true;		
	}

	public String getBlock(int level) { 
		/***for formatting the terraform code**/ 
		String headIndent=""; 
		for(int i=0; i<level; i++){
			headIndent += Constants.indent; 
		} 
		String elementIndent= headIndent +  Constants.indent; 
		/*******************************************************/
		String block = "timeouts { \n" ;
		for(String name : timeouts.keySet()){
			block += elementIndent + name + " = \"" + timeouts.get(name) +"\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
