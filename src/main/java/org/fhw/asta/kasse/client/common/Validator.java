package org.fhw.asta.kasse.client.common;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class Validator<T> {

	private List<Predicate<T>> predicates;
	
	static public <E> Validator<E> start() {
		return new Validator<E>();
	}
	
	public Validator() {
		this.predicates = Lists.newLinkedList();
	}
	
	public Validator<T> add(Predicate<T> predicate) {
		this.predicates.add(predicate);
		return this;
	}
	
	public boolean validate(T arg) {
	
		Iterator<Predicate<T>> predicateIterator = predicates.iterator();

		boolean ret = true;
		
		while (ret && predicateIterator.hasNext()) {
			ret &= predicateIterator.next().apply(arg);
		}
		
		return ret;
	}

	/*

		boolean valid = Validator.start().add(new Predicate<Article> { 
			
			public boolean apply(Article a) {
				return a.getId > 0;
			}
		
		}).add(new Predicate<Article> {
			
			public boolean apply(Article a) {
				return !Strings.isNullOrEmpty(a.getName());
			}
			
		}).validate();
		
	}*/
	
	
}
