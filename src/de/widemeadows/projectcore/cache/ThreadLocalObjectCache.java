package de.widemeadows.projectcore.cache;

import org.jetbrains.annotations.NotNull;

/**
 * A cache that allows recycling of destroyed objects to compensate for the garbage collector.
 * <h2>Thread Safety</h2>
 * This wrapper provides thread-local storage of caches to circumvent the locking problem.
 * <p/>
 * Thus, members of this class are considered thread safe.
 */
public final class ThreadLocalObjectCache<T> implements IObjectCache<T> {

	/**
	 * The thread local object cache
	 */
	@NotNull
	private final ThreadLocal<RealObjectCache<T>> threadLocalCache;

	/**
	 * Creates a new instance of the {@link ThreadLocalObjectCache} class.
	 * @param factory The factory to create new instances
	 */
	public ThreadLocalObjectCache(@NotNull final ObjectFactory<T> factory) {
		threadLocalCache = new ThreadLocal<RealObjectCache<T>>() {
			@Override
			protected RealObjectCache<T> initialValue() {
				return new RealObjectCache<T>(factory);
			}
		};
	}

	/**
	 * Bezieht die Referenz auf den Objekt-Cache
	 * @return Der Objeckt-Cache
	 */
	@NotNull
	public final RealObjectCache<T> get() {
		return threadLocalCache.get();
	}

	/**
	 * Registers an element with the cache
	 *
	 * @param element The element to cache
	 * @return This instance for method chaining
	 */
	@NotNull
	@Override
	public RealObjectCache<T> registerElement(@NotNull T element) {
		return threadLocalCache.get().registerElement(element);
	}

	/**
	 * Gets the number of elements
	 *
	 * @return The number of elements in the cache
	 */
	@Override
	public int getCount() {
		return threadLocalCache.get().getCount();
	}

	/**
	 * Determines if there are elements in the cache
	 *
	 * @return <code>true</code> if there are elements, otherwise <code>false</code>
	 */
	@Override
	public boolean hasElements() {
		return threadLocalCache.get().hasElements();
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
	@NotNull
	@Override
	public T getOrCreate() {
		return threadLocalCache.get().getOrCreate();
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
	@NotNull
	@Override
	public T getElement() {
		return threadLocalCache.get().getElement();
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
	@Override
	public T getElementOrNull() {
		return threadLocalCache.get().getElementOrNull();
	}

	/**
	 * Removes all cached items.
	 * A call to this method does not change the capacity of the queue.
	 */
	@Override
	public void clear() {
		threadLocalCache.get().clear();
	}

	/**
	 * Compacts the queue by removing all elements and all nodes, reducing the capacity of the queue to 1.
	 * Use with caution.
	 *
	 * @param forceGc Forces a garbage collect after compacting
	 */
	@Override
	public void compact(boolean forceGc) {
		threadLocalCache.get().compact(forceGc);
	}
}
