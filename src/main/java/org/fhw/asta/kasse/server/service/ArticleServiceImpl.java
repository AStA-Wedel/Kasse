package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.fhw.asta.kasse.server.dao.ArticleDao;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.model.Category;
import org.fhw.asta.kasse.shared.service.article.ArticleService;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArticleServiceImpl implements ArticleService {

	@Inject
	private ArticleDao dao;

	@Override
	public List<Article> getArticles() {
		return this.dao.getAllArticles();
	}

	@Override
	public List<Article> getArticleComponents(Article article) {
		return this.dao.getArticleComponents(article);
	}

	@Override
	public Optional<Article> getArticleById(String id) {
		return this.dao.getArticleById(id);
	}

	@Override
	public List<Category> getCategories() {
		return this.dao.getAllCategories();
	}

	@Override
	public List<Article> getArticlesByCategory(String id) {
		return this.dao.getArticlesByCategory(id);
	}
	
}
