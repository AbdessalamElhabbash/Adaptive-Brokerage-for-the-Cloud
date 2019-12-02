package terraform.resources.aws;

import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class EbsBlockDevice {
	
	private String device_name;
	private String snapshot_id;
	private String volume_type; // "standard", "gp2", "io1"
	private String volume_size;
	private String iops; //required with "io1"
	private boolean delete_on_termination; //default true
	private boolean encrypted; //default false
	public static final Set<String> TYPES;

    static {
    	TYPES = new HashSet<String>();
    	TYPES.add("standard");
    	TYPES.add("gp2");
    	TYPES.add("io1");    	
    }

	/**
	 * @return the device_name
	 */
	public String getDevice_name() {
		return device_name;
	}

	/**
	 * @param device_name the device_name to set
	 */
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	/**
	 * @return the snapshot_id
	 */
	public String getSnapshot_id() {
		return snapshot_id;
	}

	/**
	 * @param snapshot_id the snapshot_id to set
	 */
	public void setSnapshot_id(String snapshot_id) {
		this.snapshot_id = snapshot_id;
	}

	/**
	 * @return the volume_type
	 */
	public String getVolume_type() {
		return volume_type;
	}

	/**
	 * @param volume_type the volume_type to set
	 */
	public void setVolume_type(String volume_type) throws InvalidTypeException{
		if(!TYPES.contains(volume_type)){
			throw new InvalidTypeException("volume_type type can be standard, gp2, or io1.");
		}else{
			this.volume_type = volume_type;
		}
	}

	/**
	 * @return the volume_size
	 */
	public String getVolume_size() {
		return volume_size;
	}

	/**
	 * @param volume_size the volume_size to set
	 */
	public void setVolume_size(String volume_size) {
		this.volume_size = volume_size;
	}

	/**
	 * @return the iops
	 */
	public String getIops() {
		return iops;
	}

	/**
	 * @param iops the iops to set
	 */
	public void setIops(String iops) {
		this.iops = iops;
	}

	/**
	 * @return the delete_on_termination
	 */
	public boolean isDelete_on_termination() {
		return delete_on_termination;
	}

	/**
	 * @param delete_on_termination the delete_on_termination to set
	 */
	public void setDelete_on_termination(boolean delete_on_termination) {
		this.delete_on_termination = delete_on_termination;
	}

	/**
	 * @return the encrypted
	 */
	public boolean isEncrypted() {
		return encrypted;
	}

	/**
	 * @param encrypted the encrypted to set
	 */
	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	/**
	 * @return the types
	 */
	public static Set<String> getTypes() {
		return TYPES;
	}
    
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(volume_type.equals("io1")){
			if(iops ==null || iops.equals("")){
				return false;
			}
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
		
		String block = "ebs_block_device { \n" ;
		
		if(device_name != null && !device_name.equals("")){
			block += elementIndent + "device_name = " + device_name +"\n";
		}
		if(snapshot_id != null && !snapshot_id.equals("")){
			block += elementIndent + "snapshot_id = " + snapshot_id +"\n";
		}
		if(volume_type != null && !volume_type.equals("")){
			block += elementIndent + "volume_type = " + volume_type +"\n";
		}
		if(volume_size != null && !volume_size.equals("")){
			block += elementIndent + "volume_size = " + volume_size +"\n";
		}
		if(iops != null && !iops.equals("")){
			block += elementIndent + "iops = " + iops +"\n";
		}
		if(!delete_on_termination){
			block += elementIndent + "delete_on_termination = " + delete_on_termination +"\n";
		}
		if(encrypted){
			block += elementIndent + "delete_on_termination = " + delete_on_termination +"\n";
		}
		block += headIndent + "}\n";
		return block; 
	}
}
