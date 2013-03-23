package org.fhw.asta.kasse.client.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.client.common.PrintCustomsToken;
import org.fhw.asta.kasse.client.common.PrintCustomsToken.PrintType;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.service.checkout.CheckoutServiceAsync;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;

public class BasketController {
	private final ListDataProvider<BasketItem> basketDataProvider;

	private BasketWidget basketWidget;

	private FieldUpdater<BasketItem, String> deleteUpdater;
	private FieldUpdater<BasketItem, String> amountUpdater;
	private FieldUpdater<BasketItem, String> discountUpdater;
	private ValueChangeHandler<String> masterDiscountUpdater;
	private ValueChangeHandler<String> matrNrUpdater;
	private ClickHandler checkoutHandler;
	private BasketComparator basketComparator = new BasketComparator();

	@Inject
	private CheckoutServiceAsync checkoutService;

	@Inject
	private PlaceController placeController;

	public BasketController() {
		this.basketDataProvider = new ListDataProvider<BasketItem>(
				new BasketItemKeyProvider());
	}

	@Inject
	public void init(BasketWidget basketWidget) {

		this.basketWidget = basketWidget;

		basketDataProvider.addDataDisplay(this.basketWidget.getBasketTable());

		deleteUpdater = new DeleteUpdater();
		basketWidget.getDeleteColumn().setFieldUpdater(deleteUpdater);

		amountUpdater = new AmountUpdater();
		basketWidget.getAmountColumn().setFieldUpdater(amountUpdater);

		discountUpdater = new DiscountUpdater();
		basketWidget.getDiscountColumn().setFieldUpdater(discountUpdater);

		masterDiscountUpdater = new MasterDiscountUpdater();
		basketWidget.getDiscountBox().addValueChangeHandler(
				masterDiscountUpdater);

		checkoutHandler = new CheckoutHandler();
		basketWidget.getCheckoutButton().addClickHandler(checkoutHandler);

		matrNrUpdater = new MatrNrUpdater();
		basketWidget.getMatrNrBox().addValueChangeHandler(matrNrUpdater);
	}

	public void addBasketPosition(BasketItem basketItem) {

		Iterator<BasketItem> basketIterator = basketDataProvider.getList()
				.iterator();
		while (basketIterator.hasNext()) {
			BasketItem next = basketIterator.next();
			if (next.getArticleId() == basketItem.getArticleId()) {
				basketItem = new BasketItem(basketItem.getItemName(),
						basketItem.getItemPrice(), basketItem.getArticleId(),
						basketItem.getAmount() + next.getAmount(),
						next.getDiscount());
				basketDataProvider.getList().remove(next);
			}
		}

		basketDataProvider.getList().add(basketItem);
		flush();

	}

	public void addBasketPosition(Article article) {

		addBasketPosition(new BasketItem(article.getName(), article.getPrice(),
				article.getId(), 1));
	}

	private void flush() {
		Collections.sort(basketDataProvider.getList(), basketComparator);

		int sum = 0;

		Iterator<BasketItem> basketIterator = basketDataProvider.getList()
				.iterator();
		while (basketIterator.hasNext()) {
			BasketItem next = basketIterator.next();
			sum += Math.round((next.getItemPrice().getCentAmount() * next
					.getAmount()) * ((100 - next.getDiscount()) / 100.0));

		}

		int discount = 0;

		try {
			discount = Integer.valueOf(basketWidget.getDiscountBox().getText());
		} catch (NumberFormatException e) {
			discount = 0;
		}

		basketWidget.getSumLabel().setText(
				EuroFormatter.format((int) Math.round(sum
						* ((100 - discount) / 100.0))));
	}

	private class CheckoutHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			checkoutService.doCheckout(Lists.newArrayList(basketDataProvider.getList()),
					Integer.valueOf(basketWidget.getDiscountBox().getText()),
					basketWidget.getMatrNrBox().getText(),
					new CheckoutCallback());
		}

	}

	private class AmountUpdater implements FieldUpdater<BasketItem, String> {

		@Override
		public void update(int index, BasketItem object, String value) {
			BasketItem toUpdate;
			if (value.matches("[1-9][0-9]*")) {
				toUpdate = new BasketItem(object.getItemName(),
						object.getItemPrice(), object.getArticleId(),
						Integer.valueOf(value), object.getDiscount());

			} else {
				toUpdate = new BasketItem(object.getItemName(),
						object.getItemPrice(), object.getArticleId(), 1,
						object.getDiscount());
			}

			basketDataProvider.getList().remove(object);
			basketDataProvider.getList().add(toUpdate);
			flush();

		}
	}

	private class DeleteUpdater implements FieldUpdater<BasketItem, String> {

		@Override
		public void update(int index, BasketItem object, String value) {
			basketDataProvider.getList().remove(object);
			flush();
		}
	}

	private class DiscountUpdater implements FieldUpdater<BasketItem, String> {

		@Override
		public void update(int index, BasketItem object, String value) {
			BasketItem toUpdate;

			if (value.matches("[0-9]+")) {
				toUpdate = new BasketItem(object.getItemName(),
						object.getItemPrice(), object.getArticleId(),
						object.getAmount(), Integer.valueOf(value));
			} else {
				toUpdate = new BasketItem(object.getItemName(),
						object.getItemPrice(), object.getArticleId(),
						object.getAmount(), 0);
			}

			basketDataProvider.getList().remove(object);
			basketDataProvider.getList().add(toUpdate);
			flush();

		}

	}

	private class MasterDiscountUpdater implements ValueChangeHandler<String> {

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			if (!event.getValue().matches("[0-9]+")) {
				basketWidget.getDiscountBox().setText("0");
			}
			flush();
		}

	}

	private class MatrNrUpdater implements ValueChangeHandler<String> {

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			if (!event.getValue().matches("[0-9]+")) {
				basketWidget.getMatrNrBox().setText("0000");
			}
			flush();
		}

	}

	private static final class BasketItemKeyProvider implements
			ProvidesKey<BasketItem> {

		@Override
		public Object getKey(BasketItem item) {
			return item.getArticleId();
		}

	}

	private final class BasketComparator implements Comparator<BasketItem> {

		@Override
		public int compare(BasketItem o1, BasketItem o2) {
			return ComparisonChain.start()
					.compare(o1.getArticleId(), o2.getArticleId()).result();
		}

	}

	private class CheckoutCallback implements AsyncCallback<Integer> {

		@Override
		public void onFailure(Throwable caught) {
			//TODO FIXME
		}

		@Override
		public void onSuccess(Integer result) {
			basketDataProvider.getList().clear();
			PrintCustomsToken token = new PrintCustomsToken(PrintType.BILLORDER, result);
			Window.open(Window.Location.createUrlBuilder().setHash("PrintCustomsPlace:").buildString() + token.toString(), "_blank", "");			
		}

	}

}
