package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.component.user.DummyUserComponent;
import org.fhw.asta.kasse.server.component.user.UserComponent;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ComponentModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserComponent.class).to(/* FIXME: ldap user Component einbinden */DummyUserComponent.class).in(Singleton.class);
	}

}
