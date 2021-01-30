package inf102.h20.iterating;

import java.util.Iterator;

/**
 * This class wraps an Iterator such that the remove method is disabled
 * @author Martin Vatshelle, inspired by Chris Smith
 *
 * @param <T>
 */
public class UnmodifiableIterator<T> implements Iterator<T>{

	private Iterator<T> iter;
	
	public UnmodifiableIterator(Iterator<T> iter) {
		this.iter = iter;
	}
	
	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public T next() {
		return iter.next();
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
