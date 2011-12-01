package de.widemeadows.projectcore.transformation.tests;

import de.widemeadows.projectcore.math.Vector3;
import de.widemeadows.projectcore.transformation.TransformationState;
import org.junit.Test;

import static de.widemeadows.projectcore.math.MathUtils.DEFAULT_EPSILON;
import static de.widemeadows.projectcore.math.MathUtils.deg2rad;
import static junit.framework.Assert.assertEquals;

/**
 * Tests der Transformation States
 */
public class TransformationStateTests {

	/**
	 * Überprüft die Translation
	 */
	@Test
	public void translationTest() {

		TransformationState state = new TransformationState();

		// Nicht-Verschiebung
		assertEquals(0, state.getTranslationX(), DEFAULT_EPSILON);
		assertEquals(0, state.getTranslationY(), DEFAULT_EPSILON);
		assertEquals(0, state.getTranslationZ(), DEFAULT_EPSILON);

		Vector3 point = Vector3.createNew();
		state.transformPoint(point);

		assertEquals(0, point.x, DEFAULT_EPSILON);
		assertEquals(0, point.y, DEFAULT_EPSILON);
		assertEquals(0, point.z, DEFAULT_EPSILON);

		// Absolute Translation
		state.setTranslation(1, 2, 3);
		assertEquals(1, state.getTranslationX(), DEFAULT_EPSILON);
		assertEquals(2, state.getTranslationY(), DEFAULT_EPSILON);
		assertEquals(3, state.getTranslationZ(), DEFAULT_EPSILON);

		point = Vector3.createNew();
		state.transformPoint(point);

		assertEquals(1, point.x, DEFAULT_EPSILON);
		assertEquals(2, point.y, DEFAULT_EPSILON);
		assertEquals(3, point.z, DEFAULT_EPSILON);

		// Relative Translation
		state.translate(-2, -4, -6);
		assertEquals(-1, state.getTranslationX(), DEFAULT_EPSILON);
		assertEquals(-2, state.getTranslationY(), DEFAULT_EPSILON);
		assertEquals(-3, state.getTranslationZ(), DEFAULT_EPSILON);

		state.transformPoint(point);

		assertEquals(0, point.x, DEFAULT_EPSILON);
		assertEquals(0, point.y, DEFAULT_EPSILON);
		assertEquals(0, point.z, DEFAULT_EPSILON);
	}

	/**
	 * Überprüft die Rotation
	 */
	@Test
	public void setRotationTest() {

		TransformationState state = new TransformationState();

		// Nicht-Rotation
		Vector3 point = Vector3.createNew(1, 2, 3);
		state.transformPoint(point);
		assertEquals(1, point.x, DEFAULT_EPSILON);
		assertEquals(2, point.y, DEFAULT_EPSILON);
		assertEquals(3, point.z, DEFAULT_EPSILON);

		// Rotation um X
		point = Vector3.createNew(1, 2, 3);
		state.setRotationY(deg2rad(90));
		state.transformPoint(point);
		assertEquals(3, point.x, DEFAULT_EPSILON);
		assertEquals(2, point.y, DEFAULT_EPSILON);
		assertEquals(-1, point.z, DEFAULT_EPSILON);

		// Rotation um X
		point = Vector3.createNew(1, 2, 3);
		state.setRotationX(deg2rad(90));
		state.transformPoint(point);
		assertEquals(1, point.x, DEFAULT_EPSILON);
		assertEquals(-3, point.y, DEFAULT_EPSILON);
		assertEquals(2, point.z, DEFAULT_EPSILON);

		// Rotation um Z
		point = Vector3.createNew(1, 2, 3);
		state.setRotationZ(deg2rad(90));
		state.transformPoint(point);
		assertEquals(-2, point.x, DEFAULT_EPSILON);
		assertEquals(1, point.y, DEFAULT_EPSILON);
		assertEquals(3, point.z, DEFAULT_EPSILON);

		// Rotation um RPY
		point = Vector3.createNew(1, 2, 3);
		state.setRotation(deg2rad(90), deg2rad(90), deg2rad(90));
		state.transformPoint(point);

		assertEquals(3, point.x, DEFAULT_EPSILON);
		assertEquals(2, point.y, DEFAULT_EPSILON);
		assertEquals(-1, point.z, DEFAULT_EPSILON);
	}

	/**
	 * Überprüft die Rotation
	 */
	@Test
	public void rotateTest() {

		TransformationState state = new TransformationState();

		// Nicht-Rotation
		Vector3 point = Vector3.createNew(1, 2, 3);
		state.transformPoint(point);
		assertEquals(1, point.x, DEFAULT_EPSILON);
		assertEquals(2, point.y, DEFAULT_EPSILON);
		assertEquals(3, point.z, DEFAULT_EPSILON);

		// Rotation um X
		point = Vector3.createNew(1, 2, 3);
		state.rotateObjectX(deg2rad(90)); // Rotation um X
		state.transformPoint(point);
		assertEquals(1, point.x, DEFAULT_EPSILON);
		assertEquals(-3, point.y, DEFAULT_EPSILON);
		assertEquals(2, point.z, DEFAULT_EPSILON);

		// Erneute Rotation um X --> Rotation um 180°
		point = Vector3.createNew(1, 2, 3);
		state.rotateObjectX(deg2rad(90)); // Rotation um X
		state.transformPoint(point);
		assertEquals(1, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(-3, point.z, DEFAULT_EPSILON);

		// Erneute Rotation um Y
		point = Vector3.createNew(1, 2, 3);
		state.rotateObjectY(deg2rad(90)); // Rotation um Y'
		state.transformPoint(point);
		assertEquals(3, point.x, DEFAULT_EPSILON);
		assertEquals(-2, point.y, DEFAULT_EPSILON);
		assertEquals(1, point.z, DEFAULT_EPSILON);

		// Erneute Rotation um Z
		point = Vector3.createNew(1, 2, 3);
		state.rotateObjectZ(deg2rad(90)); // Rotation um Z''
		state.transformPoint(point);
		assertEquals(3, point.x, DEFAULT_EPSILON);
		assertEquals(-1, point.y, DEFAULT_EPSILON);
		assertEquals(-2, point.z, DEFAULT_EPSILON);

		// RPY-test
		point = Vector3.createNew(1, 2, 3);
		state.resetRotation();
		state.setRotation(deg2rad(180), deg2rad(90), deg2rad(90)); // Rotation um X, Y, Z der Welt!
		state.transformPoint(point);
		assertEquals(2, point.x, DEFAULT_EPSILON);
		assertEquals(-3, point.y, DEFAULT_EPSILON);
		assertEquals(-1, point.z, DEFAULT_EPSILON);
	}
}
