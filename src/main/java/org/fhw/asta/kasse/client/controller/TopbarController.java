package org.fhw.asta.kasse.client.controller;

import org.fhw.asta.kasse.client.event.LoginEvent;
import org.fhw.asta.kasse.client.event.LoginEventHandler;
import org.fhw.asta.kasse.client.widget.topbar.TopBarWidgetContainer;
import org.fhw.asta.kasse.client.widget.topbar.ready.ReadyTopBarWidget;
import org.fhw.asta.kasse.shared.service.article.ArticleServiceAsync;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class TopbarController {

	@Inject
	private ReadyTopBarWidget readyTopbarWidget;
	
	@Inject
	private TopBarWidgetContainer topbarWidget;
	
	@Inject
	private BasketController basketController;
	
	@Inject
	private ArticleServiceAsync articleService;
	
	@Inject
	public void init(EventBus eventBus) {
		eventBus.addHandler(LoginEvent.TYPE, new LoginHandler());
	}
	
	private void onLogin() {
		//topbarWidget.setWidget(readyTopbarWidget);
		
		//readyTopbarWidget.getQuickBoxHandlers().addChangeHandler(new QuickBoxHandler());
	}
		
	private class LoginHandler implements LoginEventHandler {

		@Override
		public void onLogin(LoginEvent loginEvent) {
			TopbarController.this.onLogin();
		}
		
	}
		
}
