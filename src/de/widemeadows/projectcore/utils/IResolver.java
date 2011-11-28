package de.widemeadows.projectcore.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Interface für Klassen, die ein Element beziehen können.
 * <p/>
 * Der ermittelte Wert darf nicht <c>null</c> sein.
 */
public interface IResolver<T> {

	/**
	 * Bezieht ein Element
	 *
	 * @return Das Element (darf nicht <c>null</c> sein)
	 */
	@NotNull
	T get();
}