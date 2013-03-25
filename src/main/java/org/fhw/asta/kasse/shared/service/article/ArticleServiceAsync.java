package org.fhw.asta.kasse.shared.service.article;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.model.Category;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArticleServiceAsync {

	void getArticles(AsyncCallback<List<Article>> callback);

	void getArticleComponents(Article article,
			AsyncCallback<List<Article>> callback);

	void getArticleById(String id, AsyncCallback<Optional<Article>> callback);

	void getCategories(AsyncCallback<List<Category>> callback);

	void getArticlesByCategory(String id, AsyncCallback<List<Article>> callback);

}
