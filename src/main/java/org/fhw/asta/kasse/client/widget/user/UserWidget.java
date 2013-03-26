package org.fhw.asta.kasse.client.widget.user;

import org.fhw.asta.kasse.shared.model.Person;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.IsWidget;

public interface UserWidget extends IsWidget{
	void showPerson(Person pers);
	HasClickHandlers getAbortButton();
}
