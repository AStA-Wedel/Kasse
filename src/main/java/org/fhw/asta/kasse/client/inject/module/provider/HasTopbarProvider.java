package org.fhw.asta.kasse.client.inject.module.provider;

import org.fhw.asta.kasse.client.widget.HasTopbar;
import org.fhw.asta.kasse.client.widget.root.KasseRootPanel;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class HasTopbarProvider implements Provider<HasTopbar> {

	@Inject
	private KasseRootPanel kasseRootPanel;
	
	@Override
	public HasTopbar get() {
		return kasseRootPanel;
	}

}
