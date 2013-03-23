package org.fhw.asta.kasse.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Container to represent a specific bill OR order stored in DB. Bills and order are stored in the same way, because any
 * order will eventually result in a bill with the same structure. This Difference is determined by it's order state. If
 * the order state is "fetched", the Object represents a bill. A BillOrder alway has an issuer and a receipient. It can
 * be cancelled which should make it invalid for company reports.
 * 
 * @author Julian Wefers
 */
public class BillOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Bill ID, generated by DB */
  private int id;

  /** receipient/customer */
  private int receipientRevision;
  private String recipientLdapName;

  /** issuer/employee who took the order */
  private int issuerRevision;
  private String issuerLdapName;

  /** Date and time of creation */
  private Date datetimeOfCreation;

  /** Flag, whether the customer paid cash */
  private boolean paidCash;

  /** State of the order, if it is fetched, this object is considered a bill */
  private OrderState state;

  /**
   * If an order is cancelled, any service granted has been revoked, thus this Order is invalid for company reports
   */
  private boolean cancelled;

  private int discount;

  /**
   * Creates an Object representation of a specific BillOrder. Values may not ne changed after they have been set here.
   * 
   * @param id
   * @param receipient
   * @param issuer
   * @param creationDate
   * @param paidCash
   * @param state
   * @param cancelled
   * @param articles
   */
  public BillOrder(int id, int receipientRevision, String recipientLdapName, int issuerRevision, String issuerLdapName,
      Date datetimeOfCreation, boolean paidCash, OrderState state, boolean cancelled, int discount) {
    this.id = id;
    this.receipientRevision = receipientRevision;
    this.recipientLdapName = recipientLdapName;
    this.issuerRevision = issuerRevision;
    this.issuerLdapName = issuerLdapName;
    this.datetimeOfCreation = datetimeOfCreation;
    this.paidCash = paidCash;
    this.state = state;
    this.discount = discount;
    this.cancelled = cancelled;
  }

  public BillOrder() {
  };

  public int getId() {
    return this.id;
  }

  public int getReceipientRevision() {
    return this.receipientRevision;
  }

  public String getRecipientLdapName() {
    return this.recipientLdapName;
  }

  public int getIssuerRevision() {
    return this.issuerRevision;
  }

  public String getIssuerLdapName() {
    return this.issuerLdapName;
  }

  public Date getDatetimeOfCreation() {
    return this.datetimeOfCreation;
  }

  public boolean isPaidCash() {
    return this.paidCash;
  }

  public OrderState getState() {
    return this.state;
  }

  public int getDiscount() {
    return this.discount;
  }

  public boolean isCancelled() {
    return this.cancelled;
  }
}
