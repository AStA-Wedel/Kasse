package org.fhw.asta.kasse.client.widget.articlelist;

import java.util.Iterator;
import java.util.List;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
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
	@UiField Button closeOverlayButton2;
	@UiField Button overlayToBasketButton;
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
		
		idColumn = new IdColumn(openOverlayCell);
		nameColumn = new NameColumn(openOverlayCell);
		toBasketColumn = new ToBasketColumn(toBasketCell);
		priceTextColumn = new PriceTextColumn();
		
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
		closeOverlay();

		
		ClickHandler closeOverlayHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				closeOverlay();
			}
		};
		
		closeOverlayButton.addClickHandler(closeOverlayHandler);
		closeOverlayButton2.addClickHandler(closeOverlayHandler);
		articleOverlayBG.addClickHandler(closeOverlayHandler);
	}
	
	@Override
	public HasData<Article> getArticleList() {
		return cellTable;
	}
	
	@Override
	public Column<Article, String> getToBasketColumn() {
		return toBasketColumn;
	}
	
	@Override
	public Column<Article, String> getIdColumn() {
		return idColumn;
	}

	@Override
	public Column<Article, String> getNameColumn() {
		return nameColumn;
	}
	
	@Override
	public Button getOverlayToBasketButton() {
		return overlayToBasketButton;
	}

	public void showOverlay(Article article, List<Article> bundle)
	{
		articleID.setText(Integer.toString(article.getId()));
		articleName.setText(article.getName());
		articlePrice.setText(EuroFormatter.format(article.getPrice()));
		articleTax.setText(article.getTaxCategoryName());
		articleBundle.setHTML("");
		
		Iterator<Article> iterator = bundle.iterator();
		while(iterator.hasNext())
		{
			articleBundle.setHTML(articleBundle.getHTML() + "<br />" + iterator.next().getName());
		}
		
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

	@Override
	public void closeOverlay() {
		articleOverlay.setVisible(false);
		articleOverlayBG.setVisible(false);		
	}


	
}
