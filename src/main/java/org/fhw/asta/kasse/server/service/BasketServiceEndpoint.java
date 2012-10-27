package org.fhw.asta.kasse.server.service;

import java.util.LinkedList;
import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.service.basket.BasketService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BasketServiceEndpoint extends RemoteServiceServlet implements BasketService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8252919332985322511L;

	@Override
	public List<BasketItem> getBasket() {
		List<BasketItem> basket = new LinkedList<BasketItem>();
		return basket;
	}

}
