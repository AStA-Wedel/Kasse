package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.fhw.asta.kasse.server.common.UserLdapNameProvider;
import org.fhw.asta.kasse.server.dao.BillOrderDao;
import org.fhw.asta.kasse.server.dao.UserDao;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.exception.CheckoutException;
import org.fhw.asta.kasse.shared.service.checkout.CheckoutService;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class CheckoutServiceImpl implements CheckoutService {

  private static final Logger LOGGER = Logger.getLogger(CheckoutServiceImpl.class);

  private static final String DEFAULT_RECIPIENT = "default";
  
  @Inject
  private Injector injector;
  
  @Inject
  private UserDao userDao;

  @Inject
  private BillOrderDao billOrderDao;

  @Override
  public Integer doCheckout(List<BasketItem> items, int discount, String receipientLdapName, char orderState)
      throws CheckoutException {

    final Optional<String> issuerLdapName = injector.getInstance(UserLdapNameProvider.class).get();
    
    if (issuerLdapName.isPresent() && this.userDao.exists(issuerLdapName.get())) {
    	
    	String receipient = this.userDao.exists(receipientLdapName) ? receipientLdapName : DEFAULT_RECIPIENT;
 
   		return this.billOrderDao.saveBillOrder(items, discount, receipient, issuerLdapName.get(), orderState);
   	 
    } else {
      LOGGER.info("A non registered user tried to checkout");
      throw new CheckoutException("No issuer for checkout given. Are you logged in?");
    }
  }
}
