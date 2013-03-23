package org.fhw.asta.kasse.client.app;

import org.fhw.asta.kasse.client.common.login.LoginToken;
import org.fhw.asta.kasse.client.components.SessionManagerComponent;
import org.fhw.asta.kasse.client.inject.module.factory.ArticleListActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.BackofficeActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.LoginActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.PrintCustomsActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.UserListActivityFactory;
import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.place.BackofficePlace;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.place.PrintCustomsPlace;
import org.fhw.asta.kasse.client.place.UserListPlace;

import com.google.common.base.Optional;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class AppActivityMapper implements ActivityMapper {

	@Inject
	private PlaceController placeController;
	
	@Inject
	private SessionManagerComponent sessionManager;
	
	@Inject
	private LoginActivityFactory loginActivityFactory;
	
	@Inject
	private ArticleListActivityFactory articleListActivityFactory;
	
	@Inject
	private BackofficeActivityFactory backofficeActivityFactory;
	
	@Inject
	private PrintCustomsActivityFactory printCustomsActivityFactory;
	
	@Inject
	private UserListActivityFactory userListActivityFactory;
	
	@Override
	public Activity getActivity(Place place) {

		if (place instanceof LoginPlace) {
			return loginActivityFactory.create((LoginPlace)place);
		}
		
		if (!sessionManager.isLoggedIn()) {
			return loginActivityFactory.create(new LoginPlace(new LoginToken(Optional.<String>absent(), Optional.of(place))));		
		}
		
		// INSERT NEW ACTIVITIES AFTER THIS
		
		if (place instanceof ArticleListPlace) {
			return articleListActivityFactory.create((ArticleListPlace)place);
		}
		
		if (place instanceof BackofficePlace) {
			return backofficeActivityFactory.create((BackofficePlace)place);
		}
		
		if (place instanceof PrintCustomsPlace) {
			return printCustomsActivityFactory.create((PrintCustomsPlace)place);
		}
		
		if (place instanceof UserListPlace) {
			return userListActivityFactory.create((UserListPlace)place);
		}

		return null;
	}
	
}
