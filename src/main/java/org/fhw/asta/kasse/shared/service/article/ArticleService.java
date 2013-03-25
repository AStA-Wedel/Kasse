package org.fhw.asta.kasse.shared.service.article;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.model.Category;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("article")
public interface ArticleService extends RemoteService {

	List<Article> getArticles();

	List<Article> getArticlesByCategory(String id);
	
	List<Article> getArticleComponents(Article article); 
	
	Optional<Article> getArticleById(String id);
	
	List<Category> getCategories();
	
}
