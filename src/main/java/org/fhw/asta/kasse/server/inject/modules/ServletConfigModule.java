package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.servlet.ArticleServiceServlet;
import org.fhw.asta.kasse.server.servlet.BillOrderServiceServlet;
import org.fhw.asta.kasse.server.servlet.CheckoutServiceServlet;
import org.fhw.asta.kasse.server.servlet.LetterheadServiceServlet;
import org.fhw.asta.kasse.server.servlet.UserServiceServlet;

import com.google.inject.servlet.ServletModule;

public class ServletConfigModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/kasse/user").with(UserServiceServlet.class);
		serve("/kasse/article").with(ArticleServiceServlet.class);
		serve("/kasse/checkout").with(CheckoutServiceServlet.class);
		serve("/kasse/billorder").with(BillOrderServiceServlet.class);
		serve("/kasse/letterhead").with(LetterheadServiceServlet.class);
		
	}
	
}
