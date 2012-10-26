package org.fhw.asta.kasse.client.activity;

import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.widget.login.LoginWidget;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class LoginActivity extends AbstractActivity {

	private final LoginPlace loginPlace; 
	
	@Inject 
	private LoginWidget loginWidget; 
	
	@Inject
	public LoginActivity(@Assisted LoginPlace loginPlace) {
		this.loginPlace = loginPlace;
	}
	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		panel.setWidget(loginWidget);
		
		loginWidget.getSubmitButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				String email = loginWidget.getEmailText().getText();
				String password = loginWidget.getPasswordText().getText();
				
				
				
				
				
			}
		});
		
	}
	
}
