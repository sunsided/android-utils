package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.IObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;
import de.widemeadows.projectcore.cache.ThreadLocalObjectCache;
import org.jetbrains.annotations.NotNull;

/**
 * Definiert eine Kugel
 */
public final class Sphere {

	/**
	 * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
	 */
	public static final IObjectCache<Sphere> Cache = new ThreadLocalObjectCache<Sphere>(new ObjectFactory<Sphere>() {
		@NotNull
		@Override
		public Sphere createNew() {
			return new Sphere();
		}
	});

	/**
	 * Erzeugt eine neue Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Instanz kann korrupt sein!
	 * </p>
	 *
	 * @param originX    Der Ursprung (X-Komponente)
	 * @param originY    Der Ursprung (Y-Komponente)
	 * @param originZ    Der Ursprung (Z-Komponente)
	 * @param radius     Der Radius
	 * @return Die neue oder aufbereitete Instanz
	 * @see #Cache
	 */
	@NotNull
	public static Sphere createNew(final float originX, final float originY, final float originZ,
	                             final float radius) {
		return Cache.getOrCreate().set(originX, originY, originZ, radius);
	}

	/**
	 * Erzeugt eine neue {@link Sphere}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand kann korrupt sein!
	 * </p>
	 *
	 * @param origin    Der Ursprung
	 * @param radius Der Radius
	 * @return Die neue oder aufbereitete Sphere
	 * @see #Cache
	 */
	public static Sphere createNew(@NotNull final Vector3 origin, final float radius) {
		return Cache.getOrCreate().set(origin, radius);
	}

	/**
	 * Erzeugt eine neue {@link Sphere}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand kann korrupt sein!
	 * </p>
	 *
	 * @param other Die zu kopierende Sphere
	 * @return Die neue oder aufbereitete Sphere
	 * @see #Cache
	 */
	public static Sphere createNew(@NotNull final Sphere other) {
		return Cache.getOrCreate().set(other);
	}

	/**
	 * Erzeugt eine neue {@link Sphere}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand kann korrupt sein!
	 * </p>
	 *
	 * @return Die neue oder aufbereitete Sphere
	 * @see #Cache
	 */
	@NotNull
	public static Sphere createNew() {
		return Cache.getOrCreate();
	}

	/**
	 * Recyclet einen Ray
	 *
	 * @param box Der zu recyclende Ray
	 * @see #Cache
	 * @see AxisAlignedBox#recycle()
	 */
	public static void recycle(@NotNull final Sphere box) {
		Cache.registerElement(box);
	}

	/**
	 * Registriert diesen Ray für das spätere Cache
	 *
	 * @see #Cache
	 * @see Vector3#recycle(Vector3)
	 */
	public void recycle() {
		Cache.registerElement(this);
	}

	/**
	 * Radius der Kugel
	 * @see #_radiusSq
	 */
	private float _radius = 1.f;

	/**
	 * Quadrierter Radius der Kugel
	 * @see #_radius
	 */
	private float _radiusSq = 1.f;

	/**
	 * Bezieht den Radius
	 * @return Der Radius
	 */
	public float getRadius() { return _radius; }

	/**
	 * Bezieht den quadrierten Radius
	 *
	 * @return Der quadrierte Radius
	 */
	public float getRadiusSq() {
		return _radiusSq;
	}

	/**
	 * Setzt den Radius
	 * @param radius Der Radius im Bereich ]0..n]
	 */
	public void setRadius(final float radius) {
		assert radius > 0;
		_radius = radius;
		_radiusSq = radius*radius;
	}
	
	/**
	 * Die Position der Kugel
	 */
	@NotNull
	private final Vector3 _origin = Vector3.createNew();
	
	/**
	 * Setzt die Position der Kugel
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 */
	public void setPosition(float x, float y, float z) {
		_origin.set(x, y, z);
	}

	/**
	 * Setzt die Position der Kugel
	 *
	 * @param pos Die Position
	 */
	public void setPosition(@NotNull final Vector3 pos) {
		_origin.set(pos.x, pos.y, pos.z);
	}
	
	/**
	 * Bezieht die Position der Kugel
	 * @return Die Position der Kugel
	 */
	@NotNull
	public Vector3 getPosition() { return _origin; }
	
	/**
	 * Bezieht die X-Koordinate der Kugel
	 * @return X-Koordinate
	 */
	public float getX() { return _origin.x; }
	
	/**
	 * Bezieht die Y-Koordinate der Sphere
	 * @return Y-Koordinate
	 */
	public float getY() { return _origin.y; }
	
	/**
	 * Bezieht die Z-Koordinate der Sphere
	 * @return Z-Koordinate
	 */
	public float getZ() { return _origin.z; }

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
	 * Modifiziert die Kugel so, dass der Punkt in ihr liegt
	 * @param point Der zu umfassende Punkt
	 */
	public void add(@NotNull final Vector3 point) {
		float distance = _origin.getDistanceSquared(point);
		if (distance <= _radiusSq) return;
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
	public Sphere createFrom(@NotNull final Vector3... points) {
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
	public Sphere createFrom(float minRadius, @NotNull final Vector3 ... points) {
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

	/**
	 * Setzt die Werte
	 *
	 * @param other Die zu kopierende Kugel
	 * @return Diese Instanz für method chaining
	 */
	public Sphere set(@NotNull final Sphere other) {
		_origin.set(other._origin);
		_radius = other._radius;
		_radiusSq = other._radiusSq;
		return this;
	}
	
	/**
	 * Setzt die Werte
	 *
	 * @param x Die X-Koordinate
	 * @param y Die Y-Koordinate
	 * @param z Die Z-Koordinate
	 * @param radius   Der Radius im Bereich ]0..n]
	 * @return Diese Instanz für method chaining
	 */
	public Sphere set(final float x, final float y, final float z, final float radius) {
		setPosition(x, y, z);
		setRadius(radius);
		return this;
	}

	/**
	 * Setzt die Werte
	 *
	 * @param position Die Position
	 * @param radius   Der Radius im Bereich ]0..n]
	 * @return Diese Instanz für method chaining
	 */
	public Sphere set(@NotNull final Vector3 position, final float radius) {
		setPosition(position);
		setRadius(radius);
		return this;
	}

	/**
	 * Ermittelt, ob der Punkt innerhalb oder auf der Kugel liegt
	 *
	 * @param point Der zu testende Punkt
	 * @return <code>true</code>, wenn der Punkt innerhalb der Kugel liegt
	 */
	public boolean intersects(@NotNull final Vector3 point) {
		return _origin.getDistanceSquared(point) <= _radiusSq;
	}

	/**
	 * Ermittelt, ob der Ray innerhalb oder auf der Kugel liegt
	 *
	 * @param ray Der zu testende Ray
	 * @return <code>true</code>, wenn der Ray innerhalb der Kugel liegt
	 */
	public boolean intersects(@NotNull final Ray3 ray) {
		// Projektion des Kugel-Mittelpunktes auf den Strahl
		Vector3 projected = ray.projectPoint(this._origin);
		
		// Distanz von projiziertem Punkt zu Mittelpunkt berechnen
		final float distSq = projected.getDistanceSquared(_origin);
		projected.recycle();

		// Wenn Distanz < Radius - Treffer
		return distSq <= _radiusSq;
	}
}