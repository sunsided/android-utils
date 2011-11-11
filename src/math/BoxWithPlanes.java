package math;

import org.jetbrains.annotations.NotNull;
import utils.cache.annotations.ReturnsCachedValue;

/**
 * BoxWithVectors, die um Planes erweitert wurde
 */
public final class BoxWithPlanes extends Box {

	/**
	 * Die Position
	 */
	private Vector3 position = Vector3.createNew();

	/**
	 * Die "top"-Plane
	 */
	@NotNull
	private Plane top = new Plane(Vector3.YAXIS, Vector3.YAXIS);

	/**
	 * Die "bottom"-Plane
	 */
	@NotNull
	private Plane bottom = new Plane(Vector3.YAXIS_NEGATIVE, Vector3.YAXIS_NEGATIVE);

	/**
	 * Die "left"-Plane
	 */
	@NotNull
	private Plane left = new Plane(Vector3.XAXIS_NEGATIVE, Vector3.XAXIS_NEGATIVE);

	/**
	 * Die "right"-Plane
	 */
	@NotNull
	private Plane right = new Plane(Vector3.XAXIS, Vector3.XAXIS);

	/**
	 * Die "front"-Plane
	 */
	@NotNull
	private Plane front = new Plane(Vector3.ZAXIS, Vector3.ZAXIS);

	/**
	 * Die "back"-Plane
	 */
	@NotNull
	private Plane back = new Plane(Vector3.ZAXIS_NEGATIVE, Vector3.ZAXIS_NEGATIVE);

	/**
	 * Gibt an, ob diese Box vektorbasiert ist
	 *
	 * @return Immer false
	 */
	@Override
	public boolean isVectorBased() {
		return false;
	}

