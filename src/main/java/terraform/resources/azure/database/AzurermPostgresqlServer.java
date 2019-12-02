package terraform.resources.azure.database;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AzurermPostgresqlServer extends Resource{
	
	private String name; //required
	private String resource_group_name; //required
	private String location; //required
	private Sku sku; //required
	private StorageProfile storage_profile; //required
	private String administrator_login; //required
	private String administrator_login_password; //required
	private String version; //required. Valid values are 9.5, 9.6, and 10.0.
	private String ssl_enforcement;//required. Possible values are Enabled and Disabled.
	private Tags tags;
	
	public AzurermPostgresqlServer(String resourceName) {
		super(resourceName);
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
	 * @return the resource_group_name
	 */
	public String getResource_group_name() {
		return resource_group_name;
	}
	/**
	 * @param resource_group_name the resource_group_name to set
	 */
	public void setResource_group_name(String resource_group_name) {
		this.resource_group_name = resource_group_name;
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
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the sku
	 */
	public Sku getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(Sku sku) {
		this.sku = sku;
	}

	/**
	 * @return the storage_profile
	 */
	public StorageProfile getStorage_profile() {
		return storage_profile;
	}

	/**
	 * @param storage_profile the storage_profile to set
	 */
	public void setStorage_profile(StorageProfile storage_profile) {
		this.storage_profile = storage_profile;
	}

	/**
	 * @return the administrator_login
	 */
	public String getAdministrator_login() {
		return administrator_login;
	}

	/**
	 * @param administrator_login the administrator_login to set
	 */
	public void setAdministrator_login(String administrator_login) {
		this.administrator_login = administrator_login;
	}

	/**
	 * @return the administrator_login_password
	 */
	public String getAdministrator_login_password() {
		return administrator_login_password;
	}

	/**
	 * @param administrator_login_password the administrator_login_password to set
	 */
	public void setAdministrator_login_password(String administrator_login_password) {
		this.administrator_login_password = administrator_login_password;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the ssl_enforcement
	 */
	public String getSsl_enforcement() {
		return ssl_enforcement;
	}

	/**
	 * @param ssl_enforcement the ssl_enforcement to set
	 */
	public void setSsl_enforcement(String ssl_enforcement) {
		this.ssl_enforcement = ssl_enforcement;
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
	 * @throws RequiredArgumentException
	 * @throws InvalidTypeException
	 */
	private boolean isValid() throws RequiredArgumentException, InvalidTypeException{
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name is a required config for the azurerm_postgresql_server"); 
		}
		if(resource_group_name == null || resource_group_name.equals("")){
			throw new RequiredArgumentException("resource_group_name is a required config for the azurerm_postgresql_server"); 
		}
		if(location == null || location.equals("")){
			throw new RequiredArgumentException("location is a required config for the azurerm_postgresql_server"); 
		}
		if(administrator_login == null || administrator_login.equals("")){
			throw new RequiredArgumentException("administrator_login is a required config for the azurerm_postgresql_server"); 
		}
		if(administrator_login_password == null || administrator_login_password.equals("")){
			throw new RequiredArgumentException("administrator_login_password is a required config for the azurerm_postgresql_server"); 
		}
		if(version == null || version.equals("")){
			throw new RequiredArgumentException("version is a required config for the azurerm_postgresql_server"); 
		}else if(!version.equalsIgnoreCase("9.5") && !version.equalsIgnoreCase("9.6") && !version.equalsIgnoreCase("10.0")){
			throw new InvalidTypeException("Invalid version type. Possible values are 9.5, 9.6, and 10.0."); 
		}
		if(ssl_enforcement == null || ssl_enforcement.equals("")){
			throw new RequiredArgumentException("ssl_enforcement is a required config for the azurerm_postgresql_server"); 
		}else if(!ssl_enforcement.equalsIgnoreCase("Enabled") && !ssl_enforcement.equalsIgnoreCase("Disabled")){
			throw new InvalidTypeException("Invalid ssl_enforcement type. Possible values are Enabled and Disabled."); 
		}
		if(sku == null){
			throw new RequiredArgumentException("sku config is a required config for the azurerm_postgresql_server");
		}
		if(storage_profile == null){
			throw new RequiredArgumentException("storage_profile config is a required config for the azurerm_postgresql_server");
		}
		
		return true;
	}
	
	@Override
	public String getBlock(int level) {
		try {
			isValid();
		} catch (RequiredArgumentException | InvalidTypeException e) {
			e.printStackTrace();
		}
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block = headIndent + "resource azurerm_postgresql_server \"" + this.resourceName + "\" { \n";
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "resource_group_name = \"" + resource_group_name + "\"\n";
		block += elementIndent + "location = \"" + location + "\"\n";
		block += elementIndent + "administrator_login = \"" + administrator_login + "\"\n";
		block += elementIndent + "administrator_login_password = \"" + administrator_login_password + "\"\n";
		block += elementIndent + "version = \"" + version + "\"\n";
		block += elementIndent + "ssl_enforcement = \"" + ssl_enforcement + "\"\n";
		block += sku.getBlock(level+1);
		block += storage_profile.getBlock(level+1);

		if (tags != null) {
			block += tags.getBlock(level+1);
		}
		block += headIndent + "}\n";
		return block;
	}	
}
