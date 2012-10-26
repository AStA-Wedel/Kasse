package org.fhw.asta.kasse.client.widget.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidgetImpl extends Composite implements LoginWidget {

	private static LoginWidgetUiBinder uiBinder = GWT
			.create(LoginWidgetUiBinder.class);

	interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidgetImpl> {
	}

	public LoginWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));		
	}

	@UiField
	TextBox emailTextBox;
	
	@UiField
	TextBox passwordTextBox;
	
	@UiField
	Button submitButton;
	
	public HasText getEmailText() {
		return emailTextBox;
	}
	
	public HasText getPasswordText() {
		return passwordTextBox;
	}
	
	public HasClickHandlers getSubmitButton() {
		return submitButton;
	}
	
}
