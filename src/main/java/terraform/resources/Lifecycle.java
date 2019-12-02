package terraform.resources;

import java.util.ArrayList;

import terraform.provider.Constants;

public class Lifecycle {
	private boolean create_before_destroy;
	private boolean prevent_destroy;
	private ArrayList<String> ignore_changes;
	/**
	 * @return the create_before_destroy
	 */
	public boolean isCreate_before_destroy() {
		return create_before_destroy;
	}
	/**
	 * @param create_before_destroy the create_before_destroy to set
	 */
	public void setCreate_before_destroy(boolean create_before_destroy) {
		this.create_before_destroy = create_before_destroy;
	}
	/**
	 * @return the prevent_destroy
	 */
	public boolean isPrevent_destroy() {
		return prevent_destroy;
	}
	/**
	 * @param prevent_destroy the prevent_destroy to set
	 */
	public void setPrevent_destroy(boolean prevent_destroy) {
		this.prevent_destroy = prevent_destroy;
	}
	/**
	 * @return the ignore_changes
	 */
	public ArrayList<String> getIgnore_changes() {
		return ignore_changes;
	}
	/**
	 * @param ignore_changes the ignore_changes to set
	 */
	public void setIgnore_changes(ArrayList<String> ignore_changes) {
		this.ignore_changes = ignore_changes;
	}

	/**
	 * Returns the terraform block of the Lifecycle object
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
		
		String block = headIndent + "lifecycle { \n" ;
		if(create_before_destroy){
			block += elementIndent + "create_before_destroy = " + create_before_destroy +"\n";
		}
		
		if(prevent_destroy){
			block += elementIndent + "prevent_destroy = " + prevent_destroy +"\n";
		}
		
		if(ignore_changes != null && !ignore_changes.isEmpty()){
			block += elementIndent + "ignore_changes = " + ignore_changes +"\n";
		}
		
		block += headIndent + "}\n";
		return block; 
	}	
}