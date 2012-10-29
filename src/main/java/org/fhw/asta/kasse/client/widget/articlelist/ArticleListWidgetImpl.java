package org.fhw.asta.kasse.client.widget.articlelist;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class ArticleListWidgetImpl extends Composite implements ArticleListWidget {

	private static ArticleListWidgetImplUiBinder uiBinder = GWT
			.create(ArticleListWidgetImplUiBinder.class);
	
	@UiField(provided=true) CellTable<Article> cellTable;
	@UiField HTMLPanel articleOverlay;
	@UiField HTML articleOverlayBG;
	@UiField Button closeOverlayButton;
	@UiField Label articleID;
	@UiField Label articleName;
	@UiField Label articlePrice;
	@UiField Label articleTax;
	@UiField HTML articleBundle;
	
	private ToBasketColumn toBasketColumn;
	private NameColumn nameColumn;
	private IdColumn idColumn;
	private PriceTextColumn priceTextColumn;
	
	interface ArticleListWidgetImplUiBinder extends
			UiBinder<Widget, ArticleListWidgetImpl> {
	}

	private void initializeCellTable()
	{
		cellTable = new CellTable<Article>();
		
		ClickableTextCell openOverlayCell = new ClickableTextCell();
		ToBasketButtonCell toBasketCell = new ToBasketButtonCell();
		
		OverlayOpenFieldUpdater openOverlayFieldUpdater = new OverlayOpenFieldUpdater();
		
		idColumn = new IdColumn(openOverlayCell);
		nameColumn = new NameColumn(openOverlayCell);
		toBasketColumn = new ToBasketColumn(toBasketCell);
		priceTextColumn = new PriceTextColumn();
		
		idColumn.setFieldUpdater(openOverlayFieldUpdater);
		nameColumn.setFieldUpdater(openOverlayFieldUpdater);
		
		cellTable.addColumn(idColumn,"ID");
		cellTable.addColumn(nameColumn,"Name");
		cellTable.addColumn(priceTextColumn,"Preis");
		cellTable.addColumn(toBasketColumn);
		
		
		cellTable.setColumnWidth(idColumn,"15%");
		cellTable.setColumnWidth(nameColumn,"60%");
		cellTable.setColumnWidth(priceTextColumn,"20%");
		cellTable.setColumnWidth(toBasketColumn,"5%");
		
		cellTable.getHeader(2).setHeaderStyleNames(cellTable.getHeader(2).getHeaderStyleNames()+" tblleft");
	}
	
	public ArticleListWidgetImpl() {
		initializeCellTable();
		initWidget(uiBinder.createAndBindUi(this));
		articleOverlay.setVisible(false);
		articleOverlayBG.setVisible(false);
		
		closeOverlayButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				articleOverlay.setVisible(false);
				articleOverlayBG.setVisible(false);
			}
		});
	}
	
	@Override
	public HasData<Article> getArticleList() {
		return cellTable;
	}
	
	@Override
	public Column<Article, String> getToBasketColumn() {
		return toBasketColumn;
	}

	public void showOverlay(Article article)
	{
		articleID.setText(Integer.toString(article.getId()));
		articleName.setText(article.getName());
		articlePrice.setText(EuroFormatter.format(article.getPrice()));
		articleTax.setText(article.getTaxCategoryName());
		
		//TODO: Implement Bundleview!
		
		articleOverlay.setVisible(true);
		articleOverlayBG.setVisible(true);
	}
	
	static private class PriceTextColumn extends TextColumn<Article> {
		public PriceTextColumn()
		{
			super();
			this.setHorizontalAlignment(ALIGN_RIGHT);
		}
		
		@Override
		public String getValue(Article object) {
			return EuroFormatter.format(object.getPrice());
		}
	}
	
	static private class ToBasketColumn extends Column<Article, String>{

		public ToBasketColumn(Cell<String> cell) {
			super(cell);
		}

		@Override
		public String getValue(Article object) {
			return "";
		}
		
	}
	
	private class IdColumn extends Column<Article, String>{

		public IdColumn(Cell<String> cell) {
			super(cell);
		}
		
		@Override
		public String getValue(Article object) {
			return Integer.toString(object.getId());
		}
		
	}
	
	private class NameColumn extends Column<Article, String>{

		public NameColumn(Cell<String> cell) {
			super(cell);
		}
		
		@Override
		public String getValue(Article object) {
			return object.getName();
		}
		
	}
	
	private class OverlayOpenFieldUpdater implements FieldUpdater<Article, String>{

		@Override
		public void update(int index, Article object, String value) {
			showOverlay(object);
		}
		
	}
	
	static private class ToBasketButtonCell extends ButtonCell {
		/**
		 * Constructor.
		 */
		public ToBasketButtonCell() {
			super();

		}

		@Override
		public void render(final Context context, final SafeHtml data,
				final SafeHtmlBuilder sb) {

			sb.appendHtmlConstant("<button type=\"button\" "
					+ "class = \"btn btn-success btn-small\""
					+ "tabindex=\"-1\">"
					+ "<i class=\"icon-white icon-shopping-cart\"></i>");

			if (data != null) {
				sb.append(data);
			}
			sb.appendHtmlConstant("</button>");
		}

	}
}
