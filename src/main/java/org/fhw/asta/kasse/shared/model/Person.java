package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class Person implements Serializable {
  public static final long serialVersionUID = 1L;

  private String ldapName;
  private int revision;
  private String surname;
  private String prename;
  private String matrNo;
  private boolean isAdmin;
  private int groupId;
  private String phoneMobile;
  private String phoneHome;
  private String street;
  private String zipcode;
  private String town;
  private String streetnumber;
  private String email;

  public Person(String ldapName, int revision, String surname, String prename, String matrNo, boolean isAdmin,
      int groupId, String phoneMobile, String phoneHome, String street, String zipcode, String town,
      String streetnumber, String email) {
    this.ldapName = ldapName;
    this.revision = revision;
    this.surname = surname;
    this.prename = prename;
    this.matrNo = matrNo;
    this.isAdmin = isAdmin;
    this.groupId = groupId;
    this.phoneMobile = phoneMobile;
    this.phoneHome = phoneHome;
    this.street = street;
    this.zipcode = zipcode;
    this.town = town;
    this.streetnumber = streetnumber;
    this.email = email;
  }

  public Person() {
  };

  public String getLdapName() {
    return this.ldapName;
  }

  public int getRevision() {
    return this.revision;
  }

  public String getSurname() {
    return this.surname;
  }

  public String getPrename() {
    return this.prename;
  }

  public String getMatrNo() {
    return this.matrNo;
  }

  public boolean isAdmin() {
    return this.isAdmin;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public String getPhoneMobile() {
    return this.phoneMobile;
  }

  public String getPhoneHome() {
    return this.phoneHome;
  }

  public String getStreet() {
    return this.street;
  }

  public String getZipcode() {
    return this.zipcode;
  }

  public String getTown() {
    return this.town;
  }

  public String getStreetnumber() {
    return this.streetnumber;
  }

  public String getEmail() {
    return this.email;
  }

}
