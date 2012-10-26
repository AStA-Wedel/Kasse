package org.fhw.asta.kasse.server.service;

import org.fhw.asta.kasse.shared.service.UserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(String email, String password) throws Exception {		
		return "alex".equals(email);
	}

}
