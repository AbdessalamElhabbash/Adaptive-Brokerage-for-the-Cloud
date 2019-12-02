package terraform.resources.azure.database;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.TerraformObject;
import terraform.provider.Constants;

public class StorageProfile extends TerraformObject{
	private int storage_mb; //required
	private int backup_retention_days=7;//supported values are between 7 and 35 days.
	private String geo_redundant_backup; // Valid values for this property are Enabled or Disabled, not supported for the basic tier.

	/**
	 * @return the storage_mb
	 */
	public int getStorage_mb() {
		return storage_mb;
	}

	/**
	 * @param storage_mb the storage_mb to set
	 */
	public void setStorage_mb(int storage_mb) {
		this.storage_mb = storage_mb;
	}
	/**
	 * @return the backup_retention_days
	 */
	public int getBackup_retention_days() {
		return backup_retention_days;
	}
	/**
	 * @param backup_retention_days the backup_retention_days to set
	 */
	public void setBackup_retention_days(int backup_retention_days) {
		this.backup_retention_days = backup_retention_days;
	}
	/**
	 * @return the geo_redundant_backup
	 */
	public String getGeo_redundant_backup() {
		return geo_redundant_backup;
	}
	/**
	 * @param geo_redundant_backup the geo_redundant_backup to set
	 * @throws InvalidTypeException 
	 */
	public void setGeo_redundant_backup(String geo_redundant_backup) throws InvalidTypeException {
		if(geo_redundant_backup.equalsIgnoreCase("Enabled") || geo_redundant_backup.equalsIgnoreCase("Disabled")){
			this.geo_redundant_backup = geo_redundant_backup;
		}else{
			throw new InvalidTypeException("Invalid geo_redundant_backup type. Possible values are Enabled and Disabled."); 
		}
	}


	private boolean isValid() throws InvalidTypeException, RequiredArgumentException{
		if(storage_mb < 0){
			throw new InvalidTypeException("Invalid value for the storage_mb config"); 
		}
		if(backup_retention_days < 7 || backup_retention_days > 35){
			throw new InvalidTypeException("Invalid value for the backup_retention_days config. Supported values are between 7 and 35 days."); 
		}
		return true;
	}
	
	@Override
	public String getBlock(int level) {
		
		try {
			isValid();
		} catch (InvalidTypeException | RequiredArgumentException e) {
			e.printStackTrace();
		}
		
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block = headIndent + "storage_profile  { \n";
		block += elementIndent + "storage_mb = " + storage_mb + "\n";
		block += elementIndent + "backup_retention_days  = \"" + backup_retention_days + "\"\n";
		
		if(geo_redundant_backup != null && geo_redundant_backup.equals("")){
			block += elementIndent + "geo_redundant_backup = \"" + geo_redundant_backup + "\"\n";
		}

		block += headIndent + "}\n";
		return block;
	}	
}
