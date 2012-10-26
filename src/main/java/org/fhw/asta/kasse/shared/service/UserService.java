package org.fhw.asta.kasse.shared.service;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

	AuthenticationResult authenticate(String email, String password);
		
}
