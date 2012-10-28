package org.fhw.asta.kasse.server.service;

import java.util.List;

import org.fhw.asta.kasse.server.dao.ArticleDao;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.service.article.ArticleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class ArticleServiceEndpoint extends RemoteServiceServlet implements
    ArticleService
{

  private static final long serialVersionUID = 1L;

  @Override
  public List<Article> getArticles()
  {
    return new ArticleDao().getAllArticles();
  }

}
