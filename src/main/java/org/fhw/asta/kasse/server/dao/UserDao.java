package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.model.Person;
import org.springframework.jdbc.core.RowMapper;

public class UserDao extends GenericDao
{
  public List<Person> getAllUsers()
  {
    return this.template.query("SELECT * FROM person", new RowMapper<Person>() {

      @Override
      public Person mapRow(final ResultSet arg0, final int arg1)
          throws SQLException
      {
        return new Person(arg0.getString(0), arg0.getInt(1), arg0.getString(2),
            arg0.getString(3), arg0.getInt(4), arg0.getBoolean(5), arg0
                .getInt(6));
      }

    });
  }

}
