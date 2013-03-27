package org.fhw.asta.kasse.client.widget.user;

import java.util.List;

import org.fhw.asta.kasse.client.place.UserListPlace;
import org.fhw.asta.kasse.shared.model.Account;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.model.PersonGroup;
import org.fhw.asta.kasse.shared.service.user.UserServiceAsync;

import com.google.common.base.Optional;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class UserWidgetImpl extends Composite implements UserWidget {

	@Inject
	private PlaceController placeController;
	
	@UiField
	SimpleCheckBox isAdmin;

	@UiField
	Button abortButton;

	@UiField
	Button saveButton;

	@UiField
	TextBox rzLogin;

	@UiField
	TextBox matrNr;

	@UiField
	ListBox userGroup;

	@UiField
	TextBox preName;

	@UiField
	TextBox surName;

	@UiField
	TextBox mobile;

	@UiField
	TextBox phone;

	@UiField
	TextBox street;

	@UiField
	TextBox streetNumber;

	@UiField
	TextBox zipcode;

	@UiField
	TextBox town;

	@UiField
	TextBox mail;

	@UiField
	TextBox accOwner;

	@UiField
	TextBox accNumber;

	@UiField
	TextBox accBLZ;

	@Inject
	private UserServiceAsync userService;

	private static UserWidgetImplementationUiBinder uiBinder = GWT
			.create(UserWidgetImplementationUiBinder.class);

	interface UserWidgetImplementationUiBinder extends
			UiBinder<Widget, UserWidgetImpl> {
	}

	public UserWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		saveButton.addClickHandler(new SaveClickHander());
	}

	private Person pers;

	@Override
	public void showPerson(Person pers) {
		this.pers = pers;

		userService.getAllUserGroups(new UserGroupCallback());
		userService.loggedOnUserIsAdmin(new AdminCallback());
		userService.getUserAccount(pers.getLdapName(), new AccountCallback());
		rzLogin.setText(pers.getLdapName());
		matrNr.setText(pers.getMatrNo());
		preName.setText(pers.getPrename());
		surName.setText(pers.getSurname());
		mobile.setText(pers.getPhoneMobile());
		phone.setText(pers.getPhoneHome());
		street.setText(pers.getStreet());
		streetNumber.setText(pers.getStreetnumber());
		zipcode.setText(pers.getZipcode());
		town.setText(pers.getTown());
		mail.setText(pers.getEmail());
		isAdmin.setValue(pers.isAdmin());
	}

	private class UserGroupCallback implements AsyncCallback<List<PersonGroup>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(List<PersonGroup> result) {

			userGroup.clear();

			for (PersonGroup pg : result) {
				userGroup.insertItem(pg.getName(),
						String.valueOf(pg.getGroupId()), -1);
			}

			int indexToFind = -1;
			for (int i = 0; i < userGroup.getItemCount(); i++) {
				if (userGroup.getValue(i).equals(
						String.valueOf(pers.getGroupId()))) {
					indexToFind = i;
					break;
				}
			}

			userGroup.setItemSelected(indexToFind, true);
		}
	}

	private class AdminCallback implements AsyncCallback<Boolean> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Boolean result) {
			isAdmin.setEnabled(result);
		}

	}

	private class AccountCallback implements AsyncCallback<Optional<Account>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Optional<Account> result) {
			if (result.isPresent()) {
				accBLZ.setText(result.get().getBlz());
				accOwner.setText(result.get().getOwner());
				accNumber.setText(result.get().getNumber());
			} else {
				accBLZ.setText("");
				accOwner.setText("");
				accNumber.setText("");
			}
		}

	}

	private class SaveClickHander implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Person p = new Person(rzLogin.getText(), 0, surName.getText(),
					preName.getText(), matrNr.getText(), isAdmin.getValue(),
					Integer.valueOf(userGroup.getValue(userGroup.getSelectedIndex())),
					mobile.getText(), phone.getText(), street.getText(),
					zipcode.getText(), town.getText(), streetNumber.getText(),
					mail.getText());
			Account a = new Account(rzLogin.getText(),accOwner.getText(),accNumber.getText(),accBLZ.getText());
			userService.updateOrCreateUserAndAccount(p, a, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Void result) {
					placeController.goTo(new UserListPlace());
					
				}
			});
			
		
		}

	}

	@Override
	public HasClickHandlers getAbortButton() {
		return abortButton;
	}

}
