package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.IObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;
import de.widemeadows.projectcore.cache.ThreadLocalObjectCache;
import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Symmetrische Box
 */
public final class AxisAlignedBox {

	/**
	 * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
	 */
	public static final IObjectCache<AxisAlignedBox> Cache = new ThreadLocalObjectCache<AxisAlignedBox>(new ObjectFactory<AxisAlignedBox>() {
		@NotNull
		@Override
		public AxisAlignedBox createNew() {
			return new AxisAlignedBox();
		}
	});

	/**
	 * Erzeugt eine neue Vektor-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
	 * @param centerX Die Position (X-Komponente)
	 * @param centerY Die Position (Y-Komponente)
	 * @param centerZ Die Position (Z-Komponente)
	 * @param extentX Der Maximalvektor (X-Komponente)
	 * @param extentY Der Maximalvektor (Y-Komponente)
	 * @param extentZ Der Maximalvektor (Z-Komponente)
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Cache
	 */
	@NotNull
	public static AxisAlignedBox createNew(final float centerX, final float centerY, final float centerZ,
	                            final float extentX, final float extentY, final float extentZ) {
		return Cache.getOrCreate().set(centerX, centerY, centerZ, extentX, extentY, extentZ);
	}

	/**
	 * Erzeugt eine neue {@link AxisAlignedBox}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
	 * @param other Die zu kopierende Box
	 * @return Die neue oder aufbereitete Box
	 * @see #Cache
	 */
	public static AxisAlignedBox createNew(@NotNull final AxisAlignedBox other) {
		return Cache.getOrCreate().set(other);
	}

	/**
	 * Erzeugt eine neue {@link AxisAlignedBox}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
	 * @param center Der Mittelpunkt der Box
	 * @param extent Der Maximalvektor der Box
	 * @return Die neue oder aufbereitete Box
	 * @see #Cache
	 */
	public static AxisAlignedBox createNew(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
		return Cache.getOrCreate().set(center, extent);
	}

	/**
	 * Erzeugt eine neue {@link AxisAlignedBox}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Box kann korrupt sein!
	 * </p>
	 *
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Cache
	 */
	@NotNull
	public static AxisAlignedBox createNew() {
		return Cache.getOrCreate();
	}

