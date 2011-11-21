package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.MathUtils;
import de.widemeadows.projectcore.math.Matrix4;
import de.widemeadows.projectcore.math.Vector3;
import de.widemeadows.projectcore.math.exceptions.MatrixException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit Tests für {@link de.widemeadows.projectcore.math.Matrix4}
 */
public class MatrixTests {

	/**
	 * Epsilonwert für Float-Vergleiche
	 */
	private static final float Epsilon = MathUtils.DEFAULT_EPSILON;

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

	/**
	 * Testet das Transponieren der Matrix
	 */
	@Test
	public void transpose() {

		Matrix4 a = Matrix4.MAGIC.getTransposed();
		for (int r = 0; r < 4; ++r) {
			for (int c = 0; c < 4; ++c) {
				assertEquals(Matrix4.MAGIC.getAt(c, r), a.getAt(r, c), ZeroEpsilon);
			}
		}

		a = Matrix4.MAGIC.clone().transposeInPlace();
		for (int r = 0; r < 4; ++r) {
			for (int c = 0; c < 4; ++c) {
				assertEquals(Matrix4.MAGIC.getAt(c, r), a.getAt(r, c), ZeroEpsilon);
			}
		}
	}

	/**
	 * Testet den Vergleich zweier Matrizen
	 */
	@Test
	public void is() {

		// Selbsttest
		assertTrue(Matrix4.MAGIC.equals(Matrix4.MAGIC));
		assertTrue(Matrix4.MAGIC.equals(Matrix4.MAGIC.clone()));

		// Gegentest
		assertFalse(Matrix4.MAGIC.equals(Matrix4.UNIT));

	}

	/**
	 * Testet das Multiplizieren zweier Matrizen
	 */
	@Test
	public void multiplyMatrices() {

		// Testet die Multiplikation mit der Einheitsmatrix
		Matrix4 result = Matrix4.MAGIC.mul(Matrix4.UNIT);
		assertTrue(result.equals(Matrix4.MAGIC, ZeroEpsilon));

		// Testet die Multiplikation mit sich selbst
		result = Matrix4.MAGIC.mul(Matrix4.MAGIC);
		assertTrue(result.equals(
				345, 257, 281, 273,
				257, 313, 305, 281,
				281, 305, 313, 257,
				273, 281, 257, 345,
				Epsilon));

		// Testet die Multiplikation mit dem transponierten selbst
		result = Matrix4.MAGIC.mul(Matrix4.MAGIC.getTransposed());
		assertTrue(result.equals(
				438, 236, 332, 150,
				236, 310, 278, 332,
				332, 278, 310, 236,
				150, 332, 236, 438,
				Epsilon));

		// Testet die Multiplikation mit sich selbst
		result = Matrix4.MAGIC.mul(Matrix4.MAGIC);
		assertTrue(result.equals(
				345, 257, 281, 273,
				257, 313, 305, 281,
				281, 305, 313, 257,
				273, 281, 257, 345,
				Epsilon));

		// Testet die Multiplikation mit dem transponierten selbst
		result = Matrix4.MAGIC.clone().mulInPlace(Matrix4.MAGIC.getTransposed());
		assertTrue(result.equals(
				438, 236, 332, 150,
				236, 310, 278, 332,
				332, 278, 310, 236,
				150, 332, 236, 438,
				Epsilon));
	}

	/**
	 * Testet das Multiplizieren einer Matrix mit einem Skalar
	 */
	@Test
	public void multiplyMatrixScalar() {

		// Testet die Multiplikation mit der Einheitsmatrix
		Matrix4 result = Matrix4.UNIT.mul(2);
		assertTrue(result.equals(
				2, 0, 0, 0,
				0, 2, 0, 0,
				0, 0, 2, 0,
				0, 0, 0, 2,
				ZeroEpsilon));

		// Test einer vollen Matrix
		result = Matrix4.MAGIC.mul(2);
		assertTrue(result.equals(
				32,  4,  6, 26,
				10, 22, 20, 16,
				18, 14, 12, 24,
				 8, 28, 30,  2,
				Epsilon));

		// Test einer vollen Matrix
		result = Matrix4.MAGIC.clone().mulInPlace(2);
		assertTrue(result.equals(
				32,  4,  6, 26,
				10, 22, 20, 16,
				18, 14, 12, 24,
				 8, 28, 30,  2,
				Epsilon));
	}

	/**
	 * Testet das Ermitteln der Determinante
	 */
	@Test
	public void getDeterminant() {
		assertEquals(0, Matrix4.MAGIC.getDeterminant(), Epsilon);
		assertEquals(1, Matrix4.UNIT.getDeterminant(), Epsilon);

		Matrix4 a = Matrix4.createNew(
				 9,  2,  3,  4,
				 5,  6,  7,  8,
				 9, 20, 11, 12,
				13, 14, 15, 16);
		assertEquals(640, a.getDeterminant(), Epsilon);
	}

