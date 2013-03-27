package org.fhw.asta.kasse.shared.service.user;

import java.util.List;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.model.Account;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.model.PersonGroup;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

  AuthenticationResult authenticate(String email, String password);
  Optional<Person> getUserById(String ldapName);
  Optional<Person> getUserByIdAndRevision(String ldapName, int revision);
  List<Person> getAllUsers();
  List<Person> getUsersByGroup(String groupId);
  List<PersonGroup> getAllUserGroups();
  Boolean loggedOnUserIsAdmin();
  Optional<Account> getUserAccount(String ldapName);
  void updateOrCreateUserAndAccount(Person person,Account account);
}
