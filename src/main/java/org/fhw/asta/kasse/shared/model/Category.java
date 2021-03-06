package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class Category implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  public Category(final int id, final String name)
  {
    this.id = id;
    this.name = name;
  }

  public Category()
  {
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
