package org.fhw.asta.kasse.shared.model;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class Stuff extends Article
{
  private static final long serialVersionUID = 1L;

  public Stuff(final int id, final int revision, final String name,
      EuroAmount price, final String taxCategoryName, final int taxRevision,
      final boolean enabled)
  {
    super(id, revision, name, price, taxCategoryName, taxRevision, enabled);
  }

  public Stuff()
  {
  }
}
