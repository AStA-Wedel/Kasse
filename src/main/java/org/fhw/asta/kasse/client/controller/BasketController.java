package org.fhw.asta.kasse.client.controller;

import java.util.List;

import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.service.basket.BasketServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;

public class BasketController {
	
	private BasketServiceAsync basketService;
	
	private final ListDataProvider<BasketItem> basketDataProvider;
	
	private BasketWidget basketWidget; 
	
	public BasketController() {
		this.basketDataProvider = new ListDataProvider<BasketItem>(new BasketItemKeyProvider());
	}
	
	@Inject
	public void init(BasketWidget basketWidget,BasketServiceAsync basketService ) {
		
		this.basketWidget = basketWidget;
		this.basketService = basketService;
		
		basketDataProvider.addDataDisplay(basketWidget.getBasketTable());
		
		loadBasket();
	}
	
	public void addBasketPosition(BasketItem basketItem) {
 		
		//TODO: position an server für session
	
		basketDataProvider.getList().add(basketItem); //FIXME
	}

	public void loadBasket()
	{		
		basketService.getBasket(new BasketDataHandler());	
	}
	
	private static final class BasketItemKeyProvider implements ProvidesKey<BasketItem> {

		@Override
		public Object getKey(BasketItem item) {
			return item.getArticleId();
		}
	
	}
	
	private final class BasketDataHandler implements AsyncCallback<List<BasketItem>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<BasketItem> result) {
			basketDataProvider.getList().addAll(result);
			
		}
	}
	
}
