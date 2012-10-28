package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

/**
 * Container to represent a specific address stored in DB. An address always
 * belongs to a single Person.
 * 
 * @author Julian Wefers
 */
public class Address implements Serializable
{
  private static final long serialVersionUID = 1L;

  /** Ldap name of the address owner */
  private String ownerLdapName;

  /** Revision number of the address owner */
  private int ownerRevision;

  private String street;

  private String zipcode;

  private String town;

  private String streetnumber;

  /**
   * Creates an object representation for a specific address belonging to a
   * specific person.
   * 
   * @param ownerLdapName
   * @param ownerRevision
   * @param street
   * @param zipcode
   * @param town
   * @param streetnumber
   */
  public Address(final String ownerLdapName, final int ownerRevision,
      final String street, final String zipcode, final String town,
      final String streetnumber)
  {
    this.ownerLdapName = ownerLdapName;
    this.ownerRevision = ownerRevision;
    this.street = street;
    this.zipcode = zipcode;
    this.town = town;
    this.streetnumber = streetnumber;
  }

  public Address()
  {
  }

  public String getOwnerLdapName()
  {
    return this.ownerLdapName;
  }

  public int getOwnerRevision()
  {
    return this.ownerRevision;
  }

  public String getStreet()
  {
    return this.street;
  }

  public String getZipcode()
  {
    return this.zipcode;
  }

  public String getTown()
  {
    return this.town;
  }

  public String getStreetnumber()
  {
    return this.streetnumber;
  }
}
