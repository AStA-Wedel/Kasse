package org.fhw.asta.kasse.client.controller;

import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;

public class BasketController {

	private final ListDataProvider<BasketItem> basketDataProvider;
	
	private BasketWidget basketWidget; 
	
	public BasketController() {
		this.basketDataProvider = new ListDataProvider<BasketItem>(new BasketItemKeyProvider());
	}
	
	@Inject
	public void init(BasketWidget basketWidget) {
		
		this.basketWidget = basketWidget;
		
		basketDataProvider.addDataDisplay(basketWidget.getBasketTable());
	}
	
	public void addBasketPosition(BasketItem basketItem) {
 		
		//TODO: position an server für session
	
		basketDataProvider.getList().add(basketItem); //FIXME
	}
	
	private static final class BasketItemKeyProvider implements ProvidesKey<BasketItem> {

		@Override
		public Object getKey(BasketItem item) {
			return item.getArticleId();
		}
	
	}
	
}
