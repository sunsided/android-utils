package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;
import de.widemeadows.projectcore.cache.ObjectCache;
import de.widemeadows.projectcore.cache.ObjectFactory;

/**
 * 3D-Vektor
 * <p>
 *     Neue Objekte werden erzeugt mittels {@link #createNew()}; Nicht länger benötigte Objekte sollten per
 *     {@link #recycle(Vector3)} zurückgegeben werden.
 * </p>
 */
public final class Vector3 {

	/**
	 * Instanz, die die Verwaltung nicht länger benötigter Instanzen übernimmt
	 */
	public static final ObjectCache<Vector3> Recycling = new ObjectCache<Vector3>(new ObjectFactory<Vector3>() {
		@NotNull
        @Override
		public Vector3 createNew() {
			return new Vector3();
		}
	});

	/**
	 * Erzeugt eine neue Vektor-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @param z Z-Koordinate
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Recycling
	 */
	@NotNull
	public static Vector3 createNew(float x, float y, float z) {
		return Recycling.getOrCreate().set(x, y, z);
	}

	/**
	 * Kopierkonstruktor. Erzeugt eine neue Vektor-Instanz.
	 * <p>
	 * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
	 * </p>
	 *
     * @param other der zu kopierende Vector3
	 * @return Der neue oder aufbereitete Vektor
	 * @see #Recycling
	 */
    public static Vector3 createNew(Vector3 other) {
        return Recycling.getOrCreate().set(other);
    }

    /**
     * Erzeugt eine neue Vektor-Instanz.
     * <p>
     * <strong>Hinweis:</strong> Der Zustand der Vektors kann korrupt sein!
     * </p>
     *
     * @return Der neue oder aufbereitete Vektor
     * @see #Recycling
     */
    @NotNull
    public static Vector3 createNew() {
        return Recycling.getOrCreate();
    }

	/**
	 * Registriert einen Vektor für das spätere Recycling
	 *
	 * @param vector Der zu registrierende Vektor
	 * @see #Recycling
     * @see Vector3#recycle()
	 */
	public static void recycle(@NotNull Vector3 vector) {
		Recycling.registerElement(vector);
	}

    /**
     * Registriert diesen Vektor für das spätere Recycling
     *
     * @see #Recycling
     * @see Vector3#recycle(Vector3)
     */
    public void recycle() {
        Recycling.registerElement(this);
    }

	/**
	 * Der Nullvektor {0, 0, 0}
	 */
	@NotNull
	public static final Vector3 ZERO = new Vector3(0, 0, 0);

	/**
	 * Der X-Vektor {1, 0, 0}
	 */
	@NotNull
	public static final Vector3 XAXIS = new Vector3(1, 0, 0);

	/**
	 * Der X-Vektor {-1, 0, 0}
	 */
	@NotNull
	public static final Vector3 XAXIS_NEGATIVE = new Vector3(-1, 0, 0);

	/**
	 * Der Y-Vektor {0, 1, 0}
	 */
	@NotNull
	public static final Vector3 YAXIS = new Vector3(0, 1, 0);

	/**
	 * Der Y-Vektor {0, -1, 0}
	 */
	@NotNull
	public static final Vector3 YAXIS_NEGATIVE = new Vector3(0, -1, 0);

	/**
	 * Der Z-Vektor {0, 0, 1}
	 */
	@NotNull
	public static final Vector3 ZAXIS = new Vector3(0, 0, 1);

	/**
	 * Der Z-Vektor {0, 0, -1}
	 */
	@NotNull
	public static final Vector3 ZAXIS_NEGATIVE = new Vector3(0, 0, -1);

	/**
	 * X-Komponente des Vektors
	 */
	public float x = 0.f;
	
	/**
	 * Y-Komponente des Vektors
	 */
	public float y = 0.f;
	
	/**
	 * Z-Komponente des Vektors
	 */
	public float z = 0.f;

    final static float EPSILON = 0.0001f;
	
	/**
	 * Setzt alle Werte des Vektors
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 * @return Diese Instanz für Method chaining
	 */
	@NotNull
	public Vector3 set(float x, float y, float z) {
		this.x = x; this.y = y; this.z = z;
		return this;
	}
	
	/**
	 * Setzt alle Werte des Vektors
	 *
	 * @param position Die Position
	 * @return Diese Instanz für Method chaining
	 */
	@NotNull
	public Vector3 set(@NotNull final Vector3 position) {
		this.x = position.x; this.y = position.y; this.z = position.z;
		return this;
	}
	
	/**
	 * Erzeugt einen neuen, leeren Vektor
	 */
	private Vector3() {
	}
	
