package org.fhw.asta.kasse.client.widget.main;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainWidget extends IsWidget, AcceptsOneWidget {

	void setSidebarWidget(IsWidget widget);
	
	void setBasketWidget(IsWidget widget);
	
	void setMainWidget(IsWidget widget);
	
}
