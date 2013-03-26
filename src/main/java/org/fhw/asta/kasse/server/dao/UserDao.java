package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.model.PersonGroup;
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
          arg0.getString(11), arg0.getString(12), arg0.getString(13), arg0.getString(14));
    }
  }
  
  private static final class PersonGroupRowMapper implements RowMapper<PersonGroup> {
	    @Override
	    public PersonGroup mapRow(ResultSet arg0, int arg1) throws SQLException {
	      return new PersonGroup(arg0.getInt(1), arg0.getString(2));
	    }
	  }

  public List<Person> getAllUsers() {
    return this.template
        .query(
            "SELECT p1.* FROM person p1 WHERE p1.revision=(SELECT MAX(p2.revision) FROM person p2 WHERE p1.ldap_name = p2.ldap_name) AND p1.ldap_name <> 'default'",
            new PersonRowMapper());
  }

  public List<PersonGroup> getAllUserGroups() {
	  return this.template.query("SELECT * FROM person_group", new PersonGroupRowMapper());
  }
  
  public boolean exists(String ldapName) {
	return getPersonById(ldapName).isPresent();
  }
  
  public List<Person> getUsersByGroup(String groupId) {
	  return this.template
		        .query(
		            "SELECT p1.* FROM person p1 WHERE p1.revision=(SELECT MAX(p2.revision) FROM person p2 WHERE p1.ldap_name = p2.ldap_name) AND p1.ldap_name <> 'default' AND p1.group_id = ?",
		            new Object[] { groupId },new PersonRowMapper());
  }
  
  public Optional<Person> getPersonById(final String ldapName) {
	return queryForObject("SELECT p1.* FROM person p1 WHERE p1.ldap_name = ? AND p1.revision ="
            + "(SELECT MAX(p2.revision) FROM person p2 WHERE p2.ldap_name = p1.ldap_name)", new Object[]{ldapName}, new PersonRowMapper());	  
  }

  public Optional<Person> getPersonByIdAndRevision(String ldapName, int revision) {
    return Optional.fromNullable(this.template.queryForObject(
        "SELECT * FROM person WHERE ldap_name = ? AND revision = ?", new Object[]{ldapName, revision},
        new PersonRowMapper()));
  }

public Boolean userIsAdmin(String ldapName) {
	return queryForObject("SELECT p1.* FROM person p1 WHERE p1.ldap_name = ? AND p1.revision ="
            + "(SELECT MAX(p2.revision) FROM person p2 WHERE p2.ldap_name = p1.ldap_name) AND p1.is_admin = TRUE", new Object[]{ldapName}, new PersonRowMapper()).isPresent();	
}
  
 
}
