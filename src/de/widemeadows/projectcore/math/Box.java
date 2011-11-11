package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Basisklasse für Boxen (AABB, OBB, ...)
 */
public abstract class Box implements IBoundsTestable {

	/**
	 * Gibt an, ob diese Box vektorbasiert ist
	 *
	 * @return
	 */
	public abstract boolean isVectorBased();

	/**
	 * Gibt an, ob diese Box ebenenbasiert ist
	 *
	 * @return
	 */
	public abstract boolean isPlaneBased();

	/**
	 * Setzt die Größe des Quaders
	 *
	 * @param width  Breite
	 * @param height Höhe
	 * @param depth  Tiefe
	 * @see #setExtent(float, float, float)
	 */
	public abstract void setSize(float width, float height, float depth);

	/**
	 * Setzt die Größe des Quaders
	 *
	 * @param width  Breite
	 * @param height Höhe
	 * @param depth  Tiefe
	 */
	@NotNull
	public abstract Box setExtent(float width, float height, float depth);

	/**
	 * Setzt die Breite der Box
	 * @param width Die Breite
	 */
	public abstract void setWidth(float width);

	/**
	 * Setzt die Höhe der Box
	 * @param height Die Höhe
	 */
	public abstract void setHeight(float height);

	/**
	 * Setzt die Tiefe der Box
	 * @param depth Die Tiefe
	 */
	public abstract void setDepth(float depth);

	/**
	 * Bezieht die Breite der Box
	 * @return Die Breite
	 */
	public abstract float getWidth();

	/**
	 * Bezieht die Höhe der Box
	 * @return Die Höhe
	 */
	public abstract float getHeight();

	/**
	 * Bezieht die Tiefe der Box
	 * @return Die Tiefe
	 */
	public abstract float getDepth();

	/**
	 * Setzt die Ausdehnung der Box
	 *
	 * @param extent Die Ausdehnung
	 * @return Diese Instanz für method chaining
	 * @see #getExtent()
	 */
	@NotNull
	public abstract Box setExtent(@NotNull Vector3 extent);

	/**
	 * Bezieht die Ausdehnung der Box
	 *
	 * @return Die Ausdehnung des Quaders
	 * @see #setExtent(Vector3)
	 */
	@NotNull
	public abstract Vector3 getExtent();

	/**
	 * Setzt die Position der Box
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 * @return Diese Instanz für method chaining
	 */
	@NotNull
	public abstract Box setPosition(float x, float y, float z);

	/**
	 * Setzt die Position der Box
	 *
	 * @param position Die Position
	 * @return Diese Instanz für method chaining
	 */
	@NotNull
	public abstract Box setPosition(@NotNull Vector3 position);

	/**
	 * Bezieht die Position der Box
	 * @return Die Position des Quaders
	 */
	@NotNull
	public abstract Vector3 getPosition();

	/**
	 * Bezieht die X-Koordinate der Box
	 * @return X-Koordinate
	 */
	public abstract float getX();

	/**
	 * Bezieht die Y-Koordinate der Box
	 * @return Y-Koordinate
	 */
	public abstract float getY();

	/**
	 * Bezieht die Z-Koordinate der Box
	 * @return Z-Koordinate
	 */
	public abstract float getZ();

	/**
	 * Überprüft, ob sich ein Punkt innerhalb der Box befindet
	 * @param point Die zu testende Position
	 * @return <code>true</code>, wenn sich der Punkt innerhalb des Quaders befindet
	 */
	@Override
	public abstract boolean containsPoint(@NotNull final Vector3 point);

	/**
	 * Erzeugt eine optimale Bounding Box aus einer Punktwolke
	 *
	 * @param points    Die Punkte
	 * @return Die Bounding Sphere
	 * @see BoxWithVectors#createFrom(float, Vector3...)
	 */
	@NotNull
	public final Box createFrom(@NotNull final Vector3... points) {
		return createFrom(0.01f, points);
	}

	/**
	 * Erzeugt eine optimale Bounding Box aus einer Punktwolke
	 *
	 * @param minSize   Die minimale Größe
	 * @param points    Die Punkte
	 * @return Die Bounding Sphere
	 * @see BoxWithVectors#createFrom(Vector3...)
	 */
	@NotNull
	public final Box createFrom(float minSize, @NotNull final Vector3... points) {
		assert minSize > 0;
		assert points.length > 0;

		assert minSize > 0;
		assert points.length > 0;

		// Minimal- und Maximalwerte finden
		float xmin = points[0].x;
		float xmax = points[0].x;
		float ymin = points[0].y;
		float ymax = points[0].y;
		float zmin = points[0].z;
		float zmax = points[0].z;

		// Mittelwert der Vektoren bilden, um Position zu finden
		Vector3 mean = Vector3.createNew().set(xmin, ymin, zmin);
		for (int i = 1; i < points.length; ++i) {
			xmax = Math.max(xmax, points[i].x);
			xmin = Math.min(xmin, points[i].x);
			ymax = Math.max(ymax, points[i].y);
			ymin = Math.min(ymin, points[i].y);
			zmax = Math.max(zmax, points[i].z);
			zmin = Math.min(zmin, points[i].z);

			// aufaddieren
			mean.addInPlace(points[i]);
		}
		mean.mulInPlace(1.0f / points.length);

		// Dimensionen berechnen
		float width = Math.abs(xmax - xmin);
		float height = Math.abs(ymax - ymin);
		float depth = Math.abs(zmax - zmin);

		// Position berechnen
		float posX = mean.x;
		float posY = mean.y;
		float posZ = mean.z;
		Vector3.recycle(mean);

		setPosition(posX, posY, posZ);
		setExtent(width, height, depth);
		return this;
	}

	/**
	 * Bezieht einen gecachten Vektor, der den Minimalpunkt der Box repräsentiert
	 * @return Minimaler Punkt (cached)
	 */
	@NotNull
	@ReturnsCachedValue
	public abstract Vector3 getMinPoint();

	/**
	 * Bezieht einen gecachten Vektor, der den Maximalpunkt der Box repräsentiert
	 *
	 * @return Maximaler Punkt (cached)
	 */
	@NotNull
	@ReturnsCachedValue
	public abstract Vector3 getMaxPoint();

	/**
	 * Wandelt diese Box in eine vektorbasierte Box um.
	 * <p><b>Ist diese Box bereits vektorbasiert, wird dieselbe Instanz zurückgegeben!</b></p>
	 *
	 * @return Vektorbasierte Box
	 */
	@NotNull
	public final BoxWithVectors toVectorBasedBox() {
		if (isVectorBased()) return (BoxWithVectors) this;

		Vector3 extents = getExtent();
		try {
			return new BoxWithVectors(extents.x, extents.y, extents.z).setPosition(getPosition());
		} finally {
			extents.recycle();
		}
	}

	/**
	 * Wandelt diese Box in eine Plane-basierte Box um.
	 * <p><b>Ist diese Box bereits planebasiert, wird dieselbe Instanz zurückgegeben!</b></p>
	 *
	 * @return Planebasierte Box
	 */
	@NotNull
	public final BoxWithPlanes toPlaneBasedBox() {
		if (isPlaneBased()) return (BoxWithPlanes)this;

		Vector3 extents = getExtent();
		try {
			return new BoxWithPlanes().setPosition(getPosition()).setExtent(extents.x, extents.y, extents.z);
		} finally {
			extents.recycle();
		}
	}
}
