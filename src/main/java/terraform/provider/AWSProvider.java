package terraform.provider;

import java.util.ArrayList;
import exceptions.RequiredArgumentException;

public class AWSProvider extends Provider{
	
	private String access_key; 
	private String secret_key; 
	private String region; //Required
	private String profile; 
	private String shared_credentials_file; // This is the path to the shared credentials file. If this is not set and a profile is specified, ~/.aws/credentials will be used.
	private String token; 
	private int max_retries = -1;
	private ArrayList<String> allowed_account_ids;
	private ArrayList<String> forbidden_account_ids; 
	private boolean insecure=false;
	private boolean skip_credentials_validation=false;
	private boolean skip_get_ec2_platforms=false;
	private boolean skip_region_validation=false;
	private boolean skip_requesting_account_id=false;
	private boolean skip_metadata_api_check = false; 
	private boolean s3_force_path_style = false; 
	private Assume_role assume_role; 
	private Endpoints endpoints; 
	
	
	
	/**
	 * Checks if the required attributes are set
	 * @return
	 * @throws RequiredArgumentException 
	 */
	public boolean isValid() throws RequiredArgumentException{
		if(region == null || region.equals("")){
			throw new RequiredArgumentException("region is a required config for the aws provider");
		}
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name is a required config for the aws provider");
		}
		if(alias == null || alias.equals("")){
			throw new RequiredArgumentException("alias is a required config for the aws provider");
		}
		return true;
	}
	
	/**
	 * Returns the AWSProvider block if valid. If not, returns an error message.
	 * @return
	 */
	@Override
	public String getBlock(int level) {
		try {
			isValid();
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		} 
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		String block =  headIndent + "provider \"aws\" { \n" ;

		block += elementIndent + "region = \"" + region + "\"\n";
		if(alias != null && !alias.equals("")){
			block += elementIndent + "alias = \"" + alias + "\"\n"; 
		} 
		if(version != null && !version.equals("")){
			block += elementIndent + "version = \"" + version + "\"\n"; 
		} 
		if(access_key != null && !access_key.equals("")){
			block += elementIndent + "access_key = \"" + access_key + "\"\n"; 
		} 
		if(secret_key != null && !secret_key.equals("")){
			block += elementIndent + "secret_key = \"" + secret_key + "\"\n"; 
		}
		if(profile != null && !profile.equals("")){
			block += elementIndent + "profile = \"" + profile + "\"\n"; 
		}
		if(shared_credentials_file != null && !shared_credentials_file.equals("")){
			block += elementIndent + "shared_credentials_file = \"" + shared_credentials_file + "\"\n"; 
		}
		if(token != null && !token.equals("")){
			block += elementIndent + "token = \"" + token + "\"\n"; 
		}
		if(max_retries > -1 ){
			block += elementIndent + "max_retries = " + max_retries + "\n"; 
		} 
		if(allowed_account_ids != null && !allowed_account_ids.isEmpty()){
			block += elementIndent + "allowed_account_ids = " + appendDoubleQuotes(allowed_account_ids) + "\n"; 
		}
		if(forbidden_account_ids != null && !forbidden_account_ids.isEmpty()){
			block += elementIndent + "forbidden_account_ids = " + appendDoubleQuotes(forbidden_account_ids) + "\n"; 
		}
		if(insecure){
			block += elementIndent + "insecure = " + insecure + "\n"; 
		}
		if(skip_credentials_validation){
			block += elementIndent + "skip_credentials_validation = " + skip_credentials_validation + "\n"; 
		}
		if(skip_get_ec2_platforms){
			block += elementIndent + "skip_get_ec2_platforms = " + skip_get_ec2_platforms + "\n"; 
		}
		if(skip_region_validation){
			block += elementIndent + "skip_region_validation = " + skip_region_validation + "\n"; 
		} 
		if(skip_requesting_account_id){
			block += elementIndent + "skip_requesting_account_id = " + skip_requesting_account_id + "\n"; 
		}
		if(skip_metadata_api_check){
			block += elementIndent + "skip_metadata_api_check = " + skip_metadata_api_check + "\n"; 
		}
		if(s3_force_path_style){
			block += elementIndent + "s3_force_path_style = " + s3_force_path_style + "\n"; 
		}
		if(assume_role != null){
			block += elementIndent + assume_role.getBlock(level+1); 
		}
		if(endpoints != null){
			block += elementIndent + endpoints.getBlock(level+1); 
		}
		block += headIndent + "}\n";		
		return block;
	}
	
	public String getIDReference(){
		return alias + "." + name;
	}

	
}
