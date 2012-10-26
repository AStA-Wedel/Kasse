package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.widget.login.LoginWidget;
import org.fhw.asta.kasse.client.widget.login.LoginWidgetImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class WidgetModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LoginWidget.class).to(LoginWidgetImpl.class).in(Singleton.class);
	}
	
}
