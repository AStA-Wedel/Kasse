package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.inject.Inject;

public class UserDao
{

  @Inject
  private JdbcTemplate template;

  public List<Person> getAllUsers()
  {
    return this.template.query("SELECT * FROM person", new RowMapper<Person>() {

      @Override
      public Person mapRow(final ResultSet arg0, final int arg1)
          throws SQLException
      {
        return null;
      }

    });
  }

}
