package org.fhw.asta.kasse.client.inject.module.factory;

import org.fhw.asta.kasse.client.activity.PrintCustomsActivity;
import org.fhw.asta.kasse.client.place.PrintCustomsPlace;

public interface PrintCustomsActivityFactory {

	PrintCustomsActivity create(PrintCustomsPlace printCustomsPlace);
}
