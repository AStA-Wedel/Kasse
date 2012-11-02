package org.fhw.asta.kasse.server.inject;

import org.fhw.asta.kasse.server.inject.modules.ComponentModule;
import org.fhw.asta.kasse.server.inject.modules.ConfigModule;
import org.fhw.asta.kasse.server.inject.modules.DaoModule;
import org.fhw.asta.kasse.server.inject.modules.ServletConfigModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

public class RootInjector extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new ServletConfigModule(),
                new ConfigModule(), new ComponentModule(), new DaoModule(),
                new JpaPersistModule("JpaPersistenceUnit"));
        
        injector.getInstance(ApplicationInitializer.class);
        return injector;
    }

}
