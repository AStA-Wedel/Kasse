package org.fhw.asta.kasse.client.widget.login;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface LoginWidget extends IsWidget {

	HasText getPasswordText();
	
	HasText getEmailText();
	
	HasClickHandlers getSubmitButton();
	
}
