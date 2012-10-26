package org.fhw.asta.kasse.server.servlet;

import org.fhw.asta.kasse.server.inject.modules.ServiceInjector;
import org.fhw.asta.kasse.server.inject.modules.ServletConfigModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletConfigModule(), new ServiceInjector());
	}

}
