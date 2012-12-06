package org.fhw.asta.kasse.shared.service.article;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArticleServiceAsync {

	void getArticles(AsyncCallback<List<Article>> callback);

	void getArticleComponents(Article article,
			AsyncCallback<List<Article>> callback);

	void getArticleById(String id, AsyncCallback<Article> callback);

}
