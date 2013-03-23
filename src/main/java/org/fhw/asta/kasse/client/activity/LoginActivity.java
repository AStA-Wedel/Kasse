package org.fhw.asta.kasse.client.activity;

import org.fhw.asta.kasse.client.common.login.LoginToken;
import org.fhw.asta.kasse.client.components.SessionManagerComponent;
import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.HasTopbar;
import org.fhw.asta.kasse.client.widget.login.LoginWidget;
import org.fhw.asta.kasse.client.widget.topbar.TopBarWidgetContainer;
import org.fhw.asta.kasse.client.widget.topbar.empty.EmptyTopBarWidget;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.service.user.UserServiceAsync;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;


public class LoginActivity extends AbstractActivity {

	private final LoginPlace loginPlace; 

	@Inject 
	private LoginWidget loginWidget; 

	@Inject
	private UserServiceAsync userService;

	@Inject
	private PlaceController placeController;
	
	@Inject
	private EmptyTopBarWidget topbarWidget;
	
	@Inject
	private HasTopbar topbarContainer;
	
	@Inject 
	private SessionManagerComponent sessionManager;
	
	private HandlerRegistration submitHandlerRegistration;
	
	@Inject
	public LoginActivity(@Assisted LoginPlace loginPlace) {
		this.loginPlace = loginPlace;
	}
	
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {		
		
		submitHandlerRegistration = loginWidget.getSubmitButton().addClickHandler(new LoginClickHandler());	
		
		LoginToken loginToken = loginPlace.getLoginToken();
		
		Optional<String> maybeEmail = loginToken.getEmail();
		
		loginWidget.getEmailText().setText(maybeEmail.isPresent() ? maybeEmail.get() : "");
				
		panel.setWidget(loginWidget);
		topbarContainer.setTopbar(topbarWidget);
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
			
			sessionManager.setLoggedIn(loginWidget.getEmailText().getText());
			
			if (loginPlace.getLoginToken().getRedirect().isPresent()) {
				placeController.goTo(loginPlace.getLoginToken().getRedirect().get());
			} else {
				placeController.goTo(new ArticleListPlace());				
			}
			
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
