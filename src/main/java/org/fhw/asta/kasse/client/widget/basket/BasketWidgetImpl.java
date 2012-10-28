package org.fhw.asta.kasse.client.widget.basket;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class BasketWidgetImpl extends Composite implements BasketWidget {

	private static BasketWidgetUiBinder uiBinder = GWT
			.create(BasketWidgetUiBinder.class);

	@UiField(provided = true)
	CellTable<BasketItem> basketTable;

	private Column<BasketItem,String> deleteColumn;
	
	private void initializeCellTable() {
		basketTable = new CellTable<BasketItem>();

		EditTextCell amountCell = new EditTextCell();

		basketTable.addColumn(new Column<BasketItem, String>(amountCell) {

			@Override
			public String getValue(BasketItem object) {
				return Integer.toString(object.getAmount());
			}
		}, "Menge");

		basketTable.addColumn(new TextColumn<BasketItem>() {

			@Override
			public String getValue(BasketItem object) {
				return object.getItemName();
			}

		}, "Name");

		basketTable.addColumn(new PriceTextColumn(), "Preis");

		basketTable.getHeader(2).setHeaderStyleNames(
				basketTable.getHeader(2).getHeaderStyleNames() + " tblleft");

		ButtonCell deleteButton = new DeleteButtonCell();
		deleteColumn = new DeleteColumn(deleteButton);
		
		basketTable.addColumn(deleteColumn);
		basketTable.setColumnWidth(basketTable.getColumn(0),"10%");
		basketTable.setColumnWidth(basketTable.getColumn(1),"60%");
		basketTable.setColumnWidth(basketTable.getColumn(2),"20%");
		basketTable.setColumnWidth(basketTable.getColumn(3),"10%");

	}

	interface BasketWidgetUiBinder extends UiBinder<Widget, BasketWidgetImpl> {
	}

	public BasketWidgetImpl() {
		initializeCellTable();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasData<BasketItem> getBasketTable() {
		return basketTable;
	}
	
	static private class DeleteColumn extends Column<BasketItem,String>
	{
		public DeleteColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(BasketItem object) {
			return "";
		}
	}
	
	static private class PriceTextColumn extends TextColumn<BasketItem> {
		public PriceTextColumn() {
			super();
			this.setHorizontalAlignment(ALIGN_RIGHT);
		}

		@Override
		public String getValue(BasketItem object) {
			return EuroFormatter.format(object.getItemPrice());
		}
	}

	static private class DeleteButtonCell extends ButtonCell {
		/**
		 * Constructor.
		 */
		public DeleteButtonCell() {
			super();

		}

		@Override
		public void render(final Context context, final SafeHtml data,
				final SafeHtmlBuilder sb) {

			sb.appendHtmlConstant("<button type=\"button\" "
					+ "class = \"btn btn-danger btn-mini\""
					+ "tabindex=\"-1\">"
					+ "<i class=\"icon-white icon-remove-circle\"></i>");

			if (data != null) {
				sb.append(data);
			}
			sb.appendHtmlConstant("</button>");
		}

	}

	@Override
	public Column<BasketItem, String> getDeleteColumn() {
		return deleteColumn;
	}

}
