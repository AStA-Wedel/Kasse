package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class PersonGroup implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private boolean bankAccountMandatory;

  private boolean addressMandatory;

  public PersonGroup(final int id, final String name,
      final boolean bankAccountMandatory, final boolean addressMandatory)
  {
    this.id = id;
    this.name = name;
    this.bankAccountMandatory = bankAccountMandatory;
    this.addressMandatory = addressMandatory;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public boolean isBankAccountMandatory()
  {
    return this.bankAccountMandatory;
  }

  public boolean isAddressMandatory()
  {
    return this.addressMandatory;
  }
}
