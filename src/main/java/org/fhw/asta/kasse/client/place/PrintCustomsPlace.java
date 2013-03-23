package org.fhw.asta.kasse.client.place;

import org.fhw.asta.kasse.client.common.PrintCustomsToken;

import com.google.common.base.Optional;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PrintCustomsPlace extends Place {

	private final Optional<PrintCustomsToken> token;
	
	public PrintCustomsPlace(Optional<PrintCustomsToken> token) {
		this.token = token;
	}

	public PrintCustomsPlace() {
		this.token = Optional.absent();
	}

	public static final class Tokenizer implements PlaceTokenizer<PrintCustomsPlace> {

		@Override
		public PrintCustomsPlace getPlace(String token) {
			return new PrintCustomsPlace(PrintCustomsToken.fromString(token));
		}

		@Override
		public String getToken(PrintCustomsPlace place) {
			return place.getToken().or(PrintCustomsToken.EMPTY_TOKEN).toString();			
		}
		
	}

	public Optional<PrintCustomsToken> getToken() {
		return token;
	}
}
