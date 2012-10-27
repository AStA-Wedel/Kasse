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
import com.google.gwt.view.client.ProvidesKey;

public class ArticleListWidgetImpl extends Composite implements ArticleListWidget {

	private static ArticleListWidgetImplUiBinder uiBinder = GWT
			.create(ArticleListWidgetImplUiBinder.class);
	@UiField(provided=true) CellTable<Article> cellTable = new CellTable<Article>();

	interface ArticleListWidgetImplUiBinder extends
			UiBinder<Widget, ArticleListWidgetImpl> {
	}

	
	private void initializeCellTable()
	{
		cellTable = new CellTable<Article>(new ProvidesKey<Article>() {

			@Override
			public Object getKey(Article arg0) {
				return arg0.getId();
			}
			
		});
		
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
		
		cellTable.addColumn(new TextColumn<Article>() {

			@Override
			public String getValue(Article object) {
				return object.getPriceString();
			}
		},"Preis");
		
				
	}
	
	public ArticleListWidgetImpl() {
		initializeCellTable();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasData<Article> getArticleList() {
		return cellTable;
	}

}
