package org.fhw.asta.kasse.client.activity;

import java.util.List;
import java.util.Set;

import org.fhw.asta.kasse.client.controller.BasketController;
import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.widget.HasTopbar;
import org.fhw.asta.kasse.client.widget.articlelist.ArticleListWidget;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.client.widget.topbar.ready.ReadyTopBarWidget;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.model.Category;
import org.fhw.asta.kasse.shared.service.article.ArticleServiceAsync;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ArticleListActivity extends AbstractActivity {

	@Inject
	private ArticleListWidget articleListWidget;

	@Inject
	private PlaceController placeController;
	
	@Inject
	private BasketController basketController;
	
	@Inject 
	private ArticleServiceAsync articleService;

	@Inject
	private ReadyTopBarWidget readyTopbarWidget;
	
	@Inject
	private BasketWidget basketWidget;
	
	@Inject
	private SidebarWidget sidebarWidget;
	
	@Inject
	private MainWidget mainWidget;
	
	@Inject
	private HasTopbar topbarContainer;
	
	private ListDataProvider<Article> articleDataProvider;
	
	private Article currentOverlayObject; 
	
	private Set<HandlerRegistration> handlerRegistrations = Sets.newHashSet();

	private ArticleListPlace articleListPlace;
	
	@Inject
	public ArticleListActivity(@Assisted ArticleListPlace articleListPlace) {
		this.articleListPlace = articleListPlace;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		topbarContainer.setTopbar(readyTopbarWidget);
		
		mainWidget.setSidebarWidget(sidebarWidget);
		mainWidget.setBasketWidget(basketWidget);
		mainWidget.setWidget(articleListWidget);
		
		panel.setWidget(mainWidget);
		
		articleListWidget.getToBasketColumn().setFieldUpdater(new ToBasketUpdater());
		
		OverlayOpenFieldUpdater openOverlayFieldUpdater = new OverlayOpenFieldUpdater();
		articleListWidget.getIdColumn().setFieldUpdater(openOverlayFieldUpdater);
		articleListWidget.getNameColumn().setFieldUpdater(openOverlayFieldUpdater);
		
		handlerRegistrations.add(articleListWidget.getOverlayToBasketButton().addDoubleClickHandler(new ToBasketClickHandler()));
				
		articleDataProvider = new ListDataProvider<Article>(new ArticleIdProvider());
		articleDataProvider.addDataDisplay(articleListWidget.getArticleList());		
		articleService.getCategories(new CategoryCallback());
		
		if (Strings.isNullOrEmpty(articleListPlace.getToken())) {
			articleService.getArticles(new ArticleDataHandler());			
		} else {
			articleService.getArticlesByCategory(articleListPlace.getToken(), new ArticleDataHandler());			
		}		
	}
	
	@Override
	public void onStop() {
		articleDataProvider.removeDataDisplay(articleListWidget.getArticleList());
		
		for (HandlerRegistration handlerRegistration : handlerRegistrations) {
			handlerRegistration.removeHandler();
		}
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
	
	private class ToBasketClickHandler implements DoubleClickHandler {

		@Override
		public void onDoubleClick(DoubleClickEvent event) {
			basketController.addBasketPosition(currentOverlayObject);
			articleListWidget.closeOverlay();			
		}
		
		
	}
	
	private class CategoryCallback implements AsyncCallback<List<Category>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<Category> result) {
			sidebarWidget.addCats(result);			
		}	
	}	
	
}
