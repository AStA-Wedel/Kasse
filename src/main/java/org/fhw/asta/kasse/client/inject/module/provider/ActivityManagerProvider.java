package org.fhw.asta.kasse.client.inject.module.provider;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ActivityManagerProvider implements Provider<ActivityManager> {

	@Inject
	private ActivityMapper activityMapper;
	
	@Inject
	private SimpleEventBus eventBus;
	
	@Override
	public ActivityManager get() {
		return new ActivityManager(activityMapper, eventBus);
	}
	
}
