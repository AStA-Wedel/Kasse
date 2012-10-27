package org.fhw.asta.kasse.client.widget.sidebar;

import com.google.gwt.user.client.ui.IsWidget;

public interface SidebarWidget extends IsWidget{
	
	public void clear();
	
	public void addHtmlLink(String htmlLink);

	public void addLink(String link, String name);

}
