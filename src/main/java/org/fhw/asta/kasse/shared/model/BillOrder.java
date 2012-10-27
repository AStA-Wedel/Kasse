package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BillOrder implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;

  private Person receipient;

  private Person issuer;

  private Date creationDate;

  private boolean paidCash;

  private OrderState state;

  private boolean cancelled;

  private List<Article> articles;

  public BillOrder(final int id, final Person receipient, final Person issuer,
      final Date creationDate, final boolean paidCash, final OrderState state,
      final boolean cancelled, final List<Article> articles)
  {
    this.id = id;
    this.receipient = receipient;
    this.issuer = issuer;
    this.creationDate = creationDate;
    this.paidCash = paidCash;
    this.state = state;
    this.cancelled = cancelled;
    this.articles = articles;
  }

  public int getId()
  {
    return this.id;
  }

  public Person getIssuer()
  {
    return this.issuer;
  }

  public Person getReceipient()
  {
    return this.receipient;
  }

  public Date getCreationDate()
  {
    return this.creationDate;
  }

  public boolean paidCash()
  {
    return this.paidCash;
  }

  public OrderState getState()
  {
    return this.state;
  }

  public boolean isCancelled()
  {
    return this.cancelled;
  }

  public void setCancelled(final boolean cancelled)
  {
    this.cancelled = cancelled;
  }

  public List<Article> getArticles()
  {
    return this.articles;
  }
}
