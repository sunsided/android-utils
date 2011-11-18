package de.widemeadows.projectcore.cache;

import org.jetbrains.annotations.NotNull;

/**
 * A cache that allows recycling of destroyed objects to compensate for the garbage collector.
 * <p/>
 * This wrapper provides thread-local storage of caches to circumvent the locking problem.
 */
public final class ThreadLocalObjectCache<T> {

	/**
	 * The thread local object cache
	 */
	@NotNull
	private final ThreadLocal<ObjectCache<T>> threadLocalCache;

	/**
	 * Creates a new instance of the {@link ThreadLocalObjectCache} class.
	 * @param factory The factory to create new instances
	 */
	public ThreadLocalObjectCache(@NotNull final ObjectFactory<T> factory) {
		threadLocalCache = new ThreadLocal<ObjectCache<T>>() {
			@Override
			protected ObjectCache<T> initialValue() {
				return new ObjectCache<T>(factory);
			}
		};
	}

	/**
	 * Bezieht die Referenz auf den Objekt-Cache
	 * @return Der Objeckt-Cache
	 */
	@NotNull
	public final ObjectCache<T> get() {
		return threadLocalCache.get();
	}
}
