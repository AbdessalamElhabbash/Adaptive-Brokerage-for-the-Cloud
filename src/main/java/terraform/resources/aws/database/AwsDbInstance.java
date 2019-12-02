package terraform.resources.aws.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;

public class AwsDbInstance extends Resource{

	private String allocated_storage; //Done. Required unless a snapshot_identifier or replicate_source_db is provided.
	private boolean allow_major_version_upgrade = true;
	private boolean apply_immediately = false; 
	private boolean auto_minor_version_upgrade = true;
	private String availability_zone;
	private int backup_retention_period = 0; // Done. The days to retain backups for. Must be between 0 and 35
	private TimeWindow backup_window; //  Done. Example: "09:46-10:16". Must not overlap with maintenance_window.
	private String character_set_name; //Done. See Oracle Character Sets Supported in Amazon RDS 
	private boolean copy_tags_to_snapshot = false; 
	private String db_subnet_group_name;
	private boolean deletion_protection = false;
	private String domain;
	private String domain_iam_role_name;
	private String enabled_cloudwatch_logs_exports; //Done. Valid values (depending on engine): alert, audit, error, general, listener, slowquery, trace, postgresql (PostgreSQL), upgrade (PostgreSQL).
	private String engine;// Done. Required unless a snapshot_identifier or replicate_source_db is provided. Possible values Amazon Aurora, MySQL,MariaDB,PostgreSQL,Oracle,SQL Server
	private String engine_version; //Done. If auto_minor_version_upgrade is enabled, you can provide a prefix of the version such as 5.7 (for 5.7.10) and this attribute will ignore differences in the patch version automatically (e.g. 5.7.17). For supported values, see the EngineVersion parameter in API action CreateDBInstance. Note that for Amazon Aurora instances the engine version must match the DB cluster's engine version'.
	private String final_snapshot_identifier;
	private boolean iam_database_authentication_enabled = false;
	private String identifier;
	private String identifier_prefix;
	private String instance_class; //Done. required
	private String iops; //Setting this implies a storage_type of "io1".
	private String kms_key_id;
	private String license_model;
	private TimeWindow maintenance_window; //done. The window to perform maintenance in. Syntax: "ddd:hh24:mi-ddd:hh24:mi" 
	private int monitoring_interval =0; //done. The default is 0. Valid Values: 0, 1, 5, 10, 15, 30, 60
	private String monitoring_role_arn;
	private boolean multi_az = false;
	private String name;
	private String option_group_name;
	private String parameter_group_name;
	private String password; // done(Required unless a snapshot_identifier or replicate_source_db is provided
	private int port;
	private boolean publicly_accessible=false;
	private String replicate_source_db; //Note that if you are creating a cross-region replica of an encrypted database you will also need to specify a kms_key_id
	private ArrayList<String> security_group_names;
	private boolean skip_final_snapshot=false; 
	private String snapshot_identifier;//this correlates to the snapshot ID you'd find in the RDS consol
	private boolean storage_encrypted = false;
	private String storage_type; //done. One of "standard" (magnetic), "gp2" (general purpose SSD), or "io1" (provisioned IOPS SSD). The default is "io1" if iops is specified, "standard" if not.
	private Tags tags;
	private String timezone;
	private String username; //done Required unless a snapshot_identifier or replicate_source_db is provided) Username for the master DB user
	private ArrayList<String> vpc_security_group_ids;
	private S3Import s3_import;
	
	private static final Set<String> ENGINES;
	private static final Set<String> LOG_EXPORTS;
	private static final Set<String> CHARSET;
	private static final Set<Integer> MONITORING;
	
	static{
		MONITORING = new HashSet<Integer>();
		MONITORING.add(0);
		MONITORING.add(1);
		MONITORING.add(5);
		MONITORING.add(10);
		MONITORING.add(15);
		MONITORING.add(30);
		MONITORING.add(60);
	}

	static {
		ENGINES = new HashSet<String>();
		ENGINES.add("amazon aurora");
		ENGINES.add("mysql");
		ENGINES.add("mariadb");
		ENGINES.add("postgresql");
		ENGINES.add("oracle");
		ENGINES.add("sql server");	 
	}
	
