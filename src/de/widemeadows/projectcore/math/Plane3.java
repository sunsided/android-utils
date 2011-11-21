package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.IObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;
import de.widemeadows.projectcore.cache.ThreadLocalObjectCache;
import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Eine Ebene, die durch Punkt und Normale definiert ist
 */
public final class Plane3 {

    /**
     * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
     */
    public static final IObjectCache<Plane3> Cache = new ThreadLocalObjectCache<Plane3>(new ObjectFactory<Plane3>() {
        @NotNull
        @Override
        public Plane3 createNew() {
            return new Plane3();
        }
    });

    /**
     * Erzeugt eine neue {@link Plane3}-Instanz.
     *
     * @param normal Die Normale der Ebene
     * @param d Die Distanz vom Koordinatenursprung
     * @return Die neue oder aufbereitete Plane
     * @see #Cache
     */
    @NotNull
    public static Plane3 createNew(@NotNull final Vector3 normal, final float d) {
        return Cache.getOrCreate().set(normal, d);
    }

    /**
     * Erzeugt eine neue {@link Plane3}-Instanz.
     *
     * @param normal Die Normale der Ebene
     * @param center Der Mittelpunkt der Ebene
     * @return Die neue oder aufbereitete Plane
     * @see #Cache
     */
    public static Plane3 createNew(@NotNull final Vector3 normal, @NotNull final Vector3 center) {
        return Cache.getOrCreate().set(normal, center);
    }

    /**
     * Erzeugt eine neue {@link Plane3}-Instanz.
     *
     * @param a Erster Punkt
     * @param b Zweiter Punkt
     * @param c Dritter Punkt
     * @return Die neue oder aufbereitete Plane
     * @see #Cache
     */
    public static Plane3 createNew(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
        return Cache.getOrCreate().set(a, b, c);
    }

    /**
     * Erzeugt eine neue {@link Plane3}-Instanz.
     *
     * @param plane Die zu kopierende Plane
     * @return Die neue oder aufbereitete Plane
     * @see #Cache
     */
    public static Plane3 createNew(@NotNull final Plane3 plane) {
        return Cache.getOrCreate().set(plane);
    }

    /**
     * Erzeugt eine neue {@link Plane3}-Instanz.
     *
     * @return Der neue oder aufbereitete Plane
     * @see #Cache
     */
    @NotNull
    public static Plane3 createNew() {
        return Cache.getOrCreate();
    }

    /**
     * Registriert eine Plane für das spätere Cache
     *
     * @param plane Die freizugebende Plane
     * @see #Cache
     * @see Vector3#recycle()
     */
    public static void recycle(@NotNull final Plane3 plane) {
        Cache.registerElement(plane);
    }

    /**
     * Registriert diesen Vektor für das spätere Cache
     *
     * @see #Cache
     * @see Vector3#recycle(Vector3)
     */
    public void recycle() {
        Cache.registerElement(this);
    }

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
	private Plane3(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
		assert !a.equals(b) && !b.equals(c);

		set(a, b, c);
	}
	
	/**
	 * Erzeugt eine Plane über ihren Mittelpunkt und ihre Normale
	 * @param center Mittelpunkt
	 * @param normal Normale
	 */
    private Plane3(@NotNull final Vector3 center, @NotNull final Vector3 normal) {
		assert !normal.isEmpty();

		set(center, normal);
	}
	
	/**
	 * Erzeugt eine XZ-Plane
	 */
    private Plane3() {
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
     * @return Diese Instanz für method chaining
	 */
    @NotNull
    public Plane3 set(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
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
		_distanceToOrigin = invNormal.dot(a);
		invNormal.recycle();
        return this;
	}
	
	/**
	 * Definiert die Plane über einen Punkt und eine Normake
	 * @param center Der Punkt
	 * @param normal Die Normale
     * @return Diese Instanz für method chaining
	 */
    @NotNull
    public Plane3 set(@NotNull final Vector3 center, @NotNull final Vector3 normal) {
		assert !normal.isEmpty();

		// Normale setzen
		_normal.set(normal.getNormalized());

		// Entfernung berechnen
		_distanceToOrigin = center.getLength();
        return this;
	}

    /**
     * Definiert die Plane über einen Punkt und eine Normake
     *
     * @param distance Die Distantz zum Koordinatenursprung
     * @param normal Die Normale
     * @return Diese Instanz für method chaining
     */
    @NotNull
    public Plane3 set(@NotNull final Vector3 normal, final float distance) {
        _normal.set(normal.getNormalized());
        _distanceToOrigin = distance;
        return this;
    }

    /**
     * Kopiert eine Plane
     *
     * @param plane Die Plane
     * @return Diese Instanz für method chaining
     */
    @NotNull
    public Plane3 set(@NotNull final Plane3 plane) {
        set(plane._normal, plane._distanceToOrigin);
        return this;
    }

	/**
	 * Ermittelt die Distanz eines Punktes zur Ebene
     * 
	 * @param point Der Punkt
	 * @return Die Distanz zur Ebene
	 */
	public float getDistanceFromPoint(@NotNull final Vector3 point) {
		return _normal.dot(point) + _distanceToOrigin;
	}

	/**
	 * Stringumwandlungs-Fu
     * 
	 * @return Le string
	 */
	@Override
	public String toString() {
		return "{dist: "+_distanceToOrigin+", normal: " + _normal+"}";
	}
}
