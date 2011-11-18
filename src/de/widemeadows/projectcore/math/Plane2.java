package de.widemeadows.projectcore.math;

import org.jetbrains.annotations.NotNull;

/**
 * Eine Ebene, die durch Punkt und Normale definiert ist
 */
@Deprecated
public final class Plane2 { // TODO: Caching!

	/**
	 * Position der Plane
	 */
	@NotNull
	private final Vector3 _center = Vector3.ZERO.clone();
	
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
	 * Liefert die Position dieses Plane
	 * @return Die Referenz auf die Positionskomponente
	 */
	@NotNull
	public Vector3 getPosition() {
		return _center;
	}

	/**
	 * Definiert die Plane über drei Punkte.
	 * @param a Erster Punkt
	 * @param b Zweiter Punkt
	 * @param c Dritter Punkt
	 */
	public void define(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
		assert !a.equals(b) && !b.equals(c);

		_center.set(a);

		// Normale berechnen: _normal = a.sub(b).cross(a.sub(c));
		Vector3 aSubB = a.sub(b);
		Vector3 aSubC = a.sub(c);

		_normal.set(aSubB.cross(aSubC));
		_normal.normalize();

		aSubB.recycle();
		aSubC.recycle();
	}
	
	/**
	 * Definiert die Plane über einen Punkt und eine Normake
	 * @param center Der Punkt
	 * @param normal Die Normale
	 */
	public void define(@NotNull final Vector3 center, @NotNull final Vector3 normal) {
		assert !normal.isEmpty();

		_center.set(center);
		_normal.set(normal.getNormalized());
	}

	/**
	 * Ermittelt die Distanz eines Punktes zur Ebene
	 * @param point Der Punkt
	 * @return Die Distanz zur Ebene
	 */
	public float getDistance(@NotNull final Vector3 point) {
		Vector3 direction = point.sub(_center);
		final float distance = _normal.dot(direction); // / _normal.getLength();;
		direction.recycle();
		return distance;
	}

	/**
	 * Stringumwandlungs-Fu
	 * @return Le string
	 */
	@Override
	public String toString() {
		return "{center: "+_center+", normal: " + _normal+"}";
	}
}
