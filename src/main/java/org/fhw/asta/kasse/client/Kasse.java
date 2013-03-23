package org.fhw.asta.kasse.client;

import org.fhw.asta.kasse.client.common.login.LoginToken;
import org.fhw.asta.kasse.client.inject.AppInjector;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.root.KasseRootPanel;
import org.fhw.asta.kasse.client.widget.root.KasseRootPanelImpl;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class Kasse implements EntryPoint {

	private static final Place DEFAULT_PLACE = new LoginPlace(LoginToken.EMPTY_TOKEN);

	private final AppInjector injector = GWT.create(AppInjector.class);
	
//	private final KasseRootPanelImpl rootPanel = new KasseRootPanelImpl();
	
//	private SimplePanel initialRootPanel = new SimplePanel();
	
	public void onModuleLoad() {
		
		final RootPanel htmlRootPanel = RootPanel.get();
		final KasseRootPanel rootPanel = injector.getKasseRootPanel();
		final EventBus eventBus = injector.getEventBus();
		final ActivityManager activityManager = injector.getActivityManager();
		//final PlaceController placeController = injector.getPlaceController();

		htmlRootPanel.add(rootPanel);
		activityManager.setDisplay(rootPanel);
		
//		rootPanel.add(injector.getTopbarWidget());
//		rootPanel.add(initialRootPanel);
	
//		this.rootPanel.setTopbar(injector.getTopbarWidget());
		
		PlaceHistoryHandler placeHistoryHandler = injector
				.getPlaceHistoryHandler();

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

		placeHistoryHandler.register(injector.getPlaceController(), eventBus, DEFAULT_PLACE);
		placeHistoryHandler.handleCurrentHistory();
		
/*				
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
		
		}); */	
	}
}
