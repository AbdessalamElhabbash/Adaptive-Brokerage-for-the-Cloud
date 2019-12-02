package terraform.resources.azure;

import terraform.provider.Constants;

public class Plan {

	private String name; //Required
	private String publisher; //Required
	private String product; //Required
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
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(){
		if(name == null || name.equals("")){
			return false;
		}
		if(publisher == null || publisher.equals("")){
			return false;
		}
		if(product == null || product.equals("")){
			return false;
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
		String block = headIndent + "plan { \n";
		
		block += elementIndent + "name = " + name + "\n";
		block += elementIndent + "publisher = " + publisher + "\n";
		block += elementIndent + "product = " + product + "\n";
		
		block += headIndent + "}\n";
		return block;
	}
}
