package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.fhw.asta.kasse.server.common.UserEmailProvider;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.exception.CheckoutException;
import org.fhw.asta.kasse.shared.service.checkout.CheckoutService;

import com.google.common.base.Optional;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class CheckoutServiceEndpoint extends RemoteServiceServlet implements CheckoutService {

	private static final long serialVersionUID = 1L;

	@Override
	public Integer doCheckout(List<BasketItem> items, int discount,
			String matrNr) throws CheckoutException {
		
		UserEmailProvider emailProvider = new UserEmailProvider(getThreadLocalRequest());		
		
		Optional<String> email = emailProvider.get();
		
		if (email.isPresent()) {
			
			
			
			
		} else {
			throw new CheckoutException("No issuer for checkout given. Are you logged in?");
		}
		
		return 0;
	}

}
