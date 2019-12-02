package terraform.provider;

import terraform.TerraformObject;

public abstract class Provider extends TerraformObject {

	protected String alias; //required
	protected String version;
	protected String name;//required
	
	public abstract String getIDReference();
}
