package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.common.UserLdapNameProvider;
import org.fhw.asta.kasse.server.service.ArticleServiceImpl;
import org.fhw.asta.kasse.server.service.BillOrderServiceImpl;
import org.fhw.asta.kasse.server.service.CheckoutServiceImpl;
import org.fhw.asta.kasse.server.service.LetterHeadServiceImpl;
import org.fhw.asta.kasse.server.service.UserServiceImpl;
import org.fhw.asta.kasse.shared.service.article.ArticleService;
import org.fhw.asta.kasse.shared.service.billorder.BillOrderService;
import org.fhw.asta.kasse.shared.service.checkout.CheckoutService;
import org.fhw.asta.kasse.shared.service.letterhead.LetterHeadService;
import org.fhw.asta.kasse.shared.service.user.UserService;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		
		bind(UserLdapNameProvider.class);
		
		bind(ArticleService.class).to(ArticleServiceImpl.class);
		bind(BillOrderService.class).to(BillOrderServiceImpl.class);
		bind(CheckoutService.class).to(CheckoutServiceImpl.class);
		bind(LetterHeadService.class).to(LetterHeadServiceImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}
	
}
