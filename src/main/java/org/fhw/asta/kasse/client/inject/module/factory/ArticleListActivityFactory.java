package org.fhw.asta.kasse.client.inject.module.factory;

import org.fhw.asta.kasse.client.activity.ArticleListActivity;
import org.fhw.asta.kasse.client.place.ArticleListPlace;

public interface ArticleListActivityFactory {

	ArticleListActivity create(ArticleListPlace articleListPlace);
	
}
