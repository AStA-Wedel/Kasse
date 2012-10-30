package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.component.user.UserComponent;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ComponentModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserComponent.class).in(Singleton.class);
	}

}
