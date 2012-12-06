package org.fhw.asta.kasse.shared.service.basket;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("basket")
public interface BasketService extends RemoteService{
	
	List<BasketItem> getBasket();
	
	void removeItem(BasketItem item);
		
	void addItem(BasketItem item);
	
	Integer getDiscount();
	
	void setDiscount(int discount);
	
}
