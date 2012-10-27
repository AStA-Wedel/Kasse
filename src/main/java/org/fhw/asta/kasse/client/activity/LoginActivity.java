package org.fhw.asta.kasse.client.activity;

import java.util.List;

import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.client.widget.login.LoginWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.service.UserServiceAsync;
import org.fhw.asta.kasse.shared.service.basket.BasketServiceAsync;

import com.google.common.base.Strings;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class LoginActivity extends AbstractActivity {

	private final LoginPlace loginPlace; 
		
	private HandlerRegistration submitHandlerRegistration;
	
	@Inject 
	private LoginWidget loginWidget; 
	
	@Inject
	private PlaceController placeController;
	
	@Inject
	private UserServiceAsync userService;
	
	@Inject
	private BasketServiceAsync basketService;
	
	@Inject
	private BasketWidget basketWidget;
	
	@Inject
	private SidebarWidget sidebarWidget;
	
	@Inject
	public LoginActivity(@Assisted LoginPlace loginPlace) {
		this.loginPlace = loginPlace;
	}
	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {		
		
		submitHandlerRegistration = loginWidget.getSubmitButton().addClickHandler(new LoginClickHandler());	
		
		loginWidget.getEmailText().setText(loginPlace.getEmail());
		
		panel.setWidget(loginWidget);		
	}
	
	private void doLogin(String email, String password) {
		if (!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(password)) {
			userService.authenticate(email, password, new AuthenticationCallback());
		} else {
			//TODO loginWidget.showError("Email and password need to be provided");
		}
		
	}
	
	private void handleAuthenticationResult(AuthenticationResult authenticationResult) {
	
		switch (authenticationResult.getAuthenticationStatus()) {
			
		case AUTHENTICATED:
			sidebarWidget.addCat("Category");
			sidebarWidget.addLink("#","Link");
			basketService.getBasket(new AsyncCallback<List<BasketItem>>() {
				
				@Override
				public void onSuccess(List<BasketItem> result) {
				
					ListDataProvider<BasketItem> basketDataProvider = new ListDataProvider<BasketItem>();
					
					basketDataProvider.addDataDisplay(basketWidget.getBasketTable());
					basketDataProvider.setList(result);
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			
			
			
			
			
			placeController.goTo(/* TODO */ null);
			break;
			
		case NOT_AUTHENTICATED:
			//TODO
			break;
		}
	}
	
	private void handleAuthenticationError() {
		
	}
	
	public void onStop() {
		loginWidget.getPasswordText().setText("");
		submitHandlerRegistration.removeHandler();
	}
	
	private class AuthenticationCallback implements AsyncCallback<AuthenticationResult> {

		@Override
		public void onFailure(Throwable caught) {
			handleAuthenticationError();
		}

		@Override
		public void onSuccess(AuthenticationResult result) {
			handleAuthenticationResult(result);
		}

	}
	
	private class LoginClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
		
			String email = loginWidget.getEmailText().getText();
			String password = loginWidget.getPasswordText().getText();
			
			doLogin(email, password);
		}
		
	}
	
}
