package de.widemeadows.projectcore.transformation;

import de.widemeadows.projectcore.math.Ray3;
import de.widemeadows.projectcore.math.Vector3;
import org.jetbrains.annotations.NotNull;

/**
 * Transformationszustand eines Objektes
 */
public class TransformationState {

	// TODO: Clone
	// TODO: Invert
	// TODO: Chain
	// TODO: Cache

	/**
	 * Gibt an, ob dieses Objekt einen verädnerten Zustand hat
	 */
	private boolean isDirty = false;

	/**
	 * Der Skalierungsfaktor des Objektes
	 */
	private float scale = 1.0f;

	/**
	 * Der inverse Skalierungsfaktor
	 */
	private float invScale = 1.0f;

	/**
	 * Die affine 3x3-Rotationsmatrix
	 */
	@NotNull
	private final float[] rotation = new float[] {
		1, 0, 0,
		0, 1, 0,
		0, 0, 1
		};

	/**
	 * Der affine Translationsvektor
	 */
	@NotNull
	private final Vector3 translation = Vector3.createNew();

	/**
	 * Liefert den dirty-Zustand des Objektes
	 * @return Der Dirty-Zustand
	 */
	public boolean isDirty() {
		return isDirty;
	}

	/**
	 * Markiert die Transformation als dirty
	 */
	public void makeDirty() {
		isDirty = true;
	}

	/**
	 * Bezieht die X-Transformation
	 * @return Translation in X-Richtung
	 */
	public float getTranslationX() {
		return translation.x;
	}

	/**
	 * Setzt die X-Transformation
	 * @param value Der Wert
	 */
	public void setTranslationX(final float value) {
		translation.x = value;
		isDirty = true;
	}

	/**
	 * Bezieht die Y-Transformation
	 * @return Translation in Y-Richtung
	 */
	public float getTranslationY() {
		return translation.y;
	}

	/**
	 * Setzt die Y-Transformation
	 *
	 * @param value Der Wert
	 */
	public void setTranslationY(final float value) {
		translation.y = value;
		isDirty = true;
	}

	/**
	 * Bezieht die Z-Transformation
	 * @return Translation in Z-Richtung
	 */
	public float getTranslationZ() {
		return translation.z;
	}

	/**
	 * Setzt die Z-Transformation
	 *
	 * @param value Der Wert
	 */
	public void setTranslationZ(final float value) {
		translation.z = value;
		isDirty = true;
	}

	/**
	 * Setzt die Transformation
	 *
	 * @param x Der Wert
	 * @param y Der Wert
	 * @param z Der Wert
	 */
	public void setTranslation(final float x, final float y, final float z) {
		translation.set(x, y, z);
		isDirty = true;
	}

	/**
	 * Bezieht die Skalierung
	 * @return Der Skalierungsfaktor
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Setzt die Skalierung
	 *
	 * @param factor Der Skalierungsfaktor
	 */
	public void setScale(final float factor) {
		assert factor > 0;
		scale = factor;
		invScale = 1.0f / factor;
		isDirty = true;
	}

	/**
	 * Transformiert einen Punkt
	 * @param point Der zu transformierende Punkt
	 */
	public void transformPoint(@NotNull Vector3 point) {
		transformVector(point);
		point.addInPlace(translation);
	}

	/**
	 * Transformiert einen Vektor
	 *
	 * @param vector Der zu transformierende Vektor
	 */
	public void transformVector(@NotNull Vector3 vector) {
		final float x = scale * (vector.x * rotation[0] + vector.y * rotation[1] + vector.z * rotation[2]);
		final float y = scale * (vector.x * rotation[3] + vector.y * rotation[4] + vector.z * rotation[5]);
		final float z = scale * (vector.x * rotation[6] + vector.y * rotation[7] + vector.z * rotation[8]);
		vector.set(x, y, z);
	}

	/**
	 * Transformiert einen Vektor invers
	 * @param vector Der invers zu transformierende Vektor
	 */
	public void inverseTransformVector(@NotNull Vector3 vector) {
		final float invScale = this.invScale;
		final float x = (vector.x * rotation[0] + vector.y * rotation[3] + vector.z * rotation[6]) * invScale;
		final float y = (vector.x * rotation[1] + vector.y * rotation[4] + vector.z * rotation[7]) * invScale;
		final float z = (vector.x * rotation[2] + vector.y * rotation[5] + vector.z * rotation[8]) * invScale;
		vector.set(x, y, z);
	}

	/**
	 * Transformiert einen Punkt invers
	 *
	 * @param point Der invers zu transformierende Punkt
	 */
	public void inverseTransformPoint(@NotNull Vector3 point) {
		final float invScale = this.invScale;

		final float tx = -(rotation[0] * translation.x + rotation[3] * translation.y + rotation[6] * translation.z);
		final float ty = -(rotation[1] * translation.x + rotation[4] * translation.y + rotation[7] * translation.z);
		final float tz = -(rotation[2] * translation.x + rotation[5] * translation.y + rotation[8] * translation.z);
		
		final float x = (point.x * rotation[0] + point.y * rotation[3] + point.z * rotation[6]) * invScale + tx;
		final float y = (point.x * rotation[1] + point.y * rotation[4] + point.z * rotation[7]) * invScale + ty;
		final float z = (point.x * rotation[2] + point.y * rotation[5] + point.z * rotation[8]) * invScale + tz;

		point.set(x, y, z);
	}

	/**
	 * Transformiert einen Strahl
	 *
	 * @param ray Der Strahl
	 */
	public void transform(@NotNull Ray3 ray) {
		transformPoint(ray.origin);
		transformPoint(ray.direction);
		ray.setDirection(ray.direction);
	}

	/**
	 * Transformiert einen Strahl invers
	 * @param ray Der Strahl
	 */
	public void inverseTransform(@NotNull Ray3 ray) {
		inverseTransformPoint(ray.origin);
		inverseTransformPoint(ray.direction);
		ray.setDirection(ray.direction);
	}
}
