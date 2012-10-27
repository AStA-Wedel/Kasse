package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int receiptId;

  private Date creationDatetime;

  private int centAmount;

  private boolean payedCash;

  private String reason;

  private String issuerLdapName;

  private int issuerRevision;

  private int accountCategoryId;

  private boolean cancelled;

  public Receipt(final Date creationDatetime, final int centAmount,
      final boolean payedCash, final String reason,
      final String issuerLdapName, final int issuerRevision,
      final int accountCategoryId, final boolean cancelled)
  {
    this.creationDatetime = creationDatetime;
    this.centAmount = centAmount;
    this.payedCash = payedCash;
    this.reason = reason;
    this.issuerLdapName = issuerLdapName;
    this.issuerRevision = issuerRevision;
    this.accountCategoryId = accountCategoryId;
    this.cancelled = cancelled;
  }

  public Receipt()
  {
  }

  public int getReceiptId()
  {
    return this.receiptId;
  }

  public Date getCreationDatetime()
  {
    return this.creationDatetime;
  }

  public int getCentAmount()
  {
    return this.centAmount;
  }

  public boolean getPayedCash()
  {
    return this.payedCash;
  }

  public String getReason()
  {
    return this.reason;
  }

  public String getIssuerLdapName()
  {
    return this.issuerLdapName;
  }

  public int getIssuerRevision()
  {
    return this.issuerRevision;
  }

  public int getCategory()
  {
    return this.accountCategoryId;
  }

  public boolean isCancelled()
  {
    return this.cancelled;
  }

  public void setCancelled(final boolean cancelled)
  {
    this.cancelled = cancelled;
  }
}
