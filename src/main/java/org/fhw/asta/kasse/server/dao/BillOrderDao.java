package org.fhw.asta.kasse.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fhw.asta.kasse.shared.model.BillOrder;
import org.fhw.asta.kasse.shared.model.OrderState;
import org.springframework.jdbc.core.RowMapper;

public class BillOrderDao extends GenericDao {

  private class BillOrderRowMapper implements RowMapper<BillOrder> {
    @Override
    public BillOrder mapRow(final ResultSet arg0, final int arg1) throws SQLException {
      return new BillOrder(arg0.getInt(1), arg0.getInt(2), arg0.getString(3), arg0.getInt(4), arg0.getString(5),
          arg0.getDate(6), arg0.getBoolean(7), OrderState.valueOf(arg0.getString(8)), arg0.getBoolean(9),
          arg0.getInt(10));
    }
  }

}
