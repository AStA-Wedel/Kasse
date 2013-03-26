package org.fhw.asta.kasse.client.widget.sidebar;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Category;
import org.fhw.asta.kasse.shared.model.PersonGroup;

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
		clear();
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
	public void addLink(String link, String name) {
		addHtml("<li><a href=\""+link+"\">"+name+"</a></li>");
		
	}

	@Override
	public void addCat(Category cat) {
		addHtml("<li><a href=\"#ArticleListPlace:"+cat.getId()+"\">"+cat.getName()+"</a></li>");
		
	}

	@Override
	public void addCats(List<Category> cats) {
		clear();
		addLink("#ArticleListPlace:","Alle");
		for (Category cat : cats)
		{
			addCat(cat);
		}
		
	}

	@Override
	public void addCapital(String capital) {
		addHtml("<li class=\"nav-header\">"+capital+"</li>");	
	}

	@Override
	public void addUserGroup(PersonGroup pers) {
		addHtml("<li><a href=\"#UserListPlace:"+pers.getGroupId()+"\">"+pers.getName()+"</a></li>");
		
	}

	@Override
	public void addUserGroups(List<PersonGroup> pers) {
		clear();
		addLink("#UserListPlace:","Alle");
		for (PersonGroup group: pers)
		{
			addUserGroup(group);
		}
		
	}
}