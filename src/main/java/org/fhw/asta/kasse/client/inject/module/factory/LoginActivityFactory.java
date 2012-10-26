package org.fhw.asta.kasse.client.inject.module.factory;

import org.fhw.asta.kasse.client.activity.LoginActivity;
import org.fhw.asta.kasse.client.place.LoginPlace;

public interface LoginActivityFactory {
	
	LoginActivity create(LoginPlace loginPlace);

}
