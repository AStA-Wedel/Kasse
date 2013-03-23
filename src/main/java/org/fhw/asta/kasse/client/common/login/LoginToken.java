package org.fhw.asta.kasse.client.common.login;

import org.fhw.asta.kasse.client.app.AppPlaceHistoryMapper;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class LoginToken {

	public static final LoginToken EMPTY_TOKEN = new LoginToken(Optional.<String>absent(), Optional.<Place>absent());

	private static final PlaceHistoryMapper appPlaceHistoryMapper = GWT.create(AppPlaceHistoryMapper.class);
	
	private static final RegExp TOKEN_PATTERN = RegExp.compile("([^;]+)(;(.+))?");
	
	private final Optional<String> email;
	
	private final Optional<Place> redirect;

	public static LoginToken fromString(String token) {
		
		MatchResult matcher = TOKEN_PATTERN.exec(token);
		
		String email = matcher.getGroup(1);
		String redirect = matcher.getGroup(4);
		
		if (Strings.isNullOrEmpty(email) && Strings.isNullOrEmpty(redirect)) {
			return EMPTY_TOKEN;
		}
		
		if (Strings.isNullOrEmpty(email)) {
			return new LoginToken(Optional.<String>absent(), Optional.fromNullable(appPlaceHistoryMapper.getPlace(matcher.getGroup(2))));
		}
		
		if (Strings.isNullOrEmpty(redirect)) {
			return new LoginToken(Optional.of(email), Optional.<Place>absent());
		}
		
		return new LoginToken(Optional.of(email),  Optional.fromNullable(appPlaceHistoryMapper.getPlace(matcher.getGroup(2))));		
	}
	
	public String toHistoryToken() {
		
		if (email.isPresent() && redirect.isPresent()) {
			return email.get() + ";" + appPlaceHistoryMapper.getToken(redirect.get());
		} else if (email.isPresent()) {
			return email.get();
		} else if (!email.isPresent() && redirect.isPresent()) {
			return ";" + appPlaceHistoryMapper.getToken(redirect.get());
		}
		
		return "";
	}
	
	public LoginToken(Optional<String> email, Optional<Place> redirect) {
		this.email = email;
		this.redirect = redirect;
	}
	
	public Optional<String> getEmail() {
		return email;
	}

	public Optional<Place> getRedirect() {
		return redirect;
	}
		
}
