package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.controller.BasketController;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ControllerModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BasketController.class).in(Singleton.class);
	}

}
