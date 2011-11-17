package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.Box;
import de.widemeadows.projectcore.math.Vector3;
import org.junit.Test;

import static de.widemeadows.projectcore.math.MathUtils.DEFAULT_EPSILON;
import static junit.framework.Assert.assertEquals;

/**
 * Tests für die Bounding Box
 */
public class BoxTests {

	/**
	 * Testet die Flächenberechnung der Box
	 */
	@Test
	public void areaTest() {

		// Einheitsbox testen
		Box box = Box.createNew();
		assertEquals(1.0f, box.calculateArea(), DEFAULT_EPSILON);

		// Box der Größe 2x2x2
		box = Box.createNew(
				0, 0, 0,
				1, 1, 1
		);
		assertEquals(8.0f, box.calculateArea(), DEFAULT_EPSILON);

		// Box der Größe 2x2x2
		box = Box.createNew(
				0, 0, 0,
				-1, -1, -1
		);
		assertEquals(8.0f, box.calculateArea(), DEFAULT_EPSILON);

		// Box der Größe 2x2x2
		box = Box.createNew(
				0, 1000, 0,
				1, -1, 1
		);
		assertEquals(8.0f, box.calculateArea(), DEFAULT_EPSILON);

	}

	/**
	 * Stellt sicher, dass der Extent-Vektor immer positiv ist
	 */
	@Test
	public void extentAlwaysPositiveTest() {
		// Alle Komponenten positiv
		Box box = Box.createNew(
				0, 0, 0,
				1, 1, 1
		);
		assertEquals(1.0f, box.extent.x, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.y, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.z, DEFAULT_EPSILON);

		// Alle Komponenten negativ
		box = Box.createNew(
				0, 0, 0,
				-1, -1, -1
		);
		assertEquals(1.0f, box.extent.x, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.y, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.z, DEFAULT_EPSILON);

		// Setzen der Komponenten
		box.setExtent(-1, 1, 1);
		assertEquals(1.0f, box.extent.x, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.y, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.z, DEFAULT_EPSILON);

		// Setzen der Komponenten
		box.setExtent(Vector3.createNew(1, -1, -1));
		assertEquals(1.0f, box.extent.x, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.y, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.z, DEFAULT_EPSILON);
	}

	/**
	 * Testet das Beziehen der Eckpunkte der Box
	 */
	@Test
	public void boxCornerSelection() {

		// Alle Komponenten positiv
		Box box = Box.createNew(
				0, 0, 0,
				1, 2, 3
		);

		// vorne unten links
		Vector3 point = box.getCornerPoint(Box.Point.FrontBottomLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// vorne unten rechts
		point = box.getCornerPoint(Box.Point.FrontBottomRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// vorne oben links
		point = box.getCornerPoint(Box.Point.FrontTopLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// vorne oben rechts
		point = box.getCornerPoint(Box.Point.FrontTopRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// hinten unten links
		point = box.getCornerPoint(Box.Point.BackBottomLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// hinten unten rechts
		point = box.getCornerPoint(Box.Point.BackBottomRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// hinten oben links
		point = box.getCornerPoint(Box.Point.BackTopLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// hinten oben rechts
		point = box.getCornerPoint(Box.Point.BackTopRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);
	}
}