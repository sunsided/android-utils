package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Factory für {@link Ray3}
 */
public final class RayFactory {

	/**
	 * Hiddensee
	 */
	private RayFactory() {}

	/**
	 * Erzeugt einen Strahl durch zwei Punkte
	 * 
	 * @param p1 Der erste Punkt
	 * @param p2 Der zweite Punkt
	 * @return Der erzeugte Ray
	 * @see #rayFromPointAndDirection(Vector3, Vector3) 
	 */
	@NotNull @ReturnsCachedValue
	public static Ray3 rayFromTwoPoints(@NotNull final Vector3 p1, @NotNull final Vector3 p2) {
		Vector3 direction = p2.sub(p1);
		Ray3 ray = Ray3.createNew(p1, direction);
		direction.recycle();
		return ray;
	}

	/**
	 * Erzeugt einen Ray von einem Punkt in eine Richtung.
	 * <p/>
	 * Dies ist ein Shortcut für {@link Ray3#createNew(Vector3, Vector3)}.
	 *
	 * @param point Der Ursprung
	 * @param direction Die Richtung
	 * @return Der erzeugte Ray
	 * @see #rayFromTwoPoints(Vector3, Vector3) 
	 */
	@NotNull
	@ReturnsCachedValue
	public static Ray3 rayFromPointAndDirection(@NotNull final Vector3 point, @NotNull final Vector3 direction) {
		return Ray3.createNew(point, direction);
	}
}
