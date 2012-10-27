package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class TaxCategory implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String name;

  private int revision;

  private float taxRatePercent;

  public TaxCategory(final String name, final int revision,
      final float taxRatePercent)
  {
    this.name = name;
    this.revision = revision;
    this.taxRatePercent = taxRatePercent;
  }

  public TaxCategory()
  {
  }

  public String getName()
  {
    return this.name;
  }

  public int getRevision()
  {
    return this.revision;
  }

  public float getTaxRatePercent()
  {
    return this.taxRatePercent;
  }

}
