package org.fhw.asta.kasse.client.app;

import org.fhw.asta.kasse.client.inject.module.factory.ArticleListActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.LoginActivityFactory;
import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.place.LoginPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class AppActivityMapper implements ActivityMapper {

	@Inject
	private LoginActivityFactory loginActivityFactory;
	
	@Inject
	private ArticleListActivityFactory articleListActivityFactory;
	
	@Override
	public Activity getActivity(Place place) {

		if (place instanceof LoginPlace) {
			return loginActivityFactory.create((LoginPlace)place);
		}
		
		if (place instanceof ArticleListPlace) {
			return articleListActivityFactory.create();
		}

		return null;
	}
	
}
