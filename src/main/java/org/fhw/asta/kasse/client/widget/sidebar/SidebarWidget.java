package org.fhw.asta.kasse.client.widget.sidebar;

import com.google.gwt.user.client.ui.IsWidget;

public interface SidebarWidget extends IsWidget{
	
	public void clear();
	public void addHtml(String html);
	public void addLink(String link, String name);
	public void addCat(String cat);

}
