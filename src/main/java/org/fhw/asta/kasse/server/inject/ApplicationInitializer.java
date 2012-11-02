package org.fhw.asta.kasse.server.inject;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 * Initializes JPA Entitymanager.
 * @author Chris
 *
 */
public class ApplicationInitializer {
	@Inject ApplicationInitializer(PersistService service) {
		service.start();
	}
}
