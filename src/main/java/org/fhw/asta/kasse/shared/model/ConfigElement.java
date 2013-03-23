package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;

public class ConfigElement implements Serializable {

  private static final long serialVersionUID = 1L;

  private String key;
  private String value;

  public ConfigElement(final String key, final String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return this.key;
  }

  public String getValue() {
    return this.value;
  }
}
