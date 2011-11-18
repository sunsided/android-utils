package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

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

	/**
	 * Liefert den Vektor aus den maximalen Komponenten zweier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 max(@NotNull final Vector3 a, @NotNull final Vector3 b) {
		float x = Math.max(a.x, b.x);
		float y = Math.max(a.y, b.y);
		float z = Math.max(a.z, b.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den maximalen Komponenten dreier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 max(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
		float x = Math.max(Math.max(a.x, b.x), c.x);
		float y = Math.max(Math.max(a.y, b.y), c.y);
		float z = Math.max(Math.max(a.z, b.z), c.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten von sechs Eingabevektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @param d Vierter Vektor
	 * @param e Fünfter Vektor
	 * @param f Sechster Vektor
	 * @return Der Maximalvektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 max(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c, @NotNull final Vector3 d, @NotNull final Vector3 e, @NotNull final Vector3 f) {
		float x = Math.max(Math.max(Math.max(Math.max(Math.max(a.x, b.x), c.x), d.x), e.x), f.x);
		float y = Math.max(Math.max(Math.max(Math.max(Math.max(a.y, b.y), c.y), d.y), e.y), f.y);
		float z = Math.max(Math.max(Math.max(Math.max(Math.max(a.z, b.z), c.z), d.z), e.z), f.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten zweier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 min(@NotNull final Vector3 a, @NotNull final Vector3 b) {
		float x = Math.min(a.x, b.x);
		float y = Math.min(a.y, b.y);
		float z = Math.min(a.z, b.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten dreier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 min(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c) {
		float x = Math.min(Math.min(a.x, b.x), c.x);
		float y = Math.min(Math.min(a.y, b.y), c.y);
		float z = Math.min(Math.min(a.z, b.z), c.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten von sechs Eingabevektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @param d Vierter Vektor
	 * @param e Fünfter Vektor
	 * @param f Sechster Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 min(@NotNull final Vector3 a, @NotNull final Vector3 b, @NotNull final Vector3 c, @NotNull final Vector3 d, @NotNull final Vector3 e, @NotNull final Vector3 f) {
		float x = Math.min(Math.min(Math.min(Math.min(Math.min(a.x, b.x), c.x), d.x), e.x), f.x);
		float y = Math.min(Math.min(Math.min(Math.min(Math.min(a.y, b.y), c.y), d.y), e.y), f.y);
		float z = Math.min(Math.min(Math.min(Math.min(Math.min(a.z, b.z), c.z), d.z), e.z), f.z);
		return Vector3.createNew(x, y, z);
	}
}
