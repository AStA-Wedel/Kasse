package org.fhw.asta.kasse.client.widget.topbar;

import org.fhw.asta.kasse.client.widget.topbar.empty.EmptyTopBarWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class TopBarWidgetImpl extends Composite implements  TopBarWidget {

	private static TopBarWidgetImplUiBinder uiBinder = GWT
			.create(TopBarWidgetImplUiBinder.class);

	interface TopBarWidgetImplUiBinder extends
			UiBinder<Widget, TopBarWidgetImpl> {
	}

	public TopBarWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Inject
	public void init(EmptyTopBarWidget emptyTopBarWidget)
	{
		setWidget(emptyTopBarWidget);
	}
	
	@UiField
	SimplePanel simplePanel;
	
	@Override
	public void setWidget(IsWidget widget) {
		simplePanel.setWidget(widget);
	}


}