	/**
	 * Erzeugt einen neuen Vektor der Dimensionen {x, y, z}
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 * @see #createNew()
	 */
	private Vector3(float x, float y, float z) {
		set(x, y, z);
	}
	
	/**
	 * Ermittelt die Länge des Vektors
	 *
	 * @return Die Länge des Vektors
	 * @see Vector3#getLengthSquared
	 */
	public float getLength() {
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Ermittelt die quadrierte Länge des Vektors
	 * Diese Operation ist schneller als getLength()
	 *
	 * @return Die quadrierte Länge des Vektors
	 * @see Vector3#getLength
	 */
	public float getLengthSquared() {
		return (float)(x*x + y*y + z*z);
	}
	
	/**
	 * Berechnet die Distanz zwischen zwei Vektoren
	 *
	 * @param from Der Vektor, zu dem die Distanz berechnet werden soll
	 * @return Die Distanz zwischen den Vektoren
	 * @see Vector3#getDistanceSquared
	 */
	public float getDistance(Vector3 from) {
		float dx = from.x - x;
		float dy = from.y - y;
		float dz = from.z - z;
		return (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	/**
	 * Berechnet die quadratische Distanz zwischen zwei Vektoren
	 * Diese Operation ist schneller als <code>getDistanceSquared()</code>
	 *
	 * @param from Der Vektor, zu dem die Distanz berechnet werden soll
	 * @return Die quadrierte Distanz
	 * @see Vector3#getDistance
	 */
	public float getDistanceSquared(Vector3 from) {
		float dx = from.x - x;
		float dy = from.y - y;
		float dz = from.z - z;
		return dx*dx + dy*dy + dz*dz;
	}
	
	/**
	 * Bezieht eine normalisierte Kopie dieses Vektors
	 *
	 * @return Der normalisierte Vektor (Kopie!)
	 */
	@NotNull
	@ReturnsCachedValue
	public Vector3 getNormalized() {
		float invLength = 1.0f / getLength();
		return createNew().set(x*invLength, y*invLength, z*invLength);
	}
	
	/**
	 * Normalisiert diesen Vektor
	 */
	public void normalize() {
		float invLength = 1.0f / getLength();
		x *= invLength;
		y *= invLength;
		z *= invLength;
	}
		
	/**
	 * Addiert einen Vektor auf diesen Vektor und liefert das Ergebnis zurück.
	 * Dieser Vektor wird nicht modifiziert.
	 *
	 * @param b Der zu addierende Vektor
	 * @return Die Summe (Kopie!)
	 * @see Vector3#addInPlace(Vector3)
	 */
	@ReturnsCachedValue
	public Vector3 add(@NotNull Vector3 b) {
		return createNew().set(x+b.x, y+b.y, z+b.z);
	}
	
	/**
	 * Addiert einen Vektor auf diesen Vektor
	 *
	 * @param b Der zu addierende Vektor
	 * @see Vector3#add(Vector3)
	 */
	@NotNull
	public Vector3 addInPlace(@NotNull Vector3 b) {
		x += b.x;
		y += b.y;
		z += b.z;
		return this;
	}

	/**
	 * Addiert einen Vektor auf diesen Vektor
	 *
	 * @param x Der zu addierende X-Wert
	 * @param y Der zu addierende Y-Wert
	 * @param z Der zu addierende Z-Wert
	 * @see Vector3#add(Vector3)
	 */
	@NotNull
	public Vector3 addInPlace(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
		
	/**
	 * Subtrahiert einen Vektor von diesem Vektor und liefert das Ergebnis zurück.
	 * Dieser Vektor wird nicht modifiziert.
	 *
	 * @param b Der zu subtrahierende Vektor
	 * @return Die Differenz (Kopie!)
	 * @see Vector3#subInPlace(Vector3)
	 */
	@ReturnsCachedValue
	public Vector3 sub(@NotNull Vector3 b) {
		return createNew().set(x-b.x, y-b.y, z-b.z);
	}
	
	/**
	 * Subtrahiert einen Vektor von diesem Vektor
	 *
	 * @param b Der zu subtrahierende Vektor
	 * @see Vector3#sub(Vector3)
	 */
	@NotNull
	public Vector3 subInPlace(@NotNull Vector3 b) {
		x -= b.x;
		y -= b.y;
		z -= b.z;
		return this;
	}

	/**
	 * Subtrahiert einen Vektor von diesem Vektor
	 *
	 * @param x Zu subtrahierende X-Komponente
	 * @param y Zu subtrahierende Y-Komponente
	 * @param z Zu subtrahierende Z-Komponente
	 * @see Vector3#sub(Vector3)
	 */
	@NotNull
	public Vector3 subInPlace(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	/**
	 * Skaliert einen Vektor und liefert das Ergebnis zurück.
	 * Dieser Vektor wird nicht modifiziert.
	 *
	 * @param f Skalierungsfaktor
	 * @return Der skalierte Vektor (Kopie!)
	 * @see Vector3#mulInPlace(float)
	 */
	@NotNull
	@ReturnsCachedValue
	public Vector3 mul(float f) {
		return createNew().set(x*f, y*f, z*f);
	}
	
	/**
	 * Skaliert einen Vektor
	 *
	 * @param f Skalierungsfaktor
	 * @see Vector3#mul(float)
	 */
	@NotNull
	public Vector3 mulInPlace(float f) {
		x *= f;
		y *= f;
		z *= f;
		return this;
	}
	
	/**
	 * Berechnet das Punktprodukt zweier Vektoren
	 *
	 * @param b Der zweite Vektor
	 * @return Punktprodukt der Vektoren
	 */
	public float dot(@NotNull Vector3 b) {
		return x*b.x + y*b.y + z*b.z;
	}
	
	/**
	 * Berechnet das Kreuzprodukt zweier Vektoren
	 *
	 * @param b Der zweite Vektor
	 * @return Kreuzprodukt beider Vektoren (Kopie!)
	 * @see Vector3#crossInPlace(Vector3)
	 */
	@NotNull
	@ReturnsCachedValue
	public Vector3 cross(@NotNull Vector3 b) {
		float nx = y*b.z - z*b.y;
		float ny = z*b.x - x*b.z;
		float nz = x*b.y - y*b.x;
		return createNew().set(nx, ny, nz);
	}
	
	/**
	 * Berechnet das Kreuzprodukt zweier Vektoren
	 * 
	 * @param b Der zweite Vektor
	 * @see Vector3#cross(Vector3)
	 */
	@NotNull
	public Vector3 crossInPlace(@NotNull Vector3 b) {
		float nx = y*b.z - z*b.y;
		float ny = z*b.x - x*b.z;
			   z = x*b.y - y*b.x;
		this.y = ny;
		this.x = nx;
		return this;
	}

	/**
	 * Stringify
	 * @return Der String
	 */
	@Override
	public String toString() {
		return "{" + x + "; " + y + "; " + z + "}"; 
	}

	/**
	 * Liefert eine invertierte Kopie dieses Vektors
	 * @return Die invertierte Kopie
	 */
	@NotNull
	@ReturnsCachedValue
	public Vector3 getInverted() {
		return createNew().set(-x, -y, -z);
	}

	/**
	 * Invertiert diesen Vektor
	 */
	public void invert() {
		x = -x;
		y = -y;
		z = -z;
	}

    /**
	 * Multipliziert die Werte des Vectors mit einem bestimmten Faktor, ohne einen neuen Vektor generierne zu müssen
	 */
	public void multiplyByFactor(float factor) {
		x *= factor;
		y *= factor;
		z *= factor;
	}



	/**
	 * Erzeugt eine identische Kopie
	 * @return Die Kopie
	 */
	@NotNull @Override
	@ReturnsCachedValue
	public Vector3 clone() {
		return createNew().set(x, y, z);
	}

    /**
	 * Vergleicht dieses Objekt mit einem anderen. TODO: Delta!?
     *
	 * @param o Das Vergleichsobjekt
	 * @return <code>true</code>, wenn beide Elemente identisch sind
	 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3)) return false;

        Vector3 vector3 = (Vector3) o;

	    return (Math.abs(vector3.x - x) <= EPSILON &&
				Math.abs(vector3.y - y) <= EPSILON &&
				Math.abs(vector3.z - z) <= EPSILON);
    }

	/**
	 * Vergleicht dieses Objekt mit einem anderen.
	 *
	 * @param other Der Vergleichsvektor
	 * @return <code>true</code>, wenn beide Vektoren identisch sind
	 */
	public boolean equals(@NotNull Vector3 other) {
		return equals(other, EPSILON);
	}

	/**
	 * Vergleicht dieses Objekt mit einem anderen.
	 *
	 * @param x Der Vergleichsvektor (X-Komponente)
	 * @param y Der Vergleichsvektor (Y-Komponente)
	 * @param z Der Vergleichsvektor (Z-Komponente)
	 * @return <code>true</code>, wenn beide Vektoren identisch sind
	 */
	public boolean equals(float x, float y, float z) {
		return equals(x, y, z, EPSILON);
	}

	/**
	 * Vergleicht dieses Objekt mit einem anderen.
	 *
	 * @param other Der Vergleichsvektor
	 * @param epsilon Der zu verwendende Deltawert
	 * @return <code>true</code>, wenn beide Vektoren identisch sind
	 */
	public boolean equals(Vector3 other, float epsilon) {
		return this == other ||
				(Math.abs(other.x - x) <= epsilon &&
				Math.abs(other.y - y) <= epsilon &&
				Math.abs(other.z - z) <= epsilon);
	}

	/**
	 * Vergleicht dieses Objekt mit einem anderen.
	 *
	 * @param x Der Vergleichsvektor (X-Komponente)
	 * @param y Der Vergleichsvektor (Y-Komponente)
	 * @param z Der Vergleichsvektor (Z-Komponente)
	 * @param epsilon Der Deltawert
	 * @return <code>true</code>, wenn beide Vektoren identisch sind
	 */
	public boolean equals(float x, float y, float z, float epsilon) {
		return (Math.abs(x - this.x) <= epsilon &&
				Math.abs(y - this.y) <= epsilon &&
				Math.abs(z - this.z) <= epsilon);
	}

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }

    /**
	 * Überprüft, ob der Vektor leer ist
	 * @return true, wenn der Vektor leer ist
	 */
	public boolean isEmpty() {
		return equals(Vector3.ZERO);
	}

	/**
	 * Liefert den Vektor aus den maximalen Komponenten zweier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 max(@NotNull Vector3 a, @NotNull Vector3 b) {
		float x = Math.max(a.x, b.x);
		float y = Math.max(a.y, b.y);
		float z = Math.max(a.z, b.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den maximalen Komponenten dreier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 max(@NotNull Vector3 a, @NotNull Vector3 b, @NotNull Vector3 c) {
		float x = Math.max(Math.max(a.x, b.x), c.x);
		float y = Math.max(Math.max(a.y, b.y), c.y);
		float z = Math.max(Math.max(a.z, b.z), c.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten von sechs Eingabevektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @param d Vierter Vektor
	 * @param e Fünfter Vektor
	 * @param f Sechster Vektor
	 * @return Der Maximalvektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 max(@NotNull Vector3 a, @NotNull Vector3 b, @NotNull Vector3 c, @NotNull Vector3 d, @NotNull Vector3 e, @NotNull Vector3 f) {
		float x = Math.max(Math.max(Math.max(Math.max(Math.max(a.x, b.x), c.x), d.x), e.x), f.x);
		float y = Math.max(Math.max(Math.max(Math.max(Math.max(a.y, b.y), c.y), d.y), e.y), f.y);
		float z = Math.max(Math.max(Math.max(Math.max(Math.max(a.z, b.z), c.z), d.z), e.z), f.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten zweier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 min(@NotNull Vector3 a, @NotNull Vector3 b) {
		float x = Math.min(a.x, b.x);
		float y = Math.min(a.y, b.y);
		float z = Math.min(a.z, b.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten dreier Vektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 min(@NotNull Vector3 a, @NotNull Vector3 b, @NotNull Vector3 c) {
		float x = Math.min(Math.min(a.x, b.x), c.x);
		float y = Math.min(Math.min(a.y, b.y), c.y);
		float z = Math.min(Math.min(a.z, b.z), c.z);
		return Vector3.createNew(x, y, z);
	}

	/**
	 * Liefert den Vektor aus den minimalen Komponenten von sechs Eingabevektoren
	 *
	 * @param a Erster Vektor
	 * @param b Zweiter Vektor
	 * @param c Dritter Vektor
	 * @param d Vierter Vektor
	 * @param e Fünfter Vektor
	 * @param f Sechster Vektor
	 * @return Der Vektor
	 */
	@NotNull
	@ReturnsCachedValue
	public static Vector3 min(@NotNull Vector3 a, @NotNull Vector3 b, @NotNull Vector3 c, @NotNull Vector3 d, @NotNull Vector3 e, @NotNull Vector3 f) {
		float x = Math.min(Math.min(Math.min(Math.min(Math.min(a.x, b.x), c.x), d.x),e.x),f.x);
		float y = Math.min(Math.min(Math.min(Math.min(Math.min(a.y, b.y), c.y), d.y),e.y),f.y);
		float z = Math.min(Math.min(Math.min(Math.min(Math.min(a.z, b.z), c.z), d.z),e.z),f.z);
		return Vector3.createNew(x, y, z);
	}
}
