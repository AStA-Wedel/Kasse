package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.BillOrder;
import org.fhw.asta.kasse.shared.model.OrderState;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.google.common.base.Optional;
import com.google.inject.Singleton;

@Singleton
public class BillOrderDao extends GenericDao {

  private final BillOrderRowMapper billOrderMapper = new BillOrderRowMapper();
  private final BasketItemMapper basketItemMapper = new BasketItemMapper();

  private class BillOrderRowMapper implements RowMapper<BillOrder> {
    @Override
    public BillOrder mapRow(final ResultSet arg0, final int arg1) throws SQLException {
      return new BillOrder(arg0.getInt(1), arg0.getInt(2), arg0.getString(3), arg0.getInt(4), arg0.getString(5),
          arg0.getDate(6), arg0.getBoolean(7), OrderState.valueOf(arg0.getString(8)), arg0.getBoolean(9),
          arg0.getInt(10));
    }
  }

  private class BasketItemMapper implements RowMapper<BasketItem> {
    @Override
    public BasketItem mapRow(ResultSet arg0, int arg1) throws SQLException {
      // TODO Auto-generated method stub
      return new BasketItem(arg0.getString(1), new EuroAmount(arg0.getInt(2)), arg0.getInt(3), arg0.getInt(4),
          arg0.getInt(5));
    }
  }

  public List<BillOrder> getAllBillOrders() {
    return this.template.query("SELECT * FROM bill_order", this.billOrderMapper);
  }

  public Optional<BillOrder> getBillOrder(int id) {
    // TODO: loggen, falls wider erwarten mehr als ein ergebnis zurückkommt
    return this.queryForObject("SELECT * FROM bill_order WHERE bill_id = ?", new Object[]{id}, this.billOrderMapper);
  }

  public List<BasketItem> getBillOrderArticles(int id) {
    return this.template.query("SELECT a.name, a.price, a.article_id, bca.count, "
        + "bca.discount FROM bill_order bo INNER JOIN bill_contains_article "
        + "bca INNER JOIN article a WHERE bo.bill_id = ?", new Object[]{id}, this.basketItemMapper);
  }

  public void saveBillOrder(List<BasketItem> items, int discount, String recipientLdapName, String isserLdapName) {
    final KeyHolder keyholder = new GeneratedKeyHolder();
    final String insertBillOrder = "INSERT INTO bill_order"
        + "(receipient_revision, receipient_ldap_name, issuer_revision, issuer_ldap_name, paid_cash, state, discount) "
        + "VALUES(" + "SELECT MAX(revision) FROM person WHERE ldap_name = RECEIPIENT_NAME," + "RECEIPIENT_NAME,"
        + "SELECT MAX(revision) FROM person WHERE ldap_name = ISSUER_NAME," + "ISSUER_NAME," + "PAID_CASH," + "STATE,"
        + "DISCOUNT);";
    this.template.update(insertBillOrder, new Object[]{}, keyholder);

    for (final BasketItem bi : items) {
      new StringBuilder().append("INSERT INTO BILL_CONTAINS_ARTICLE VALUES(" + "SELECT MAX(bill_id) FROM bill_order,"
          + "ARTICLE_ID," + "SELECT MAX(article_revision) FROM article WHERE article_id = ARTICLE_ID," + "COUNT,"
          + "FALSE," + "DISCOUNT);");
    }

  }
}
