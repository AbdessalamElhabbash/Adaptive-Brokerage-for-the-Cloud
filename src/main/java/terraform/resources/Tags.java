package terraform.resources;

import java.util.HashMap;
import java.util.Map;

import terraform.provider.Constants;

public class Tags {
	
private Map<String,String> tags = new HashMap<>();
	
	public void addTag(String key, String value){
			tags.put(key,value);		
	}
	
	public boolean isEmpty(){
		return tags.isEmpty();
	}
	
	public String getBlock(int level) {
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		String block = headIndent + "tags { \n" ;
		for(String key : tags.keySet()){
			block += elementIndent + key + " = \"" + tags.get(key) +"\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}

}
