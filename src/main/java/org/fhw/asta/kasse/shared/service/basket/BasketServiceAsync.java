package org.fhw.asta.kasse.shared.service.basket;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BasketServiceAsync {

	void getBasket(AsyncCallback<List<BasketItem>> callback);

}
