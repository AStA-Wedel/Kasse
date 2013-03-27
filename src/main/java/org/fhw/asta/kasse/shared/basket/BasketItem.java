package org.fhw.asta.kasse.shared.basket;

import java.io.Serializable;

import org.fhw.asta.kasse.shared.common.EuroAmount;

public class BasketItem implements Serializable {

  private static final long serialVersionUID = 1L;

  private String itemName;
  private EuroAmount itemPrice;
  private int articleId;
  private int amount;
  private int discount;

  public BasketItem() {
    this("", new EuroAmount(), 0, 0);
  }

  public BasketItem(String itemName, EuroAmount itemPrice, int articleId, int amount) {
    this(itemName, itemPrice, articleId, amount, 0);
  }

  public BasketItem(String itemName, EuroAmount itemPrice, int articleId, int amount, int discount) {
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.articleId = articleId;
    this.amount = amount;
    this.discount = discount;
  }

  public int getArticleId() {
    return this.articleId;
  }

  public String getItemName() {
    return this.itemName;
  }

  public int getDiscount() {
    return this.discount;
  }

  public EuroAmount getItemPrice() {
    return this.itemPrice;
  }

  public int getAmount() {
    return this.amount;
  }

  public EuroAmount total() {
    return this.getItemPrice().times(this.amount);
  }
  
  public EuroAmount totalWithDiscount() {
	  return total().withDiscount(discount);
  }
  
  public BasketItem updateAmount(int amount) {
	  return new BasketItem(itemName, itemPrice, articleId, amount, discount);
  }
  
  public BasketItem updateDiscount(int discount) {
	  return new BasketItem(itemName, itemPrice, articleId, amount, discount);
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.amount;
    result = prime * result + this.articleId;
    result = prime * result + this.discount;
    result = prime * result + ((this.itemName == null) ? 0 : this.itemName.hashCode());
    result = prime * result + ((this.itemPrice == null) ? 0 : this.itemPrice.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (this.getClass() != obj.getClass())
      return false;
    final BasketItem other = (BasketItem) obj;
    if (this.amount != other.amount)
      return false;
    if (this.articleId != other.articleId)
      return false;
    if (this.discount != other.discount)
      return false;
    if (this.itemName == null) {
      if (other.itemName != null)
        return false;
    } else if (!this.itemName.equals(other.itemName))
      return false;
    if (this.itemPrice == null) {
      if (other.itemPrice != null)
        return false;
    } else if (!this.itemPrice.equals(other.itemPrice))
      return false;
    return true;
  }

}
