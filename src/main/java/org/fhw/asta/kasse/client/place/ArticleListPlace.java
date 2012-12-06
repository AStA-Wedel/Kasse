package org.fhw.asta.kasse.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ArticleListPlace extends Place {
	
	private String token;
	
	public ArticleListPlace(String token) {
		this.token = token;
	}

	public ArticleListPlace() {
		this.token = "";
	}

	public static final class Tokenizer implements PlaceTokenizer<ArticleListPlace> {

		@Override
		public ArticleListPlace getPlace(String token) {
			return new ArticleListPlace(token);
		}

		@Override
		public String getToken(ArticleListPlace place) {
			return place.getToken();
		}
		
	}

	public String getToken() {
		return token;
	}
		
}
