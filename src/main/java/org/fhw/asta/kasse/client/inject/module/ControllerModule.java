package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.components.SessionManagerComponent;
import org.fhw.asta.kasse.client.controller.BasketController;
import org.fhw.asta.kasse.client.controller.TopbarController;

import com.google.gwt.inject.client.AbstractGinModule;

public class ControllerModule extends AbstractGinModule {

	@Override
	protected void configure() {
		
		bind(SessionManagerComponent.class).asEagerSingleton();
		
		bind(BasketController.class).asEagerSingleton();
		bind(TopbarController.class).asEagerSingleton();
	}

}