	static {
		LOG_EXPORTS = new HashSet<String>();
		LOG_EXPORTS.add("alert");
		LOG_EXPORTS.add("audit");
		LOG_EXPORTS.add("error");
		LOG_EXPORTS.add("general");
		LOG_EXPORTS.add("listener");
		LOG_EXPORTS.add("slowquery");
		LOG_EXPORTS.add("trace");	 
	}
	static {
		CHARSET = new HashSet<String>();
		CHARSET.add("al32utf8");
		CHARSET.add("ar8iso8859p6");
		CHARSET.add("ar8mswin1256");
		CHARSET.add("blt8iso8859p13");
		CHARSET.add("blt8mswin1257");
		CHARSET.add("cl8iso8859p5");
		CHARSET.add("cl8mswin1251");
		CHARSET.add("ee8iso8859p2");
		CHARSET.add("el8iso8859p7");
		CHARSET.add("ee8mswin1250");
		CHARSET.add("el8mswin1253");
		CHARSET.add("iw8iso8859p8");
		CHARSET.add("iw8mswin1255");
		CHARSET.add("ja16euc");
		CHARSET.add("ja16euctilde");
		CHARSET.add("ja16sjis");
		CHARSET.add("ja16sjistilde");
		CHARSET.add("ko16mswin949");
		CHARSET.add("ne8iso8859p10");
		CHARSET.add("nee8iso8859p4");
		CHARSET.add("th8tisascii");
		CHARSET.add("tr8mswin1254");
		CHARSET.add("us7ascii");
		CHARSET.add("utf8");
		CHARSET.add("vn8mswin1258");
		CHARSET.add("we8iso8859p1");
		CHARSET.add("we8iso8859p15");
		CHARSET.add("we8iso8859p9");
		CHARSET.add("we8mswin1252");
		CHARSET.add("zhs16gbk");
		CHARSET.add("zht16hkscs");
		CHARSET.add("zht16mswin950");
		CHARSET.add("zht32euc");

	}
	
	public AwsDbInstance(String resourceName) {
		super(resourceName);
	}
		
	/**
	 * @return the allocated_storage
	 */
	public String getAllocated_storage() {
		return allocated_storage;
	}

	/**
	 * @param allocated_storage the allocated_storage to set
	 */
	public void setAllocated_storage(String allocated_storage) {
		this.allocated_storage = allocated_storage;
	}

	/**
	 * @return the allow_major_version_upgrade
	 */
	public boolean isAllow_major_version_upgrade() {
		return allow_major_version_upgrade;
	}

	/**
	 * @param allow_major_version_upgrade the allow_major_version_upgrade to set
	 */
	public void setAllow_major_version_upgrade(boolean allow_major_version_upgrade) {
		this.allow_major_version_upgrade = allow_major_version_upgrade;
	}

	/**
	 * @return the apply_immediately
	 */
	public boolean isApply_immediately() {
		return apply_immediately;
	}

	/**
	 * @param apply_immediately the apply_immediately to set
	 */
	public void setApply_immediately(boolean apply_immediately) {
		this.apply_immediately = apply_immediately;
	}

	/**
	 * @return the auto_minor_version_upgrade
	 */
	public boolean isAuto_minor_version_upgrade() {
		return auto_minor_version_upgrade;
	}

	/**
	 * @param auto_minor_version_upgrade the auto_minor_version_upgrade to set
	 */
	public void setAuto_minor_version_upgrade(boolean auto_minor_version_upgrade) {
		this.auto_minor_version_upgrade = auto_minor_version_upgrade;
	}

	/**
	 * @return the availability_zone
	 */
	public String getAvailability_zone() {
		return availability_zone;
	}

	/**
	 * @param availability_zone the availability_zone to set
	 */
	public void setAvailability_zone(String availability_zone) {
		this.availability_zone = availability_zone;
	}

	/**
	 * @return the backup_retention_period
	 */
	public int getBackup_retention_period() {
		return backup_retention_period;
	}

