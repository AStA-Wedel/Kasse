package org.fhw.asta.kasse.server.inject;



import org.fhw.asta.kasse.server.inject.modules.ComponentModule;
import org.fhw.asta.kasse.server.inject.modules.ConfigModule;
import org.fhw.asta.kasse.server.inject.modules.DaoModule;
import org.fhw.asta.kasse.server.inject.modules.ServletConfigModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class RootInjector extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletConfigModule(), new ConfigModule(), new ComponentModule(), new DaoModule());
	}

}
