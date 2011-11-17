package de.widemeadows.projectcore.math;

import org.jetbrains.annotations.NotNull;

/**
 * Strahl
 */
public final class Ray3 {

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
	 * Setzt die Richtung des Strahls
	 *
	 * @param direction Die Richtung
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 setDirection(@NotNull final Vector3 direction) {
		this.direction.set(direction);
		return this;
	}

	/**
	 * Setzt Ursprung und Richtung des Strahls
	 * @param origin Der Ursprung
	 * @param direction Die Richtung
	 * @return Dieselbe Instanz für method chaining
	 */
	@NotNull
	public Ray3 set(@NotNull final Vector3 origin, @NotNull final Vector3 direction) {
		this.origin.set(origin);
		this.direction.set(direction);
		return this;
	}
}
