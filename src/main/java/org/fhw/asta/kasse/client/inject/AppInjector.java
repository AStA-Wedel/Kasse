package org.fhw.asta.kasse.client.inject;

import org.fhw.asta.kasse.client.inject.module.ActivityModule;
import org.fhw.asta.kasse.client.inject.module.AppModule;
import org.fhw.asta.kasse.client.inject.module.ControllerModule;
import org.fhw.asta.kasse.client.inject.module.WidgetModule;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.root.KasseRootPanel;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.client.widget.topbar.TopBarWidgetContainer;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules({AppModule.class, WidgetModule.class, ActivityModule.class, ControllerModule.class})
public interface AppInjector extends Ginjector {
	
		ActivityManager getActivityManager();
		
		PlaceHistoryHandler getPlaceHistoryHandler();
		
		PlaceController getPlaceController();
		
		EventBus getEventBus();
	
		MainWidget getMainWidget();
		
		BasketWidget getBasketWidget();
		
		SidebarWidget getSidebarWidget();
		
		TopBarWidgetContainer getTopbarWidget();
		
		KasseRootPanel getKasseRootPanel();
		
}