	/**
	 * Gibt an, ob diese Box ebenenbasiert ist
	 *
	 * @return Immer true
	 */
	@Override
	public boolean isPlaneBased() {
		return true;
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
	public BoxWithPlanes setExtent(float width, float height, float depth) {
		assert width > 0 && height > 0 && depth > 0;
		top.getPosition().set(position).addInPlace(0, height, 0);
		bottom.getPosition().set(position).addInPlace(0, -height, 0);
		left.getPosition().set(position).addInPlace(-width, 0, 0);
		right.getPosition().set(position).addInPlace(width, 0, 0);
		front.getPosition().set(position).addInPlace(0, 0, depth);
		back.getPosition().set(position).addInPlace(0, 0, -depth);
		return this;
	}

	/**
	 * Setzt die Breite der Box
	 *
	 * @param width Die Breite
	 */
	@Override
	public void setWidth(float width) {
		assert width > 0;
		left.getPosition().set(position).addInPlace(-width, 0, 0);
		right.getPosition().set(position).addInPlace(width, 0, 0);
	}

	/**
	 * Setzt die Höhe der Box
	 *
	 * @param height Die Höhe
	 */
	@Override
	public void setHeight(float height) {
		assert height > 0;
		top.getPosition().set(position).addInPlace(0, height, 0);
		bottom.getPosition().set(position).addInPlace(0, -height, 0);
	}

	/**
	 * Setzt die Tiefe der Box
	 *
	 * @param depth Die Tiefe
	 */
	@Override
	public void setDepth(float depth) {
		assert depth > 0;
		front.getPosition().set(position).addInPlace(0, 0, depth);
		back.getPosition().set(position).addInPlace(0, 0, -depth);
	}

	/**
	 * Bezieht die Breite der Box
	 *
	 * @return Die Breite
	 */
	@Override
	public float getWidth() {
		return right.getPosition().x - position.x;
	}

	/**
	 * Bezieht die Höhe der Box
	 *
	 * @return Die Höhe
	 */
	@Override
	public float getHeight() {
		return right.getPosition().y - position.y;
	}

	/**
	 * Bezieht die Tiefe der Box
	 *
	 * @return Die Tiefe
	 */
	@Override
	public float getDepth() {
		return front.getPosition().z - position.z;
	}

	/**
	 * Setzt die Ausdehnung der Box
	 *
	 * @param extent Die Ausdehnung
	 * @return Diese Instanz für method chaining
	 * @see #getExtent()
	 */
	@NotNull
	@Override
	public BoxWithPlanes setExtent(@NotNull Vector3 extent) {
		setExtent(extent.x, extent.y, extent.z);
		return this;
	}

	/**
	 * Bezieht die Ausdehnung der Box
	 *
	 * @return Die Ausdehnung des Quaders (cached value)
	 * @see #setExtent(Vector3)
	 */
	@NotNull
	@Override
	@ReturnsCachedValue
	public Vector3 getExtent() {
		return Vector3.createNew(getWidth(), getHeight(), getDepth());
	}

	/**
	 * Setzt die Position der Box
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param z Z-Koordinate
	 * @return Diese Instanz für method chaining
	 */
	@NotNull
	@Override
	public BoxWithPlanes setPosition(float x, float y, float z) {

		// hackity hack: Eigener Positionswert wird als Zwischenspeicher mißbraucht und am Ende
		//               des Aufrufes wieder korrekt gesetzt.

		Vector3 difference = position.subInPlace(x, y, z);
		moveBy(difference);
		this.position.set(x, y, z);
		return this;
	}

	/**
	 * Setzt die Position der Box
	 *
	 * @param position Die Position
	 * @return Diese Instanz für method chaining
	 */
	@NotNull
	@Override
	public BoxWithPlanes setPosition(@NotNull Vector3 position) {

		// hackity hack: Eigener Positionswert wird als Zwischenspeicher mißbraucht und am Ende
		//               des Aufrufes wieder korrekt gesetzt.

		Vector3 difference = position.subInPlace(position);
		moveBy(difference);
		this.position.set(position);
		return this;
	}

	/**
	 * Verschiebt die Position der Box um einen bestimmten Wert
	 *
	 * @param offset Der Wert
	 * @return Diese Instanz für method chaining
	 */
	@NotNull
	public BoxWithPlanes moveBy(@NotNull Vector3 offset) {
		//    v-- getPosition() liefert eine Referenz und kann daher auf diesem Wege geändert werden
		top.getPosition().addInPlace(offset);
		bottom.getPosition().addInPlace(offset);
		left.getPosition().addInPlace(offset);
		right.getPosition().addInPlace(offset);
		front.getPosition().addInPlace(offset);
		back.getPosition().addInPlace(offset);
		return this;
	}

	/**
	 * Bezieht die Position der Box
	 *
	 * @return Die Position des Quaders (Referenz!)
	 */
	@NotNull
	@Override
	public Vector3 getPosition() {
		return position;
	}

	/**
	 * Bezieht die X-Koordinate der Box
	 *
	 * @return X-Koordinate
	 */
	@Override
	public float getX() {
		return position.x;
	}

	/**
	 * Bezieht die Y-Koordinate der Box
	 *
	 * @return Y-Koordinate
	 */
	@Override
	public float getY() {
		return position.y;
	}

	/**
	 * Bezieht die Z-Koordinate der Box
	 *
	 * @return Z-Koordinate
	 */
	@Override
	public float getZ() {
		return position.z;
	}

	/**
	 * Überprüft, ob sich ein Punkt innerhalb der Box befindet
	 *
	 * @param point Die zu testende Position
	 * @return <code>true</code>, wenn sich der Punkt innerhalb des Quaders befindet
	 */
	@Override
	public boolean containsPoint(@NotNull final Vector3 point) {

		// Die Annahme ist, dass der Punkt dann innerhalb der Box ist,
		// wenn er sich auf derselben Seite aller Planes befindet.
		// Dies wiederum ist dann der Fall, wenn die Distanz zur
		// Plane für alle Planes dasselbe Vorzeichen hat.
		//
		//  <-A    B-> Normalenrichtung
		//    |####|
		//    |#X##|    Distanz zu A: -2
		//    |####|    Distanz zu B: -3
		//
		//  <-A    B-> Normalenrichtung
		//    |####|
		//   X|####|    Distanz zu A: +1
		//    |####|    Distanz zu B: -6
		//

		if (top.getDistance(point) <= 0) {
			return bottom.getDistance(point) <= 0 &&
					left.getDistance(point) <= 0 &&
					right.getDistance(point) <= 0 &&
					front.getDistance(point) <= 0 &&
					back.getDistance(point) <= 0;
		}
		else { // distance ist größer oder gleich 0
			return bottom.getDistance(point) >= 0 &&
					left.getDistance(point) >= 0 &&
					right.getDistance(point) >= 0 &&
					front.getDistance(point) >= 0 &&
					back.getDistance(point) >= 0;
		}
	}

	/**
	 * Bezieht einen gecachten Vektor, der den Minimalpunkt der Box repräsentiert
	 *
	 * @return Minimaler Punkt (cached)
	 */
	@NotNull
	@Override
	public Vector3 getMinPoint() {
		// TODO: Kann deutlich optimiert werden, wenn wir von einem Quader ausgehen, bei dem die Position immer mittig ist.
		return Vector3.min(top.getPosition(), bottom.getPosition(), left.getPosition(), right.getPosition(), front.getPosition(), back.getPosition());
	}

	/**
	 * Bezieht einen gecachten Vektor, der den Maximalpunkt der Box repräsentiert
	 *
	 * @return Maximaler Punkt (cached)
	 */
	@NotNull
	@Override
	@ReturnsCachedValue
	public Vector3 getMaxPoint() {
		// TODO: Kann deutlich optimiert werden, wenn wir von einem Quader ausgehen, bei dem die Position immer mittig ist.
		return Vector3.max(top.getPosition(), bottom.getPosition(), left.getPosition(), right.getPosition(), front.getPosition(), back.getPosition());
	}
}
