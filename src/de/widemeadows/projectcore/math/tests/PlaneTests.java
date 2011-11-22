package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.Plane3;
import de.widemeadows.projectcore.math.Vector3;
import org.junit.Test;

import static de.widemeadows.projectcore.math.MathUtils.DEFAULT_EPSILON;
import static junit.framework.Assert.assertEquals;

/**
 * Tests für {@link de.widemeadows.projectcore.math.Plane3}
 */
public class PlaneTests {

	/**
	 * Prüft die Distanzberechnung
	 */
	@Test
	public void distance() {

		// Nullpunkt testen

		Plane3 plane = Plane3.createNew(Vector3.YAXIS, 0);

		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(0, 0, 0)), DEFAULT_EPSILON);
		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(-1, 0, 0)), DEFAULT_EPSILON);
		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(1000, 0, 1000)), DEFAULT_EPSILON);

		assertEquals(1, plane.getDistanceFromPoint(Vector3.createNew(0, 1, 0)), DEFAULT_EPSILON);
		assertEquals(1, plane.getDistanceFromPoint(Vector3.createNew(-1, 1, 0)), DEFAULT_EPSILON);
		assertEquals(1, plane.getDistanceFromPoint(Vector3.createNew(1000, 1, 0)), DEFAULT_EPSILON);
		assertEquals(2, plane.getDistanceFromPoint(Vector3.createNew(1000, 2, 0)), DEFAULT_EPSILON);

		assertEquals(-1, plane.getDistanceFromPoint(Vector3.createNew(0, -1, 0)), DEFAULT_EPSILON);
		assertEquals(-1, plane.getDistanceFromPoint(Vector3.createNew(-1, -1, 0)), DEFAULT_EPSILON);
		assertEquals(-2, plane.getDistanceFromPoint(Vector3.createNew(1000, -2, 40)), DEFAULT_EPSILON);

		// Distanz über Punkt setzen

		plane = Plane3.createNew(Vector3.XAXIS, Vector3.createNew(10, 0, 0));

		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(10, 0, 0)), DEFAULT_EPSILON);
		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(10, -20, 0)), DEFAULT_EPSILON);
		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(10, 1000, 1000)), DEFAULT_EPSILON);

		assertEquals(-10, plane.getDistanceFromPoint(Vector3.createNew(0, 0, 0)), DEFAULT_EPSILON);
		assertEquals(-11, plane.getDistanceFromPoint(Vector3.createNew(-1, 0, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, 0, 1000)), DEFAULT_EPSILON);

		assertEquals(-10, plane.getDistanceFromPoint(Vector3.createNew(0, 1, 0)), DEFAULT_EPSILON);
		assertEquals(-11, plane.getDistanceFromPoint(Vector3.createNew(-1, 1, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, 1, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, 2, 0)), DEFAULT_EPSILON);

		assertEquals(-10, plane.getDistanceFromPoint(Vector3.createNew(0, -1, 0)), DEFAULT_EPSILON);
		assertEquals(-11, plane.getDistanceFromPoint(Vector3.createNew(-1, -1, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, -2, 40)), DEFAULT_EPSILON);

		// Distanz direkt setzen

		plane = Plane3.createNew(Vector3.XAXIS, 10);

		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(10, 0, 0)), DEFAULT_EPSILON);
		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(10, -20, 0)), DEFAULT_EPSILON);
		assertEquals(0, plane.getDistanceFromPoint(Vector3.createNew(10, 1000, 1000)), DEFAULT_EPSILON);

		assertEquals(-10, plane.getDistanceFromPoint(Vector3.createNew(0, 0, 0)), DEFAULT_EPSILON);
		assertEquals(-11, plane.getDistanceFromPoint(Vector3.createNew(-1, 0, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, 0, 1000)), DEFAULT_EPSILON);

		assertEquals(-10, plane.getDistanceFromPoint(Vector3.createNew(0, 1, 0)), DEFAULT_EPSILON);
		assertEquals(-11, plane.getDistanceFromPoint(Vector3.createNew(-1, 1, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, 1, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, 2, 0)), DEFAULT_EPSILON);

		assertEquals(-10, plane.getDistanceFromPoint(Vector3.createNew(0, -1, 0)), DEFAULT_EPSILON);
		assertEquals(-11, plane.getDistanceFromPoint(Vector3.createNew(-1, -1, 0)), DEFAULT_EPSILON);
		assertEquals(990, plane.getDistanceFromPoint(Vector3.createNew(1000, -2, 40)), DEFAULT_EPSILON);
	}

}
