package terraform.resources;

import java.util.ArrayList;

import terraform.TerraformObject;

public abstract class Resource extends TerraformObject{
	protected String resourceType;
	protected String resourceName; //required
	protected int count;
	protected ArrayList<String> depends_on;
	protected String provider;
	protected Lifecycle lifecycle;
	protected Timeouts timeouts;
	protected Connection connection;
	protected ArrayList<Provisioner> provisioners;
	
	public Resource(String resourceName){
		this.resourceName = resourceName;
		objectType = "resource";
	}
	
	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}
	
	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}



	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the depends_on
	 */
	public ArrayList<String> getDepends_on() {
		return depends_on;
	}
	/**
	 * @param depends_on the depends_on to set
	 */
	public void setDepends_on(ArrayList<String> depends_on) {
		this.depends_on = depends_on;
	}
	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}
	/**
	 * @return the lifecycle
	 */
	public Lifecycle getLifecycle() {
		return lifecycle;
	}
	/**
	 * @param lifecycle the lifecycle to set
	 */
	public void setLifecycle(Lifecycle lifecycle) {
		this.lifecycle = lifecycle;
	}
	/**
	 * @return the timeouts
	 */
	public Timeouts getTimeouts() {
		return timeouts;
	}
	/**
	 * @param timeouts the timeouts to set
	 */
	public void setTimeouts(Timeouts timeouts) {
		this.timeouts = timeouts;
	}
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	/**
	 * @return the provisioners
	 */
	public ArrayList<Provisioner> getProvisioners() {
		return provisioners;
	}
	/**
	 * @param provisioners the provisioners to set
	 */
	public void setProvisioners(ArrayList<Provisioner> provisioners) {
		this.provisioners = provisioners;
	}
}
