package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.service.checkout.CheckoutService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class CheckoutServiceEndpoint extends RemoteServiceServlet implements CheckoutService {

  private static final long serialVersionUID = 1L;

  @Override
  public Integer doCheckout(final List<BasketItem> items, final int discount, final String matrNr) {

    // TODO Auto-generated method stub
    return 0;
  }

}
