package org.fhw.asta.kasse.shared.service.article;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArticleServiceAsync {

	void getArticles(AsyncCallback<List<Article>> callback);

}