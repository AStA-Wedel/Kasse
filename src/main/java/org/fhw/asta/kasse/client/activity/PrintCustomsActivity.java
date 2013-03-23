package org.fhw.asta.kasse.client.activity;


import java.util.List;

import org.fhw.asta.kasse.client.common.EuroFormatter;
import org.fhw.asta.kasse.client.place.PrintCustomsPlace;
import org.fhw.asta.kasse.client.widget.HasTopbar;
import org.fhw.asta.kasse.client.widget.print.PrintWidget;
import org.fhw.asta.kasse.shared.basket.BasketItem;
import org.fhw.asta.kasse.shared.common.EuroAmount;
import org.fhw.asta.kasse.shared.model.BillOrder;
import org.fhw.asta.kasse.shared.model.LetterHead;
import org.fhw.asta.kasse.shared.service.billorder.BillOrderServiceAsync;
import org.fhw.asta.kasse.shared.service.letterhead.LetterHeadServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class PrintCustomsActivity extends AbstractActivity {

	@Inject
	private PrintWidget printWidget;

	@Inject
	private BillOrderServiceAsync billOrderService;

	@Inject
	private LetterHeadServiceAsync letterHeadService;

	@Inject
	private HasTopbar topBarContainer;

	private PrintCustomsPlace printCustomsPlace;

	@Inject
	public PrintCustomsActivity(@Assisted PrintCustomsPlace printCustomsPlace) {
		this.printCustomsPlace = printCustomsPlace;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(printWidget);
		printWidget.clear();
		topBarContainer.setTopbar(null);

		letterHeadService.getLetterHead(new getHeadCallback());

	}

	private class getHeadCallback implements AsyncCallback<LetterHead> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(LetterHead result) {
			printLetterHead(result);

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

	}

	private void printLetterHead(LetterHead letterHead) {
		printWidget.addHtml("<div class='headline'>" + letterHead.getName()
				+ " - " + letterHead.getStreet() + letterHead.getStreetnumber()
				+ " - " + letterHead.getZipcode() + " " + letterHead.getTown()
				+ "</div>");
	}

	private void printBILLORDER(int id) {
		billOrderService.getBillOrder(id, new AsyncCallback<BillOrder>() {

			@Override
			public void onSuccess(BillOrder result) {
				printBillOrder(result);
				billOrderService.getBillOrderArticles(result.getId(),
						new AsyncCallback<List<BasketItem>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(List<BasketItem> result) {
								printArticles(result);

							}
						});

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

	private int masterDiscount;
	
	private void printBillOrder(BillOrder billOrder) {
		printWidget.addHtml("<br /><br />");
		printWidget.addHtml("<div class='recipient'><table><tr>"
				+ "<td><strong>Name: </strong></td>"
				+ "<td>"+billOrder.getReceipient().getFullName()+"</td>"
				+ "</tr><tr><td><strong>Matrikel-Nr.: </strong></td>"
				+ "<td>"+billOrder.getReceipient().getMatrNo()+"</td></tr></table>"
				+ "<div>");
		printWidget.addHtml("<div class='billdata'><table class='billtbl'><tr>"
				+"<td><strong>Rechnungs-Nr.: </strong></td>"
				+ "<td class='billdata-right'>"+billOrder.getId()+"</td>"
				+ "</tr><tr><td><strong>Datum: </strong></td>"
				+ "<td class='billdata-right'>"+DateTimeFormat.getFormat("dd.MM.yyyy").format(billOrder.getCreationDate())+"</td></tr>"
				+ "<tr><td><strong>Bediener: </strong></td>"
				+ "<td class='billdata-right'>"+billOrder.getIssuer().getFullName()+"</td></tr></table>"
				+ "<div>");
		printWidget.addHtml("<br/><br/><br/><br/><h2><strong>Rechnung</strong></h2>");
		masterDiscount = billOrder.getDiscount();
	}

	private void printArticles(List<BasketItem> articles) {
		StringBuilder strb = new StringBuilder();
		int sum = 0;
		strb.append("<br/>");
		strb.append("<table class='regtb'><tr class='headline'><td>Menge</td><td class='desc'>Beschreibung</td><td>Rabatt (&#037;)</td><td>E-Preis</td><td>G-Preis</td><td>Abgehohlt</td></tr>");
		for (BasketItem art : articles) {
			EuroAmount euroAmount = new EuroAmount((int)Math.round((art.getItemPrice().getCentAmount()*art.getAmount())*(100.0-art.getDiscount())));
			sum += euroAmount.getCentAmount();
			strb.append("<tr class='unbreakable'><td>"
								+art.getAmount()
								+"</td><td class='desc'>"
								+art.getItemName()
								+"</td><td>"
								+art.getDiscount()
								+"</td><td>"
								+EuroFormatter.format(art.getItemPrice())
								+"</td><td>"
								+EuroFormatter.format(euroAmount)
								+"</td><td><div class='abghe'>"
								+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</div>"
								+"</td></tr>");
		}
		strb.append("<tr class='headline'><td></td><td class='desc'></td><td></td><td></td><td></td><td></td></tr>");
		strb.append("<tr><td></td><td class='desc'></td><td></td><td></td><td><strong>Gesamt:</strong></td><td>"+EuroFormatter.format(new EuroAmount(sum))+"</td></tr>");
		strb.append("<tr><td></td><td class='desc'></td><td></td><td></td><td><strong>Rabatt (&#037;):</strong></td><td>"+masterDiscount+"</td></tr>");
		strb.append("<tr><td></td><td class='desc'></td><td></td><td></td><td><strong>Endsumme:</strong></td><td>"+EuroFormatter.format(new EuroAmount((int)Math.round(sum*(100.0-masterDiscount))))+"</td></tr>");
		strb.append("</table>");
		printWidget.addHtml(strb.toString());
		printWidget.addHtml("<br /><br /><br />");
		printWidget.addHtml("<strong><center>Diese Rechnung ist laut &sect;19 UStG Umsatzsteuerbefreit</center></strong>");
	}

	private void printRECEIPT(int id) {

	}

	private void printTAX(int year) {

	}

	private void printUSERLIST() {

	}
}
