package org.fhw.asta.kasse.shared.common;
public class Triple<D, E, F> {

  private final D fst;
  private final E snd;
  private final F trd;

  public static <G, H, I> Triple<G, H, I> mk(G fst, H snd, I trd) {
    return new Triple<G, H, I>(fst, snd, trd);
  }

  private Triple(D fst, E snd, F trd) {
    this.fst = fst;
    this.snd = snd;
    this.trd = trd;
  }

  public D fst() {
    return this.fst;
  }

  public E snd() {
    return this.snd;
  }

  public F trd() {
    return this.trd;
  }

}
