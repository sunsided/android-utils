package de.widemeadows.projectcore.utils;

import org.jetbrains.annotations.Nullable;

/**
 * Interface für Klassen, die ein Element beziehen können.
 * <p/>
 * Der ermittelte Wert kann <c>null</c> sein.
 */
public interface INullableResolver<T> {

	/**
	 * Bezieht ein Element
	 *
	 * @return Das Element (kann <c>null</c> sein)
	 */
	@Nullable
	T get();
}