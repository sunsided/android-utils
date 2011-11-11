package math;

import org.jetbrains.annotations.NotNull;

/**
 * Interface, welches Methoden zum Testen des Umfangs des Objektes bereitstellt
 */
public interface IBoundsTestable {

	/**
	 * Überprüft, ob sich ein Punkt innerhalb des Elementes befindet
	 * @param point Die zu testende Position
	 * @return <code>true</code>, wenn sich der Punkt innerhalb des Elementes befindet
	 */
	boolean containsPoint(@NotNull Vector3 point);

}
