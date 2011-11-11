package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import org.jetbrains.annotations.NotNull;

/**
 * Ein Quader, formerly known as Axis Aligned Bounding Box (AABB)
 */
public class BoxWithVectors extends Box {

	/**
	 * Die Position der Box
	 */
	@NotNull
	private Vector3 _position = Vector3.createNew();

	/**
	 * Die Ausdehnung der Box
	 */
	@NotNull
	private Vector3 _extent = Vector3.createNew();

	/**
	 * Gibt an, ob diese Box vektorbasiert ist
	 * @return Immer true
	 */
	@Override
	public boolean isVectorBased() {
		return true;
	}

	/**
	 * Gibt an, ob diese Box ebenenbasiert ist
	 * @return Immer false
	 */
	@Override
	public boolean isPlaneBased() {
		return false;
	}

	/**
	 * Setzt die Größe des Quaders
	 *
	 * @param width  Breite
	 * @param height Höhe
	 * @param depth  Tiefe
	 * @see #setExtent(float, float, float)
	 */
	@Override
	public void setSize(float width, float height, float depth) {
		setExtent(width, height, depth);
	}

	/**
	 * Setzt die Größe des Quaders
	 *
	 * @param width  Breite
	 * @param height Höhe
	 * @param depth  Tiefe
	 */
	@Override
	@NotNull
	public BoxWithVectors setExtent(float width, float height, float depth) {
		assert width > 0 && height > 0 && depth > 0;
		_extent.set(width, height, depth);
		return this;
	}

	/**
	 * Setzt die Breite der Box
	 * @param width Die Breite
	 */
	@Override
	public void setWidth(float width) {
		_extent.x = width;
	}
	
	/**
	 * Setzt die Höhe der Box
	 * @param height Die Höhe
	 */
	@Override
	public void setHeight(float height) {
		_extent.y = height; }
	
	/**
	 * Setzt die Tiefe der Box
	 * @param depth Die Tiefe
	 */
	@Override
	public void setDepth(float depth) {
		_extent.z = depth; }
	
	/**
	 * Bezieht die Breite der Box
	 * @return Die Breite
	 */
	@Override
	public final float getWidth() { return _extent.x; }
	
	/**
	 * Bezieht die Höhe der Box
	 * @return Die Höhe
	 */
	@Override
	public final float getHeight() { return _extent.y; }
	
	/**
	 * Bezieht die Tiefe der Box
	 * @return Die Tiefe
	 */
	@Override
	public final float getDepth() { return _extent.z; }

	/**
	 * Setzt die Ausdehnung der Box
	 *
	 * @param extent Die Ausdehnung
	 * @return Diese Instanz für method chaining
	 * @see #getExtent()
	 */
	@Override
	@NotNull
	public BoxWithVectors setExtent(@NotNull Vector3 extent) {
		_extent.set(extent);
		return this;
	}

	/**
	 * Bezieht die Ausdehnung der Box
	 *
	 * @return Die Ausdehnung des Quaders
	 * @see #setExtent(Vector3)
	 */
	@Override
	@NotNull
	public final Vector3 getExtent() {
		return _extent;
	}

	/**
	 * Setzt die Position der Box
	 * 
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 * @return Diese Instanz für method chaining
	 */
	@Override
	@NotNull
	public final BoxWithVectors setPosition(float x, float y, float z) {
		_position.set(x, y, z);
		return this;
	}

	/**
	 * Setzt die Position der Box
	 *
	 * @param position Die Position
	 * @return Diese Instanz für method chaining
	 */
	@Override
	@NotNull
	public final BoxWithVectors setPosition(@NotNull Vector3 position) {
		_position.set(position);
		return this;
	}

	/**
	 * Bezieht die Position der Box
	 * @return Die Position des Quaders
	 */
	@Override
	@NotNull
	public final Vector3 getPosition() { return _position; }

	/**
	 * Bezieht die X-Koordinate der Box
	 * @return X-Koordinate
	 */
	@Override
	public final float getX() { return _position.x; }

	/**
	 * Bezieht die Y-Koordinate der Box
	 * @return Y-Koordinate
	 */
	@Override
	public final float getY() { return _position.y; }

	/**
	 * Bezieht die Z-Koordinate der Box
	 * @return Z-Koordinate
	 */
	@Override
	public final float getZ() { return _position.z; }

	/**
	 * Erzeugt eine Box der Größe 1x1x1
	 */
	public BoxWithVectors() {}

	/**
	 * Erzeugt eine Box der Größe SxSxS
	 * @param size Breite, Höhe und Tiefe der Box
	 */
	public BoxWithVectors(float size) {
		assert size > 0;
		setSize(size, size, size);
	}
	
	/**
	 * Erzeugt eine Box der Größe WxHxD
	 * @param width Breite des Quaders
	 * @param height Höhe des Quaders
	 * @param depth Tiefe des Quaders
	 */
	public BoxWithVectors(float width, float height, float depth) {
		assert width > 0 && height > 0 && depth > 0;
		setSize(width, height, depth);
	}

	/**
	 * Überprüft, ob sich ein Punkt innerhalb der Box befindet
	 * @param point Die zu testende Position
	 * @return <code>true</code>, wenn sich der Punkt innerhalb des Quaders befindet
	 */
	@Override
	public boolean containsPoint(@NotNull final Vector3 point) {
		float w2 = _extent.x * 0.5f;
		if (point.x < _position.x - w2) return false;
		if (point.x > _position.x + w2) return false;

		float h2 = _extent.y * 0.5f;
		if (point.y < _position.y - h2) return false;
		if (point.y > _position.y + h2) return false;

		float d2 = _extent.z * 0.5f;
		return point.z >= _position.z - d2 && point.z <= _position.z + d2;
	}

	/**
	 * Bezieht einen gecachten Vektor, der den Minimalpunkt der Box repräsentiert
	 * @return Minimaler Punkt (cached)
	 */
	@Override
	@NotNull
	@ReturnsCachedValue
	public final Vector3 getMinPoint() {
		return Vector3.createNew(_position).subInPlace(_extent);
	}

	/**
	 * Bezieht einen gecachten Vektor, der den Maximalpunkt der Box repräsentiert
	 *
	 * @return Maximaler Punkt (cached)
	 */
	@Override
	@NotNull
	@ReturnsCachedValue
	public final Vector3 getMaxPoint() {
		return Vector3.createNew(_position).addInPlace(_extent);
	}

	/**
	 * Bezieht eine Box, die durch die gegebene Matrix transformiert wurde
	 * @param transformation Die Transformationsmatrix
	 * @return
	 */
	public BoxWithVectors getTransformedBox(@NotNull Matrix4 transformation) {

		// Max-Punkt transformieren
		Vector3 max = getMaxPoint();
		Vector3 maxTransformed = transformation.transform(max);
		max.recycle();

		// Min-Punkt transformieren
		Vector3 min = getMinPoint();
		Vector3 minTransformed = transformation.transform(min);
		min.recycle();

		// Neue min und max ermitteln
		max = Vector3.max(maxTransformed, minTransformed);
		min = Vector3.min(maxTransformed, minTransformed);

		// Position aus min und max ermitteln
		Vector3 extent = max.sub(min).mulInPlace(0.5f);
		Vector3 position = max.add(min).mulInPlace(0.5f);

		max.recycle(); min.recycle();
		try {
			// Neue Werte ausspucken
			return new BoxWithVectors(extent.x, extent.y, extent.z).setPosition(position);
		}
		finally {
			extent.recycle();
			position.recycle();
		}
	}
}
