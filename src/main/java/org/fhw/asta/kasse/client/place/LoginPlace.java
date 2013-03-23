package org.fhw.asta.kasse.client.place;

import org.fhw.asta.kasse.client.common.login.LoginToken;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LoginPlace extends Place {

	private final LoginToken loginToken;
	
	public LoginPlace(LoginToken loginToken) {
		this.loginToken = loginToken;
	}
	
	public LoginToken getLoginToken() {
		return loginToken;
	}
	
	public static final class Tokenizer implements PlaceTokenizer<LoginPlace> {

		@Override
		public LoginPlace getPlace(String token) {
			return new LoginPlace(LoginToken.fromString(token));
		}

		@Override
		public String getToken(LoginPlace place) {
			return place.getLoginToken().toHistoryToken();
		}
		
	}

}
