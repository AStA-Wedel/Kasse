package org.fhw.asta.kasse.shared.model;

public class Stuff extends Article
{
  private static final long serialVersionUID = 1L;

  public Stuff(final int id, final int revision, final String name,
      final int centPrice, final String taxCategoryName, final int taxRevision,
      final boolean enabled)
  {
    super(id, revision, name, centPrice, taxCategoryName, taxRevision, enabled);
  }

  public Stuff()
  {
  }
}
