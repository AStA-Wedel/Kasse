package org.fhw.asta.kasse.client.widget.topbar.modules.quickbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QuickBoxWidgetImpl extends Composite implements QuickBoxWidget {

	private static QuickBoxWidgetImplUiBinder uiBinder = GWT
			.create(QuickBoxWidgetImplUiBinder.class);

	interface QuickBoxWidgetImplUiBinder extends
			UiBinder<Widget, QuickBoxWidgetImpl> {
	}

	public QuickBoxWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		textBox.getElement().setAttribute("placeholder", "Quickbox");
	}

	@UiField
	TextBox textBox;
	
	@Override
	public HasText getQuickBox() {
		return textBox;
	}

}
