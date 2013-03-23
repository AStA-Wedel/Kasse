package org.fhw.asta.kasse.shared.service.billorder;

import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.model.BillOrder;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("billorder")
public interface BillOrderService extends RemoteService {
	Optional<BillOrder> getBillOrder(int id);
	List<BasketItem> getBillOrderArticles(int id);
}
