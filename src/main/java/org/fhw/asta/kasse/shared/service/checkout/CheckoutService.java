package org.fhw.asta.kasse.shared.service.checkout;


import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("checkout")
public interface CheckoutService extends RemoteService {

	Boolean doCheckout(List<BasketItem> items, int discount, String matrNr); 
}
