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

	/**
	 * Überprüft die Equals-Funktion
	 */
	@Test
	public void isZero() {
		assertTrue(MathUtils.isZero(0, 0));
		assertTrue(MathUtils.isZero(1, 1));
		assertTrue(MathUtils.isZero(-1, 1));
		assertTrue(MathUtils.isZero(0.001f, 0.01f));
		assertTrue(MathUtils.isZero(-0.001f, 0.01f));
		assertFalse(MathUtils.isZero(0.1f, 0.01f));
		assertFalse(MathUtils.isZero(-0.1f, 0.01f));
		assertFalse(MathUtils.isZero(2, 1));
		assertFalse(MathUtils.isZero(-2, 1));
	}

	/**
	 * Überprüft die Equals-Funktion
	 */
	@Test
	public void isLargerZero() {
		assertFalse(MathUtils.isLargerZero(0, 0));
		assertFalse(MathUtils.isLargerZero(1, 1));
		assertFalse(MathUtils.isLargerZero(-1, 1));
		assertFalse(MathUtils.isLargerZero(0.001f, 0.01f));
		assertFalse(MathUtils.isLargerZero(-0.001f, 0.01f));
		assertTrue(MathUtils.isLargerZero(0.1f, 0.01f));
		assertFalse(MathUtils.isLargerZero(-0.1f, 0.01f));
		assertTrue(MathUtils.isLargerZero(2, 1));
		assertFalse(MathUtils.isLargerZero(-2, 1));
	}

	/**
	 * Überprüft die Equals-Funktion
	 */
	@Test
	public void isSmallerZero() {
		assertFalse(MathUtils.isSmallerZero(0, 0));
		assertFalse(MathUtils.isSmallerZero(1, 1));
		assertFalse(MathUtils.isSmallerZero(-1, 1));
		assertFalse(MathUtils.isSmallerZero(0.001f, 0.01f));
		assertFalse(MathUtils.isSmallerZero(-0.001f, 0.01f));
		assertFalse(MathUtils.isSmallerZero(0.1f, 0.01f));
		assertTrue(MathUtils.isSmallerZero(-0.1f, 0.01f));
		assertFalse(MathUtils.isSmallerZero(2, 1));
		assertTrue(MathUtils.isSmallerZero(-2, 1));
	}
}
