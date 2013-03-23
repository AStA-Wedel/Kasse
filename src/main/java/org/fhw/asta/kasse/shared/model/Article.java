package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

import org.fhw.asta.kasse.shared.common.EuroAmount;

/**
 * Container to represent a specific article stored in DB.
 * 
 * @author julian
 */
public class Article implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private int revision;

  private String name;

  private EuroAmount price;

  private String description;
 

  /**
   * Tax category name, determines, if an article has reduced/full/no tax rate.
   */
  private String taxCategoryName;

  /** Tax category revision, stored to track tax rate changes */
  private int taxRevision;

  /** Article can be disabled in the sense that it is not available for sell */
  private boolean enabled;

  /**
   * Creates an Object representation of a specfifc article stored in DB.
   * 
   * @param id
   * @param revision
   * @param name
   * @param price
   * @param taxCategoryName
   * @param taxRevision
   * @param enabled
   */
  public Article(final int id, final int revision, final String name,
      final String description, final EuroAmount price,
      final String taxCategoryName, final int taxRevision, final boolean enabled)
  {
    this.id = id;
    this.revision = revision;
    this.name = name;
    this.description = description;
    this.price = price;
    this.taxCategoryName = taxCategoryName;
    this.taxRevision = taxRevision;
    this.enabled = enabled;
  }
  

  /**
   * Default constructor, only exists because of Serialization. Will create an
   * empty article. Don't use.
   */
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

  public String getDescription()
  {
    return this.description;
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

  /**
   * Enable or disable an article (in the sense that it is not avaiable for
   * purchase/selling.
   * 
   * @param enabled
   *          true to enable an article, false to disable it
   */
  public void setEnabled(final boolean enabled)
  {
    this.enabled = enabled;
  }

  public EuroAmount getPrice()
  {
    return this.price;
  }

}
