package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class Article implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private int revision;

  private String name;

  private EuroAmount price;

  private String taxCategoryName;

  private int taxRevision;

  private boolean enabled;

  public Article(final int id, final int revision, final String name,
      final EuroAmount price, final String taxCategoryName, final int taxRevision,
      final boolean enabled)
  {
    this.id = id;
    this.revision = revision;
    this.name = name;
    this.price = price;
    this.taxCategoryName = taxCategoryName;
    this.taxRevision = taxRevision;
    this.enabled = enabled;
  }

  public Article()
  {
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
    return price.getCentAmount();
  }

  public String getPriceString()
  {
	  return price.toString();
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
