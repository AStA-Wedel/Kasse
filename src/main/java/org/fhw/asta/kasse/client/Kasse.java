package org.fhw.asta.kasse.client;

import org.fhw.asta.kasse.client.common.login.LoginToken;
import org.fhw.asta.kasse.client.inject.AppInjector;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.root.KasseRootPanel;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

public class Kasse implements EntryPoint {

	private static final Place DEFAULT_PLACE = new LoginPlace(LoginToken.EMPTY_TOKEN);

	private final AppInjector injector = GWT.create(AppInjector.class);
	
	public void onModuleLoad() {
		
		final RootPanel htmlRootPanel = RootPanel.get();
		final KasseRootPanel rootPanel = injector.getKasseRootPanel();
		final EventBus eventBus = injector.getEventBus();
		final ActivityManager activityManager = injector.getActivityManager();

		htmlRootPanel.add(rootPanel);
		activityManager.setDisplay(rootPanel);
		
		PlaceHistoryHandler placeHistoryHandler = injector
				.getPlaceHistoryHandler();

		placeHistoryHandler.register(injector.getPlaceController(), eventBus, DEFAULT_PLACE);
		placeHistoryHandler.handleCurrentHistory();
	}
}
