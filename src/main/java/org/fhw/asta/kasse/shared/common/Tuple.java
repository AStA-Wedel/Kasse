package org.fhw.asta.kasse.shared.common;

public class Tuple<T, E> {

  private final T fst;

  private final E snd;

  public static <F, G> Tuple<F, G> mk(F fst, G snd) {
    return new Tuple<F, G>(fst, snd);
  }

  private Tuple(T fst, E snd) {
    this.fst = fst;
    this.snd = snd;

  }

  public T fst() {
    return this.fst;
  }

  public E snd() {
    return this.snd;
  }

}
