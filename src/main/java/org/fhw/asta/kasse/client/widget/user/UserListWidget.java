package org.fhw.asta.kasse.client.widget.user;

import org.fhw.asta.kasse.server.common.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class UserListWidget extends Composite {

	private static UserListWidgetUiBinder uiBinder = GWT
			.create(UserListWidgetUiBinder.class);

	interface UserListWidgetUiBinder extends UiBinder<Widget, UserListWidget> {
	}

	@UiField(provided = true)
	CellTable<User> userTable;
	
	public UserListWidget() {
		
		userTable = initUserTable();
		
		initWidget(uiBinder.createAndBindUi(this));
	}

	public HasData<User> userData() {
		return userTable;
	}
	
	private CellTable<User> initUserTable() {
	
		CellTable<User> userTable = new CellTable<User>();
		
		
		
		
		
		
		
		
		
		
		
		
		return userTable;
	}
	
	
}
