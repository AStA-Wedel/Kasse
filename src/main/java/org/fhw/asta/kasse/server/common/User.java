package org.fhw.asta.kasse.server.common;

import java.io.Serializable;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult.AuthenticationStatus;



import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	

	private AuthenticationStatus authenticationStatus = AuthenticationStatus.NOT_AUTHENTICATED;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}
	
	public AuthenticationStatus getAuthenticationStatus() {
		return authenticationStatus;
	}		
}
