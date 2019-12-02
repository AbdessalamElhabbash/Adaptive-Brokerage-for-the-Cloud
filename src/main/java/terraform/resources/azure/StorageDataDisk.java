package terraform.resources.azure;

import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class StorageDataDisk {

	private String name; //Required;
	private String caching; //Possible values include None, ReadOnly and ReadWrite.
	private String create_option; //Required. Possible values are Attach, FromImage and Empty.
	private String disk_size_gb;
	private String lun;
	private boolean write_accelerator_enabled; //default false. his can only be enabled on Premium_LRS managed disks with no caching and M-Series VMs.
	//The following properties apply when using Managed Disks:
	private String managed_disk_type; //Possible values are either Standard_LRS, StandardSSD_LRS or Premium_LRS.
	private String managed_disk_id; //When this field is set create_option must be set to Attach.
	//The following properties apply when using Unmanaged Disks
	private String vhd_uri;
	public static final Set<String> CACHING;
	public static final Set<String> CREATION_OPTIONS;
	public static final Set<String> MANAGED_DISK_TYPES;

    static {
    	CACHING = new HashSet<String>();
    	CACHING.add("None");
    	CACHING.add("ReadOnly");
    	CACHING.add("ReadWrite");
    	
    	CREATION_OPTIONS = new HashSet<String>();
    	CREATION_OPTIONS.add("Attach");
    	CREATION_OPTIONS.add("FromImage");
    	CREATION_OPTIONS.add("Empty");
    	
    	MANAGED_DISK_TYPES = new HashSet<String>();
    	MANAGED_DISK_TYPES.add("Standard_LRS");
    	MANAGED_DISK_TYPES.add("StandardSSD_LRS");
    	MANAGED_DISK_TYPES.add("Premium_LRS");
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
	 * @return the caching
	 */
	public String getCaching() {
		return caching;
	}
	/**
	 * @param caching the caching to set
	 */
	public void setCaching(String caching) throws InvalidTypeException{
		if(CACHING.contains(caching)){
			this.caching = caching;
		}else{
			throw new InvalidTypeException("Possible values include None, ReadOnly and ReadWrite.");
		}
	}
	/**
	 * @return the create_option
	 */
	public String getCreate_option() {
		return create_option;
	}
	/**
	 * @param create_option the create_option to set
	 */
	public void setCreate_option(String create_option) throws InvalidTypeException{
		if(CREATION_OPTIONS.contains(create_option)){
			this.create_option = create_option;
		}else{
			throw new InvalidTypeException("Possible values are Attach, FromImage and Empty.");
		}
	}
	/**
	 * @return the disk_size_gb
	 */
	public String getDisk_size_gb() {
		return disk_size_gb;
	}
	/**
	 * @param disk_size_gb the disk_size_gb to set
	 */
	public void setDisk_size_gb(String disk_size_gb) {
		this.disk_size_gb = disk_size_gb;
	}
	/**
	 * @return the lun
	 */
	public String getLun() {
		return lun;
	}
	/**
	 * @param lun the lun to set
	 */
	public void setLun(String lun) {
		this.lun = lun;
	}
	/**
	 * @return the write_accelerator_enabled
	 */
	public boolean isWrite_accelerator_enabled() {
		return write_accelerator_enabled;
	}
	/**
	 * @param write_accelerator_enabled the write_accelerator_enabled to set
	 */
	public void setWrite_accelerator_enabled(boolean write_accelerator_enabled) {
		this.write_accelerator_enabled = write_accelerator_enabled;
	}
	/**
	 * @return the managed_disk_type
	 */
	public String getManaged_disk_type() {
		return managed_disk_type;
	}
	/**
	 * @param managed_disk_type the managed_disk_type to set
	 */
	public void setManaged_disk_type(String managed_disk_type) throws InvalidTypeException{
		if(MANAGED_DISK_TYPES.contains(managed_disk_type)){
			this.managed_disk_type = managed_disk_type;
		}else{
			throw new InvalidTypeException("Possible values are either Standard_LRS, StandardSSD_LRS or Premium_LRS.");
		}
	}
	/**
	 * @return the managed_disk_id
	 */
	public String getManaged_disk_id() {
		return managed_disk_id;
	}
	/**
	 * @param managed_disk_id the managed_disk_id to set
	 */
	public void setManaged_disk_id(String managed_disk_id) {
		this.managed_disk_id = managed_disk_id;
	}
	/**
	 * @return the vhd_uri
	 */
	public String getVhd_uri() {
		return vhd_uri;
	}
	/**
	 * @param vhd_uri the vhd_uri to set
	 */
	public void setVhd_uri(String vhd_uri) {
		this.vhd_uri = vhd_uri;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(name == null || name.equals("")){
			return false;
		}
		if(caching == null || caching.equals("")){
			return false;
		}		
		if(managed_disk_id != null && !managed_disk_id.equals("") && !create_option.equals("\"Attach\"")){
			return false;
		}
		if(write_accelerator_enabled && !managed_disk_type.equals("\"Premium_LRS\"") && !caching.equals("\"None\"")){
			return false;
		}		
		return true;
	}
	
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
		String block = headIndent + "storage_data_disk { \n" ;
		
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "create_option = \"" + create_option + "\"\n";
		
		if(caching != null && !caching.equals("")){
			block += elementIndent + "caching = \"" + caching + "\"\n";
		}
		if(disk_size_gb != null && !disk_size_gb.equals("")){
			block += elementIndent + "disk_size_gb = \"" + disk_size_gb + "\"\n";
		}
		if(lun != null && !lun.equals("")){
			block += elementIndent + "lun = \"" + lun + "\"\n";
		}
		if(write_accelerator_enabled){
			block += elementIndent + "write_accelerator_enabled = \"" + write_accelerator_enabled + "\"\n";
		}
		if(managed_disk_type != null && !managed_disk_type.equals("")){
			block += elementIndent + "managed_disk_type = \"" + managed_disk_type + "\"\n";
		}
		if(managed_disk_id != null && !managed_disk_id.equals("")){
			block += elementIndent + "managed_disk_id = \"" + managed_disk_id + "\"\n";
		}
		if(vhd_uri != null && !vhd_uri.equals("")){
			block += elementIndent + "vhd_uri = \"" + vhd_uri + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
	
}
