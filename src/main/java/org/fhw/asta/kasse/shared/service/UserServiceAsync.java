package org.fhw.asta.kasse.shared.service;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void authenticate(String email, String password,
			AsyncCallback<AuthenticationResult> callback);

}
