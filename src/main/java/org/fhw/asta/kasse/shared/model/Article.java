package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class Article implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private int revision;

  private String name;

  private int centPrice;

  private String taxCategoryName;

  private int taxRevision;

  private boolean enabled;

  public Article(final int id, final int revision, final String name,
      final int centPrice, final String taxCategoryName, final int taxRevision,
      final boolean enabled)
  {
    this.id = id;
    this.revision = revision;
    this.name = name;
    this.centPrice = centPrice;
    this.taxCategoryName = taxCategoryName;
    this.taxRevision = taxRevision;
    this.enabled = enabled;
  }

  public int getId()
  {
    return this.id;
  }

  public int getRevision()
  {
    return this.revision;
  }

  public String getName()
  {
    return this.name;
  }

  public int getCentPrice()
  {
    return this.centPrice;
  }

  public String getTaxCategoryName()
  {
    return this.taxCategoryName;
  }

  public int getTaxRevision()
  {
    return this.taxRevision;
  }

  public boolean isEnabled()
  {
    return this.enabled;
  }

  public void setEnabledFlag(final boolean enabled)
  {
    this.enabled = enabled;
  }
}
