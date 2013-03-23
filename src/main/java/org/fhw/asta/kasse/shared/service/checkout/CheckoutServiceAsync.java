package org.fhw.asta.kasse.shared.service.checkout;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CheckoutServiceAsync {

	void doCheckout(List<BasketItem> items,int discount, String matrNr,
			AsyncCallback<Integer> callback);

}
