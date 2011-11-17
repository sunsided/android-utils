package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.ObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;
import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Symmetrische Box
 */
public final class Box {

	/** Der Punkt vorne unten links (000) */
	public static final int FRONT_BOTTOM_LEFT = 0;

	/** Der Punkt vorne unten rechts (001) */
	public static final int FRONT_BOTTOM_RIGHT = 1;

	/** Der Punkt vorne oben links (010) */
	public static final int FRONT_TOP_LEFT = 2;

	/** Der Punkt vorne oben rechts (011) */
	public static final int FRONT_TOP_RIGHT = 3;

	/** Der Punkt hinten unten links (100) */
	public static final int BACK_BOTTOM_LEFT = 4;

	/** Der Punkt hinten unten rechts (101) */
	public static final int BACK_BOTTOM_RIGHT = 5;

	/** Der Punkt hinten oben links (110) */
	public static final int BACK_TOP_LEFT = 6;

	/** Der Punkt hinten oben rechts (111) */
	public static final int BACK_TOP_RIGHT = 7;
	
	/**
	 * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
	 */
	public static final ObjectCache<Box> Recycling = new ObjectCache<Box>(new ObjectFactory<Box>() {
		@NotNull
		@Override
		public Box createNew() {
			return new Box();
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
	public static Box createNew(final float centerX, final float centerY, final float centerZ,
	                            final float extentX, final float extentY, final float extentZ) {
		return Recycling.getOrCreate().set(centerX, centerY, centerZ, extentX, extentY, extentZ);
	}

	/**
	 * Erzeugt eine neue {@link Box}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
	 * @param other Die zu kopierende Box
	 * @return Die neue oder aufbereitete Box
	 * @see #Recycling
	 */
	public static Box createNew(@NotNull final Box other) {
		return Recycling.getOrCreate().set(other);
	}

	/**
	 * Erzeugt eine neue {@link Box}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
	 * @param center Der Mittelpunkt der Box
	 * @param extent Der Maximalvektor der Box   
	 * @return Die neue oder aufbereitete Box
	 * @see #Recycling
	 */
	public static Box createNew(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
		return Recycling.getOrCreate().set(center, extent);
	}

	/**
	 * Erzeugt eine neue {@link Box}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Box kann korrupt sein!
	 * </p>
	 *
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Recycling
	 */
	@NotNull
	public static Box createNew() {
		return Recycling.getOrCreate();
	}

	/**
	 * Recyclet eine Box
	 *
	 * @param box Die zu recyclende Box
	 * @see #Recycling
	 * @see Box#recycle()
	 */
	public static void recycle(@NotNull final Box box) {
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
	 * Erzeugt eine neue Instanz der {@link Box}-Klasse
	 */
	private Box() {
		this.extent.set(0.5f, 0.5f, 0.5f);
	}

	/**
	 * Erzeugt eine neue Instanz der {@link Box}-Klasse
	 *
	 * @param center Der Mittelpunkt der Box
	 * @param extent Der Maximalvektor der Box
	 */
	private Box(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
		this.center.set(center);
		this.extent.set(extent).makeAbsolute();
	}

	/**
	 * Setzt Mittelpunkt und Dimensionen der Box
	 *
	 * @param other Die zu kopierende Box
	 * @return Diese Instanz für method chaining
	 */
	public final Box set(@NotNull final Box other) {
		return set(other.center, other.extent);
	}

	/**
	 * Setzt Mittelpunkt und Dimensionen der Box
	 *
	 * @param center Der Mittelpunkt
	 * @param extent Die Dimensionen
	 * @return Diese Instanz für method chaining
	 */
	public final Box set(@NotNull final Vector3 center, @NotNull final Vector3 extent) {
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
	public final Box set(final float centerX, final float centerY, final float centerZ, final float extentX, final float extentY, final float extentZ) {
		setCenter(centerX, centerY, centerZ);
		setExtent(extentX, extentY, extentZ);
		return this;
	}

	/**
	 * Setzt die Dimensionen der Box

	 * @param extent Der Maximalvektor
	 * @return Diese Instanz für method chaining
	 */
	public final Box setExtent(@NotNull final Vector3 extent) {
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
	public final Box setExtent(final float x, final float y, final float z) {
		this.extent.set(x, y, z).makeAbsolute();
		return this;
	}

	/**
	 * Setzt die Position der Box
	 *
	 * @param center Die Position
	 * @return Diese Instanz für method chaining
	 */
	public final Box setCenter(@NotNull final Vector3 center) {
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
	public final Box setCenter(final float x, final float y, final float z) {
		this.center.set(x, y, z);
		return this;
	}

	/**
	 * Liefert den Punkt mit dem angegebenen Index
	 * @param pointIndex Der Punktindex
	 * @return Der Punkt (cached)
	 */
	@ReturnsCachedValue @NotNull
	public final Vector3 getCornerPoint(int pointIndex) {
		assert pointIndex >= 0;
		assert pointIndex <= 7;
		assert extent.x >= 0;
		assert extent.y >= 0;
		assert extent.z >= 0;

		return Vector3.createNew(
				((pointIndex & 1) == 1) ? (center.x - extent.x) : (center.x + extent.x),
				((pointIndex & 2) == 2) ? (center.y - extent.y) : (center.y + extent.y),
				((pointIndex & 4) == 4) ? (center.z - extent.z) : (center.z + extent.z)
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
	public final boolean intersects(final float x, final float y, final float z) {
		assert extent.x >= 0;
		assert extent.y >= 0;
		assert extent.z >= 0;

		final float minX = center.x - extent.x;
		final float minY = center.y - extent.y;
		final float minZ = center.z - extent.z;
		final float maxX = center.x + extent.x;
		final float maxY = center.y + extent.y;
		final float maxZ = center.z + extent.z;

		return  x >= minX && x <= maxX &&
				y >= minY && y <= maxY &&
				z >= minZ && z <= maxZ;
	}
}
