package de.widemeadows.projectcore.math;

import org.jetbrains.annotations.NotNull;

/**
 * Definiert eine Kugel
 */
@Deprecated
public final class Sphere {
	
	/**
	 * Radius der Kugel
	 */
	private float _radius = 1.f;
	
	/**
	 * Bezieht den Radius
	 * @return Der Radius
	 */
	public float getRadius() { return _radius; }
	
	/**
	 * Setzt den Radius
	 * @param radius Der Radius im Bereich ]0..n]
	 */
	public void setRadius(float radius) {
		assert radius > 0;
		_radius = radius;
	}
	
	/**
	 * Die Position der Kugel
	 */
	@NotNull
	private Vector3 _position = Vector3.createNew();
	
	/**
	 * Setzt die Position der Kugel
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 */
	public void setPosition(float x, float y, float z) {
		_position.set(x, y, z);
	}
	
	/**
	 * Bezieht die Position der Kugel
	 * @return Die Position der Kugel
	 */
	@NotNull
	public Vector3 getPosition() { return _position; }
	
	/**
	 * Bezieht die X-Koordinate der Kugel
	 * @return X-Koordinate
	 */
	public float getX() { return _position.x; }
	
	/**
	 * Bezieht die Y-Koordinate der Sphere
	 * @return Y-Koordinate
	 */
	public float getY() { return _position.y; }
	
	/**
	 * Bezieht die Z-Koordinate der Sphere
	 * @return Z-Koordinate
	 */
	public float getZ() { return _position.z; }

	/**
	 * Erzeugt eine Kugel mit Radius 1
	 */
	public Sphere() {}
	
	/**
	 * Erzeugt eine Kugel mit Radius R
	 * @param radius Der Radius im Bereich ]0..n]
	 */
	public Sphere(float radius) {
		assert radius > 0;
		_radius = radius;
	}
	
	/**
	 * Ermittelt, ob der Punkt innerhalb oder auf der Kugel liegt
	 *
	 * @param point Der zu testende Punkt
	 * @return <code>true</code>, wenn der Punkt innerhalb der Kugel liegt
	 */
	public boolean containsPoint(@NotNull Vector3 point) {
		return _position.getDistance(point) <= _radius;
	}

	/**
	 * Modifiziert die Kugel so, dass der Punkt in ihr liegt
	 * @param point Der zu umfassende Punkt
	 * @see Sphere#containsPoint(Vector3)
	 */
	public void add(@NotNull Vector3 point) {
		float distance = _position.getDistance(point);
		if (distance <= _radius) return;
		_radius = distance;
	}

	/**
	 * Erzeugt eine optimale Bounding Sphere aus einer Punktwolke
	 *
	 * @param points Die Punkte
	 * @return Die Bounding Sphere
	 * @see Sphere#createFrom(float, Vector3...)
	 */
	@NotNull
	public Sphere createFrom(@NotNull Vector3... points) {
		return createFrom(0.01f, points);
	}

	/**
	 * Erzeugt eine optimale Bounding Sphere aus einer Punktwolke
	 * 
	 * @param minRadius Der Minmalradius
	 * @param points Die Punkte
	 * @return Die Bounding Sphere
	 * @see Sphere#createFrom(Vector3...)
	 */
	@NotNull
	public Sphere createFrom(float minRadius, @NotNull Vector3 ... points) {
		assert minRadius > 0;
		assert points.length > 0;

		// Mittelwert der Vektoren bilden, um Position zu finden
		Vector3 mean = Vector3.createNew();
		for(int i=0; i<points.length; ++i) {
			mean.addInPlace(points[i]);
		}
		mean.mulInPlace(1.0f/points.length);

		// Punkte hinzufügen
		Sphere sphere = new Sphere(minRadius);
		sphere.setPosition(mean.x, mean.y, mean.z);
		for (int i = 0; i < points.length; ++i) {
			sphere.add(points[i]);
		}

		// Aufräumen
		Vector3.recycle(mean);

		// Voilà
		return sphere;
	}
}
