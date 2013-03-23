package org.fhw.asta.kasse.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UserListPlace extends Place {
	private String token;
	
	public UserListPlace(String token) {
		this.token = token;
	}

	public UserListPlace() {
		this.token = "";
	}

	public static final class Tokenizer implements PlaceTokenizer<UserListPlace> {

		@Override
		public UserListPlace getPlace(String token) {
			return new UserListPlace(token);
		}

		@Override
		public String getToken(UserListPlace place) {
			return place.getToken();
		}
		
	}

	public String getToken() {
		return token;
	}
}
