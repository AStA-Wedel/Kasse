package org.fhw.asta.kasse.client.widget.articlelist;

import java.util.List;

import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface ArticleListWidget extends IsWidget{
	HasData<Article> getArticleList();
	Column<Article,String> getToBasketColumn();
	Column<Article,String> getIdColumn();
	Column<Article,String> getNameColumn();
	void showOverlay(Article article, List<Article> bundle);
}
