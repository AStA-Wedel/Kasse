package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class Person implements Serializable
{
  public static final long serialVersionUID = 1L;

  private String ldapName;

  private int revision;

  private String surname;

  private String prename;

  private int matrNo;

  private boolean isAdmin;

  private int groupId;

  public Person(final String ldapName, final int revision,
      final String surname, final String prename, final int matrNo,
      final boolean isAdmin, final int groupId)
  {
    this.ldapName = ldapName;
    this.revision = revision;
    this.surname = surname;
    this.prename = prename;
    this.matrNo = matrNo;
    this.isAdmin = isAdmin;
    this.groupId = groupId;
  }

  public String getLdapName()
  {
    return this.ldapName;
  }

  public int getRevision()
  {
    return this.revision;
  }

  public String getSurname()
  {
    return this.surname;
  }

  public String getPrename()
  {
    return this.prename;
  }

  public String getFullName()
  {
    return this.prename + ' ' + this.surname;
  }

  public int getMatrNo()
  {
    return this.matrNo;
  }

  public boolean isAdmin()
  {
    return this.isAdmin;
  }

  public void setAdminFlag(final boolean isAdmin)
  {
    this.isAdmin = isAdmin;
  }

  public int getGroupId()
  {
    return this.groupId;
  }

  public void setGroupId(final int groupId)
  {
    this.groupId = groupId;
  }
}
