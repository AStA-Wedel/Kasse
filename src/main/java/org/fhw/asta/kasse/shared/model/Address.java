package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class Address implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String ownerLdapName;

  private int ownerRevision;

  private String street;

  private String zipcode;

  private String town;

  private String streetnumber;

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
