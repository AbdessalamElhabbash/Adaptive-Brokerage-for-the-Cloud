package terraform.resources.aws.database;

import exceptions.RequiredArgumentException;
import terraform.TerraformObject;
import terraform.provider.Constants;

public class S3Import extends TerraformObject{
	
	private String bucket_name; //required
	private String bucket_prefix;
	private String ingestion_role;//required
	private String source_engine;//required
	private String source_engine_version; //required
	
	
	/**
	 * @return the bucket_name
	 */
	public String getBucket_name() {
		return bucket_name;
	}


	/**
	 * @param bucket_name the bucket_name to set
	 */
	public void setBucket_name(String bucket_name) {
		this.bucket_name = bucket_name;
	}


	/**
	 * @return the bucket_prefix
	 */
	public String getBucket_prefix() {
		return bucket_prefix;
	}


	/**
	 * @param bucket_prefix the bucket_prefix to set
	 */
	public void setBucket_prefix(String bucket_prefix) {
		this.bucket_prefix = bucket_prefix;
	}


	/**
	 * @return the ingestion_role
	 */
	public String getIngestion_role() {
		return ingestion_role;
	}


	/**
	 * @param ingestion_role the ingestion_role to set
	 */
	public void setIngestion_role(String ingestion_role) {
		this.ingestion_role = ingestion_role;
	}


	/**
	 * @return the source_engine
	 */
	public String getSource_engine() {
		return source_engine;
	}


	/**
	 * @param source_engine the source_engine to set
	 */
	public void setSource_engine(String source_engine) {
		this.source_engine = source_engine;
	}


	/**
	 * @return the source_engine_version
	 */
	public String getSource_engine_version() {
		return source_engine_version;
	}


	/**
	 * @param source_engine_version the source_engine_version to set
	 */
	public void setSource_engine_version(String source_engine_version) {
		this.source_engine_version = source_engine_version;
	}


	/**
	 * 
	 * @return
	 * @throws RequiredArgumentException
	 */
	private boolean isValid() throws RequiredArgumentException{
		if(bucket_name == null || bucket_name.equals("")){
			throw new RequiredArgumentException("bucket_name is required for the S3Import object");
		}
		if(ingestion_role == null || ingestion_role.equals("")){
			throw new RequiredArgumentException("ingestion_role is required for the S3Import object");
		}
		if(source_engine == null || source_engine.equals("")){
			throw new RequiredArgumentException("source_engine is required for the S3Import object");
		}
		if(source_engine_version == null || source_engine_version.equals("")){
			throw new RequiredArgumentException("source_engine_version is required for the S3Import object");
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param level
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
		
		String block = headIndent + "s3_import \"" + "\" { \n";
		block += elementIndent + "bucket_name = \"" + bucket_name + "\"\n";
		block += elementIndent + "ingestion_role = \"" + ingestion_role + "\"\n";
		block += elementIndent + "source_engine = \"" + source_engine + "\"\n";
		block += elementIndent + "source_engine_version = \"" + source_engine_version + "\"\n";
		
		if(bucket_prefix != null && !bucket_prefix.equals("")){
			block += elementIndent + "bucket_prefix = \"" + bucket_prefix + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}

}
