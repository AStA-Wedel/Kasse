package org.fhw.asta.kasse.client.widget.topbar.modules.searchbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchBoxWidgetImpl extends Composite implements SearchBoxWidget {

	private static SearchBoxWidgetImplUiBinder uiBinder = GWT
			.create(SearchBoxWidgetImplUiBinder.class);

	interface SearchBoxWidgetImplUiBinder extends
			UiBinder<Widget, SearchBoxWidgetImpl> {
	}

	public SearchBoxWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		suggestBox.getElement().setAttribute("placeholder", "Suche");
	}
	
	@UiField
	SuggestBox suggestBox;

	@Override
	public HasText getSearchBox() {
		return suggestBox;
	}
	
	
}
