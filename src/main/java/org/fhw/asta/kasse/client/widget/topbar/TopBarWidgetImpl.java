package org.fhw.asta.kasse.client.widget.topbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TopBarWidgetImpl extends Composite implements  TopBarWidget {

	private static TopBarWidgetImplUiBinder uiBinder = GWT
			.create(TopBarWidgetImplUiBinder.class);

	interface TopBarWidgetImplUiBinder extends
			UiBinder<Widget, TopBarWidgetImpl> {
	}

	public TopBarWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	SimplePanel simplePanel;
	
	@Override
	public void setWidget(IsWidget widget) {
		simplePanel.setWidget(widget);
	}


}
