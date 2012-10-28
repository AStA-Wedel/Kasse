package org.fhw.asta.kasse.client.widget.topbar.empty;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EmptyTopBarWidgetImpl extends Composite implements EmptyTopBarWidget {

	private static EmptyTopBarWidgetImplUiBinder uiBinder = GWT
			.create(EmptyTopBarWidgetImplUiBinder.class);

	interface EmptyTopBarWidgetImplUiBinder extends
			UiBinder<Widget, EmptyTopBarWidgetImpl> {
	}

	public EmptyTopBarWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
