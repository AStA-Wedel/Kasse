package org.fhw.asta.kasse.client.widget.topbar.modules.navbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

public class NavBarWidgetImpl extends Composite implements NavBarWidget {

	private static NavBarWidgetImplUiBinder uiBinder = GWT
			.create(NavBarWidgetImplUiBinder.class);

	interface NavBarWidgetImplUiBinder extends
			UiBinder<Widget, NavBarWidgetImpl> {
	}

	public NavBarWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	//@UiField
	MenuBar menuBar;

	@Override
	public HasAnimation getNavBar() {
		return menuBar;
	}
	
	
	
	
}
