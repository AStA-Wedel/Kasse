package org.fhw.asta.kasse.client.activity;

import org.fhw.asta.kasse.client.place.PrintCustomsPlace;
import org.fhw.asta.kasse.client.widget.print.PrintWidget;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class PrintCustomsActivity extends AbstractActivity {

	@Inject
	PrintWidget printWidget;
	private PrintCustomsPlace printCustomsPlace;
	
	@Inject
	public PrintCustomsActivity(@Assisted PrintCustomsPlace printCustomsPlace) {
		this.printCustomsPlace = printCustomsPlace;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(printWidget);	
		printWidget.clear();
		
		if (printCustomsPlace.getToken().isPresent()) {
			
			switch (printCustomsPlace.getToken().get().getPrintType()) {
				case BILLORDER:
					printBILLORDER(printCustomsPlace.getToken().get().getId());
				break;
				
				case RECEIPT:
					printRECEIPT(printCustomsPlace.getToken().get().getId());
				break;
				
				case TAX:
					printTAX(printCustomsPlace.getToken().get().getId());
				break;
				
				case USERLIST:
					printUSERLIST();
				break;
			}
		}
		
	}
	
	private void printBILLORDER(int id) {
		
	}
	
	private void printRECEIPT(int id) {
		
	}

	private void printTAX(int year) {
		
	}
	
	private void printUSERLIST() {
		
	}
}
