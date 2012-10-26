package org.fhw.asta.kasse.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void login(String email, String password, AsyncCallback<Boolean> callback);

}
