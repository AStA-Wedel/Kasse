package org.fhw.asta.kasse.client.activity;

import java.util.Arrays;
import java.util.List;

import org.fhw.asta.kasse.client.controller.BasketController;
import org.fhw.asta.kasse.client.widget.articlelist.ArticleListWidget;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;

public class ArticleListActivity extends AbstractActivity {

	@Inject
	private ArticleListWidget articleListWidget;

	@Inject
	private PlaceController placeController;
	
	@Inject
	private BasketController basketController;
	
	@Inject
	private BasketWidget basketWidget;
	
	private ListDataProvider<Article> articleDataProvider;
	
	private ListDataProvider<BasketItem> basketDataProvider;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(articleListWidget);
		//panel.setWidget(basketWidget);
		
		basketDataProvider = new ListDataProvider<BasketItem>();
		basketDataProvider.addDataDisplay(basketWidget.getBasketTable());
		
		
		articleDataProvider = new ListDataProvider<Article>(new ArticleIdProvider());
		articleDataProvider.addDataDisplay(articleListWidget.getArticleList());		

		List<Article> articles = Arrays.asList(new Article(123, 0, "Strift", new EuroAmount(100), "BLAH", 0, true));
		
		BasketItem basketItem = new BasketItem("Stift", new EuroAmount(100), 1234);
		basketController.addBasketPosition(basketItem);
		
		articleDataProvider.setList(articles);
		
	}
	
	@Override
	public void onStop() {
		articleDataProvider.removeDataDisplay(articleListWidget.getArticleList());
	}
	
	private static final class ArticleIdProvider implements ProvidesKey<Article> {

		@Override
		public Object getKey(Article item) {
			return item.getId();
		}
		
	}

}
