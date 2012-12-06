package org.fhw.asta.kasse.client.widget.sidebar;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Category;

import com.google.gwt.user.client.ui.IsWidget;

public interface SidebarWidget extends IsWidget{
	
	public void clear();
	public void addHtml(String html);
	public void addLink(String link, String name);
	public void addCapital(String capital);
	public void addCat(Category cat);
	public void addCats(List<Category> cats);

}
