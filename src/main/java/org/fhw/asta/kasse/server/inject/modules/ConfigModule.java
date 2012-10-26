package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.component.config.ConfigProvider;
import org.fhw.asta.kasse.server.component.config.DevConfigProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ConfigProvider.class).to(DevConfigProvider.class).in(Singleton.class);		
	}

}
