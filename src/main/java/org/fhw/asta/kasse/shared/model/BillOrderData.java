package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;
import java.util.List;

import org.fhw.asta.kasse.shared.basket.BasketItem;

public class BillOrderData implements Serializable {

	private static final long serialVersionUID = 1L;

	private BillOrder billOrder;
	
	private List<BasketItem> basket;
	
	private Person issuer;
	
	private Person receipient;

	public BillOrder getBillOrder() {
		return billOrder;
	}

	public void setBillOrder(BillOrder billOrder) {
		this.billOrder = billOrder;
	}

	public List<BasketItem> getBasket() {
		return basket;
	}

	public void setBasket(List<BasketItem> basket) {
		this.basket = basket;
	}

	public Person getIssuer() {
		return issuer;
	}

	public void setIssuer(Person issuer) {
		this.issuer = issuer;
	}

	public Person getReceipient() {
		return receipient;
	}

	public void setReceipient(Person receipient) {
		this.receipient = receipient;
	}
		
}
