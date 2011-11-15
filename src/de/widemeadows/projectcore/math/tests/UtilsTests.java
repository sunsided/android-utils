package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.MathUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test der Mathehelfer
 */
public class UtilsTests {

	/**
	 * Überprüft die Equals-Funktion
	 */
	@Test
	public void equals() {
		assertTrue(MathUtils.equals(0, 0, 0));
		assertFalse(MathUtils.equals(0, 1, 0));
		assertTrue(MathUtils.equals(0, 1, 1));
		assertFalse(MathUtils.equals(0, 1.0001f, 1));
		assertTrue(MathUtils.equals(0, -1, 1));
		assertTrue(MathUtils.equals(0, -0.9999f, 1));
		assertFalse(MathUtils.equals(0, -1.0001f, 1));
		assertTrue(MathUtils.equals(0, 0.001f, 0.001f));
		assertTrue(MathUtils.equals(0, -0.001f, 0.001f));
		assertTrue(MathUtils.equals(0, 0.001f, 0.002f));
		assertTrue(MathUtils.equals(0, -0.001f, 0.002f));
	}

}
