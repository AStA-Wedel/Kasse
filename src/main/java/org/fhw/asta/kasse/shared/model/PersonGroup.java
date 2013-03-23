package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class PersonGroup implements Serializable {
  private static final long serialVersionUID = 1L;

  private int groupId;
  private String name;

  public PersonGroup(final int groupId, final String name) {
    this.groupId = groupId;
    this.name = name;
  }

  public PersonGroup() {
  }

  public int getGroupId() {
    return this.groupId;
  }

  public String getName() {
    return this.name;
  }
}