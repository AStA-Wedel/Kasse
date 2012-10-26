package org.fhw.asta.kasse.client.app;

import org.fhw.asta.kasse.client.inject.module.factory.LoginActivityFactory;
import org.fhw.asta.kasse.client.place.LoginPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class AppActivityMapper implements ActivityMapper {

	@Inject
	private LoginActivityFactory loginActivityFactory;
	
	@Override
	public Activity getActivity(Place place) {

		if (place instanceof LoginPlace) {
			return loginActivityFactory.create((LoginPlace)place);
		}

		return null;
	}
	
}
