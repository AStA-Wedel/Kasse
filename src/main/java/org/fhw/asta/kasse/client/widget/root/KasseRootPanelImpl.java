package org.fhw.asta.kasse.client.widget.root;

import org.fhw.asta.kasse.client.widget.topbar.TopBarWidgetContainer;
import org.fhw.asta.kasse.client.widget.topbar.TopbarWidget;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

public class KasseRootPanelImpl extends HTMLPanel implements KasseRootPanel {

	private TopBarWidgetContainer topbarWidget;
	
	private final SimplePanel contentContainer;
		
	public KasseRootPanelImpl() {
		super("");
				
		contentContainer = new SimplePanel();		
	}

	@Inject
	private void afterConstruct(TopBarWidgetContainer topbarWidgetContainer) {
		topbarWidget = topbarWidgetContainer;
		
		this.add(topbarWidgetContainer);
		this.add(contentContainer);
	}
	
	@Override
	public void setWidget(IsWidget w) {
		contentContainer.setWidget(w);				
	}

	@Override
	public void setTopbar(TopbarWidget widget) {
		topbarWidget.setTopbar(widget);
	}

}
