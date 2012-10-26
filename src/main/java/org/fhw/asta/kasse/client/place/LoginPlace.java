package org.fhw.asta.kasse.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LoginPlace extends Place {

	private final String email; 
	
	public LoginPlace() {
		this("");
	}
	
	public LoginPlace(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
		
	public static final class Tokenizer implements PlaceTokenizer<LoginPlace> {

		@Override
		public LoginPlace getPlace(String token) {
			return new LoginPlace(token);
		}

		@Override
		public String getToken(LoginPlace place) {
			return place.getEmail();
		}

	}

}
