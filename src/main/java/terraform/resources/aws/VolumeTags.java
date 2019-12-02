package terraform.resources.aws;

import java.util.HashMap;
import java.util.Map;

import terraform.provider.Constants;

public class VolumeTags {
	
private Map<String,String> volumeTags = new HashMap<>();
	
	public void addVolumeTag(String key, String value){
			volumeTags.put(key,value);		
	}
	
	public boolean isEmpty(){
		return volumeTags.isEmpty();
	}
	
	public String getBlock(int level) {
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		String block = "volume_tags { \n" ;
		for(String key : volumeTags.keySet()){
			block += elementIndent + key + " = " + volumeTags.get(key) +"\n";
		}
		block += headIndent + "}\n";
		return block;
	}

}
