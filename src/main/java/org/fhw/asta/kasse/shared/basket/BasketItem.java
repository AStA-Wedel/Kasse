package org.fhw.asta.kasse.shared.basket;

import java.io.Serializable;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class BasketItem implements Serializable {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + articleId;
		result = prime * result + discount;
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result
				+ ((itemPrice == null) ? 0 : itemPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketItem other = (BasketItem) obj;
		if (amount != other.amount)
			return false;
		if (articleId != other.articleId)
			return false;
		if (discount != other.discount)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
	
	private String itemName;
	private EuroAmount itemPrice;
	private int articleId;
	private int amount;
	private int discount;
	
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
		this.discount = 0;
	}
	
	public BasketItem(String itemName, EuroAmount itemPrice, int articleId, int amount, int discount)
	{
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.articleId = articleId;
		this.amount = amount;
		this.discount = discount;
	}
	
	public int getArticleId()
	{
		return articleId;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public int getDiscount()
	{
		return discount;
	}
	
	public EuroAmount getItemPrice()
	{
		return itemPrice;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public EuroAmount getTotal()  {
		return getItemPrice().times(amount);
	}
	
}
