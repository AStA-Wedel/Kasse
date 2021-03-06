package org.fhw.asta.kasse.client.app;

import org.fhw.asta.kasse.client.place.ArticleListPlace;
import org.fhw.asta.kasse.client.place.LoginPlace;
import org.fhw.asta.kasse.client.place.PrintCustomsPlace;
import org.fhw.asta.kasse.client.place.UserListPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({LoginPlace.Tokenizer.class, ArticleListPlace.Tokenizer.class, PrintCustomsPlace.Tokenizer.class, UserListPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {}
