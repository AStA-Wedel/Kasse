package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.widget.articlelist.ArticleListWidget;
import org.fhw.asta.kasse.client.widget.articlelist.ArticleListWidgetImpl;
import org.fhw.asta.kasse.client.widget.basket.BasketWidget;
import org.fhw.asta.kasse.client.widget.basket.BasketWidgetImpl;
import org.fhw.asta.kasse.client.widget.login.LoginWidget;
import org.fhw.asta.kasse.client.widget.login.LoginWidgetImpl;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.main.MainWidgetImpl;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidgetImpl;
import org.fhw.asta.kasse.client.widget.topbar.TopBarWidget;
import org.fhw.asta.kasse.client.widget.topbar.TopBarWidgetImpl;
import org.fhw.asta.kasse.client.widget.topbar.empty.EmptyTopBarWidget;
import org.fhw.asta.kasse.client.widget.topbar.empty.EmptyTopBarWidgetImpl;
import org.fhw.asta.kasse.client.widget.topbar.modules.navbar.NavBarWidget;
import org.fhw.asta.kasse.client.widget.topbar.modules.navbar.NavBarWidgetImpl;
import org.fhw.asta.kasse.client.widget.topbar.modules.quickbox.QuickBoxWidget;
import org.fhw.asta.kasse.client.widget.topbar.modules.quickbox.QuickBoxWidgetImpl;
import org.fhw.asta.kasse.client.widget.topbar.modules.searchbox.SearchBoxWidget;
import org.fhw.asta.kasse.client.widget.topbar.modules.searchbox.SearchBoxWidgetImpl;
import org.fhw.asta.kasse.client.widget.topbar.ready.ReadyTopBarWidget;
import org.fhw.asta.kasse.client.widget.topbar.ready.ReadyTopBarWidgetImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class WidgetModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TopBarWidget.class).to(TopBarWidgetImpl.class).in(Singleton.class);
		bind(EmptyTopBarWidget.class).to(EmptyTopBarWidgetImpl.class).in(Singleton.class);
		bind(ReadyTopBarWidget.class).to(ReadyTopBarWidgetImpl.class).in(Singleton.class);
		bind(LoginWidget.class).to(LoginWidgetImpl.class);
		
		bind(MainWidget.class).to(MainWidgetImpl.class).in(Singleton.class);		
		bind(BasketWidget.class).to(BasketWidgetImpl.class).in(Singleton.class);
		bind(SidebarWidget.class).to(SidebarWidgetImpl.class).in(Singleton.class);
		bind(ArticleListWidget.class).to(ArticleListWidgetImpl.class).in(Singleton.class);
		bind(NavBarWidget.class).to(NavBarWidgetImpl.class).in(Singleton.class);
		bind(QuickBoxWidget.class).to(QuickBoxWidgetImpl.class).in(Singleton.class);
		bind(SearchBoxWidget.class).to(SearchBoxWidgetImpl.class).in(Singleton.class);
		
		
	}
	
}
