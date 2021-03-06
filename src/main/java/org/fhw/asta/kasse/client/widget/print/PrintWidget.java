package org.fhw.asta.kasse.client.widget.print;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;

public interface PrintWidget extends IsWidget {
	
	public void clear();

	public void addHtml(String html);

	public void addHtml(SafeHtml safeHtml);
	
}
