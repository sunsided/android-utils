package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Eine Ebene, die durch Punkt und Normale definiert ist
 */
@Deprecated
public final class Plane2 { // TODO: Caching!

	/**
	 * Distanz der Normalen zum Ursprung
	 */
	private float _distanceToOrigin = 0;
	
	/**
	 * Normale der Plane
	 */
	@NotNull
	private final Vector3 _normal = Vector3.YAXIS.clone();

	/**
	 * Erzeugt eine Plane aus drei Punkten
	 * @param a Erster Punkt
	 * @param b Zeiter Punkt
	 * @param c Dritter Punkt
	 */
	public Plane2(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
		assert !a.equals(b) && !b.equals(c);

		define(a, b, c);
	}
	
	/**
	 * Erzeugt eine Plane über ihren Mittelpunkt und ihre Normale
	 * @param center Mittelpunkt
	 * @param normal Normale
	 */
	public Plane2(@NotNull final Vector3 center, @NotNull final Vector3 normal) {
		assert !normal.isEmpty();

		define(center, normal);
	}
	
	/**
	 * Erzeugt eine XZ-Plane
	 */
	public Plane2() {
	}

	/**
	 * Liefert die Entfernung dieser Plane vom Ursprung
	 *
	 * @return Die Referenz auf die Positionskomponente
	 * @see #getCenter()
	 */
	public float getDistanceFromOrigin() {
		return _distanceToOrigin;
	}

	/**
	 * Liefert die Position dieser Plane
	 *
	 * @return Die Referenz auf die Positionskomponente
	 * @see #getDistanceFromOrigin()
	 */
	@NotNull @ReturnsCachedValue
	public Vector3 getCenter() {
		return _normal.mul(_distanceToOrigin);
	}

	/**
	 * Definiert die Plane über drei Punkte.
	 * @param a Erster Punkt
	 * @param b Zweiter Punkt
	 * @param c Dritter Punkt
	 */
	public void define(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
		assert !a.equals(b) && !b.equals(c);

		// Normale berechnen: _normal = a.sub(b).cross(a.sub(c));
		final Vector3 aSubB = a.sub(b);
		Vector3 aSubC = a.sub(c);

		_normal.set(aSubB.cross(aSubC));
		_normal.normalize();

		// aSubB freigeben.
		// aSubC wird noch nicht freigegeben, da wir den Wert in Kürze weiterverwenden werden
		aSubB.recycle();

		// Entfernung berechnen
		final Vector3 invNormal = aSubC;
		invNormal.set(_normal).invert();
		_distanceToOrigin = invNormal.dot(a); // TODO: Tests!
		invNormal.recycle();
	}
	
	/**
	 * Definiert die Plane über einen Punkt und eine Normake
	 * @param center Der Punkt
	 * @param normal Die Normale
	 */
	public void define(@NotNull final Vector3 center, @NotNull final Vector3 normal) {
		assert !normal.isEmpty();

		// Normale setzen
		_normal.set(normal.getNormalized());

		// Entfernung berechnen
		_distanceToOrigin = center.getLength();
	}

	/**
	 * Ermittelt die Distanz eines Punktes zur Ebene
	 * @param point Der Punkt
	 * @return Die Distanz zur Ebene
	 */
	public float getDistance(@NotNull final Vector3 point) {
		return _normal.dot(point) + _distanceToOrigin; // TODO: Tests!
	}

	/**
	 * Stringumwandlungs-Fu
	 * @return Le string
	 */
	@Override
	public String toString() {
		return "{dist: "+_distanceToOrigin+", normal: " + _normal+"}";
	}
}
