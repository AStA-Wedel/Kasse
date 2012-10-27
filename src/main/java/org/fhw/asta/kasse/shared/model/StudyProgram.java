package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyProgram implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private String degree;

  private String shortName;

  private List<Script> scripts;

  public StudyProgram(final int id, final String name, final String degree,
      final String shortName, final List<Script> scripts)
  {
    this.id = id;
    this.name = name;
    this.degree = degree;
    this.shortName = shortName;
    this.scripts = scripts;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getDegree()
  {
    return this.degree;
  }

  public String getShortName()
  {
    return this.shortName;
  }

  public List<Script> getScripts()
  {
    return this.scripts;
  }

  public void setScripts(final List<Script> scripts)
  {
    this.scripts = scripts;
  }

  public void addScript(final Script script)
  {
    if (this.scripts == null)
      this.scripts = new ArrayList<Script>(1);

    this.scripts.add(script);
  }

  public void removeScript(final Script script)
  {
    if (this.scripts != null)
      this.scripts.remove(script);
  }

}
