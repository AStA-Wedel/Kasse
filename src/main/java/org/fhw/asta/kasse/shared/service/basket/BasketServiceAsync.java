package org.fhw.asta.kasse.shared.service.basket;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BasketServiceAsync {

	void getBasket(AsyncCallback<List<BasketItem>> callback);

	void removeItem(BasketItem item, AsyncCallback<Void> callback);

	void updateItem(BasketItem item, AsyncCallback<Void> callback);

	void addItem(BasketItem item, AsyncCallback<Void> callback);

	void setDiscount(int discount, AsyncCallback<Void> callback);

	void getDiscount(AsyncCallback<Integer> callback);

}
