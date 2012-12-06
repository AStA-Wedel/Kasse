package org.fhw.asta.kasse.client.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.service.basket.BasketServiceAsync;

import com.google.common.collect.ComparisonChain;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;

public class BasketController {

	private BasketServiceAsync basketService;

	private final ListDataProvider<BasketItem> basketDataProvider;

	private BasketWidget basketWidget;

	private FieldUpdater<BasketItem, String> deleteUpdater;
	private FieldUpdater<BasketItem, String> amountUpdater;
	private FieldUpdater<BasketItem, String> discountUpdater;
	private ValueChangeHandler<String> masterDiscountUpdater;

	private BasketComparator basketComparator = new BasketComparator();

	public BasketController() {
		this.basketDataProvider = new ListDataProvider<BasketItem>(
				new BasketItemKeyProvider());
	}

	@Inject
	public void init(BasketWidget basketWidget,
			final BasketServiceAsync basketService) {

		this.basketWidget = basketWidget;
		this.basketService = basketService;

		basketDataProvider.addDataDisplay(this.basketWidget.getBasketTable());
		loadBasket();

		deleteUpdater = new DeleteUpdater();
		basketWidget.getDeleteColumn().setFieldUpdater(deleteUpdater);

		amountUpdater = new AmountUpdater();
		basketWidget.getAmountColumn().setFieldUpdater(amountUpdater);

		discountUpdater = new DiscountUpdater();
		basketWidget.getDiscountColumn().setFieldUpdater(discountUpdater);

		masterDiscountUpdater = new MasterDiscountUpdater();
		basketWidget.getDiscountBox().addValueChangeHandler(
				masterDiscountUpdater);
	}

	public void addBasketPosition(BasketItem basketItem) {

		Iterator<BasketItem> basketIterator = basketDataProvider.getList()
				.iterator();
		while (basketIterator.hasNext()) {
			BasketItem next = basketIterator.next();
			if (next.getArticleId() == basketItem.getArticleId()) {
				basketItem = new BasketItem(basketItem.getItemName(),
						basketItem.getItemPrice(), basketItem.getArticleId(),
						basketItem.getAmount() + next.getAmount(), next.getDiscount());
				basketDataProvider.getList().remove(next);
				basketService.removeItem(next, new BasketVoidHandler());
			}
		}

		basketService.addItem(basketItem, new BasketVoidHandler());
		basketDataProvider.getList().add(basketItem);
		flush();

	}

	public void addBasketPosition(Article article) {

		addBasketPosition(new BasketItem(article.getName(), article.getPrice(),
				article.getId(), 1));
	}

	public void loadBasket() {
		basketService.getBasket(new BasketDataHandler());
		basketService.getDiscount(new BasketDiscountHandler());
	}

	private void flush() {
		Collections.sort(basketDataProvider.getList(), basketComparator);

		int sum = 0;

		Iterator<BasketItem> basketIterator = basketDataProvider.getList()
				.iterator();
		while (basketIterator.hasNext()) {
			BasketItem next = basketIterator.next();
			sum += Math.round((next.getItemPrice().getCentAmount() * next.getAmount()) * ((100-next.getDiscount()) / 100.0 ));

		}
		
		int discount = 0;
		
		try {
			discount = Integer.valueOf(basketWidget.getDiscountBox().getText());
		} catch (NumberFormatException e){
			discount = 0;
		}
		
		basketWidget.getSumLabel().setText(
				EuroFormatter.format((int) Math.round(sum
						* ((100 - discount) / 100.0))));
	}

	private class AmountUpdater implements FieldUpdater<BasketItem, String> {

		@Override
		public void update(int index, BasketItem object, String value) {
			if (value.matches("[1-9][0-9]*")) {
				BasketItem toUpdate = new BasketItem(object.getItemName(),
						object.getItemPrice(), object.getArticleId(),
						Integer.valueOf(value),object.getDiscount());
				basketService.removeItem(object, new BasketVoidHandler());
				basketService.addItem(toUpdate, new BasketVoidHandler());
				basketDataProvider.getList().remove(object);
				basketDataProvider.getList().add(toUpdate);
				flush();
			} else {
				basketDataProvider.getList().clear();
				loadBasket();
			}

		}
	}

	private class DeleteUpdater implements FieldUpdater<BasketItem, String> {

		@Override
		public void update(int index, BasketItem object, String value) {
			basketDataProvider.getList().remove(object);
			basketService.removeItem(object, new BasketVoidHandler());
			flush();
		}
	}

	private class DiscountUpdater implements FieldUpdater<BasketItem, String> {

		@Override
		public void update(int index, BasketItem object, String value) {
			if (value.matches("[0-9]+")) {
				BasketItem toUpdate = new BasketItem(object.getItemName(),
						object.getItemPrice(), object.getArticleId(),
						object.getAmount(), Integer.valueOf(value));
				basketService.removeItem(object, new BasketVoidHandler());
				basketService.addItem(toUpdate, new BasketVoidHandler());
				basketDataProvider.getList().remove(object);
				basketDataProvider.getList().add(toUpdate);
				flush();
			} else {
				basketDataProvider.getList().clear();
				loadBasket();
			}

		}

	}

	private class MasterDiscountUpdater implements ValueChangeHandler<String> {

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			if (event.getValue().matches("[0-9]+")) {
				basketService.setDiscount(Integer.valueOf(event.getValue()),
						new BasketVoidHandler());
				flush();
			} else {
				loadBasket();
			}

		}

	}

	private static final class BasketItemKeyProvider implements
			ProvidesKey<BasketItem> {

		@Override
		public Object getKey(BasketItem item) {
			return item.getArticleId();
		}

	}

	private final class BasketDataHandler implements
			AsyncCallback<List<BasketItem>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(List<BasketItem> result) {
			basketDataProvider.getList().clear();
			basketDataProvider.getList().addAll(result);
			flush();

		}
	}

	private final class BasketDiscountHandler implements
			AsyncCallback<Integer>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Integer result) {
			basketWidget.getDiscountBox().setText(result.toString());
			flush();
		}
	}

	private final class BasketVoidHandler implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub

		}
	}

	private final class BasketComparator implements Comparator<BasketItem> {

		@Override
		public int compare(BasketItem o1, BasketItem o2) {	
			return ComparisonChain.start().compare(o1.getArticleId(), o2.getArticleId()).result();			
		}

	}

}
