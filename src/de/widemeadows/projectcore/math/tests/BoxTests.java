package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.*;
import org.junit.Test;

import static de.widemeadows.projectcore.math.MathUtils.DEFAULT_EPSILON;
import static junit.framework.Assert.*;

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
		AxisAlignedBox box = AxisAlignedBox.createNew();
		assertEquals(1.0f, box.calculateArea(), DEFAULT_EPSILON);

		// Box der Größe 2x2x2
		box = AxisAlignedBox.createNew(
				0, 0, 0,
				1, 1, 1
		);
		assertEquals(8.0f, box.calculateArea(), DEFAULT_EPSILON);

		// Box der Größe 2x2x2
		box = AxisAlignedBox.createNew(
				0, 0, 0,
				-1, -1, -1
		);
		assertEquals(8.0f, box.calculateArea(), DEFAULT_EPSILON);

		// Box der Größe 2x2x2
		box = AxisAlignedBox.createNew(
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
		AxisAlignedBox box = AxisAlignedBox.createNew(
				0, 0, 0,
				1, 1, 1
		);
		assertEquals(1.0f, box.extent.x, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.y, DEFAULT_EPSILON);
		assertEquals(1.0f, box.extent.z, DEFAULT_EPSILON);

		// Alle Komponenten negativ
		box = AxisAlignedBox.createNew(
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
		AxisAlignedBox box = AxisAlignedBox.createNew(
				0, 0, 0,
				1, 2, 3
		);

		// vorne unten links
		Vector3 point = box.getCornerPoint(BoxPoint.FrontBottomLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// vorne unten rechts
		point = box.getCornerPoint(BoxPoint.FrontBottomRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// vorne oben links
		point = box.getCornerPoint(BoxPoint.FrontTopLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// vorne oben rechts
		point = box.getCornerPoint(BoxPoint.FrontTopRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(+3, point.z, DEFAULT_EPSILON);

		// hinten unten links
		point = box.getCornerPoint(BoxPoint.BackBottomLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// hinten unten rechts
		point = box.getCornerPoint(BoxPoint.BackBottomRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// hinten oben links
		point = box.getCornerPoint(BoxPoint.BackTopLeft);
		assertEquals(-1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// hinten oben rechts
		point = box.getCornerPoint(BoxPoint.BackTopRight);
		assertEquals(+1, point.x, DEFAULT_EPSILON);
		assertEquals(+2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);
	}

	/**
	 * Überprüft Punkte
	 */
	@Test
	public void intersectionWithPoint() {

		// Alle Komponenten positiv
		AxisAlignedBox box = AxisAlignedBox.createNew(
				0, 0, 0,
				1, 2, 3
		);

		// Punkt in der Box
		Vector3 point = Vector3.createNew(0, 0, 0);
		assertTrue(box.intersects(point));
		assertTrue(box.intersects(point.x, point.y, point.z));

		// Punkt in der Box
		point = Vector3.createNew(0.5f, -0.5f, 0.23f);
		assertTrue(box.intersects(point));
		assertTrue(box.intersects(point.x, point.y, point.z));

		// Punkt auf der Box
		point = Vector3.createNew(1, 2, 3);
		assertTrue(box.intersects(point));
		assertTrue(box.intersects(point.x, point.y, point.z));

		// Punkt auf der Box
		point = Vector3.createNew(-1, -2, -3);
		assertTrue(box.intersects(point));
		assertTrue(box.intersects(point.x, point.y, point.z));

		// Punkt außerhalb der Box
		point = Vector3.createNew(1, 2, 3.01f);
		assertFalse(box.intersects(point));
		assertFalse(box.intersects(point.x, point.y, point.z));

		// Punkt außerhalb der Box
		point = Vector3.createNew(1, 2.01f, 3);
		assertFalse(box.intersects(point));
		assertFalse(box.intersects(point.x, point.y, point.z));

		// Punkt außerhalb der Box
		point = Vector3.createNew(1.01f, 2, 3);
		assertFalse(box.intersects(point));
		assertFalse(box.intersects(point.x, point.y, point.z));

		// Punkt außerhalb der Box
		point = Vector3.createNew(1.01f, 10000, -3000);
		assertFalse(box.intersects(point));
		assertFalse(box.intersects(point.x, point.y, point.z));
	}

	/**
	 * Schnitt von {@link AxisAlignedBox} und {@link Ray3}
	 */
	@Test
	public void rayBoxIntersection() {
		AxisAlignedBox box = AxisAlignedBox.createNew(
				0, 0, 0,
				0.5f, 0.5f, 0.5f
		);

		assertTrue(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(-10, 0, 0), Vector3.createNew(10, 0, 0)), 0, 100));
		assertFalse(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(-10, 10, 0), Vector3.createNew(10, 10, 0)), 0, 100));
	}
}
