package org.fhw.asta.kasse.client.app;

import org.fhw.asta.kasse.client.inject.module.factory.ArticleListActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.BackofficeActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.LoginActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.PrintCustomsActivityFactory;
import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.place.BackofficePlace;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.place.PrintCustomsPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class AppActivityMapper implements ActivityMapper {

	@Inject
	private LoginActivityFactory loginActivityFactory;
	
	@Inject
	private ArticleListActivityFactory articleListActivityFactory;
	
	@Inject
	private BackofficeActivityFactory backofficeActivityFactory;
	
	@Inject
	private PrintCustomsActivityFactory printCustomsActivityFactory;
	
	@Override
	public Activity getActivity(Place place) {

		if (place instanceof LoginPlace) {
			return loginActivityFactory.create((LoginPlace)place);
		}
		
		if (place instanceof ArticleListPlace) {
			return articleListActivityFactory.create((ArticleListPlace)place);
		}
		
		if (place instanceof BackofficePlace) {
			return backofficeActivityFactory.create((BackofficePlace)place);
		}
		
		if (place instanceof PrintCustomsPlace) {
			return printCustomsActivityFactory.create((PrintCustomsPlace)place);
		}

		return null;
	}
	
}
