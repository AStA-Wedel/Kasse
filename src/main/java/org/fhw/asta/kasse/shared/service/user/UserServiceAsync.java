package org.fhw.asta.kasse.shared.service.user;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.model.Person;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void authenticate(String email, String password,
			AsyncCallback<AuthenticationResult> callback);

	void getUserById(int id, AsyncCallback<Person> callback);

}
