package org.fhw.asta.kasse.shared.authentication;

import java.io.Serializable;

// TODO: weiter ausarbeiten User sollte mit rein!
public class AuthenticationResult implements Serializable {

	private static final long serialVersionUID = -3053536034936040454L;

	private AuthenticationStatus authenticationStatus;

	public AuthenticationResult() {
		this(AuthenticationStatus.NOT_AUTHENTICATED);
	}
	
	public AuthenticationResult(AuthenticationStatus authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	public AuthenticationStatus getAuthenticationStatus() {
		return authenticationStatus;
	}
	
}
