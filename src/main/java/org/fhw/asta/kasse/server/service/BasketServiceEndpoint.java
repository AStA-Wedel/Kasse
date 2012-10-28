package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.service.basket.BasketService;

import com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class BasketServiceEndpoint extends RemoteServiceServlet implements BasketService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8252919332985322511L;

	@Override
	public List<BasketItem> getBasket() {
		List<BasketItem> basket = Lists.newArrayList(new BasketItem("Stift", new EuroAmount(123), 1234,12));
		// TODO fill content
		return basket;
	}

	@Override
	public void removeItem(BasketItem item) {
		// TODO fill content
		
	}

}
