package inf102.h20.iterating;

import java.util.Iterator;
import java.util.function.Function;

/**
 * This class makes an Iterable.
 * Sometimes one have an Iterator but not an Iterable 
 * and sometimes one have private variables holding Collections which
 * is not desirable to make available.
 * TODO: maybe the name is not optimal, try to improve
 * 
 * @author Martin Vatshelle
 *
 * @param <T> The type of elements you want to Iterate over
 * @param <Data> The input data needed to restart the Iteration
 */
public class IterableIterator<T,Data> implements Iterable<T>{

	private Function<Data,Iterator<T>> constructor;
	private Data data;
		
	/**
	 * This Constructor takes in a function handle to a contructor which makes an Iterable<T>
	 * Then whenever a new Iterable is needed on can call that constructor
	 * @param constructor - function to restart the iterator
	 * @param data - data needed as input to constructor function that restarts
	 */
	public IterableIterator(Function<Data,Iterator<T>> constructor, Data data) {
		this.constructor = constructor;
		this.data = data;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new UnmodifiableIterator<T>(constructor.apply(data));
	}

	/**
	 * This method protects an Iterable from beeing modified.
	 * 
	 * @param <T> - The element type to iterate over
	 * @param <Data> - Subtype of the Iterable<T>
	 * @param iter - The iterable we want to protect
	 * @return an IterableIterator that iterates over the same elements as the input
	 */
	public static <T,Data extends Iterable<T>> IterableIterator<T, Data> wrap(Data iter) {
		Function<Data,Iterator<T>> fun = Iterable::iterator;
		return new IterableIterator<T,Data>(fun,iter);
	}
}

