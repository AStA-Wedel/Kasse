package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.Article;
import org.springframework.jdbc.core.RowMapper;

public class ArticleDao extends GenericDao
{
  private final String getAllArticlesQueryString = "SELECT article_id,"
      + "article_revision, article_name, price, tax_category_name,"
      + "tax_revision, enabled FROM article;";

  private class ArticleRowMapper implements RowMapper<Article>
  {
    @Override
    public Article mapRow(final ResultSet arg0, final int arg1)
        throws SQLException
    {
      return new Article(arg0.getInt(1), arg0.getInt(2), arg0.getString(3),
          new EuroAmount(arg0.getInt(4)), arg0.getString(5), arg0.getInt(6),
          arg0.getBoolean(7));
    }
  }

  public List<Article> getAllArticles()
  {
    return this.template.query(this.getAllArticlesQueryString,
        new ArticleRowMapper());
  }
}
