package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.ObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Strahl im 3D-Raum, bestehend aus Ursprung und Richtung
 * @see RayFactory
 */
public final class Ray3 {

	/**
	 * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
	 */
	public static final ObjectCache<Ray3> Recycling = new ObjectCache<Ray3>(new ObjectFactory<Ray3>() {
		@NotNull
		@Override
		public Ray3 createNew() {
			return new Ray3();
		}
	});

	/**
	 * Erzeugt eine neue Ray-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand des Rays kann korrupt sein!
	 * </p>
	 *
	 * @param originX Der Ursprung (X-Komponente)
	 * @param originY Der Ursprung (Y-Komponente)
	 * @param originZ Der Ursprung (Z-Komponente)
	 * @param directionX Die Richtung (X-Komponente)
	 * @param directionY Die Richtung (Y-Komponente)
	 * @param directionZ Die Richtung (Z-Komponente)
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Recycling
	 */
	@NotNull
	public static Ray3 createNew(final float originX, final float originY, final float originZ,
	                             final float directionX, final float directionY, final float directionZ) {
		return Recycling.getOrCreate().set(originX, originY, originZ, directionX, directionY, directionZ);
	}

	/**
	 * Erzeugt eine neue {@link Ray3}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand kann korrupt sein!
	 * </p>
	 *
	 * @param origin Der Ursprung
	 * @param direction Die Richtung
	 * @return Der neue oder aufbereitete Ray
	 * @see #Recycling
	 */
	public static Ray3 createNew(@NotNull final Vector3 origin, @NotNull final Vector3 direction) {
		return Recycling.getOrCreate().set(origin, direction);
	}

	/**
	 * Erzeugt eine neue {@link Ray3}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand kann korrupt sein!
	 * </p>
	 *
	 * @param other    Der zu kopierende Ray
	 * @return Der neue oder aufbereitete Ray
	 * @see #Recycling
	 */
	public static Ray3 createNew(@NotNull final Ray3 other) {
		return Recycling.getOrCreate().set(other);
	}

	/**
	 * Erzeugt eine neue {@link Ray3}-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand kann korrupt sein!
	 * </p>
	 *
	 * @return Der neue oder aufbereitete Ray
	 * @see #Recycling
	 */
	@NotNull
	public static Ray3 createNew() {
		return Recycling.getOrCreate();
	}

	/**
	 * Recyclet einen Ray
	 *
	 * @param box Der zu recyclende Ray
	 * @see #Recycling
	 * @see AxisAlignedBox#recycle()
	 */
	public static void recycle(@NotNull final Ray3 box) {
		Recycling.registerElement(box);
	}

	/**
	 * Registriert diesen Ray für das spätere Recycling
	 *
	 * @see #Recycling
	 * @see Vector3#recycle(Vector3)
	 */
	public void recycle() {
		Recycling.registerElement(this);
	}

	/**
	 * Der Ursprung des Strahls
	 */
	@NotNull
	public final Vector3 origin = Vector3.createNew();

	/**
	 * Die Richtung des Strahls
	 */
	@NotNull
	public final Vector3 direction = Vector3.createNew(0.57735f, 0.57735f, 0.57735f);

	/**
	 * Normalokonstruktor
	 */
	private Ray3() {}

	/**
	 * Setzt Ursprung und Richtung
	 * @param origin Der Ursprung
	 * @param direction Die Richtung
	 */
	private Ray3(@NotNull final Vector3 origin, @NotNull final Vector3 direction) {
		set(origin, direction);
	}

	/**
	 * Setzt den Ursprung des Strahls
	 * @param origin Der Ursprung
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 setOrigin(@NotNull final Vector3 origin) {
		this.origin.set(origin);
		return this;
	}

	/**
	 * Setzt den Ursprung des Strahls
	 *
	 * @param originX Der Ursprung (X-Komponente)
	 * @param originY Der Ursprung (Y-Komponente)
	 * @param originZ Der Ursprung (Z-Komponente)
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 setOrigin(final float originX, final float originY, final float originZ) {
		this.origin.set(originX, originY, originZ);
		return this;
	}

	/**
	 * Setzt die Richtung des Strahls
	 *
	 * @param direction Die Richtung
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 setDirection(@NotNull final Vector3 direction) {
		this.direction.set(direction).normalize();
		return this;
	}

	/**
	 * Setzt die Richtung des Strahls
	 *
	 * @param directionX Die Richtung (X-Komponente)
	 * @param directionY Die Richtung (Y-Komponente)
	 * @param directionZ Die Richtung (Z-Komponente)
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 setDirection(final float directionX, final float directionY, final float directionZ) {
		this.direction.set(directionX, directionY, directionZ).normalize();
		return this;
	}

	/**
	 * Kopiert einen Strahl
	 *
	 * @param other    Der zu kopierende Ray
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 set(@NotNull final Ray3 other) {
		return set(other.origin, other.direction);
	}

	/**
	 * Setzt Ursprung und Richtung des Strahls
	 * @param origin Der Ursprung
	 * @param direction Die Richtung
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 set(@NotNull final Vector3 origin, @NotNull final Vector3 direction) {
		setOrigin(origin.x, origin.y, origin.z);
		setDirection(direction.x, direction.y, direction.z);
		return this;
	}

	/**
	 * Setzt Ursprung und Richtung des Strahls
	 *
	 * @param originX Der Ursprung (X-Komponente)
	 * @param originY Der Ursprung (Y-Komponente)
	 * @param originZ Der Ursprung (Z-Komponente)
	 * @param directionX Die Richtung (X-Komponente)
	 * @param directionY Die Richtung (Y-Komponente)
	 * @param directionZ Die Richtung (Z-Komponente)
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 set(
			final float originX, final float originY, final float originZ,
			final float directionX, final float directionY, final float directionZ
	) {
		setOrigin(originX, originY, originZ);
		setDirection(directionX, directionY, directionZ);
		return this;
	}
}
