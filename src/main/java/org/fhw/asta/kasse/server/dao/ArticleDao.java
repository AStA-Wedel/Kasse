package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.Article;
import org.fhw.asta.kasse.shared.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.google.common.base.Optional;

public class ArticleDao extends GenericDao {

  private CreateNewArticleProcedure createNewArticleProcedure = new CreateNewArticleProcedure(this.template);

  private static class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(final ResultSet arg0, final int arg1) throws SQLException {
      return new Article(arg0.getInt(1), arg0.getInt(2), arg0.getString(3), arg0.getString(4), EuroAmount.create(arg0
          .getInt(5)), arg0.getString(6), arg0.getInt(7), arg0.getBoolean(8));
    }
  }

  private static class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(final ResultSet arg0, final int arg1) throws SQLException {
      return new Category(arg0.getInt(1), arg0.getString(2));
    }
  }

  private static class CreateNewArticleProcedure extends StoredProcedure {
    public CreateNewArticleProcedure(final JdbcTemplate template) {
      super(template, "CREATE_BILL_ORDER");
      this.setFunction(false);
      this.declareParameter(new SqlParameter("i_name", Types.VARCHAR));
      this.declareParameter(new SqlParameter("i_description", Types.LONGVARCHAR));
      this.declareParameter(new SqlParameter("i_price", Types.INTEGER));
      this.declareParameter(new SqlParameter("i_tax_category_name", Types.CHAR));
      this.declareParameter(new SqlParameter("i_enabled", Types.BOOLEAN));
      this.compile();
    }

    public void createNewArticle(final String name, final String description, final int price,
        final String taxCategoryName, final boolean enabled) {
      this.execute(name, description, price, taxCategoryName, enabled);
    }
  }

  public List<Article> getAllArticles() {

    return this.template.query("SELECT article_id," + "article_revision, name, description, price, tax_category_name,"
        + "tax_revision, enabled FROM article WHERE enabled = true;", new ArticleRowMapper());
  }

  public List<Article> getArticlesByCategory(final String id) {
    return this.template
        .query(
            "SELECT article.article_id,article.article_revision,"
                + "name, description, price, tax_category_name,"
                + "tax_revision, enabled FROM article JOIN category_mapping"
                + " ON article.article_id = category_mapping.article_id AND article.article_revision = category_mapping.article_revision"
                + " WHERE category_id = ? AND enabled = true;", new Object[]{id}, new ArticleRowMapper());
  }

  public List<Article> getArticleComponents(final Article article) {
    return this.template.query("SELECT a.article_id, a.article_revision, "
        + "a.name, a.description, a.price, a.tax_category_name, a.tax_revision, a.enabled "
        + "FROM packet_part pp, article a WHERE pp.packet_id = ? " + "AND pp.packet_revision = ? "
        + "AND pp.article_id = a.article_id " + "AND pp.article_revision = a.article_revision",
        new Object[]{article.getId(), article.getRevision()}, new ArticleRowMapper());
  }

  public Optional<Article> getArticleById(final String id) {
    return this.queryForObject("SELECT article_id, article_revision, name, description,"
        + "price, tax_category_name, tax_revision," + "enabled FROM article WHERE article_id = ? AND enabled = true;",
        new Object[]{id}, new ArticleRowMapper());

  }

  public List<Category> getAllCategories() {
    return this.template.query("SELECT category_id, category_name FROM category;", new CategoryRowMapper());
  }

  public void createArticle(final Article article) {
    this.createNewArticleProcedure.createNewArticle(article.getName(), article.getDescription(), article.getPrice()
        .getCentAmount(), article.getTaxCategoryName(), article.isEnabled());
  }
}
