package org.fhw.asta.kasse.client;

import org.fhw.asta.kasse.client.inject.AppInjector;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.main.MainWidget;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class Kasse implements EntryPoint {

	private static final Place DEFAULT_PLACE = new LoginPlace();

	private final AppInjector injector = GWT.create(AppInjector.class);

	public void onModuleLoad() {

		MainWidget mainWidget = injector.getMainWidget();
	
		ActivityManager activityManager = injector.getActivityManager();
		activityManager.setDisplay(mainWidget);
		
		PlaceHistoryHandler placeHistoryHandler = injector
				.getPlaceHistoryHandler();
		placeHistoryHandler.register(injector.getPlaceController(),
				injector.getEventBus(), DEFAULT_PLACE);
		placeHistoryHandler.handleCurrentHistory();

		RootPanel.get().add(mainWidget);
	}
}
