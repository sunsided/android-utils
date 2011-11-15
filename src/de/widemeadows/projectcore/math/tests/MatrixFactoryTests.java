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

		Vector3 a = Vector3.createNew(1, 2, 3);

		// Testet die Rotation um die X-Achse
		{
			Matrix4 rotation = MatrixFactory.getRotationX(MathUtils.deg2Rad(90));
			Vector3 result = rotation.transformPoint(a);
			assertEquals(a.x, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.z, result.y, MathUtils.DEFAULT_EPSILON); // NOTE: OpenGL: +z ist zum Betrachter
			assertEquals(a.y, result.z, MathUtils.DEFAULT_EPSILON);

			result = rotation.transformVector(a);
			assertEquals(a.x, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.z, result.y, MathUtils.DEFAULT_EPSILON); // NOTE: OpenGL: +z ist zum Betrachter
			assertEquals(a.y, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die Rotation um die Y-Achse
		{
			Matrix4 rotation = MatrixFactory.getRotationY(MathUtils.deg2Rad(90));
			Vector3 result = rotation.transformPoint(a);
			assertEquals(-a.z, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.y, result.y, MathUtils.DEFAULT_EPSILON); // NOTE: OpenGL: +z ist zum Betrachter
			assertEquals(-a.x, result.z, MathUtils.DEFAULT_EPSILON);

			result = rotation.transformVector(a);
			assertEquals(-a.z, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.y, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(-a.x, result.z, MathUtils.DEFAULT_EPSILON);
		}

		// Testet die Rotation um die Z-Achse
		{
			Matrix4 rotation = MatrixFactory.getRotationZ(MathUtils.deg2Rad(90));
			Vector3 result = rotation.transformPoint(a);
			assertEquals(-a.y, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.x, result.y, MathUtils.DEFAULT_EPSILON); // NOTE: OpenGL: +z ist zum Betrachter
			assertEquals(a.z, result.z, MathUtils.DEFAULT_EPSILON);

			result = rotation.transformVector(a);
			assertEquals(-a.y, result.x, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.x, result.y, MathUtils.DEFAULT_EPSILON);
			assertEquals(a.z, result.z, MathUtils.DEFAULT_EPSILON);
		}
	}

}
