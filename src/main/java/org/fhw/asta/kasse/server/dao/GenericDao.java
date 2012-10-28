package org.fhw.asta.kasse.server.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.inject.Inject;

public abstract class GenericDao
{
  @Inject
  protected JdbcTemplate template;
}
