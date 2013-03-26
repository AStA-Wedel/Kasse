package org.fhw.asta.kasse.client.activity;

import java.util.List;

import org.fhw.asta.kasse.client.place.UserListPlace;
import org.fhw.asta.kasse.client.widget.HasTopbar;
import org.fhw.asta.kasse.client.widget.main.MainWidget;
import org.fhw.asta.kasse.client.widget.sidebar.SidebarWidget;
import org.fhw.asta.kasse.client.widget.topbar.ready.ReadyTopBarWidget;
import org.fhw.asta.kasse.client.widget.user.UserWidget;
import org.fhw.asta.kasse.client.widget.userlist.UserListWidget;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.model.PersonGroup;
import org.fhw.asta.kasse.shared.service.user.UserServiceAsync;

import com.google.common.base.Strings;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

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
	
	@Inject
	private UserWidget userWidget;
	
	@Inject
	private UserServiceAsync userService;
	
	
	private UserListPlace userListPlace;

	private ListDataProvider<Person> userDataProvider;
	
	
	@Inject
	public UserListActivity(@Assisted UserListPlace userListPlace) {
		this.userListPlace = userListPlace;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		topbarContainer.setTopbar(readyTopbarWidget);
		
		mainWidget.setSidebarWidget(sidebarWidget);
		mainWidget.setBasketWidget(userWidget);
		mainWidget.setWidget(userListWidget);
		
		panel.setWidget(mainWidget);
		
		AdjustUserUpdater adjustUserUpdater = new AdjustUserUpdater();
		userListWidget.getMatrNrColumn().setFieldUpdater(adjustUserUpdater);
		userListWidget.getPrenameColumn().setFieldUpdater(adjustUserUpdater);
		userListWidget.getSurnameColumn().setFieldUpdater(adjustUserUpdater);
		
		userDataProvider = new ListDataProvider<Person>(new UserIdProvider());
		userDataProvider.addDataDisplay(userListWidget.getUserList());
		
		userService.getAllUserGroups(new UserGroupCallback());
		
		if (Strings.isNullOrEmpty(userListPlace.getToken())) {
			userService.getAllUsers(new UserCallback());			
		} else {
			userService.getUsersByGroup(userListPlace.getToken(), new UserCallback());			
		}
	}
	
	private class AdjustUserUpdater implements FieldUpdater<Person,String>
	{

		@Override
		public void update(int index, Person object, String value) {
				
		}
	}

	private static final class UserIdProvider implements ProvidesKey<Person> {

		@Override
		public Object getKey(Person item) {
			return item.getLdapName();
		}
		
	}
	
	
	private final class UserCallback implements AsyncCallback<List<Person>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<Person> result) {
			userDataProvider.setList(result);
			
		}
		
	}
	
	private class UserGroupCallback implements AsyncCallback<List<PersonGroup>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<PersonGroup> result) {
			sidebarWidget.addUserGroups(result);
		}	
	}	
}
