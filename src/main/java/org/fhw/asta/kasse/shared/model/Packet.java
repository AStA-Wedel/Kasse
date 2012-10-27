package org.fhw.asta.kasse.shared.model;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class Packet extends Article
{
  private static final long serialVersionUID = 1L;

  public Packet(final int id, final int revision, final String name,
      final EuroAmount price, final String taxCategoryName, final int taxRevision,
      final boolean enabled)
  {
    super(id, revision, name, price, taxCategoryName, taxRevision, enabled);
  }

  public Packet()
  {
  }
}
