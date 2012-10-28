package org.fhw.asta.kasse.client.widget.basket;

import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class BasketWidgetImpl extends Composite implements BasketWidget {

	private static BasketWidgetUiBinder uiBinder = GWT
			.create(BasketWidgetUiBinder.class);

	@UiField(provided=true) CellTable<BasketItem> basketTable;
	
	private void initializeCellTable()
	{
		basketTable = new CellTable<BasketItem>();
		
		basketTable.addColumn(new TextColumn<BasketItem>() {

			@Override
			public String getValue(BasketItem object) {
				return Integer.toString(object.getArticleId());
			}
		
		},"ID");
		
		basketTable.addColumn(new TextColumn<BasketItem>() {

			@Override
			public String getValue(BasketItem object) {
				return object.getItemName();
			}

		},"Name");
		
		basketTable.addColumn(new TextColumn<BasketItem>() {

			@Override
			public String getValue(BasketItem object) {
				return object.getItemPrice().toString();
			}
		},"Preis");
				
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

	
}