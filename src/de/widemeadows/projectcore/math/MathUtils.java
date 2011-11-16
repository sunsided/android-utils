package de.widemeadows.projectcore.math;

/**
 * Mathematische Helfermethoden
 */
public class MathUtils {

	/**
	 * Default-Epsilonwert für {@link Float}
	 */
	public static final float DEFAULT_EPSILON = 0.00001f;

	/**
	 * Wandelt Radians in Grad um
	 */
	public static final float RAD_TO_DEG = (float) (180f / Math.PI);

	/**
	 * Wandelt Grad in Radians um
	 */
	public static final float DEG_TO_RAD = (float)(Math.PI / 180f);

	/**
	 * Verstecker Konstruktor
	 */
	private MathUtils() {}

	/**
	 * Überprüft, ob zwei Floatwerte identisch unter Beachtung eines Deltawertes sind
	 *
	 * @param value Der zu testende Wert
	 * @param referenceValue Der Vergleichswert
	 * @param delta Der Deltawert >= 0
	 * @return <code>true</code>, wenn beide Werte identisch sind
	 */
	public static boolean equals(float value, float referenceValue, float delta) {
		assert delta >= 0;
		return Math.abs(value - referenceValue) <= delta;
	}

	/**
	 * Überprüft, ob ein Floatwerte identisch mit null ist
	 *
	 * @param value          Der zu testende Wert
	 * @param delta          Der Deltawert >= 0
	 * @return <code>true</code>, wenn beide Werte identisch sind
	 */
	public static boolean isZero(float value, float delta) {
		assert delta >= 0;
		return Math.abs(value) <= delta;
	}

	/**
	 * Überprüft, ob ein Floatwerte größer als null ist
	 *
	 * @param value Der zu testende Wert
	 * @param delta Der Deltawert >= 0
	 * @return <code>true</code>, wenn beide Werte identisch sind
	 */
	public static boolean isLargerZero(float value, float delta) {
		assert delta >= 0;
		return value > delta;
	}

	/**
	 * Überprüft, ob ein Floatwerte kleiner als null ist
	 *
	 * @param value Der zu testende Wert
	 * @param delta Der Deltawert >= 0
	 * @return <code>true</code>, wenn beide Werte identisch sind
	 */
	public static boolean isSmallerZero(float value, float delta) {
		assert delta >= 0;
		return value < -delta;
	}

	/**
	 * Wandelt Radians in Grad um
	 * @param radians Der Winkel in Radians
	 * @return Der Winkel in Grad
	 */
	public static float rad2Deg(float radians) {
		return RAD_TO_DEG * radians;
	}

	/**
	 * Wandelt Grad in Radians um
	 *
	 * @param degree Der Winkel in Grad
	 * @return Der Winkel in Radians
	 */
	public static float deg2Rad(float degree) {
		return DEG_TO_RAD * degree;
	}
}
