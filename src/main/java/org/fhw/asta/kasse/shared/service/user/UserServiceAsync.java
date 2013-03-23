package org.fhw.asta.kasse.shared.service.user;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.model.Person;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

  void authenticate(String email, String password, AsyncCallback<AuthenticationResult> callback);

  void getUserById(String ldapName, AsyncCallback<Optional<Person>> callback);

  void getUserByIdAndRevision(String ldapName, int revision, AsyncCallback<Optional<Person>> callback);
}
