package de.widemeadows.projectcore.math.tests;

import de.widemeadows.projectcore.math.Matrix4;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Unit Tests für {@link de.widemeadows.projectcore.math.Matrix4}
 */
public class MatrixTests {

    @Test
    public static void testIsIdentity() {

        Matrix4 a = Matrix4.createNew();

        // einsen Testen
        Assert.assertEquals(1, a.getAt(0, 0));
        Assert.assertEquals(1, a.getAt(1, 1));
        Assert.assertEquals(1, a.getAt(2, 2));
        Assert.assertEquals(1, a.getAt(3, 3));

        // Nullen erste Reihe
        Assert.assertEquals(0, a.getAt(0, 1));
        Assert.assertEquals(0, a.getAt(0, 2));
        Assert.assertEquals(0, a.getAt(0, 3));

        // Nullen zweite Reihe
        Assert.assertEquals(0, a.getAt(1, 0));
        Assert.assertEquals(0, a.getAt(1, 2));
        Assert.assertEquals(0, a.getAt(1, 3));

        // Nullen dritte Reihe
        Assert.assertEquals(0, a.getAt(2, 0));
        Assert.assertEquals(0, a.getAt(2, 1));
        Assert.assertEquals(0, a.getAt(2, 3));

        // Nullen vierte Reihe
        Assert.assertEquals(0, a.getAt(3, 0));
        Assert.assertEquals(0, a.getAt(3, 1));
        Assert.assertEquals(0, a.getAt(3, 2));
    }

}
