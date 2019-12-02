package terraform;

import java.util.ArrayList;

public abstract class TerraformObject {
	protected String objectType; //Possible values are resource, data, variable, provider, locals, output, module, terraform, atlas,

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}
	
	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * Appends double quotes for each element of the array list
	 * @param elements
	 */
	public ArrayList<String> appendDoubleQuotes(ArrayList<String> elements){
		ArrayList<String> quotedList = new ArrayList<>();
		for(String element : elements){			
			quotedList.add("\"" + element + "\"");
		}
		return quotedList;
	}
	
	/**
	 * Returns the terraform code block of resource 
	 * @return
	 */
	public abstract String getBlock(int level);
}
