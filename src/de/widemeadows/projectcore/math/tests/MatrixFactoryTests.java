package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.MathUtils;
import de.widemeadows.projectcore.math.Matrix4;
import de.widemeadows.projectcore.math.MatrixFactory;
import de.widemeadows.projectcore.math.Vector3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests der Matrixfabrik
 */
public class MatrixFactoryTests {

	/**
	 * Testet das Verschieben von Punkten und Vektoren
	 */
	@Test
	public void translateVector() {

		Vector3 a = Vector3.createNew(1, 2, 3);

		// Testet die nicht-Verschiebung
		{
			Matrix4 zero = MatrixFactory.getTranslation(0, 0, 0);
			Vector3 result = zero.transformPoint(a);
			assertEquals(1, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(2, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(3, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die nicht-Verschiebung
		{
			Matrix4 zero = MatrixFactory.getTranslation(0, 0, 0);
			Vector3 result = zero.transformVector(a);
			assertEquals(1, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(2, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(3, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die Verschiebung eines Punktes
		{
			Matrix4 move = MatrixFactory.getTranslation(10, 20, 30);
			Vector3 result = move.transformPoint(a);
			assertEquals(11, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(22, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(33, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die Verschiebung eines Vektors (keine)
		{
			Matrix4 move = MatrixFactory.getTranslation(10, 20, 30);
			Vector3 result = move.transformVector(a);
			assertEquals(1, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(2, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(3, result.z, MathUtils.DEFAULT_EPSILON);
		}
	}

	/**
	 * Testet das Verschieben von Punkten und Vektoren
	 */
	@Test
	public void rotateVector() {

		Vector3 a = Vector3.createNew(1, 2, 3); // NOTE: OpenGL: +z ist zum Betrachter

		//     |+2
		//     |
		//     |____+1
		//    /
		//  /+3

		// Testet die Rotation um die X-Achse
		{
			Matrix4 rotation = MatrixFactory.getRotationX(MathUtils.deg2rad(90));
			Vector3 result = rotation.transformPoint(a);
			assertEquals(a.x, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.z, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.y, result.z, MathUtils.DEFAULT_EPSILON);

			result = rotation.transformVector(a);
			assertEquals(a.x, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.z, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.y, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die Rotation um die Y-Achse
		{
			Matrix4 rotation = MatrixFactory.getRotationY(MathUtils.deg2rad(90));
			Vector3 result = rotation.transformPoint(a);
			assertEquals(a.z, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.y, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.x, result.z, MathUtils.DEFAULT_EPSILON);

			result = rotation.transformVector(a);
			assertEquals(a.z, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.y, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.x, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die Rotation um die Z-Achse
		{
			Matrix4 rotation = MatrixFactory.getRotationZ(MathUtils.deg2rad(90));
			Vector3 result = rotation.transformPoint(a);
			assertEquals(-a.y, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.x, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.z, result.z, MathUtils.DEFAULT_EPSILON);

			result = rotation.transformVector(a);
			assertEquals(-a.y, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.x, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.z, result.z, MathUtils.DEFAULT_EPSILON);
		}
	}

	/**
	 * Trestet kombinierte Translation udn Rotation
	 */
	@Test
	public void translateRotate() {

		Vector3 translate = Vector3.createNew(10, 20, 30);
		Matrix4 transform = MatrixFactory.getTransformation(
				Vector3.createNew(MathUtils.deg2rad(90), 0, 0), // 90° um die X-Achse
				translate);

		Vector3 a = Vector3.createNew(1, 2, 3);

		Vector3 pointResult = transform.transformPoint(a);
		Vector3 vectorResult = transform.transformVector(a);

		// Punkt wurde gedreht und verschoben
		assertEquals(translate.x + (a.x), pointResult.x, MathUtils.DEFAULT_EPSILON);
		assertEquals(translate.y + (-a.z), pointResult.y, MathUtils.DEFAULT_EPSILON);
		assertEquals(translate.z + (a.y), pointResult.z, MathUtils.DEFAULT_EPSILON);

		// Vektor wurde gedreht, aber nicht verschoben
		assertEquals((a.x), vectorResult.x, MathUtils.DEFAULT_EPSILON);
		assertEquals((-a.z), vectorResult.y, MathUtils.DEFAULT_EPSILON);
		assertEquals((a.y), vectorResult.z, MathUtils.DEFAULT_EPSILON);
	}

	/**
	 * Trestet kombinierte Translation udn Rotation
	 */
	@Test
	public void scaleTranslateRotate() {

		Vector3 translate = Vector3.createNew(10, 20, 30);
		Matrix4 transform = MatrixFactory.getTransformation(
				Vector3.createNew(10, 100, 1000),
				Vector3.createNew(MathUtils.deg2rad(90), 0, 0), // 90° um die X-Achse
				translate);

		Vector3 a = Vector3.createNew(1, 2, 3);

		Vector3 pointResult = transform.transformPoint(a);
		Vector3 vectorResult = transform.transformVector(a);

		// Punkt wurde skaliert, gedreht und verschoben
		assertEquals(translate.x + (a.x) * 10, pointResult.x, MathUtils.DEFAULT_EPSILON*10);
		assertEquals(translate.y + (-a.z) * 1000, pointResult.y, MathUtils.DEFAULT_EPSILON*1000);
		assertEquals(translate.z + (a.y) * 100, pointResult.z, MathUtils.DEFAULT_EPSILON*100);

		// Vektor wurde skaliert und gedreht, aber nicht verschoben
		assertEquals((a.x) * 10, vectorResult.x, MathUtils.DEFAULT_EPSILON*10);
		assertEquals((-a.z) * 1000, vectorResult.y, MathUtils.DEFAULT_EPSILON*1000);
		assertEquals((a.y) * 100, vectorResult.z, MathUtils.DEFAULT_EPSILON*100);
	}

}
