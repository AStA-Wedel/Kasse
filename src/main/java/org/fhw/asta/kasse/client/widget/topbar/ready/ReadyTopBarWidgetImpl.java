package org.fhw.asta.kasse.client.widget.topbar.ready;

import org.fhw.asta.kasse.client.widget.topbar.modules.navbar.NavBarWidget;
import org.fhw.asta.kasse.client.widget.topbar.modules.quickbox.QuickBoxWidget;
import org.fhw.asta.kasse.client.widget.topbar.modules.searchbox.SearchBoxWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ReadyTopBarWidgetImpl extends Composite implements ReadyTopBarWidget {

	private static ReadyTopBarWidgetImplUiBinder uiBinder = GWT
			.create(ReadyTopBarWidgetImplUiBinder.class);

	interface ReadyTopBarWidgetImplUiBinder extends
			UiBinder<Widget, ReadyTopBarWidgetImpl> {
	}

	public ReadyTopBarWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Inject
	NavBarWidget navBar;
	
	@Inject
	SearchBoxWidget searchBox;
	
	@UiField
	HTMLPanel navBarPanel;
	
	@UiField
	HTMLPanel searchBoxPanel;
	
	@Inject
	public void init(NavBarWidget navBar, QuickBoxWidget quickBox, SearchBoxWidget searchBox)
	{
		this.navBar = navBar;
		this.searchBox = searchBox;
		
		searchBoxPanel.add(searchBox);
		navBarPanel.add(navBar);		
	}
	
	@Override
	public HasAnimation getNavBar() {
		return navBar.getNavBar();
	}

	@Override
	public HasText getSearchBox() {
		return searchBox.getSearchBox();
	}


}
