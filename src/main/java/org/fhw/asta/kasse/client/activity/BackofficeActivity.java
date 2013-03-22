package org.fhw.asta.kasse.client.activity;

import org.fhw.asta.kasse.client.place.BackofficePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class BackofficeActivity extends AbstractActivity {

	private final BackofficePlace backofficePlace;
	
	@Inject
	public BackofficeActivity(@Assisted BackofficePlace backofficePlace) {
		this.backofficePlace = backofficePlace;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub
		
	}

}
