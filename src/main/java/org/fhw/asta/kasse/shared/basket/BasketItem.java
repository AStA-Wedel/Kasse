package org.fhw.asta.kasse.shared.basket;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class BasketItem {
	private String itemName;
	private EuroAmount itemPrice;
	private int articleId;
	
	public BasketItem()
	{
		this.itemName = "";
		this.itemPrice = new EuroAmount();
		this.articleId = 0;
	}
	
	public BasketItem(String itemName, EuroAmount itemPrice, int articleId)
	{
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.articleId = articleId;
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
}
