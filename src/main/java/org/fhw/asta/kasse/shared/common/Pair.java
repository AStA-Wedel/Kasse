package org.fhw.asta.kasse.shared.common;

public class Pair<T, E> {

	private final T fst;
	
	private final E snd;
	
	public static <F, G> Pair<F, G> mk(F fst, G snd) {
		return new Pair<F, G>(fst, snd);
	}
	
	private Pair(T fst, E snd) {
		this.fst = fst;
		this.snd = snd;
		
	}
	
	public T fst() {
		return fst;
	}
	
	public E snd() {
		return snd;
	}

}
