package org.fhw.asta.kasse.client.components;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Cookies;

public class SessionManagerComponent {

	public boolean isLoggedIn() {
		return !Strings.isNullOrEmpty(Cookies.getCookie("email"));
	}
	
	public String getUserEmail() {
		
		if (!isLoggedIn()) {
			return Cookies.getCookie("email");
		} else {
			throw new RuntimeException("Junge check VORHER das der Nutzer eingeloggt ist!!");
		}
	}
	
	public void setLoggedIn(String email) {
		Cookies.setCookie("email", email);
	}
	
}
