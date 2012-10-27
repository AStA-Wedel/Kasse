package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.client.widget.basket.BasketWidgetImpl;
import org.fhw.asta.kasse.client.widget.login.LoginWidget;
import org.fhw.asta.kasse.client.widget.login.LoginWidgetImpl;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.main.MainWidgetImpl;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidgetImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class WidgetModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainWidget.class).to(MainWidgetImpl.class).in(Singleton.class);
		bind(LoginWidget.class).to(LoginWidgetImpl.class).in(Singleton.class);
		bind(BasketWidget.class).to(BasketWidgetImpl.class).in(Singleton.class);
		bind(SidebarWidget.class).to(SidebarWidgetImpl.class).in(Singleton.class);
	}
	
}
