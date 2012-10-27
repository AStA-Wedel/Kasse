package org.fhw.asta.kasse.shared.basket;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class BasketItem {
	private final String itemName;
	private final EuroAmount itemPrice;
	private final int articleId;
	
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