	/**
	 * Recyclet eine Box
	 *
	 * @param box Die zu recyclende Box
	 * @see #Cache
	 * @see AxisAlignedBox#recycle()
	 */
	public static void recycle(@NotNull final AxisAlignedBox box) {
		Cache.registerElement(box);
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
	 * Der Mittelpunkt der Box
	 */
	@NotNull
	public final Vector3 center = Vector3.createNew();

	/**
	 * Der (absolute) Maximalvektor der Box.
	 * 
	 * <h3>Hinweis zu den Werten des Vektors</h3>
	 * Die Werte des Vektors müssen immer positiv sein. Um dies sicherzustellen, sollten Veränderungen
	 * an diesem Vektor ausschließlich über die Methoden {@link #setExtent(Vector3)} oder {@link #setExtent(float, float, float)}
	 * erfolgen. 
	 * <p/>
	 * Ist ein manuelles Setzen der Werte zwingend erforderlich, muss im Anschluss auf dem Vektor {@link de.widemeadows.projectcore.math.Vector3#makeAbsolute()}
	 * aufgerufen werden.
	 */
	@NotNull
	public final Vector3 extent = Vector3.createNew();

	/**
	 * Erzeugt eine neue Instanz der {@link AxisAlignedBox}-Klasse
	 */
	private AxisAlignedBox() {
		this.extent.set(0.5f, 0.5f, 0.5f);
	}

	/**
	 * Erzeugt eine neue Instanz der {@link AxisAlignedBox}-Klasse
	 *
	 * @param center Der Mittelpunkt der Box
	 * @param extent Der Maximalvektor der Box
	 */
	private AxisAlignedBox(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
		this.center.set(center);
		this.extent.set(extent).makeAbsolute();
	}

	/**
	 * Setzt Mittelpunkt und Dimensionen der Box
	 *
	 * @param other Die zu kopierende Box
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox set(@NotNull final AxisAlignedBox other) {
		return set(other.center, other.extent);
	}

	/**
	 * Setzt Mittelpunkt und Dimensionen der Box
	 *
	 * @param center Der Mittelpunkt
	 * @param extent Die Dimensionen
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox set(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
		setCenter(center);
		setExtent(extent);
		return this;
	}

	/**
	 * Setzt Mittelpunkt und Dimensionen der Box
	 *
	 * @param centerX Die Position (X-Komponente)
	 * @param centerY Die Position (Y-Komponente)
	 * @param centerZ Die Position (Z-Komponente)
	 * @param extentX Der Maximalvektor (X-Komponente)
	 * @param extentY Der Maximalvektor (Y-Komponente)
	 * @param extentZ Der Maximalvektor (Z-Komponente)
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox set(final float centerX, final float centerY, final float centerZ, final float extentX, final float extentY, final float extentZ) {
		setCenter(centerX, centerY, centerZ);
		setExtent(extentX, extentY, extentZ);
		return this;
	}

	/**
	 * Setzt die Dimensionen der Box

	 * @param extent Der Maximalvektor
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox setExtent(@NotNull final Vector3 extent) {
		this.extent.set(extent).makeAbsolute();
		return this;
	}

	/**
	 * Setzt die Dimensionen der Box
	 *
	 * @param x Der Maximalvektor (X-Komponente)
	 * @param y Der Maximalvektor (Y-Komponente)
	 * @param z Der Maximalvektor (Z-Komponente)
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox setExtent(final float x, final float y, final float z) {
		this.extent.set(x, y, z).makeAbsolute();
		return this;
	}

	/**
	 * Setzt die Position der Box
	 *
	 * @param center Die Position
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox setCenter(@NotNull final Vector3 center) {
		this.center.set(center);
		return this;
	}

	/**
	 * Setzt die Position der Box
	 *
	 * @param x Die Position (X-Komponente)
	 * @param y Die Position (Y-Komponente)
	 * @param z Die Position (Z-Komponente)
	 * @return Diese Instanz für method chaining
	 */
	public final AxisAlignedBox setCenter(final float x, final float y, final float z) {
		this.center.set(x, y, z);
		return this;
	}

	/**
	 * Liefert den Punkt mit dem angegebenen Index
	 * @param point Der Punkt
	 * @return Der Punkt (cached)
	 */
	@ReturnsCachedValue @NotNull
	public final Vector3 getCornerPoint(@NotNull final BoxPoint point) {
		assert extent.x >= 0;
		assert extent.y >= 0;
		assert extent.z >= 0;

		final int id = point.pointId;
		return Vector3.createNew(
				((id & 1) == 0) ? (center.x - extent.x) : (center.x + extent.x),
				((id & 2) == 0) ? (center.y - extent.y) : (center.y + extent.y),
				((id & 4) == 0) ? (center.z + extent.z) : (center.z - extent.z) // NOTE: OpenGL macht's andersrum!
				);
	}

	/**
	 * Berechnet die Fläche der Box
	 * @return Die Fläche
	 */
	public final float calculateArea() {
		// return (2*extent.x) * (2*extent.y) * (2*extent.z);
		return 8 * extent.x * extent.y * extent.z;
	}

	/**
	 * Ermittelt, ob ein Punkt auf oder in der Box liegt
	 * @param vector Der zu prüfende Vektor
	 * @return <code>true</code>, wenn der Punkt auf oder in der Box liegt
	 */
	public final boolean intersects(@NotNull final Vector3 vector) {
		return intersects(vector.x, vector.y, vector.z);
	}

	/**
	 * Ermittelt, ob ein Punkt auf oder in der Box liegt
	 *
	 * @param x Der zu prüfende Vektor (X-Komponente)
	 * @param y Der zu prüfende Vektor (Y-Komponente)
	 * @param z Der zu prüfende Vektor (Z-Komponente)
	 * @return <code>true</code>, wenn der Punkt auf oder in der Box liegt
	 */
	public final boolean intersects(float x, float y, float z) {
		assert extent.x >= 0;
		assert extent.y >= 0;
		assert extent.z >= 0;

		// Koordinaten in Box-Space transformieren
		x -= center.x;
		y -= center.y;
		z -= center.z;

		// Punkte beziehen
		final float maxX = extent.x;
		final float maxY = extent.y;
		final float maxZ = extent.z;
		final float minX = -maxX;
		final float minY = -maxY;
		final float minZ = -maxZ;

		// Test durchführen
		return  x >= minX && x <= maxX &&
				y >= minY && y <= maxY &&
				z >= minZ && z <= maxZ;
	}

	/**
	 * Überprüft, ob ein Strahl die Box schneidet
	 *
	 * @param ray Der Strahl
	 * @param nearBound Der nähste, gültige Punkt
	 * @param farBound Der weiteste, gültige Punkt
	 * @return <code>true</code>, wenn der Strahl die Box schneidet
	 */
	public final boolean intersects(@NotNull final Ray3 ray, final float nearBound, final float farBound) {
		return !Float.isNaN(getIntersectionF(ray, nearBound, farBound));
	}

	/**
	 * Überprüft, ob ein Strahl die Box schneidet und liefert den Schnittpunkt
	 * in Form eines Skalars t, so dass <code>ray.getPoint(t)}</code> den Vektor ergibt.
	 *
	 * @param ray       Der Strahl
	 * @return <code>{@link Float#NaN}</code>, wenn der Strahl die Box nicht schneidet, ansonsten die Distanz vom Ursprung des Strahles
	 */
	public final float getIntersectionF(@NotNull final Ray3 ray) {

		float txmin, txmax, tymin, tymax, tzmin, tzmax;
		final float divX = ray.invDirection.x;
		final float divY = ray.invDirection.y;
		final float divZ = ray.invDirection.z;

		if (divX >= 0) {
			txmin = (center.x - extent.x - ray.origin.x) * divX;
			txmax = (center.x + extent.x - ray.origin.x) * divX;
		} else {
			txmin = (center.x + extent.x - ray.origin.x) * divX;
			txmax = (center.x - extent.x - ray.origin.x) * divX;
		}
		if (divY >= 0) {
			tymin = (center.y - extent.y - ray.origin.y) * divY;
			tymax = (center.y + extent.y - ray.origin.y) * divY;
		} else {
			tymin = (center.y + extent.y - ray.origin.y) * divY;
			tymax = (center.y - extent.y - ray.origin.y) * divY;
		}
		if ((txmin > tymax) || (tymin > txmax)) return Float.NaN;
		if (tymin > txmin) txmin = tymin;
		if (tymax < txmax) txmax = tymax;

		if (divZ >= 0) {
			tzmin = (center.z - extent.z - ray.origin.z) * divZ;
			tzmax = (center.z + extent.z - ray.origin.z) * divZ;
		} else {
			tzmin = (center.z + extent.z - ray.origin.z) * divZ;
			tzmax = (center.z - extent.z - ray.origin.z) * divZ;
		}

		if ((txmin > tzmax) || (tzmin > txmax)) return Float.NaN;
		if (tzmin > txmin) txmin = tzmin;
		return txmin;
	}
	
	/**
	 * Überprüft, ob ein Strahl die Box schneidet und liefert den Schnittpunkt
	 * in Form eines Skalars t, so dass <code>ray.getPoint(t)}</code> den Vektor ergibt.
	 *
	 * @param ray       Der Strahl
	 * @param nearBound Der nähste, gültige Punkt
	 * @param farBound  Der weiteste, gültige Punkt
	 * @return <code>{@link Float#NaN}</code>, wenn der Strahl die Box nicht innerhalb der Range schneidet, ansonsten die Distanz vom Ursprung des Strahles
	 */
	public final float getIntersectionF(@NotNull final Ray3 ray, final float nearBound, final float farBound) {

		float txmin, txmax, tymin, tymax, tzmin, tzmax;
		final float divX = ray.invDirection.x;
		final float divY = ray.invDirection.y;
		final float divZ = ray.invDirection.z;

		if (divX >= 0) {
			txmin = (center.x - extent.x - ray.origin.x) * divX;
			txmax = (center.x + extent.x - ray.origin.x) * divX;
		} else {
			txmin = (center.x + extent.x - ray.origin.x) * divX;
			txmax = (center.x - extent.x - ray.origin.x) * divX;
		}
		if (divY >= 0) {
			tymin = (center.y - extent.y - ray.origin.y) * divY;
			tymax = (center.y + extent.y - ray.origin.y) * divY;
		} else {
			tymin = (center.y + extent.y - ray.origin.y) * divY;
			tymax = (center.y - extent.y - ray.origin.y) * divY;
		}
		if ((txmin > tymax) || (tymin > txmax)) return Float.NaN;
		if (tymin > txmin) txmin = tymin;
		if (tymax < txmax) txmax = tymax;

		if (divZ >= 0) {
			tzmin = (center.z - extent.z - ray.origin.z) * divZ;
			tzmax = (center.z + extent.z - ray.origin.z) * divZ;
		} else {
			tzmin = (center.z + extent.z - ray.origin.z) * divZ;
			tzmax = (center.z - extent.z - ray.origin.z) * divZ;
		}

		if ((txmin > tzmax) || (tzmin > txmax)) return Float.NaN;
		if (tzmin > txmin) txmin = tzmin;
		//if (tzmax < txmax) txmax = tzmax;
		//return ((txmin >= nearBound) && (txmax <= farBound)) ? txmin : Float.NaN;
		return ((txmin >= nearBound) && (txmin <= farBound)) ? txmin : Float.NaN;
	}

	/**
	 * Überprüft, ob ein Strahl die Box schneidet und liefert den Schnittpunkt
	 * in Form eines Skalars t, so dass <code>ray.getPoint(t)}</code> den Vektor ergibt.
	 *
	 * @param ray       Der Strahl
	 * @param nearBound Der nähste, gültige Punkt
	 * @param farBound  Der weiteste, gültige Punkt
	 * @return <code>null</code>, wenn der Strahl die Box nicht innerhalb der Range schneidet, ansonsten der Punkt
	 */
	@Nullable @ReturnsCachedValue
	public final Vector3 getIntersectionV(@NotNull final Ray3 ray, final float nearBound, final float farBound) {
		final float t = getIntersectionF(ray, nearBound, farBound);
		return Float.isNaN(t) ? null : ray.getPoint(t);
	}

	/**
	 * Überprüft, ob ein Strahl die Box schneidet und liefert den Schnittpunkt
	 * in Form eines Vektors.
	 *
	 * @param ray       Der Strahl
	 * @return <code>null</code>, wenn der Strahl die Box nicht schneidet, ansonsten der Punkt
	 */
	@Nullable
	@ReturnsCachedValue
	public final Vector3 getIntersectionV(@NotNull final Ray3 ray) {
		final float t = getIntersectionF(ray);
		return Float.isNaN(t) ? null : ray.getPoint(t);
	}

    /**
     * Transformiert die Box mittels der Matrix
     *
     * @param transformation Die anzuwendende Transformation
     */
    @NotNull @ReturnsCachedValue
    public AxisAlignedBox transform(@NotNull final Matrix4 transformation) {
        AxisAlignedBox box = AxisAlignedBox.createNew(this);
        box.transformInPlace(transformation);
        return box;
    }

    /**
     * Transformiert die Box mittels der Matrix
     *
     * @param transformation Die anzuwendende Transformation
     */
    public void transformInPlace(@NotNull final Matrix4 transformation) {
        transformation.transformPointInPlace(center);
        transformation.transformVectorInPlace(extent);
    }
}
