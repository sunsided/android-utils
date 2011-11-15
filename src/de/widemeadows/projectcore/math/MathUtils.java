package de.widemeadows.projectcore.math;

/**
 * Mathematische Helfermethoden
 */
public class MathUtils {

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
}
