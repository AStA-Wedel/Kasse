package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.model.Person;
import org.springframework.jdbc.core.RowMapper;

import com.google.common.base.Optional;

public class UserDao extends GenericDao
{
  public List<Person> getAllUsers()
  {
    return this.template.query("SELECT * FROM person p WHERE revision=(SELECT MAX(revision) FROM person WHERE ldap_name = p.ldap_name GROUP BY revision", new PersonRowMapper());
  }
  
  public Optional<Person> getPersonById(final String ldapName) {
	 
	  Person person = null;
	  
	  person = this.template.queryForObject("SELECT * FROM person WHERE ldap_name = ? AND revision = (SELECT MAX(revision) FROM person WHERE ldap_name = ?)", new PersonRowMapper());
	  
	  return Optional.fromNullable(person);
  }
  
  
  private static final class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet arg0, int arg1) throws SQLException {
		
        return new Person(arg0.getString(0), arg0.getInt(1), arg0.getString(2),
                arg0.getString(3), arg0.getInt(4), arg0.getBoolean(5), arg0
                    .getInt(6));		

		}

  }
  
  

}
