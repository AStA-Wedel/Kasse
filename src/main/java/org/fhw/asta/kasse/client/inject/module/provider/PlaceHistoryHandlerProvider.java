package org.fhw.asta.kasse.client.inject.module.provider;

import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PlaceHistoryHandlerProvider implements Provider<PlaceHistoryHandler> {

	@Inject
	private PlaceHistoryMapper placeHistoryMapper; 
	
	@Override
	public PlaceHistoryHandler get() {
		return new PlaceHistoryHandler(placeHistoryMapper);
	}
	
	
}
