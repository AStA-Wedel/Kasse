package org.fhw.asta.kasse.client.widget.basket;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface BasketWidget extends IsWidget {

	HasData<BasketItem> getBasketTable();
	
	Column<BasketItem, String> getDeleteColumn();
	
	Column<BasketItem, String> getAmountColumn();
	
	HasText getSumLabel();
}
