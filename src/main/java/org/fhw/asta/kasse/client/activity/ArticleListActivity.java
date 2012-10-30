package org.fhw.asta.kasse.client.activity;

import java.util.List;

import org.fhw.asta.kasse.client.controller.BasketController;
import org.fhw.asta.kasse.client.widget.articlelist.ArticleListWidget;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.service.article.ArticleServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	
	@Inject 
	private ArticleServiceAsync articleService;
	
	private ListDataProvider<Article> articleDataProvider;
	
	private Article currentOverlayObject; 
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(articleListWidget);
		
		articleListWidget.getToBasketColumn().setFieldUpdater(new ToBasketUpdater());
		
		OverlayOpenFieldUpdater openOverlayFieldUpdater = new OverlayOpenFieldUpdater();
		articleListWidget.getIdColumn().setFieldUpdater(openOverlayFieldUpdater);
		articleListWidget.getNameColumn().setFieldUpdater(openOverlayFieldUpdater);
		
		articleDataProvider = new ListDataProvider<Article>(new ArticleIdProvider());
		articleDataProvider.addDataDisplay(articleListWidget.getArticleList());		
		articleService.getArticles(new ArticleDataHandler());
		
		
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
	
	private final class ArticleDataHandler implements AsyncCallback<List<Article>> 
	{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<Article> result) {
			articleDataProvider.setList(result);		
		}
		
	}
	
	private final class ArticleBundleHandler implements AsyncCallback<List<Article>> 
	{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<Article> result) {
			articleListWidget.showOverlay(currentOverlayObject,result);
		}
		
	}
	
	private class OverlayOpenFieldUpdater implements FieldUpdater<Article, String>{

		@Override
		public void update(int index, Article object, String value) {
			currentOverlayObject = object;
			articleService.getArticleComponents(object, new ArticleBundleHandler());
		}
		
	}
	
	private class ToBasketUpdater implements FieldUpdater<Article,String>
	{

		@Override
		public void update(int index, Article object, String value) {
			basketController.addBasketPosition(object);	
		}
	}

	
}
