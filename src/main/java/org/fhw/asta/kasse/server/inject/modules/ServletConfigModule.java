package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.service.UserServiceEndpoint;

import com.google.inject.servlet.ServletModule;

public class ServletConfigModule extends ServletModule {

	@Override
	protected void configureServlets() {
		//serve("/kasse/greet").with(GreetingServiceImpl.class);
		serve("/kasse/user").with(UserServiceEndpoint.class);
	}
	
}