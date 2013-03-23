package org.fhw.asta.kasse.server.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;

public class UserEmailProvider implements Supplier<Optional<String>> {

	private static final String EMAIL_COOKIE = "email";
	
	private final HttpServletRequest req;
	
	public UserEmailProvider(HttpServletRequest req) {
		this.req = req;
	}
	
	@Override
	public Optional<String> get() {
	
		Cookie[] cookies = req.getCookies();
		
		for (Cookie cookie : cookies) {
			
			if (cookie.getName().equals(EMAIL_COOKIE)) {
				
				String email = cookie.getValue(); 
				
				if (Strings.isNullOrEmpty(email)) {
					return Optional.absent();
				} else {
					return Optional.of(email);
				}
			}
		}
		
		
		return Optional.absent();
	}

}
