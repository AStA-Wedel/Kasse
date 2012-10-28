package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.app.AppActivityMapper;
import org.fhw.asta.kasse.client.app.AppPlaceHistoryMapper;
import org.fhw.asta.kasse.client.inject.module.provider.ActivityManagerProvider;
import org.fhw.asta.kasse.client.inject.module.provider.PlaceControllerProvider;
import org.fhw.asta.kasse.client.inject.module.provider.PlaceHistoryHandlerProvider;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(SimpleEventBus.class).in(Singleton.class);
		bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);

		bind(ActivityManager.class).toProvider(ActivityManagerProvider.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);
		bind(PlaceHistoryHandler.class).toProvider(PlaceHistoryHandlerProvider.class).in(Singleton.class);
		
		bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
	}

}
