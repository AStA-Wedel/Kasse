package org.fhw.asta.kasse.client.widget.articlelist;

import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class ArticleListWidgetImpl extends Composite implements ArticleListWidget {

	private static ArticleListWidgetImplUiBinder uiBinder = GWT
			.create(ArticleListWidgetImplUiBinder.class);
	
	@UiField(provided=true) CellTable<Article> cellTable;

	interface ArticleListWidgetImplUiBinder extends
			UiBinder<Widget, ArticleListWidgetImpl> {
	}

	private void initializeCellTable()
	{
		cellTable = new CellTable<Article>();
		
		cellTable.addColumn(new TextColumn<Article>() {

			@Override
			public String getValue(Article object) {
				return Integer.toString(object.getId());
			}
		},"ID");
		
		cellTable.addColumn(new TextColumn<Article>() {

			@Override
			public String getValue(Article object) {
				return object.getName();
			}
		},"Name");
		
		cellTable.addColumn(new PriceTextColumn(),"Preis");
			
	}
	
	public ArticleListWidgetImpl() {
		initializeCellTable();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasData<Article> getArticleList() {
		return cellTable;
	}

	
	static private class PriceTextColumn extends TextColumn<Article> {
		public PriceTextColumn()
		{
			super();
			this.setHorizontalAlignment(ALIGN_RIGHT);
		}
		
		@Override
		public String getValue(Article object) {
			return object.getPriceString();
		}
	}
	
}
