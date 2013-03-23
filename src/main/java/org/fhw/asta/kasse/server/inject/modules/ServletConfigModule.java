package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.service.ArticleServiceEndpoint;
import org.fhw.asta.kasse.server.service.BillOrderServiceEndpoint;
import org.fhw.asta.kasse.server.service.CheckoutServiceEndpoint;
import org.fhw.asta.kasse.server.service.LetterHeadServiceEndpoint;
import org.fhw.asta.kasse.server.service.UserServiceEndpoint;

import com.google.inject.servlet.ServletModule;

public class ServletConfigModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/kasse/user").with(UserServiceEndpoint.class);
		serve("/kasse/article").with(ArticleServiceEndpoint.class);
		serve("/kasse/checkout").with(CheckoutServiceEndpoint.class);
		serve("/kasse/billorder").with(BillOrderServiceEndpoint.class);
		serve("/kasse/letterhead").with(LetterHeadServiceEndpoint.class);
	}
	
}
