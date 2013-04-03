package org.fhw.asta.kasse.client.widget.print;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class PrintWidgetImpl extends Composite implements PrintWidget {

	@UiField HTML htmlWidget;
	
	private static PrintWidgetImplUiBinder uiBinder = GWT
			.create(PrintWidgetImplUiBinder.class);

	interface PrintWidgetImplUiBinder extends UiBinder<Widget, PrintWidgetImpl> {
	}

	public PrintWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void clear() {
		htmlWidget.setHTML("");		
	}

	@Override
	public void addHtml(String html) {
		htmlWidget.setHTML(htmlWidget.getHTML()+html);		
	}

	@Override
	public void addHtml(SafeHtml safeHtml) {
		addHtml(safeHtml.asString());
	}

}