	/**
	 * @param backup_retention_period the backup_retention_period to set
	 */
	public void setBackup_retention_period(int backup_retention_period) {
		this.backup_retention_period = backup_retention_period;
	}

	/**
	 * @return the backup_window
	 */
	public TimeWindow getBackup_window() {
		return backup_window;
	}

	/**
	 * @param backup_window the backup_window to set
	 */
	public void setBackup_window(TimeWindow backup_window) {
		this.backup_window = backup_window;
	}

	/**
	 * @return the character_set_name
	 */
	public String getCharacter_set_name() {
		return character_set_name;
	}

	/**
	 * @param character_set_name the character_set_name to set
	 */
	public void setCharacter_set_name(String character_set_name) {
		this.character_set_name = character_set_name;
	}

	/**
	 * @return the copy_tags_to_snapshot
	 */
	public boolean isCopy_tags_to_snapshot() {
		return copy_tags_to_snapshot;
	}

	/**
	 * @param copy_tags_to_snapshot the copy_tags_to_snapshot to set
	 */
	public void setCopy_tags_to_snapshot(boolean copy_tags_to_snapshot) {
		this.copy_tags_to_snapshot = copy_tags_to_snapshot;
	}

	/**
	 * @return the db_subnet_group_name
	 */
	public String getDb_subnet_group_name() {
		return db_subnet_group_name;
	}

	/**
	 * @param db_subnet_group_name the db_subnet_group_name to set
	 */
	public void setDb_subnet_group_name(String db_subnet_group_name) {
		this.db_subnet_group_name = db_subnet_group_name;
	}

	/**
	 * @return the deletion_protection
	 */
	public boolean isDeletion_protection() {
		return deletion_protection;
	}

