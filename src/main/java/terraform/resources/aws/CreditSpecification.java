package terraform.resources.aws;

import exceptions.InvalidTypeException;
import terraform.provider.Constants;

public class CreditSpecification {

	private String cpu_credits;// Can be "standard" or "unlimited"

	/**
	 * @return the cpu_credits
	 */
	public String getCpu_credits() {
		return cpu_credits;
	}

	/**
	 * @param cpu_credits the cpu_credits to set
	 */
	public void setCpu_credits(String cpu_credits)throws InvalidTypeException{
		
		if(cpu_credits.equalsIgnoreCase("standard") || cpu_credits.equalsIgnoreCase("unlimited")){
			this.cpu_credits = cpu_credits;
		}else{
			throw new InvalidTypeException("cpu_credits type can be standard or unlimited only");
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
		String block = headIndent + "credit_specification { \n" ;
		
		if(cpu_credits != null && !cpu_credits.equals("")){
			block += elementIndent + "cpu_credits = " + cpu_credits +"\n";
		}
		
		block += headIndent + "}\n";
		return block; 
	}
	
}
