package terraform.resources.aws;

import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class RootBlockDevice {

	private String volume_type; // "standard", "gp2", "io1", "sc1", or "st1"
	private String volume_size;
	private String iops; //required with "io1"
	private boolean delete_on_termination; //default true 
	public static final Set<String> TYPES;

    static {
    	TYPES = new HashSet<String>();
    	TYPES.add("standard");
    	TYPES.add("gp2");
    	TYPES.add("io1");
    	TYPES.add("sc1");
    	TYPES.add("st1");
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
			throw new InvalidTypeException("volume_type type can be standard, gp2, io1, sc1, or st1");
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
		
		String block = "root_block_device { \n" ;
		
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
		block += headIndent + "}\n";
		return block; 
	}
}
