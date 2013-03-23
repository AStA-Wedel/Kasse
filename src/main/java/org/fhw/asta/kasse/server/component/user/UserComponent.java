package org.fhw.asta.kasse.server.component.user;

import org.fhw.asta.kasse.server.common.User;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult.AuthenticationStatus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class UserComponent {

	@Inject
	private Provider<User> userProvider;
	
	public User getUser() {
		return userProvider.get();
	}
	
	//TODO
	public User authenticate(String email, String password) {
		
		//HashCode passwordHash = Hashing.md5().hashString(password); // FIXME
		
		User user = userProvider.get();
		user.setEmail(email);
		user.setAuthenticationStatus(AuthenticationStatus.AUTHENTICATED);
		
		return user;
	}
	
	
}
