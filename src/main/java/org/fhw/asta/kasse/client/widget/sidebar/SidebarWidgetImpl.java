package org.fhw.asta.kasse.client.widget.sidebar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidgetImpl extends Composite implements SidebarWidget {

	private static SidebarImplementationUiBinder uiBinder = GWT
			.create(SidebarImplementationUiBinder.class);
	
	@UiField HTML htmlWidget;

	interface SidebarImplementationUiBinder extends
			UiBinder<Widget, SidebarWidgetImpl> {
	}

	public SidebarWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void clear() {
		htmlWidget.setHTML("");
		
	}

	@Override
	public void addHtmlLink(String htmlLink) {
		htmlWidget.setHTML(htmlWidget.getHTML()+"<br />"+htmlLink);
		
	}

	@Override
	public void addLink(String link, String name) {
		addHtmlLink("<a href=\""+link+"\">"+name+"</a>");
		
	}



}
