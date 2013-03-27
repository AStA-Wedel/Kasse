package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class Account implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String owner;
	private String number;
	private String blz;
	private String ldapName;
	
	public Account() {
		
	}
	
	public Account(final String ldapName, final String owner, final String number, final String blz) {
		this.ldapName = ldapName;
		this.owner = owner;
		this.number = number;
		this.blz = blz;
	}
	
	
	public String getOwner() {
		return owner;
	}
	public String getNumber() {
		return number;
	}
	public String getBlz() {
		return blz;
	}

	public String getLdapName() {
		return ldapName;
	}

}
