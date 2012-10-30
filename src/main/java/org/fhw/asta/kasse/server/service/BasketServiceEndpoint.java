package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.fhw.asta.kasse.server.component.user.UserComponent;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.service.basket.BasketService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BasketServiceEndpoint extends RemoteServiceServlet implements BasketService {

	private static final long serialVersionUID = -8252919332985322511L;

	@Inject
	private UserComponent userComponent;
	
	@Override
	public List<BasketItem> getBasket() {
		return userComponent.getUser().getBasket();
	}

	@Override
	public void removeItem(BasketItem item) {
		// TODO fill content
		
	}

	@Override
	public void updateItem(BasketItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem(BasketItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getDiscount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDiscount(int discount) {
		// TODO Auto-generated method stub
		
	}

}
