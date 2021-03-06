package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.BillOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BillOrderDao extends GenericDao {

	private final BillOrderRowMapper billOrderMapper = new BillOrderRowMapper();
	private final BasketItemMapper basketItemMapper = new BasketItemMapper();
	private final CreateBillOrderProcedure createBillOrderProcedure;

	// private final Logger logger = Logger.getLogger(BillOrderDao.class);

	private static class BillOrderRowMapper implements RowMapper<BillOrder> {
		@Override
		public BillOrder mapRow(final ResultSet arg0, final int arg1)
				throws SQLException {
			return new BillOrder(arg0.getInt(1), arg0.getInt(2),
					arg0.getString(3), arg0.getInt(4), arg0.getString(5),
					arg0.getDate(6), arg0.getBoolean(7), arg0.getString(8)
							.charAt(0), arg0.getBoolean(9), arg0.getInt(10));
		}
	}

	private static class BasketItemMapper implements RowMapper<BasketItem> {
		@Override
		public BasketItem mapRow(ResultSet arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			return new BasketItem(arg0.getString(1), EuroAmount.create(arg0.getInt(2)), arg0.getInt(3), arg0.getInt(4),
					arg0.getInt(5));
		}
	}

	private static class CreateBillOrderProcedure extends StoredProcedure {
		public CreateBillOrderProcedure(JdbcTemplate template) {
			super(template, "CREATE_BILL_ORDER");
			this.setFunction(false);
			this.declareParameter(new SqlParameter("i_receipient_ldap_name",
					Types.VARCHAR));
			this.declareParameter(new SqlParameter("i_issuer_ldap_name",
					Types.VARCHAR));
			this.declareParameter(new SqlParameter("i_paid_cash", Types.BOOLEAN));
			this.declareParameter(new SqlParameter("i_discount", Types.TINYINT));
			this.declareParameter(new SqlParameter("i_order_state", Types.CHAR));
			this.declareParameter(new SqlOutParameter("o_bill_number",
					Types.INTEGER));
			this.compile();
		}

		public int createBill(String receipientLdapName, String issuerLdapName,
				boolean paidCash, int discount, char orderState) {
			return (Integer) this.execute(receipientLdapName, issuerLdapName,
					paidCash, discount, orderState).get("o_bill_number");
		}
	}

	@Inject
	public BillOrderDao(JdbcTemplate template) {
		this.template = template;
		createBillOrderProcedure = new CreateBillOrderProcedure(this.template);
	}

	public List<BillOrder> getAllBillOrders() {
		return this.template.query("SELECT * FROM bill_order",
				this.billOrderMapper);
	}

	public Optional<BillOrder> getBillOrder(int id) {
		// TODO: loggen, falls wider erwarten mehr als ein ergebnis zurückkommt
		return this.queryForObject(
				"SELECT * FROM bill_order WHERE bill_id = ?",
				new Object[] { id }, this.billOrderMapper);
	}

	public List<BasketItem> getBillOrderArticles(int id) {
		return this.template
				.query("SELECT a.name, a.price, a.article_id, bca.count, bca.discount "
						+ "FROM bill_contains_article bca INNER JOIN article a USING "
						+ "(article_id, article_revision) WHERE bca.bill_id = ?",
						new Object[] { id }, this.basketItemMapper);
	}

	public int saveBillOrder(List<BasketItem> items, final int discount,
			final String receipientLdapName, final String issuerLdapName,
			final char orderState) {
		final int billNumber = this.createBillOrderProcedure.createBill(
				receipientLdapName, issuerLdapName, true, discount, orderState);

		for (final BasketItem bo : items) {
			this.template
					.update("INSERT INTO bill_contains_article "
							+ "VALUES("
							+ "?,"
							+ "?,"
							+ "(SELECT MAX(article_revision) FROM article WHERE article_id = ?),"
							+ "?," + "FALSE," + "?);", new Object[] {
							billNumber, bo.getArticleId(), bo.getArticleId(),
							bo.getAmount(), bo.getDiscount() });
		}

		return billNumber;
	}
}
