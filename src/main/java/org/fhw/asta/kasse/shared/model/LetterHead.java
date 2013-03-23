package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class LetterHead implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street;

	  private String zipcode;

	  private String town;

	  private String streetnumber;
	  
	  private String name;
	  
	  public LetterHead() {
		 
	  }
	  
	  public LetterHead(String street, String zipcode,String town,String streetnumber, String name) {
		  this.street = street;
		  this.zipcode = zipcode;
		  this.town = town;
		  this.streetnumber = streetnumber;
		  this.name = name;
	}
	  
	public String getStreet() {
		return street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getTown() {
		return town;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public String getName() {
		return name;
	}
}
