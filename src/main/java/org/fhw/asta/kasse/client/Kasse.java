package org.fhw.asta.kasse.client;

import org.fhw.asta.kasse.client.inject.AppInjector;
import org.fhw.asta.kasse.client.place.LoginPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

public class Kasse implements EntryPoint {

  private static final Place DEFAULT_PLACE = new LoginPlace();
	
  private final AppInjector injector = GWT.create(AppInjector.class);
  
  public void onModuleLoad() {

	  ActivityManager activityManager = injector.getActivityManager();
		
	  activityManager.setDisplay(new AcceptsOneWidget() {
			
			@Override
			public void setWidget(IsWidget w) {
				RootPanel.get().clear();
				
				if (w != null) {
					RootPanel.get().add(w);					
				}
			}
	  });
		
	  PlaceHistoryHandler placeHistoryHandler = injector.getPlaceHistoryHandler();
	  placeHistoryHandler.register(injector.getPlaceController(), injector.getEventBus(), DEFAULT_PLACE);
	  placeHistoryHandler.handleCurrentHistory();
  }
}
