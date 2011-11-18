package de.widemeadows.projectcore.cache;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import de.widemeadows.projectcore.lists.DoubleLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A cache that allows recycling of destroyed objects to compensate for the garbage collector.
 *
 * <p>
 *     Users may want to utilize the {@link ObjectCache#getOrCreate()} method for ease of use.<br/>
 * </p>
  *
 * @param <T> The object type
 * @see #registerElement(Object)
 * @see #hasElements()
 * @see #getOrCreate()
 */
public final class ObjectCache<T> {

	/**
	 * The caching list
	 */
	@NotNull
	private final DoubleLinkedListNode<T> start = new DoubleLinkedListNode<T>();

	/**
	 * Pointer to the last element in the list that contains a value.
	 * If the pointer is null, there are no values.
	 */
	private DoubleLinkedListNode<T> lastElementWithValue = null;

	/**
	 * The number of elements, range 0..n
	 */
	private int elementCount = 0;

	/**
	 * Profiling variable, holds the maximum size of the cache over runtime
	 */
	private int maximumSize = 0;

	/**
	 * The factory
	 */
	@Nullable
	private final ObjectFactory<T> factory;

	/**
	 * Creates a new instance of the {@link ObjectCache} class.
	 * @param factory The factory to create new instances
	 */
	ObjectCache(@NotNull ObjectFactory<T> factory) {
		this.factory = factory;
	}

	/**
	 * Creates a new instance of the {@link ObjectCache} class.
	 */
	private ObjectCache() {
		factory = null;
	}

	/**
	 * Registers an element with the cache
	 *
	 * @param element The element to cache
	 * @return This instance for method chaining
	 */
	@NotNull
	public ObjectCache<T> registerElement(@NotNull T element) {
		DoubleLinkedListNode<T> target;

		// if no element is in the cache
		if (lastElementWithValue == null) {
			target = start;
		}
		else { // if there are elements in the cache
			target = lastElementWithValue.getNext();

			// if there is no follower ...
			if (target == null) {
				// ... create one and add it after the current last element
				target = new DoubleLinkedListNode<T>();
				target.insertAfter(lastElementWithValue);
			}
		}

		// set the value, rewire and count up
		target.setPayload(element);
		lastElementWithValue = target;
		++elementCount;
		maximumSize = Math.max(maximumSize, elementCount);
		return this;
	}

	/**
	 * Gets the number of elements
	 *
	 * @return The number of elements in the cache
	 */
	public int getCount() {
		return elementCount;
	}

	/**
	 * Determines if there are elements in the cache
	 *
	 * @return <code>true</code> if there are elements, otherwise <code>false</code>
	 */
	public boolean hasElements() {
		return elementCount > 0;
	}

	/**
	 * Retrieves an element from the cache and applies data to it or creates a new object, if
	 * the cache was empty.
	 *
	 * @return The new or refurbished object
	 * @see #hasElements()
	 * @see #getElement()
	 * @see #getElementOrNull()
	 * @see #registerElement(Object)
	 */
	@NotNull @ReturnsCachedValue
	public T getOrCreate() {
		assert factory != null;

		if (hasElements()) return getElement();
		return factory.createNew();
	}

	/**
	 * Retrieves an element from the cache
	 *
	 * @return The element
	 * @see #getOrCreate()
	 * @see #hasElements()
	 * @see #getElementOrNull()
	 * @see #registerElement(Object)
	 */
	@NotNull @ReturnsCachedValue
	public T getElement() {
		assert elementCount > 0;
		assert lastElementWithValue != null;

		// retrieve the element
		T element = lastElementWithValue.getPayload();
		assert element != null;

		// reduce the element count and reset the element pointer.
		// if the pointer already is on the first element, further resetting it yields null,
		// which is the desired effect
		--elementCount;
		lastElementWithValue = lastElementWithValue.getPrev();
		return element;
	}

	/**
	 * Retrieves an element from the cache or <code>null</code> if no such element was found.
	 *
	 * @return The element
	 * @see #getOrCreate()
	 * @see #hasElements()
	 * @see #getElement()
	 * @see #registerElement(Object)
	 */
	@Nullable @ReturnsCachedValue
	public T getElementOrNull() {
		if (elementCount == 0 || lastElementWithValue == null) return null;

		// retrieve the element
		T element = lastElementWithValue.getPayload();
		assert element != null;

		// reduce the element count and reset the element pointer.
		// if the pointer already is on the first element, further resetting it yields null,
		// which is the desired effect
		--elementCount;
		lastElementWithValue = lastElementWithValue.getPrev();
		return element;
	}

	/**
	 * Removes all cached items.
	 * A call to this method does not change the capacity of the queue.
	 */
	public void clear() {
		// reset counter and pointers
		elementCount = 0;
		lastElementWithValue = null;

		// delete elements
		DoubleLinkedListNode<T> pointer = start;
		do {
			pointer.setPayload(null);
			pointer = pointer.getNext();
		} while(pointer != null);
	}

	/**
	 * Compacts the queue by removing all elements and all nodes, reducing the capacity of the queue to 1.
	 * Use with caution.
	 *
	 * @param forceGc Forces a garbage collect after compacting
	 */
	public void compact(boolean forceGc) {

		// reset counter and pointers
		elementCount = 0;
		lastElementWithValue = null;

		// delete elements
		start.setPayload(null);
		while(start.hasNext()) {
			// clear and remove the element in the next node
			DoubleLinkedListNode<T> pointer = start.getNext();
			assert pointer != null;
			pointer.setPayload(null);
			start.removeAfter();
		}

		// garbage collect
		if (forceGc) System.gc();
	}
}
