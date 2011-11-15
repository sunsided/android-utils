package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.Matrix4;
import org.junit.Test;

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
	 * Stellt sicher, dass die Matrix nach Initialisierung eine Identitätsmatrix ist.
	 */
    @Test
    public void isIdentity() {

        Matrix4 a = Matrix4.createNew();
	    final float initializationDelta = 0.0f;

        // einsen Testen
        assertEquals(1, a.getAt(0, 0), initializationDelta);
        assertEquals(1, a.getAt(1, 1), initializationDelta);
        assertEquals(1, a.getAt(2, 2), initializationDelta);
        assertEquals(1, a.getAt(3, 3), initializationDelta);

        // Nullen erste Reihe
        assertEquals(0, a.getAt(0, 1), initializationDelta);
        assertEquals(0, a.getAt(0, 2), initializationDelta);
        assertEquals(0, a.getAt(0, 3), initializationDelta);

        // Nullen zweite Reihe
        assertEquals(0, a.getAt(1, 0), initializationDelta);
        assertEquals(0, a.getAt(1, 2), initializationDelta);
        assertEquals(0, a.getAt(1, 3), initializationDelta);

        // Nullen dritte Reihe
        assertEquals(0, a.getAt(2, 0), initializationDelta);
        assertEquals(0, a.getAt(2, 1), initializationDelta);
        assertEquals(0, a.getAt(2, 3), initializationDelta);

        // Nullen vierte Reihe
        assertEquals(0, a.getAt(3, 0), initializationDelta);
        assertEquals(0, a.getAt(3, 1), initializationDelta);
        assertEquals(0, a.getAt(3, 2), initializationDelta);
    }

}
