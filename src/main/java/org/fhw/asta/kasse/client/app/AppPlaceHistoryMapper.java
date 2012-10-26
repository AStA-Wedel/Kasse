package org.fhw.asta.kasse.client.app;

import org.fhw.asta.kasse.client.place.LoginPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({LoginPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {}
