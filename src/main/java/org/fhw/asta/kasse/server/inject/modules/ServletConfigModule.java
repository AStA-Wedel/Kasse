package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.service.ArticleServiceEndpoint;
import org.fhw.asta.kasse.server.service.BasketServiceEndpoint;
import org.fhw.asta.kasse.server.service.UserServiceEndpoint;

import com.google.inject.servlet.ServletModule;

public class ServletConfigModule extends ServletModule {

	@Override
	protected void configureServlets() {
		//serve("/kasse/greet").with(GreetingServiceImpl.class);
		serve("/kasse/user").with(UserServiceEndpoint.class);
		serve("/kasse/basket").with(BasketServiceEndpoint.class);
		serve("/kasse/article").with(ArticleServiceEndpoint.class);
	}
	
}
