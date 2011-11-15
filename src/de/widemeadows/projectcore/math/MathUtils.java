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
	 * @param value Der zu testende Wert
	 * @param referenceValue Der Vergleichswert
	 * @param delta Der Deltawert
	 * @return <code>true</code>, wenn beide Werte identisch sind
	 */
	public static boolean equals(float value, float referenceValue, float delta) {
		return Math.abs(value - referenceValue) <= delta;
	}
}
