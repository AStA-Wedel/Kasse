package org.fhw.asta.kasse.client.components;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Cookies;

public class SessionManagerComponent {

	private static final String COOKIE_NAME = "ldap_name";
	
	public boolean isLoggedIn() {
		return !Strings.isNullOrEmpty(Cookies.getCookie(COOKIE_NAME));
	}
	
	public String getUserEmail() {
		if (!isLoggedIn()) {
			return Cookies.getCookie(COOKIE_NAME);
		} else {
			throw new RuntimeException("Junge check VORHER das der Nutzer eingeloggt ist!!");
		}
	}
	
	public void setLoggedIn(String email) {
		
		if (!Strings.isNullOrEmpty(email)) {
			Cookies.setCookie(COOKIE_NAME, email);			
		} else {
			Cookies.removeCookie(COOKIE_NAME);
		}
		
	}
	
}
