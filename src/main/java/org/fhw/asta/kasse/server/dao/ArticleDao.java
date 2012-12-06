package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.Article;
import org.springframework.jdbc.core.RowMapper;

public class ArticleDao extends GenericDao {
	private final String getAllArticlesQueryString = "SELECT article_id,"
			+ "article_revision, name, description, price, tax_category_name,"
			+ "tax_revision, enabled FROM article;";

	private class ArticleRowMapper implements RowMapper<Article> {
		@Override
		public Article mapRow(final ResultSet arg0, final int arg1)
				throws SQLException {
			return new Article(arg0.getInt(1), arg0.getInt(2),
					arg0.getString(3), arg0.getString(4), new EuroAmount(
							arg0.getInt(5)), arg0.getString(6), arg0.getInt(7),
					arg0.getBoolean(8));
		}
	}

	public List<Article> getAllArticles() {
		return this.template.query(this.getAllArticlesQueryString,
				new ArticleRowMapper());
	}

	public List<Article> getArticleComponents(final Article article) {
		return this.template
				.query("SELECT a.article_id, a.article_revision, "
						+ "a.name, a.description, a.price, a.tax_category_name, a.tax_revision, a.enabled "
						+ "FROM packet_part pp, article a WHERE pp.packet_id = ? "
						+ "AND pp.packet_revision = ? "
						+ "AND pp.article_id = a.article_id "
						+ "AND pp.article_revision = a.article_revision",
						new Object[] { article.getId(), article.getRevision() },
						new ArticleRowMapper());
	}

	public Article getArticleById(String id)
  {
	return this.template.query("SELECT article_id, article_revision, name, description," +
								"price, tax_category_name, tax_revision," +
								"enabled FROM article WHERE article_id = '" + id + "';", new ArticleRowMapper()).get(0);
								
  }
}
