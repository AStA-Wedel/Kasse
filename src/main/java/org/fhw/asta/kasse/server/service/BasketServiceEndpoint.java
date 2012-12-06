package org.fhw.asta.kasse.server.service;

import java.util.Iterator;
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
		Iterator<BasketItem> it = userComponent.getUser().getBasket().iterator();
		while(it.hasNext()){
			BasketItem i = it.next();
			if(i.getArticleId() == item.getArticleId() && i.getAmount() == item.getAmount() && i.getDiscount() == item.getDiscount()){
				userComponent.getUser().getBasket().remove(i);
			}
		}
		userComponent.getUser().getBasket().remove(item);		
	}

	@Override
	public void addItem(BasketItem item) {
		userComponent.getUser().getBasket().add(item);
	}

	@Override
	public Integer getDiscount() {
		return userComponent.getUser().getBasketDiscount();
	}

	@Override
	public void setDiscount(int discount) {
		userComponent.getUser().setBasketDiscount(discount);		
	}

}
