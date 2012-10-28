package org.fhw.asta.kasse.shared.basket;

import java.io.Serializable;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class BasketItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String itemName;
	private EuroAmount itemPrice;
	private int articleId;
	private int amount;
	public BasketItem()
	{
		this("", new EuroAmount(), 0, 0);
	}
	
	public BasketItem(String itemName, EuroAmount itemPrice, int articleId, int amount)
	{
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.articleId = articleId;
		this.amount = amount;
	}
	
	public int getArticleId()
	{
		return articleId;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public EuroAmount getItemPrice()
	{
		return itemPrice;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
}
