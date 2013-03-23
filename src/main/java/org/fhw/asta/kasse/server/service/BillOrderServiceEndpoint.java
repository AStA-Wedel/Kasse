package org.fhw.asta.kasse.server.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.fhw.asta.kasse.server.dao.ArticleDao;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.model.BillOrder;
import org.fhw.asta.kasse.shared.model.OrderState;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.service.billorder.BillOrderService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class BillOrderServiceEndpoint extends RemoteServiceServlet implements BillOrderService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ArticleDao dao;
	
	@Override
	public BillOrder getBillOrder(int id) {
		// TODO Auto-generated method stub
		return new BillOrder(12, new Person("horst", 2, "meier", "horst", 2144, false, 12), new Person("gandalf", 2, "gandalf", "der graue", 2666, false, 12), new Date(), false, OrderState.Ordered, false,0);
	}

	@Override
	public List<BasketItem> getBillOrderArticles(int id) {
		// TODO Auto-generated method stub
		return new LinkedList<BasketItem>();
	}

}
