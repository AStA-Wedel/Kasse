package org.fhw.asta.kasse.client.inject.module.factory;

import org.fhw.asta.kasse.client.activity.UserListActivity;
import org.fhw.asta.kasse.client.place.UserListPlace;

public interface UserListActivityFactory {
	UserListActivity create(UserListPlace userListPlace);
}
