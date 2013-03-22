package org.fhw.asta.kasse.client.inject.module.factory;

import org.fhw.asta.kasse.client.activity.BackofficeActivity;
import org.fhw.asta.kasse.client.place.BackofficePlace;

public interface BackofficeActivityFactory {

	BackofficeActivity create(BackofficePlace articleListPlace);
	
}
