package org.fhw.asta.kasse.server.service;

import java.util.List;

import javax.annotation.Nullable;

import org.fhw.asta.kasse.server.dao.ArticleDao;
import org.fhw.asta.kasse.server.dao.BillOrderDao;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.model.BillOrder;
import org.fhw.asta.kasse.shared.model.BillOrderData;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.service.billorder.BillOrderService;
import org.fhw.asta.kasse.shared.service.letterhead.LetterHeadService;
import org.fhw.asta.kasse.shared.service.user.UserService;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BillOrderServiceImpl implements BillOrderService {

	@Inject 
	private UserService userService;
	
	@Inject 
	private LetterHeadService letterHeadService;
	
	@Inject
	private ArticleDao articleDao;

	@Inject
	private BillOrderDao billOrderDao;

	@Override
	public Optional<BillOrder> getBillOrder(int id) {
		return this.billOrderDao.getBillOrder(id);
	}

	@Override
	public List<BasketItem> getBillOrderArticles(int id) {
		return this.billOrderDao.getBillOrderArticles(id);
	}

	@Override
	public BillOrderData getBillOrderData(int id) {
		
		BillOrder billOrder = getBillOrder(id).or(new BillOrder());
		List<BasketItem> basket = getBillOrderArticles(id);
	
		Person issuer = userService.getUserByIdAndRevision(billOrder.getIssuerLdapName(), billOrder.getIssuerRevision()).or(new Person());				
		Person receipient = userService.getUserByIdAndRevision(billOrder.getRecipientLdapName(), billOrder.getReceipientRevision()).or(new Person());
		
		BillOrderData bod = new BillOrderData();
		
		bod.setBillOrder(billOrder);
		bod.setBasket(basket);
		bod.setIssuer(issuer);
		bod.setReceipient(receipient);
		
		return bod;
	}


}
