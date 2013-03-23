package org.fhw.asta.kasse.shared.service.billorder;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.model.BillOrder;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BillOrderServiceAsync {

	void getBillOrder(int id, AsyncCallback<Optional<BillOrder>> callback);

	void getBillOrderArticles(int id, AsyncCallback<List<BasketItem>> callback);

}
