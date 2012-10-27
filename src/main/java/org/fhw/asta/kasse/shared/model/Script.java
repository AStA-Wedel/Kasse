package org.fhw.asta.kasse.shared.model;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class Script extends Article
{
  private static final long serialVersionUID = 1L;

  private int lecturerId;

  public Script(final int id, final int revision, final String name,
      final EuroAmount price, final String taxCategoryName, final int taxRevision,
      final boolean enabled, final int lecturerId)
  {
    super(id, revision, name, price, taxCategoryName, taxRevision, enabled);

    this.lecturerId = lecturerId;
  }

  public Script()
  {
  }

  public int getLecturerId()
  {
    return this.lecturerId;
  }
}
