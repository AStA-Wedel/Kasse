package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.fhw.asta.kasse.server.common.UserLdapNameProvider;
import org.fhw.asta.kasse.server.dao.UserDao;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.exception.CheckoutException;
import org.fhw.asta.kasse.shared.model.OrderState;
import org.fhw.asta.kasse.shared.service.checkout.CheckoutService;

import com.google.common.base.Optional;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CheckoutServiceEndpoint extends RemoteServiceServlet implements CheckoutService {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(CheckoutServiceEndpoint.class);

  @Inject
  private UserDao userDao;

  @Override
  public Integer doCheckout(List<BasketItem> items, int discount, String receipientLdapName, OrderState state)
      throws CheckoutException {
    final Optional<String> issuerLdapName = new UserLdapNameProvider(this.getThreadLocalRequest()).get();
    if (issuerLdapName.isPresent() && this.userDao.exists(receipientLdapName)) {

    } else {
      LOGGER.info("A non registered user tried to checkout");
      throw new CheckoutException("No issuer for checkout given. Are you logged in?");
    }
    return 0;
  }
}
