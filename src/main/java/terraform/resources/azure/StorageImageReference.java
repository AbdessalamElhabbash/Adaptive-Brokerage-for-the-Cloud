package terraform.resources.azure;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class StorageImageReference {

	private String publisher; //required when provision from an Azure Platform Image
	private String offer; //required  when provision from an Azure Platform Image
	private String sku; //required  when provision from an Azure Platform Image
	private String version;
	private String id; //required  when provision a custom Image
	
	
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
	 * @return the offer
	 */
	public String getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(String offer) {
		this.offer = offer;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Set parameters for provisioning from an Azure Platform Image
	 * @param publisher
	 * @param offer
	 * @param sku
	 * @throws InvalidTypeException
	 */
	public void provisionFromAzurePlatformImage(String publisher, String offer, String sku) throws InvalidTypeException{
		if(publisher == null || publisher.equals("")){
			throw new InvalidTypeException("Valid publisher is required");
		}
		if(offer == null || offer.equals("")){
			throw new InvalidTypeException("Valid offer is required");
		}
		if(sku == null || sku.equals("")){
			throw new InvalidTypeException("Valid sku is required");
		}
		this.publisher = publisher;
		this.offer = offer;
		this.sku = sku;		
	}
	
	/**
	 * 
	 * @param version
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * Set parameters for provisioning a custom image
	 * @param id
	 * @throws InvalidTypeException
	 */
	public void provisionCustomImage(String id) throws InvalidTypeException{
		if(id == null || id.equals("")){
			throw new InvalidTypeException("id is required");
		}
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
		String block = headIndent + "storage_image_reference { \n";
		
		if(publisher != null && !publisher.equals("")){
			block += elementIndent + "publisher = \"" + publisher + "\"\n";
		}
		if(offer != null && !offer.equals("")){
			block += elementIndent + "offer = \"" + offer + "\"\n";
		}
		if(sku != null && !sku.equals("")){
			block += elementIndent + "sku = \"" + sku + "\"\n";
		}
		if(version != null && !version.equals("")){
			block += elementIndent + "version = \"" + version + "\"\n";
		}
		if(id != null && !id.equals("")){
			block += elementIndent + "id = \"" + id + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
	
}
