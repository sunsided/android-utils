package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.Matrix4;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Unit Tests für {@link de.widemeadows.projectcore.math.Matrix4}
 */
public class MatrixTests {

	/**
	 * Epsilonwert für Float-Vergleiche
	 */
	private static final float Epsilon = 0.00001f;

	/**
	 * Null-Epsilonumgebung
	 */
	private final float ZeroEpsilon = 0.0f;

	/**
	 * Füllt die Matrix mit zufälligen Werten
	 * @param matrix Die Matrix
	 */
	private static void randomizeValues(@NotNull Matrix4 matrix) {
		Random randomizer = new Random();
		for (int i = 0; i < 16; ++i) {
			matrix.setAt(i, randomizer.nextFloat());
		}
	}

	/**
	 * Führt den Test auf die Einheitsmatrix durch
	 * @param a Die zu testende Matrix
	 */
	private void performIdentityTest(@NotNull Matrix4 a) {
		// einsen Testen
		assertEquals(1, a.getAt(0, 0), ZeroEpsilon);
		assertEquals(1, a.getAt(1, 1), ZeroEpsilon);
		assertEquals(1, a.getAt(2, 2), ZeroEpsilon);
		assertEquals(1, a.getAt(3, 3), ZeroEpsilon);

		// Nullen erste Reihe
		assertEquals(0, a.getAt(0, 1), ZeroEpsilon);
		assertEquals(0, a.getAt(0, 2), ZeroEpsilon);
		assertEquals(0, a.getAt(0, 3), ZeroEpsilon);

		// Nullen zweite Reihe
		assertEquals(0, a.getAt(1, 0), ZeroEpsilon);
		assertEquals(0, a.getAt(1, 2), ZeroEpsilon);
		assertEquals(0, a.getAt(1, 3), ZeroEpsilon);

		// Nullen dritte Reihe
		assertEquals(0, a.getAt(2, 0), ZeroEpsilon);
		assertEquals(0, a.getAt(2, 1), ZeroEpsilon);
		assertEquals(0, a.getAt(2, 3), ZeroEpsilon);

		// Nullen vierte Reihe
		assertEquals(0, a.getAt(3, 0), ZeroEpsilon);
		assertEquals(0, a.getAt(3, 1), ZeroEpsilon);
		assertEquals(0, a.getAt(3, 2), ZeroEpsilon);
	}

	/**
	 * Stellt sicher, dass die Matrix nach Initialisierung eine Identitätsmatrix ist.
	 */
    @Test
    public void isIdentity() {

	    // Neu erzeugte Matrix testen **********************
        Matrix4 a = Matrix4.createNew();
	    performIdentityTest(a);

	    // toIdentity() testen *****************************
	    randomizeValues(a);
	    a.toIdentity();
	    performIdentityTest(a);

	    // Einheitsmatrix testen ***************************
	    performIdentityTest(Matrix4.UNIT);
    }

	/**
	 * Überprüft das Setzen der Werte und das Beziehen der Werte über die Konstanten
	 */
	@Test
	public void valueSetter() {
		Matrix4 a = Matrix4.createNew(
				 1,  2,  3,  4,
				 5,  6,  7,  8,
				 9, 10, 11, 12,
				13, 14, 15, 16);

		assertEquals(1, a.getAt(Matrix4.M11), ZeroEpsilon);
		assertEquals(2, a.getAt(Matrix4.M12), ZeroEpsilon);
		assertEquals(3, a.getAt(Matrix4.M13), ZeroEpsilon);
		assertEquals(4, a.getAt(Matrix4.M14), ZeroEpsilon);

		assertEquals(5, a.getAt(Matrix4.M21), ZeroEpsilon);
		assertEquals(6, a.getAt(Matrix4.M22), ZeroEpsilon);
		assertEquals(7, a.getAt(Matrix4.M23), ZeroEpsilon);
		assertEquals(8, a.getAt(Matrix4.M24), ZeroEpsilon);

		assertEquals(9, a.getAt(Matrix4.M31), ZeroEpsilon);
		assertEquals(10, a.getAt(Matrix4.M32), ZeroEpsilon);
		assertEquals(11, a.getAt(Matrix4.M33), ZeroEpsilon);
		assertEquals(12, a.getAt(Matrix4.M34), ZeroEpsilon);

		assertEquals(13, a.getAt(Matrix4.M41), ZeroEpsilon);
		assertEquals(14, a.getAt(Matrix4.M42), ZeroEpsilon);
		assertEquals(15, a.getAt(Matrix4.M43), ZeroEpsilon);
		assertEquals(16, a.getAt(Matrix4.M44), ZeroEpsilon);
	}

	/**
	 * Testet das Setzen und Beziehen von Werten mittels Getter und Setter
	 */
	@Test
	public void setGetTest() {
		Matrix4 a = Matrix4.createNew(false);

		// Index-Accessor
		for (int i=0; i<16; ++i) {
			a.setAt(i, i);
			assertEquals(i, a.getAt(i), ZeroEpsilon);
		}

		// Row-Column-Accessor
		randomizeValues(a);
		int i = 0;
		for (int r = 0; r < 4; ++r) {
			for (int c = 0; c < 4; ++c) {
				a.setAt(r, c, i);
				assertEquals(i, a.getAt(r, c), ZeroEpsilon);
				++i;
			}
		}
	}
}