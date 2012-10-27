package org.fhw.asta.kasse.shared.model;


public class Script extends Article
{
  private static final long serialVersionUID = 1L;

  private int lecturerId;

  public Script(final int id, final int revision, final String name,
      final int centPrice, final String taxCategoryName, final int taxRevision,
      final boolean enabled, final int lecturerId)
  {
    super(id, revision, name, centPrice, taxCategoryName, taxRevision, enabled);

    this.lecturerId = lecturerId;
  }

  public int getLecturerId()
  {
    return this.lecturerId;
  }
}
