package org.fhw.asta.kasse.client;

import org.fhw.asta.kasse.client.event.LoginEvent;
import org.fhw.asta.kasse.client.event.LoginEventHandler;
import org.fhw.asta.kasse.client.inject.AppInjector;
import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class Kasse implements EntryPoint {

	private static final Place DEFAULT_PLACE = new LoginPlace();

	private final AppInjector injector = GWT.create(AppInjector.class);
	
	private SimplePanel initialRootPanel = new SimplePanel();
	
	public void onModuleLoad() {
		
		final RootPanel rootPanel = RootPanel.get();
		final SimpleEventBus eventBus = injector.getEventBus();
		final ActivityManager activityManager = injector.getActivityManager();
		final PlaceController placeController = injector.getPlaceController();
		
		activityManager.setDisplay(initialRootPanel);
		rootPanel.add(injector.getTopbarWidget());
		rootPanel.add(initialRootPanel);
		
		PlaceHistoryHandler placeHistoryHandler = injector
				.getPlaceHistoryHandler();
		
		placeHistoryHandler.register(injector.getPlaceController(), eventBus, DEFAULT_PLACE);
		placeHistoryHandler.handleCurrentHistory();

		
		GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				injector.getMainWidget();
				injector.getBasketWidget();
				injector.getSidebarWidget();
			}
			
			@Override
			public void onFailure(Throwable reason) {
				assert(false);
			}
			
		});
		

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			
			@Override
			public void onLogin(LoginEvent loginEvent) {
		
				MainWidget mainWidget = injector.getMainWidget();
				BasketWidget basketWidget = injector.getBasketWidget();
				SidebarWidget sidebarWidget = injector.getSidebarWidget();
				mainWidget.setBasketWidget(basketWidget);
				mainWidget.setSidebarWidget(sidebarWidget);
								
				initialRootPanel.removeFromParent();
				initialRootPanel.clear();
				initialRootPanel = null;
				
				RootPanel.get().add(mainWidget);
				activityManager.setDisplay(mainWidget);
				
				placeController.goTo(new ArticleListPlace());
			}
		
		});
		
	}
}
