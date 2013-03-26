package org.fhw.asta.kasse.client.widget.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UserWidgetImpl extends Composite implements UserWidget {
	
	private static UserWidgetImplementationUiBinder uiBinder = GWT
			.create(UserWidgetImplementationUiBinder.class);

	
	
	interface UserWidgetImplementationUiBinder extends
			UiBinder<Widget, UserWidgetImpl> {
	}

	public UserWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}


}
