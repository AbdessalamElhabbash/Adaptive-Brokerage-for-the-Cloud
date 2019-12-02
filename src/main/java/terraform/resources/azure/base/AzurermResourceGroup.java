package terraform.resources.azure.base;

import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AzurermResourceGroup extends Resource{
	
	public AzurermResourceGroup(String resourceName) {
		super(resourceName);
	}

	private String name; //Required. Must be unique on your Azure subscription.
	private String location; //Required. Possible values are East US, East US 2, Central US, North Central US, South Central US, West Central US, West US, West US 2, Canada East, Canada Central, Brazil South, North Europe, West Europe, France Central, FranceSouth, UK West, UK South, Germany Central, Germany Northeast, Germany North, Germany West Central, Switzerland North, Switzerland West, Norway East, Norway West, Southeast Asia, East Asia, Australia East, Australia Southeast, Australia Central, Australia Central 2, China East, China North, China East 2, China North 2, Central India, West India, South India, Japan East, Japan West, Korea Central, Korea South, US Gov Virginia, US Gov Iowa, US Gov Arizona, US Gov Texas, US DoD East, US DoD Central, US Sec East, US Sec West, South Africa West, South Africa North, UAE Central, UAE North
	private Tags tags;
	
	public static final Set<String> LOCATIONS;

    static {
    	LOCATIONS = new HashSet<String>();    	
    	LOCATIONS.add("east us");
    	LOCATIONS.add("east us 2");
    	LOCATIONS.add("central us");
    	LOCATIONS.add("north central us");
    	LOCATIONS.add("south central us");
    	LOCATIONS.add("west central us");
    	LOCATIONS.add("west us");
    	LOCATIONS.add("west us 2");
    	LOCATIONS.add("canada east");
    	LOCATIONS.add("canada central");
    	LOCATIONS.add("brazil south");
    	LOCATIONS.add("north europe");
    	LOCATIONS.add("west europe");
    	LOCATIONS.add("france central");
    	LOCATIONS.add("france south");
    	LOCATIONS.add("uk west");
    	LOCATIONS.add("uk south");
    	LOCATIONS.add("germany central");
    	LOCATIONS.add("germany northeast");
    	LOCATIONS.add("germany north");
    	LOCATIONS.add("germany west central");
    	LOCATIONS.add("switzerland north");
    	LOCATIONS.add("switzerland west");
    	LOCATIONS.add("norway east");
    	LOCATIONS.add("norway west");
    	LOCATIONS.add("southeast asia");
    	LOCATIONS.add("east asia");
    	LOCATIONS.add("australia east");
    	LOCATIONS.add("australia southeast");
    	LOCATIONS.add("australia central");
    	LOCATIONS.add("australia central 2");
    	LOCATIONS.add("china east");
    	LOCATIONS.add("china north");
    	LOCATIONS.add("china east 2");
    	LOCATIONS.add("china north 2");
    	LOCATIONS.add("central india");
    	LOCATIONS.add("west india");
    	LOCATIONS.add("south india");
    	LOCATIONS.add("japan east");
    	LOCATIONS.add("japan west");
    	LOCATIONS.add("korea central");
    	LOCATIONS.add("korea south");
    	LOCATIONS.add("us gov virginia");
    	LOCATIONS.add("us gov iowa");
    	LOCATIONS.add("us gov arizona");
    	LOCATIONS.add("us gov texas");
    	LOCATIONS.add("us dod east");
    	LOCATIONS.add("us dod central");
    	LOCATIONS.add("us sec east");
    	LOCATIONS.add("us sec west");
    	LOCATIONS.add("south africa west");
    	LOCATIONS.add("south africa north");
    	LOCATIONS.add("uae central");
    	LOCATIONS.add("uae north");
    }
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) throws InvalidTypeException{
		if(!LOCATIONS.contains(location.toLowerCase())){
			throw new InvalidTypeException("Possible values for Microsoft Azure locations are: East US, East US 2, Central US, North Central US, South Central US, West Central US, West US, West US 2, Canada East, Canada Central, Brazil South, North Europe, West Europe, France Central, FranceSouth, UK West, UK South, Germany Central, Germany Northeast, Germany North, Germany West Central, Switzerland North, Switzerland West, Norway East, Norway West, Southeast Asia, East Asia, Australia East, Australia Southeast, Australia Central, Australia Central 2, China East, China North, China East 2, China North 2, Central India, West India, South India, Japan East, Japan West, Korea Central, Korea South, US Gov Virginia, US Gov Iowa, US Gov Arizona, US Gov Texas, US DoD East, US DoD Central, US Sec East, US Sec West, South Africa West, South Africa North, UAE Central, UAE North");
		}
		this.location = location;
	}
	/**
	 * @return the tags
	 */
	public Tags getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(Tags tags) {
		this.tags = tags;
	} 
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(name == null || name.equals("")){
			return false;
		}
		if(location == null || location.equals("")){
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
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		if(!isValid()){
			return null;
		}
		String block = headIndent + "resource \"azurerm_resource_group\" \"" + resourceName + "\" { \n";
		
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "location = \"" + location + "\"\n";
		
		if(tags != null){
			block += tags.getBlock(level+1) + "\n";
		}
		
		block += headIndent + "}\n";
		return block;
	}

}
