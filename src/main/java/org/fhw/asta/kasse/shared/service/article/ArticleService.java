package org.fhw.asta.kasse.shared.service.article;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("article")
public interface ArticleService extends RemoteService {

	List<Article> getArticles();

	List<Article> getArticleComponents(Article article); 
	
}
