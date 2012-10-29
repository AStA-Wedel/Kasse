package org.fhw.asta.kasse.client.widget.articlelist;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	
	interface ArticleListWidgetImplUiBinder extends
			UiBinder<Widget, ArticleListWidgetImpl> {
	}

	private void initializeCellTable()
	{
		cellTable = new CellTable<Article>();
		ClickableTextCell openOverlayCell = new ClickableTextCell();
		OverlayOpenFieldUpdater openOverlayFieldUpdater = new OverlayOpenFieldUpdater();
		
		IdColumn idColumn = new IdColumn(openOverlayCell);
		NameColumn nameColumn = new NameColumn(openOverlayCell);
		
		idColumn.setFieldUpdater(openOverlayFieldUpdater);
		nameColumn.setFieldUpdater(openOverlayFieldUpdater);
		
		cellTable.addColumn(idColumn,"ID");
		cellTable.addColumn(nameColumn,"Name");
		cellTable.addColumn(new PriceTextColumn(),"Preis");
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
}
