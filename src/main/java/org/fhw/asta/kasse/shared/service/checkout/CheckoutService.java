package org.fhw.asta.kasse.shared.service.checkout;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.exception.CheckoutException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("checkout")
public interface CheckoutService extends RemoteService {

  Integer doCheckout(List<BasketItem> items, int discount, String receipientLdapName, char orderState)
      throws CheckoutException;
}
