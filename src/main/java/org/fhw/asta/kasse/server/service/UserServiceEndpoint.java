package org.fhw.asta.kasse.server.service;

import org.fhw.asta.kasse.server.common.User;
import org.fhw.asta.kasse.server.component.user.UserComponent;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.service.user.UserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceEndpoint extends RemoteServiceServlet implements UserService {

	private static final long serialVersionUID = 1L;
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceEndpoint.class);
	
	@Inject
	private UserComponent userComponent;
	
	@Override
	public AuthenticationResult authenticate(String email, String password) {

		User user = userComponent.authenticate(email, password);
		
		return new AuthenticationResult(user.getAuthenticationStatus());
	}

}
