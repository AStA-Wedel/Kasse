package org.fhw.asta.kasse.server.servlet;

import org.fhw.asta.kasse.shared.service.user.UserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceServlet extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	public UserServiceServlet(UserService userService) {
		super(userService);
	}
	
}
