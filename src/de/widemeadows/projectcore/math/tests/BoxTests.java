package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.Box;
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

}
