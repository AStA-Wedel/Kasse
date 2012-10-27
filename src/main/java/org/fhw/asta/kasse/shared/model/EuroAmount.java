package org.fhw.asta.kasse.shared.model;

public final class EuroAmount
{
  private final int cents;

  public EuroAmount(final int cents)
  {
    this.cents = cents;
  }

  public int getCentAmount()
  {
    return this.cents;
  }

  public EuroAmount plus(final EuroAmount summand)
  {
    return new EuroAmount(this.cents + summand.cents);
  }

  public EuroAmount minus(final EuroAmount subtrahend)
  {
    return new EuroAmount(this.cents - subtrahend.cents);
  }

  public EuroAmount times(final float factor)
  {
    return new EuroAmount((int) (this.cents * factor));
  }

  public EuroAmount times(final int factor)
  {
    return new EuroAmount(this.cents * factor);
  }

  @Override
  public String toString()
  {
    return Integer.toString(this.cents / 100) + ','
        + Integer.toString(this.cents % 100);
  }
}
