package de.widemeadows.projectcore.cache;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface für Objektcaches
 */
public interface IObjectCache<T> {
	/**
	 * Registers an element with the cache
	 *
	 * @param element The element to cache
	 * @return This instance for method chaining
	 */
	@NotNull
	RealObjectCache<T> registerElement(@NotNull T element);

	/**
	 * Gets the number of elements
	 *
	 * @return The number of elements in the cache
	 */
	int getCount();

	/**
	 * Determines if there are elements in the cache
	 *
	 * @return <code>true</code> if there are elements, otherwise <code>false</code>
	 */
	boolean hasElements();

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
	T getOrCreate();

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
	T getElement();

	/**
	 * Retrieves an element from the cache or <code>null</code> if no such element was found.
	 *
	 * @return The element
	 * @see #getOrCreate()
	 * @see #hasElements()
	 * @see #getElement()
	 * @see #registerElement(Object)
	 */
	@Nullable
	@ReturnsCachedValue
	T getElementOrNull();

	/**
	 * Removes all cached items.
	 * A call to this method does not change the capacity of the queue.
	 */
	void clear();

	/**
	 * Compacts the queue by removing all elements and all nodes, reducing the capacity of the queue to 1.
	 * Use with caution.
	 *
	 * @param forceGc Forces a garbage collect after compacting
	 */
	void compact(boolean forceGc);
}
