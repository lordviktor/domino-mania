package br.uff.networks.domino_mania.model;

import java.util.Arrays;
import java.util.Iterator;

class CyclicIterator<T> implements Iterator<T> {

	private Iterator<? extends T> iterator;
	private Iterable<? extends T> collection;

	CyclicIterator(T[] iterable) {
		this(Arrays.asList(iterable));
	}

	CyclicIterator(Iterable<? extends T> collection) {
		this.collection = collection;
		iterator = collection.iterator();
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public T next() {
		if (iterator.hasNext())
			return iterator.next();
		iterator = collection.iterator();
		return next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}
}
