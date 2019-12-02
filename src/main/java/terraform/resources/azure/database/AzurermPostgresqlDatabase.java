package terraform.resources.azure.database;

import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;

public class AzurermPostgresqlDatabase extends Resource{
	
	public AzurermPostgresqlDatabase(String resourceName) {
		super(resourceName);
	}
	private String name; //Required needs to be a valid PostgreSQL identifier
	private String server_name; //Required
	private String resource_group_name; //Required
	private String charset; //Required. needs to be a valid PostgreSQL Charset
	private String collation;
	public static final Set<String> CHARSET;

	static {
		CHARSET = new HashSet<String>();
		CHARSET.add("big5");
		CHARSET.add("euc_cn");
		CHARSET.add("euc_jp");
		CHARSET.add("euc_jis_2004");
		CHARSET.add("euc_kr");
		CHARSET.add("euc_tw");
		CHARSET.add("gb18030");
		CHARSET.add("gbk");
		CHARSET.add("iso_8859_5");
		CHARSET.add("iso_8859_6");
		CHARSET.add("iso_8859_7");
		CHARSET.add("iso_8859_8");
		CHARSET.add("johab");
		CHARSET.add("koi8r");
		CHARSET.add("koi8u");
		CHARSET.add("latin1");
		CHARSET.add("latin2");
		CHARSET.add("latin3");
		CHARSET.add("latin4");
		CHARSET.add("latin5");
		CHARSET.add("latin6");
		CHARSET.add("latin7");
		CHARSET.add("latin8");
		CHARSET.add("latin9");
		CHARSET.add("latin10");
		CHARSET.add("mule_internal");
		CHARSET.add("sjis");
		CHARSET.add("shift_jis_2004");
		CHARSET.add("sql_ascii");
		CHARSET.add("uhc");
		CHARSET.add("utf8");
		CHARSET.add("win866");
		CHARSET.add("win874");
		CHARSET.add("win1250");
		CHARSET.add("win1251");
		CHARSET.add("win1252");
		CHARSET.add("win1253");
		CHARSET.add("win1254");
		CHARSET.add("win1255");
		CHARSET.add("win1256");
		CHARSET.add("win1257");
		CHARSET.add("win1258");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServer_name() {
		return server_name;
	}

	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	public String getResource_group_name() {
		return resource_group_name;
	}

	public void setResource_group_name(String resource_group_name) {
		this.resource_group_name = resource_group_name;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	/**
	 * 
	 * @return
	 * @throws RequiredArgumentException
	 * @throws InvalidTypeException 
	 */
	private boolean isValid() throws RequiredArgumentException, InvalidTypeException{
		if(name == null || name.equals("")){
			throw new RequiredArgumentException("name is a required config for the azurerm_postgresql_database object");
		}
		if(server_name == null || server_name.equals("")){
			throw new RequiredArgumentException("server_name is a required config for the azurerm_postgresql_database object");
		}
		if(resource_group_name == null || resource_group_name.equals("")){
			throw new RequiredArgumentException("resource_group_name is a required config for the azurerm_postgresql_database object");
		}
		if(charset == null || charset.equals("")){
			throw new RequiredArgumentException("charset is a required config for the azurerm_postgresql_database object");
		}
		if(!CHARSET.contains(charset.toLowerCase())){
			throw new InvalidTypeException("Charset needs to be a valid PostgreSQL Charset");
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
		
		String block = headIndent + "resource azurerm_postgresql_database \"" + this.resourceName + "\" { \n";
		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "server_name = \"" + server_name + "\"\n";
		block += elementIndent + "resource_group_name = \"" + resource_group_name + "\"\n";
		block += elementIndent + "charset = \"" + charset + "\"\n";


		if(collation != null && !collation.equals("")){
			block += elementIndent + "collation = \"" + collation + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}	
	
	/**
	 * returns interpolated reference to the id
	 * @return
	 */
	public String getIDReference() {
		return "${azurerm_postgresql_database." + resourceName + ".id}";
	}

}
