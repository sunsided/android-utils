package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.*;
import org.junit.Test;

import static de.widemeadows.projectcore.math.MathUtils.DEFAULT_EPSILON;
import static de.widemeadows.projectcore.math.MathUtils.deg2rad;
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

		assertTrue(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(0, -10, 0), Vector3.createNew(0, 10, 0)), 0, 100));
		assertFalse(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(-10, -10, 0), Vector3.createNew(-10, 10, 0)), 0, 100));

		assertTrue(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(0, 0, -10), Vector3.createNew(0, 0, 10)), 0, 100));
		assertFalse(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(-10, 0, -10), Vector3.createNew(-10, 0, 10)), 0, 100));

		assertTrue(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(-10, -10, -10), Vector3.createNew(10, 10, 10)), 0, 100));
		assertTrue(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(10, 10, 10), Vector3.createNew(-10, -10, -10)), 0, 100));
		assertFalse(box.intersects(RayFactory.rayFromTwoPoints(Vector3.createNew(-20, -10, -10), Vector3.createNew(-20, 10, 10)), 0, 100));
	}

	/**
	 * Schnitt von {@link AxisAlignedBox} und {@link Ray3}
	 */
	// @Test
	public void rayBoxIntersectionPerformance() {
		final AxisAlignedBox box = AxisAlignedBox.createNew(
				0, 0, 0,
				0.5f, 0.5f, 0.5f
		);

		final Vector3 from1 = Vector3.createNew(-10, 0, 0);
		final Vector3 to1 = Vector3.createNew(10, 0, 0);
		final Vector3 from2 = Vector3.createNew(-10, 10, 0);
		final Vector3 to2 = Vector3.createNew(10, 10, 0);
		final Ray3 ray1 = RayFactory.rayFromTwoPoints(from1, to1);
		final Ray3 ray2 = RayFactory.rayFromTwoPoints(from2, to2);

		assertTrue(box.intersects(ray1, 0, 100));
		assertFalse(box.intersects(ray2, 0, 100));

		final int iterations = 100000000;

		for (int i=10; i>=0; --i) {
			box.intersects(ray1, 0, 100);
			box.intersects(ray2, 0, 100);
		}

		long start = System.nanoTime();
		for (int i = iterations - 1; i >= 0; --i) {
			box.intersects(ray1, 0, 100);
			box.intersects(ray2, 0, 100);
		}
		long elapsed1 = System.nanoTime() - start;


		System.out.println("Performance Ray-Box-Intersection: " + elapsed1/(float)iterations/2 + " ms");
	}

    /**
     * Test die Rotation der Box
     */
    @Test
    public void boxRotation() {
        //Matrix4 translation = MatrixFactory.getRotationEulerRPY(0, MathUtils.deg2rad(45), 0);
        Matrix4 translation = MatrixFactory.getRotationY(deg2rad(45));
        AxisAlignedBox box = AxisAlignedBox.createNew();
	    AxisAlignedBox box2 = box.transform(translation);
	    // System.out.printf("AABB " + box.extent + " --> " + box2.extent + "\n");
	    assertEquals(0.70710677f, box2.extent.x, DEFAULT_EPSILON);
	    assertEquals(0.5, box2.extent.y, DEFAULT_EPSILON);
	    assertEquals(0.70710677f, box2.extent.z, DEFAULT_EPSILON);

	    translation = MatrixFactory.getRotationY(deg2rad(90));
	    box = AxisAlignedBox.createNew();
	    box2 = box.transform(translation);
	    assertEquals(0.5, box2.extent.x, DEFAULT_EPSILON);
	    assertEquals(0.5, box2.extent.y, DEFAULT_EPSILON);
	    assertEquals(0.5, box2.extent.z, DEFAULT_EPSILON);
	}
}
