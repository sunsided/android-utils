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
	@NotNull
	private final float[] scale = new float[] {1.0f, 1.0f, 1.0f};

	/**
	 * Der inverse Skalierungsfaktor
	 */
	@NotNull
	private final float[] invScale = new float[]{1.0f, 1.0f, 1.0f};

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
	private final float[] translation = new float[] { 0.0f, 0.0f, 0.0f };

	/**
	 * Bezieht die X-Transformation
	 * @return Translation in X-Richtung
	 */
	public float getTranslationX() {
		return translation[0];
	}

	/**
	 * Setzt die X-Transformation
	 * @param value Der Wert
	 */
	public void setTranslationX(final float value) {
		translation[0] = value;
	}

	/**
	 * Bezieht die Y-Transformation
	 * @return Translation in Y-Richtung
	 */
	public float getTranslationY() {
		return translation[1];
	}

	/**
	 * Setzt die Y-Transformation
	 *
	 * @param value Der Wert
	 */
	public void setTranslationY(final float value) {
		translation[1] = value;
	}

	/**
	 * Bezieht die Z-Transformation
	 * @return Translation in Z-Richtung
	 */
	public float getTranslationZ() {
		return translation[2];
	}

	/**
	 * Setzt die Z-Transformation
	 *
	 * @param value Der Wert
	 */
	public void setTranslationZ(final float value) {
		translation[2] = value;
	}

	/**
	 * Setzt die Transformation
	 *
	 * @param x Der Wert
	 * @param y Der Wert
	 * @param z Der Wert
	 */
	public void setTranslation(final float x, final float y, final float z) {
		translation[0] = x;
		translation[1] = y;
		translation[2] = z;
	}

	/**
	 * Bezieht die Skalierung
	 * @return Der Skalierungsfaktor
	 */
	public float getScaleX() {
		return scale[0];
	}

	/**
	 * Bezieht die Skalierung
	 *
	 * @return Der Skalierungsfaktor
	 */
	public float getScaleY() {
		return scale[1];
	}

	/**
	 * Bezieht die Skalierung
	 *
	 * @return Der Skalierungsfaktor
	 */
	public float getScaleZ() {
		return scale[2];
	}

	/**
	 * Setzt die Skalierung
	 *
	 * @param factor Der Skalierungsfaktor
	 */
	public void setScale(final float factor) {
		assert factor > 0;
		scale[0] = factor;
		scale[1] = factor;
		scale[2] = factor;
		
		final float invFactor = 1.0f / factor;
		invScale[0] = invFactor;
		invScale[1] = invFactor;
		invScale[2] = invFactor;
		isDirty = true;
	}

	/**
	 * Transformiert einen Punkt
	 * @param point Der zu transformierende Punkt
	 */
	public void transformPoint(@NotNull Vector3 point) {
		transformVector(point);
		point.addInPlace(translation[0], translation[1], translation[2]);
	}

	/**
	 * Transformiert einen Vektor
	 *
	 * @param vector Der zu transformierende Vektor
	 */
	public void transformVector(@NotNull Vector3 vector) {
		final float x = (scale[0] * vector.x * rotation[0] + vector.y * rotation[1] + vector.z * rotation[2]);
		final float y = (vector.x * rotation[3] + scale[1] * vector.y * rotation[4] + vector.z * rotation[5]);
		final float z = (vector.x * rotation[6] + vector.y * rotation[7] + scale[2] * vector.z * rotation[8]);
		vector.set(x, y, z);
	}

	/**
	 * Transformiert einen Vektor invers
	 * @param vector Der invers zu transformierende Vektor
	 */
	public void inverseTransformVector(@NotNull Vector3 vector) {
		final float invScaleX = this.invScale[0];
		final float invScaleY = this.invScale[1];
		final float invScaleZ = this.invScale[2];

		// Vektoren sind ohne Translation
		final float vectorX = vector.x;
		final float vectorY = vector.y;
		final float vectorZ = vector.z;

		// Rotationsmatrix ist orthogonal --> R^-1 == R^T
		final float x = (vectorX * rotation[0] * invScaleX + vectorY * rotation[3] + vectorZ * rotation[6]);
		final float y = (vectorX * rotation[1] + vectorY * rotation[4] * invScaleY + vectorZ * rotation[7]);
		final float z = (vectorX * rotation[2] + vectorY * rotation[5] + vectorZ * rotation[8] * invScaleZ);
		vector.set(x, y, z);
	}

	/**
	 * Transformiert einen Punkt invers
	 *
	 * @param point Der invers zu transformierende Punkt
	 */
	public void inverseTransformPoint(@NotNull Vector3 point) {
		final float invScaleX = this.invScale[0];
		final float invScaleY = this.invScale[1];
		final float invScaleZ = this.invScale[2];

		// Vektoren sind mit Translation
		final float pointX = point.x - translation[0];
		final float pointY = point.y - translation[1];
		final float pointZ = point.z - translation[2];

		// Rotationsmatrix ist orthogonal --> R^-1 == R^T
		final float x = (pointX * rotation[0] * invScaleX + pointY * rotation[3] + pointZ * rotation[6]);
		final float y = (pointX * rotation[1] + pointY * rotation[4] * invScaleY + pointZ * rotation[7]);
		final float z = (pointX * rotation[2] + pointY * rotation[5] + pointZ * rotation[8] * invScaleZ);
		point.set(x, y, z);
	}

	/**
	 * Transformiert einen Strahl
	 *
	 * @param ray Der Strahl
	 */
	public void transform(@NotNull Ray3 ray) {
		transformPoint(ray.origin);
		transformVector(ray.direction);
		ray.setDirection(ray.direction); // Inverse berechnen lassen
	}

	/**
	 * Transformiert einen Strahl invers
	 * @param ray Der Strahl
	 */
	public void inverseTransform(@NotNull Ray3 ray) {
		inverseTransformPoint(ray.origin);
		inverseTransformVector(ray.direction);
		ray.setDirection(ray.direction); // Inverse berechnen lassen
	}
}
