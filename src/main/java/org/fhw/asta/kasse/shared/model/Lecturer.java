package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;
import java.util.List;

public class Lecturer implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private String surname;

  private String prename;

  private String title;

  private List<Script> scripts;

  public Lecturer(final int id, final String surname, final String prename,
      final String title, final List<Script> scripts)
  {
    this.id = id;
    this.surname = surname;
    this.prename = prename;
    this.title = title;
    this.scripts = scripts;
  }

  public int getId()
  {
    return this.id;
  }

  public String getSurname()
  {
    return this.surname;
  }

  public String getPrename()
  {
    return this.prename;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getFullName()
  {
    return new StringBuilder(this.title).append(' ').append(this.prename)
        .append(' ').append(this.surname).toString();
  }

  public List<Script> getScripts()
  {
    return this.scripts;
  }
}
