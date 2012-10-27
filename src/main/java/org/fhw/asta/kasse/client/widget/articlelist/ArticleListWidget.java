package org.fhw.asta.kasse.client.widget.articlelist;

import org.fhw.asta.kasse.shared.model.Article;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface ArticleListWidget extends IsWidget{
	HasData<Article> getArticleList();
}
