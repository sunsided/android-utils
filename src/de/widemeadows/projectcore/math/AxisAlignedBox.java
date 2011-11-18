package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.ObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;
import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Symmetrische Box
 */
public final class AxisAlignedBox {

	/**
	 * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
	 */
	public static final ObjectCache<AxisAlignedBox> Recycling = new ObjectCache<AxisAlignedBox>(new ObjectFactory<AxisAlignedBox>() {
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
	 * @see #Recycling
	 */
	@NotNull
	public static AxisAlignedBox createNew(final float centerX, final float centerY, final float centerZ,
	                            final float extentX, final float extentY, final float extentZ) {
		return Recycling.getOrCreate().set(centerX, centerY, centerZ, extentX, extentY, extentZ);
	}

	/**
	 * Erzeugt eine neue {@link AxisAlignedBox}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
	 * @param other Die zu kopierende Box
	 * @return Die neue oder aufbereitete Box
	 * @see #Recycling
	 */
	public static AxisAlignedBox createNew(@NotNull final AxisAlignedBox other) {
		return Recycling.getOrCreate().set(other);
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
	 * @see #Recycling
	 */
	public static AxisAlignedBox createNew(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
		return Recycling.getOrCreate().set(center, extent);
	}

	/**
	 * Erzeugt eine neue {@link AxisAlignedBox}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Box kann korrupt sein!
	 * </p>
	 *
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Recycling
	 */
	@NotNull
	public static AxisAlignedBox createNew() {
		return Recycling.getOrCreate();
	}

	/**
	 * Recyclet eine Box
	 *
	 * @param box Die zu recyclende Box
	 * @see #Recycling
	 * @see AxisAlignedBox#recycle()
	 */
	public static void recycle(@NotNull final AxisAlignedBox box) {
		Recycling.registerElement(box);
	}

	/**
	 * Registriert diesen Vektor für das spätere Recycling
	 *
	 * @see #Recycling
	 * @see Vector3#recycle(Vector3)
	 */
	public void recycle() {
		Recycling.registerElement(this);
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

		final Vector3 min = center.sub(extent);
		final Vector3 max = center.add(extent);

		float txmin, txmax, tymin, tymax, tzmin, tzmax;
		final float divX = ray.invDirection.x;
		final float divY = ray.invDirection.y;
		final float divZ = ray.invDirection.z;

		if (divX >= 0) {
			txmin = (min.x - ray.origin.x) * divX;
			txmax = (max.x - ray.origin.x) * divX;
		} else {
			txmin = (max.x - ray.origin.x) * divX;
			txmax = (min.x - ray.origin.x) * divX;
		}
		if (divY >= 0) {
			tymin = (min.y - ray.origin.y) * divY;
			tymax = (max.y - ray.origin.y) * divY;
		} else {
			tymin = (max.y - ray.origin.y) * divY;
			tymax = (min.y - ray.origin.y) * divY;
		}
		if ((txmin > tymax) || (tymin > txmax)) {
			max.recycle();
			min.recycle();
			return false;
		}
		if (tymin > txmin) txmin = tymin;
		if (tymax < txmax) txmax = tymax;

		if (divZ >= 0) {
			tzmin = (min.z - ray.origin.z) * divZ;
			tzmax = (max.z - ray.origin.z) * divZ;
		} else {
			tzmin = (max.z - ray.origin.z) * divZ;
			tzmax = (min.z - ray.origin.z) * divZ;
		}

		max.recycle();
		min.recycle();

		if ((txmin > tzmax) || (tzmin > txmax)) return false;
		if (tzmin > txmin) txmin = tzmin;
		if (tzmax < txmax) txmax = tzmax;
		return ((txmin >= nearBound) && (txmax <= farBound));
	}


	/**
	 * Überprüft, ob ein Strahl die Box schneidet
	 *
	 * @param ray       Der Strahl
	 * @param nearBound Der nähste, gültige Punkt
	 * @param farBound  Der weiteste, gültige Punkt
	 * @return <code>true</code>, wenn der Strahl die Box schneidet
	 */
	public final boolean intersectsEx(@NotNull final Ray3 ray, final float nearBound, final float farBound) {
		float txmin, txmax, tymin, tymax, tzmin, tzmax, swap;

		// final Vector3 min = center.sub(extent);
		// final Vector3 max = center.add(extent);

		final float divX = ray.invDirection.x;
		final float divY = ray.invDirection.y;
		final float divZ = ray.invDirection.z;

		txmin = (center.x - extent.x - ray.origin.x) * divX;
		txmax = (center.x + extent.x - ray.origin.x) * divX;
		tymin = (center.y - extent.y - ray.origin.y) * divY;
		tymax = (center.y + extent.y - ray.origin.y) * divY;
		tzmin = (center.z - extent.z - ray.origin.z) * divZ;
		tzmax = (center.z + extent.z - ray.origin.z) * divZ;

		if (divX < 0) {
			int flt1 = Float.floatToRawIntBits(txmin);
			int flt2 = Float.floatToRawIntBits(txmax);

			flt1 = flt1 ^ flt2;
			flt2 = flt1 ^ flt2;
			flt1 = flt1 ^ flt2;

			txmin = Float.intBitsToFloat(flt1);
			txmax = Float.intBitsToFloat(flt2);
		}

		if (divY < 0) {
			int flt1 = Float.floatToRawIntBits(tymin);
			int flt2 = Float.floatToRawIntBits(tymax);

			flt1 = flt1 ^ flt2;
			flt2 = flt1 ^ flt2;
			flt1 = flt1 ^ flt2;

			tymin = Float.intBitsToFloat(flt1);
			tymax = Float.intBitsToFloat(flt2);
		}

		if (divZ < 0) {
			int flt1 = Float.floatToRawIntBits(tzmin);
			int flt2 = Float.floatToRawIntBits(tzmax);

			flt1 = flt1 ^ flt2;
			flt2 = flt1 ^ flt2;
			flt1 = flt1 ^ flt2;

			tzmin = Float.intBitsToFloat(flt1);
			tzmax = Float.intBitsToFloat(flt2);
		}

		if ((txmin > tymax) || (tymin > txmax)) return false;
		if (tymin > txmin) txmin = tymin;
		if (tymax < txmax) txmax = tymax;

		if ((txmin > tzmax) || (tzmin > txmax)) return false;
		if (tzmin > txmin) txmin = tzmin;
		if (tzmax < txmax) txmax = tzmax;
		return ((txmin >= nearBound) && (txmax <= farBound));
	}
}
