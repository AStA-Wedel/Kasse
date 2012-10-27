package org.fhw.asta.kasse.server.service;

import org.fhw.asta.kasse.server.component.user.UserComponent;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.service.UserService;

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
		//LOGGER.info("authenticate called");		
		
		return new AuthenticationResult(AuthenticationResult.AuthenticationStatus.AUTHENTICATED);
	}


}
