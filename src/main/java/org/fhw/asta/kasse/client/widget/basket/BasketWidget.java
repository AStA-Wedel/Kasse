package org.fhw.asta.kasse.client.widget.basket;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;

public interface BasketWidget extends IsWidget {

	HasData<BasketItem> getBasketTable();
	
	Column<BasketItem, String> getDeleteColumn();
	
	Column<BasketItem, String> getAmountColumn();
	
	HasText getSumLabel();
	
	TextBox getMatrNrBox();

	Column<BasketItem, String> getDiscountColumn();

	TextBox getDiscountBox();
	
	HasClickHandlers getCheckoutButton();
}
