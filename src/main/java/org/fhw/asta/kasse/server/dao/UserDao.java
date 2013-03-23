package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.model.Person;
import org.springframework.jdbc.core.RowMapper;

import com.google.common.base.Optional;
import com.google.inject.Singleton;

@Singleton
public class UserDao extends GenericDao {

  private static final class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet arg0, int arg1) throws SQLException {
      return new Person(arg0.getString(1), arg0.getInt(2), arg0.getString(3), arg0.getString(4), arg0.getString(5),
          arg0.getBoolean(6), arg0.getInt(7), arg0.getString(8), arg0.getString(9), arg0.getString(10),
          arg0.getString(11), arg0.getString(12), arg0.getString(13));
    }
  }

  public List<Person> getAllUsers() {
    return this.template
        .query(
            "SELECT p1.* FROM person p1 WHERE p1.revision=(SELECT MAX(p2.revision) FROM person p2 WHERE p1.ldap_name = p2.ldap_name)",
            new PersonRowMapper());
  }

  public Optional<Person> getPersonById(final String ldapName) {
    Person person = null;
    person = this.template
        .queryForObject(
            "SELECT p1.* FROM person p1 WHERE p1.ldap_name = ? AND p1.revision = (SELECT MAX(p2.revision) FROM person p2 WHERE p2.ldap_name = p1.ldap_name)",
            new Object[]{ldapName}, new PersonRowMapper());

    return Optional.fromNullable(person);
  }

}
