package org.fhw.asta.kasse.client.widget.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidgetImpl extends Composite implements MainWidget {

	private static MainWidgetImplUiBinder uiBinder = GWT
			.create(MainWidgetImplUiBinder.class);

	interface MainWidgetImplUiBinder extends UiBinder<Widget, MainWidgetImpl> {
	}

	public MainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	SimplePanel mainContainer;
	
	@UiField
	HTMLPanel sidebarContainer;
	
	@UiField
	HTMLPanel basketContainer;
	
	private IsWidget basketWidget;
	
	private IsWidget sidebarWidget;
	
	@Override
	public void setWidget(IsWidget w) {
		mainContainer.setWidget(w);
	}

	@Override
	public void setSidebarWidget(IsWidget widget) {
		this.basketWidget = widget;
		sidebarContainer.clear();
		
		if (widget != null) {
			sidebarContainer.add(widget);			
		}
	}

	@Override
	public void setBasketWidget(IsWidget widget) {
		this.sidebarWidget = widget;
		basketContainer.clear();
		
		if (widget != null) {
			basketContainer.add(widget);			
		}
	}

	@Override
	public void setMainWidget(IsWidget widget) {
		setWidget(widget);
	}
	
	public IsWidget getBasketWidget() {
		return basketWidget;
	}
	
	public IsWidget getSidebarWidget() {
		return sidebarWidget;
	}
	
}