	/**
	 * @param deletion_protection the deletion_protection to set
	 */
	public void setDeletion_protection(boolean deletion_protection) {
		this.deletion_protection = deletion_protection;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the domain_iam_role_name
	 */
	public String getDomain_iam_role_name() {
		return domain_iam_role_name;
	}

	/**
	 * @param domain_iam_role_name the domain_iam_role_name to set
	 */
	public void setDomain_iam_role_name(String domain_iam_role_name) {
		this.domain_iam_role_name = domain_iam_role_name;
	}

	/**
	 * @return the enabled_cloudwatch_logs_exports
	 */
	public String getEnabled_cloudwatch_logs_exports() {
		return enabled_cloudwatch_logs_exports;
	}

	/**
	 * @param enabled_cloudwatch_logs_exports the enabled_cloudwatch_logs_exports to set
	 */
	public void setEnabled_cloudwatch_logs_exports(String enabled_cloudwatch_logs_exports) {
		this.enabled_cloudwatch_logs_exports = enabled_cloudwatch_logs_exports;
	}

	/**
	 * @return the engin
	 */
	public String getEngin() {
		return engine;
	}

	/**
	 * @param engin the engin to set
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}

	/**
	 * @return the engin_version
	 */
	public String getEngine_version() {
		return engine_version;
	}

	/**
	 * @param engin_version the engin_version to set
	 */
	public void setEngin_version(String engine_version) {
		this.engine_version = engine_version;
	}

	/**
	 * @return the final_snapshot_identifier
	 */
	public String getFinal_snapshot_identifier() {
		return final_snapshot_identifier;
	}

	/**
	 * @param final_snapshot_identifier the final_snapshot_identifier to set
	 */
	public void setFinal_snapshot_identifier(String final_snapshot_identifier) {
		this.final_snapshot_identifier = final_snapshot_identifier;
	}

	/**
	 * @return the iam_database_authentication_enabled
	 */
	public boolean isIam_database_authentication_enabled() {
		return iam_database_authentication_enabled;
	}

	/**
	 * @param iam_database_authentication_enabled the iam_database_authentication_enabled to set
	 */
	public void setIam_database_authentication_enabled(boolean iam_database_authentication_enabled) {
		this.iam_database_authentication_enabled = iam_database_authentication_enabled;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the identifier_prefix
	 */
	public String getIdentifier_prefix() {
		return identifier_prefix;
	}

	/**
	 * @param identifier_prefix the identifier_prefix to set
	 */
	public void setIdentifier_prefix(String identifier_prefix) {
		this.identifier_prefix = identifier_prefix;
	}

	/**
	 * @return the instance_class
	 */
	public String getInstance_class() {
		return instance_class;
	}

	/**
	 * @param instance_class the instance_class to set
	 */
	public void setInstance_class(String instance_class) {
		this.instance_class = instance_class;
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
	 * @return the kms_key_id
	 */
	public String getKms_key_id() {
		return kms_key_id;
	}

	/**
	 * @param kms_key_id the kms_key_id to set
	 */
	public void setKms_key_id(String kms_key_id) {
		this.kms_key_id = kms_key_id;
	}

	/**
	 * @return the license_model
	 */
	public String getLicense_model() {
		return license_model;
	}

	/**
	 * @param license_model the license_model to set
	 */
	public void setLicense_model(String license_model) {
		this.license_model = license_model;
	}

	/**
	 * @return the maintenance_window
	 */
	public TimeWindow getMaintenance_window() {
		return maintenance_window;
	}

	/**
	 * @param maintenance_window the maintenance_window to set
	 */
	public void setMaintenance_window(TimeWindow maintenance_window) {
		this.maintenance_window = maintenance_window;
	}

	/**
	 * @return the monitoring_interval
	 */
	public int getMonitoring_interval() {
		return monitoring_interval;
	}

	/**
	 * @param monitoring_interval the monitoring_interval to set
	 * @throws InvalidTypeException 
	 */
	public void setMonitoring_interval(int monitoring_interval) throws InvalidTypeException {
		if(!MONITORING.contains(monitoring_interval)){
			throw new InvalidTypeException("Valid Values of monitoring_interval are 0, 1, 5, 10, 15, 30, and 60");
		}
		this.monitoring_interval = monitoring_interval;
	}

	/**
	 * @return the monitoring_role_arn
	 */
	public String getMonitoring_role_arn() {
		return monitoring_role_arn;
	}

	/**
	 * @param monitoring_role_arn the monitoring_role_arn to set
	 */
	public void setMonitoring_role_arn(String monitoring_role_arn) {
		this.monitoring_role_arn = monitoring_role_arn;
	}

	/**
	 * @return the multi_az
	 */
	public boolean isMulti_az() {
		return multi_az;
	}

	/**
	 * @param multi_az the multi_az to set
	 */
	public void setMulti_az(boolean multi_az) {
		this.multi_az = multi_az;
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
	 * @return the option_group_name
	 */
	public String getOption_group_name() {
		return option_group_name;
	}

	/**
	 * @param option_group_name the option_group_name to set
	 */
	public void setOption_group_name(String option_group_name) {
		this.option_group_name = option_group_name;
	}

	/**
	 * @return the parameter_group_name
	 */
	public String getParameter_group_name() {
		return parameter_group_name;
	}

	/**
	 * @param parameter_group_name the parameter_group_name to set
	 */
	public void setParameter_group_name(String parameter_group_name) {
		this.parameter_group_name = parameter_group_name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the publicly_accessible
	 */
	public boolean isPublicly_accessible() {
		return publicly_accessible;
	}

	/**
	 * @param publicly_accessible the publicly_accessible to set
	 */
	public void setPublicly_accessible(boolean publicly_accessible) {
		this.publicly_accessible = publicly_accessible;
	}

	/**
	 * @return the replicate_source_db
	 */
	public String getReplicate_source_db() {
		return replicate_source_db;
	}

	/**
	 * @param replicate_source_db the replicate_source_db to set
	 */
	public void setReplicate_source_db(String replicate_source_db) {
		this.replicate_source_db = replicate_source_db;
	}

	/**
	 * @return the security_group_names
	 */
	public ArrayList<String> getSecurity_group_names() {
		return security_group_names;
	}

	/**
	 * @param security_group_names the security_group_names to set
	 */
	public void setSecurity_group_names(ArrayList<String> security_group_names) {
		this.security_group_names = security_group_names;
	}

	/**
	 * @return the skip_final_snapshot
	 */
	public boolean isSkip_final_snapshot() {
		return skip_final_snapshot;
	}

	/**
	 * @param skip_final_snapshot the skip_final_snapshot to set
	 */
	public void setSkip_final_snapshot(boolean skip_final_snapshot) {
		this.skip_final_snapshot = skip_final_snapshot;
	}

	/**
	 * @return the snapshot_identifier
	 */
	public String getSnapshot_identifier() {
		return snapshot_identifier;
	}

	/**
	 * @param snapshot_identifier the snapshot_identifier to set
	 */
	public void setSnapshot_identifier(String snapshot_identifier) {
		this.snapshot_identifier = snapshot_identifier;
	}

	/**
	 * @return the storage_encrypted
	 */
	public boolean isStorage_encrypted() {
		return storage_encrypted;
	}

	/**
	 * @param storage_encrypted the storage_encrypted to set
	 */
	public void setStorage_encrypted(boolean storage_encrypted) {
		this.storage_encrypted = storage_encrypted;
	}

	/**
	 * @return the storage_type
	 */
	public String getStorage_type() {
		return storage_type;
	}

	/**
	 * @param storage_type the storage_type to set
	 */
	public void setStorage_type(String storage_type) {
		this.storage_type = storage_type;
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
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the vpc_security_group_ids
	 */
	public ArrayList<String> getVpc_security_group_ids() {
		return vpc_security_group_ids;
	}

	/**
	 * @param vpc_security_group_ids the vpc_security_group_ids to set
	 */
	public void setVpc_security_group_ids(ArrayList<String> vpc_security_group_ids) {
		this.vpc_security_group_ids = vpc_security_group_ids;
	}

	/**
	 * @return the s3_import
	 */
	public S3Import getS3_import() {
		return s3_import;
	}

	/**
	 * @param s3_import the s3_import to set
	 */
	public void setS3_import(S3Import s3_import) {
		this.s3_import = s3_import;
	}

	/**
	 * 
	 * @return
	 * @throws RequiredArgumentException
	 * @throws InvalidTypeException 
	 */
	private boolean isValid() throws RequiredArgumentException, InvalidTypeException{
		if(instance_class == null || instance_class.equals("")){
			throw new RequiredArgumentException("instance_class is required for the aws_db_instance object");
		}
		if((snapshot_identifier == null || snapshot_identifier.equals("")) && 
				(replicate_source_db == null || replicate_source_db.equals(""))){
			if(allocated_storage == null || allocated_storage.equals("")){
				throw new RequiredArgumentException("allocated_storage is required for the aws_db_instance object as long as neither snapshot_identifier nor replicate_source_db is provided");
			}
			if(username == null || username.equals("")){
				throw new RequiredArgumentException("username is required for the aws_db_instance object as long as neither snapshot_identifier nor replicate_source_db is provided");
			}
			if(password == null || password.equals("")){
				throw new RequiredArgumentException("password is required for the aws_db_instance object as long as neither snapshot_identifier nor replicate_source_db is provided");
			}
		}
		if(backup_retention_period <0 || backup_retention_period >35){
			throw new InvalidTypeException("backup_retention_period must be between 0 and 35");
		}		
		if(backup_window != null && maintenance_window != null){
			if(backup_window.hasOverlap(maintenance_window)){
				throw new InvalidTypeException("backup_window and  maintenance_window must not has overlap");
			}
		}
		if(!CHARSET.contains(character_set_name)){
			throw new InvalidTypeException("Charset needs to be a valid Oracle Charset");
		}
		if(enabled_cloudwatch_logs_exports!= null && !enabled_cloudwatch_logs_exports.equals("")){
			if(engine != null && !engine.equals("")){
				if(engine.equalsIgnoreCase("PostgreSQL") && !(enabled_cloudwatch_logs_exports.equalsIgnoreCase("postgresql") || enabled_cloudwatch_logs_exports.equalsIgnoreCase("upgrade"))){
					throw new InvalidTypeException("Possible values for enabled_cloudwatch_logs_exports are postgresql or upgrade for PostgreSQL engin");
				}else if(!LOG_EXPORTS.contains(enabled_cloudwatch_logs_exports)){
					throw new InvalidTypeException("Possible values for enabled_cloudwatch_logs_exports are alert, audit, error, general, listener, slowquery, trace for " + engine +" engin");
				}
			}
		} 
		if((snapshot_identifier == null || snapshot_identifier.equals("")) && 
				(replicate_source_db == null || replicate_source_db.equals(""))){
			if(engine == null || allocated_storage.equals("")){
				throw new RequiredArgumentException("engin is required for the aws_db_instance object as long as neither snapshot_identifier nor replicate_source_db is provided");
			}
			if(!ENGINES.contains("engin")){
				throw new InvalidTypeException("Possible values for engin are amazon aurora, mysql, mariadb, postgresql, oracle, sql server");
			}
		}
		if(engine_version != null && !engine_version.equals("")){
			if(!auto_minor_version_upgrade && !engine_version.matches("\\d{1,2}.\\d{1,2}.\\d{1,2}")){
				throw new InvalidTypeException("engin_version must match the pattern xx.xx.xx");
			}
			if(auto_minor_version_upgrade){
				if(!engine_version.matches("\\d{1,2}.\\d{1,2}")  || !engine_version.matches("\\d{1,2}.\\d{1,2}.\\d{1,2}")){
					throw new InvalidTypeException("engin_version must match the pattern xx.xx");
				}
			}
		}
		if(iops != null && !iops.equals("") && !storage_type.equalsIgnoreCase("io1")){
			throw new InvalidTypeException("storage_type must be io1 when iops is provided");
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
		
		String block = headIndent + "resource aws_db_instance \"" + this.resourceName + "\" { \n";
		block += elementIndent + "instance_class = \"" + instance_class + "\"\n";
		
		if(allocated_storage != null && !allocated_storage.equals("")){
			block += elementIndent + "allocated_storage = \"" + allocated_storage + "\"\n";
		}
		if(allow_major_version_upgrade){
			block += elementIndent + "allow_major_version_upgrade = " + allow_major_version_upgrade + "\n";
		}
		if(apply_immediately){
			block += elementIndent + "apply_immediately = " + apply_immediately + "\n";
		}
		if(auto_minor_version_upgrade){
			block += elementIndent + "auto_minor_version_upgrade = " + auto_minor_version_upgrade + "\n";
		}
		if(availability_zone != null && !availability_zone.equals("")){
			block += elementIndent + "availability_zone = \"" + availability_zone + "\"\n";
		}
		block += elementIndent + "backup_retention_period = " + backup_retention_period + "\n";
		if(backup_window != null){
			block += elementIndent + "backup_window = \"" + backup_window.getTimeWindow() + "\"\n";
		}
		if(character_set_name != null && !character_set_name.equals("")){
			block += elementIndent + "character_set_name = \"" + character_set_name + "\"\n";
		}
		if(copy_tags_to_snapshot){
			block += elementIndent + "copy_tags_to_snapshot = " + copy_tags_to_snapshot + "\n";
		}
		if(db_subnet_group_name != null && !db_subnet_group_name.equals("")){
			block += elementIndent + "db_subnet_group_name = \"" + db_subnet_group_name + "\"\n";
		}
		if(deletion_protection){
			block += elementIndent + "deletion_protection = " + deletion_protection + "\n";
		}
		if(domain != null && !domain.equals("")){
			block += elementIndent + "domain = \"" + domain + "\"\n";
		}
		if(domain_iam_role_name != null && !domain_iam_role_name.equals("")){
			block += elementIndent + "domain_iam_role_name = \"" + domain_iam_role_name + "\"\n";
		}
		if(enabled_cloudwatch_logs_exports != null && !enabled_cloudwatch_logs_exports.equals("")){
			block += elementIndent + "enabled_cloudwatch_logs_exports = \"" + enabled_cloudwatch_logs_exports + "\"\n";
		}
		if(engine != null && !engine.equals("")){
			block += elementIndent + "engin = \"" + engine + "\"\n";
		}
		if(engine_version != null && !engine_version.equals("")){
			block += elementIndent + "engin_version = \"" + engine_version + "\"\n";
		}
		if(final_snapshot_identifier != null && !final_snapshot_identifier.equals("")){
			block += elementIndent + "final_snapshot_identifier = \"" + final_snapshot_identifier + "\"\n";
		}
		if(iam_database_authentication_enabled){
			block += elementIndent + "iam_database_authentication_enabled = " + iam_database_authentication_enabled + "\n";
		}	
		if(identifier != null && !identifier.equals("")){
			block += elementIndent + "identifier = \"" + identifier + "\"\n";
		}
		if(identifier_prefix != null && !identifier_prefix.equals("")){
			block += elementIndent + "identifier_prefix = \"" + identifier_prefix + "\"\n";
		}
		if(iops != null && !iops.equals("")){
			block += elementIndent + "iops = \"" + iops + "\"\n";
		}
		if(kms_key_id != null && !kms_key_id.equals("")){
			block += elementIndent + "kms_key_id = \"" + kms_key_id + "\"\n";
		}
		if(license_model != null && !license_model.equals("")){
			block += elementIndent + "license_model = \"" + license_model + "\"\n";
		}
		if(maintenance_window != null){
			block += elementIndent + "maintenance_window = \"" + maintenance_window.getDayTimeWindow() + "\"\n";
		}
		block += elementIndent + "monitoring_interval = " + monitoring_interval + "\n";
		if(monitoring_role_arn != null && !monitoring_role_arn.equals("")){
			block += elementIndent + "monitoring_role_arn = \"" + monitoring_role_arn + "\"\n";
		}
		if(multi_az){
			block += elementIndent + "multi_az = " + multi_az + "\n";
		}
		if(name != null && !name.equals("")){
			block += elementIndent + "name = \"" + name + "\"\n";
		}
		if(option_group_name != null && !option_group_name.equals("")){
			block += elementIndent + "option_group_name = \"" + option_group_name + "\"\n";
		}
		if(parameter_group_name != null && !parameter_group_name.equals("")){
			block += elementIndent + "parameter_group_name = \"" + parameter_group_name + "\"\n";
		}
		if(password != null && !password.equals("")){
			block += elementIndent + "password = \"" + password + "\"\n";
		}
		if(username != null && !username.equals("")){
			block += elementIndent + "username = \"" + username + "\"\n";
		}
		if(port > 0){
			block += elementIndent + "port = " + port + "\n";
		}
		if(publicly_accessible){
			block += elementIndent + "publicly_accessible = " + publicly_accessible + "\n";
		}
		if(replicate_source_db != null && !replicate_source_db.equals("")){
			block += elementIndent + "replicate_source_db = \"" + replicate_source_db + "\"\n";
		}
		if(security_group_names != null && !security_group_names.isEmpty()){
			block += elementIndent + "security_group_names = \"" + this.appendDoubleQuotes(security_group_names) + "\"\n";
		}
		if(skip_final_snapshot){
			block += elementIndent + "skip_final_snapshot = " + skip_final_snapshot + "\n";
		}
		if(snapshot_identifier != null && !snapshot_identifier.equals("")){
			block += elementIndent + "snapshot_identifier = \"" + snapshot_identifier + "\"\n";
		}
		if(storage_encrypted){
			block += elementIndent + "storage_encrypted = " + storage_encrypted + "\n";
		}
		if(storage_type != null && !storage_type.equals("")){
			block += elementIndent + "storage_type = \"" + storage_type + "\"\n";
		}
		if(tags != null){
			block += elementIndent + tags.getBlock(level+1);
		}
		if(timezone != null && !timezone.equals("")){
			block += elementIndent + "timezone = \"" + timezone + "\"\n";
		}
		if(vpc_security_group_ids != null && !vpc_security_group_ids.isEmpty()){
			block += elementIndent + "vpc_security_group_ids = \"" + this.appendDoubleQuotes(vpc_security_group_ids) + "\"\n";
		}
		if(s3_import != null){
			block +=  s3_import.getBlock(level+1);
		}
		if(timeouts != null){
			block +=  timeouts.getBlock(level+1);
		}
		
		block += headIndent + "}\n";
		return block;
	}
}
