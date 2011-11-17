package de.widemeadows.projectcore.math;

/**
 * Eckpunkt einer Box
 */
public enum BoxPoint {

	/**
	 * Der Punkt vorne unten links (000)
	 */
	FrontBottomLeft(0),

	/**
	 * Der Punkt vorne unten rechts (001)
	 */
	FrontBottomRight(1),

	/**
	 * Der Punkt vorne oben links (010)
	 */
	FrontTopLeft(2),

	/**
	 * Der Punkt vorne oben rechts (011)
	 */
	FrontTopRight(3),

	/**
	 * Der Punkt hinten unten links (100)
	 */
	BackBottomLeft(4),

	/**
	 * Der Punkt hinten unten rechts (101)
	 */
	BackBottomRight(5),

	/**
	 * Der Punkt hinten oben links (110)
	 */
	BackTopLeft(6),

	/**
	 * Der Punkt hinten oben rechts (111)
	 */
	BackTopRight(7);

	/**
	 * Die ID des Punktes
	 */
	public final int pointId;

	/**
	 * Setzt die Punkt-ID
	 *
	 * @param pointId Die Punkt-ID
	 */
	private BoxPoint(int pointId) {
		this.pointId = pointId;
	}

}
