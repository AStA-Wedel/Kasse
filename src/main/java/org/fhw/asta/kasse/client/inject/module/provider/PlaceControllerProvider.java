package org.fhw.asta.kasse.client.inject.module.provider;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

public class PlaceControllerProvider implements Provider<PlaceController> {

	@Inject
	private EventBus eventBus;
	
	@Override
	public PlaceController get() {
		return new PlaceController(eventBus);
	}

}
