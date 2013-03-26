package org.fhw.asta.kasse.client.widget.userlist;

import org.fhw.asta.kasse.shared.model.Person;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface UserListWidget  extends IsWidget {
	HasData<Person> getUserList();
	Column<Person,String> getMatrNrColumn();
	Column<Person,String> getPrenameColumn();
	Column<Person,String> getSurnameColumn();
}
