package org.fhw.asta.kasse.shared.service.user;

import java.util.List;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.model.Account;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.model.PersonGroup;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

  void authenticate(String email, String password, AsyncCallback<AuthenticationResult> callback);

  void getUserById(String ldapName, AsyncCallback<Optional<Person>> callback);

  void getUserByIdAndRevision(String ldapName, int revision, AsyncCallback<Optional<Person>> callback);

  void getAllUsers(AsyncCallback<List<Person>> callback);

void getAllUserGroups(AsyncCallback<List<PersonGroup>> callback);

void getUsersByGroup(String groupId, AsyncCallback<List<Person>> callback);

void loggedOnUserIsAdmin(AsyncCallback<Boolean> callback);

void getUserAccount(String ldapName, AsyncCallback<Optional<Account>> callback);

void updateOrCreateUserAndAccount(Person person, Account account,
		AsyncCallback<Void> callback);
}
