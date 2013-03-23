package org.fhw.asta.kasse.client.activity;

import org.fhw.asta.kasse.client.widget.HasTopbar;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.client.widget.topbar.ready.ReadyTopBarWidget;
import org.fhw.asta.kasse.client.widget.userlist.UserListWidget;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class UserListActivity extends AbstractActivity {

	@Inject
	private ReadyTopBarWidget readyTopbarWidget;

	@Inject
	private SidebarWidget sidebarWidget;

	@Inject
	private MainWidget mainWidget;

	@Inject
	private HasTopbar topbarContainer;

	@Inject
	private UserListWidget userListWidget;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		topbarContainer.setTopbar(readyTopbarWidget);
		
		mainWidget.setSidebarWidget(sidebarWidget);
		mainWidget.setBasketWidget(null);
		mainWidget.setWidget(userListWidget);
		
		panel.setWidget(mainWidget);

	}

}
