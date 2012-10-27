package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class AccountingCategory implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  public AccountingCategory(final int id, final String name)
  {
    this.id = id;
    this.name = name;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }
}