	/**
	 * Testet das Invertieren einer Matrix
	 */
	@Test
	public void inverse() {
		// Invertieren der Einheitsmatrix --> Einheitsmatrix
		Matrix4 a = Matrix4.UNIT.getInverted();
		assertTrue(a.equals(Matrix4.UNIT, Epsilon));

		// Invertieren der magischen Matrix --> Fehler
		assertNull(Matrix4.MAGIC.getInvertedNoThrow());

		// Beliebige Matrix invertieren
		a = Matrix4.createNew(
				 9,  2,  3,  4,
				 5,  6,  7,  8,
				 9, 20, 11, 12,
				13, 14, 15, 16);
		a = a.getInverted();
		assertTrue(a.equals(
				 0.125f,      -0.1875f, -3.296e-017f,  0.0625f,
				-2.949e-017f, -0.05f,    0.1f,        -0.05f,
				-0.375f,      -1.3375f, -0.2f,         0.9125f,
				 0.25f,        1.45f,    0.1f,        -0.8f,
				Epsilon));
	}

	/**
	 * Testet, ob beim Invertieren einer nicht invertierbaren Matrix ein Fehler auftritt
	 * @throws MatrixException Die Matrix ist nicht invertierbar
	 */
	@Test(expected = MatrixException.class)
	public void inverseNotPossible() throws MatrixException {
		// Invertieren der magischen Matrix --> Fehler
		Matrix4.MAGIC.getInverted();
	}

	/**
	 * Multiplikation eines Vektors
	 */
	@Test
	public void multiplyVector() {

		// Test mit der Einheitsmatrix (w=0)
		Vector3 a = Vector3.createNew(1, 2, 3);
		Vector3 result = Matrix4.UNIT.transform(a, 0);
		assertEquals(a.x, result.x, Epsilon);
		assertEquals(a.y, result.y, Epsilon);
		assertEquals(a.z, result.z, Epsilon);

		// Test mit der Einheitsmatrix (w=1)
		a = Vector3.createNew(1, 2, 3);
		result = Matrix4.UNIT.transform(a, 1);
		assertEquals(a.x, result.x, Epsilon);
		assertEquals(a.y, result.y, Epsilon);
		assertEquals(a.z, result.z, Epsilon);

		// Test mit der magischen Matrix (w=0)

		// MATLAB:
		//  v = magic(4)'*[1 2 3 0]'
		//  v/v(4)

		result = Matrix4.MAGIC.transform(a, 0);
		assertEquals(0.81538f, result.x, Epsilon);
		assertEquals(0.69231f, result.y, Epsilon);
		assertEquals(0.63077f, result.z, Epsilon);

		// Test mit der magischen Matrix (w=0)

		// MATLAB:
		//  v = magic(4)'*[1 2 3 1]'
		//  v/v(4)

		result = Matrix4.MAGIC.transform(a, 1);
		assertEquals(0.86364f, result.x, Epsilon);
		assertEquals(0.89394f, result.y, Epsilon);
		assertEquals(0.84848f, result.z, Epsilon);
	}

	/**
	 * Testet die Performance von erzeugen/recyclen
	 */
	// @Test
	public void createDestroyPerformanceCached() {

		final int iterations = 100000000;

		for (int i = 10; i >= 0; --i) {
			Matrix4 mtx = Matrix4.createNew();
			mtx.recycle();
		}
		Matrix4.Cache.clear();

		long start = System.nanoTime();
		for (int i = iterations - 1; i >= 0; --i) {
			Matrix4 mtx1 = Matrix4.createNew();
			mtx1.recycle();
			Matrix4 mtx2 = Matrix4.createNew();
			Matrix4 mtx3 = Matrix4.createNew();
			mtx2.recycle();
			mtx3.recycle();
		}
		long elapsed1 = System.nanoTime() - start;
		Matrix4.Cache.clear();

		System.out.println("Erzeugen und Recyclen von " + iterations*3 + " Matrizen (cached): " + elapsed1 / (float) iterations / 3 + " ms pro Instanz");
	}

	/**
	 * Testet die Performance von erzeugen/recyclen
	 */
	// @Test
	public void createDestroyPerformanceUncached() {

		final int iterations = 100000;

		final ArrayList<Matrix4> foo = new ArrayList<Matrix4>();
		long elapsed = 0;
		for (int i = iterations - 1; i >= 0; --i) {
			long start = System.nanoTime();
			Matrix4 mtx = Matrix4.createNew();
			elapsed += System.nanoTime() - start;
			foo.add(mtx);
		}
		foo.clear();

		System.out.println("Erzeugen und Recyclen von " + iterations + " Matrizen (uncached): " + elapsed / (float) iterations + " ms pro Instanz");
	}
}
