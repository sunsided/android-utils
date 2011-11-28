package de.widemeadows.projectcore.transformation;

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
	 * Der Skalierungsfaktor des Objektes
	 */
	private float scale = 1.0f;

	/**
	 * Die affine 3x3-Rotationsmatrix
	 */
	@NotNull
	private final float[] rotation = new float[9];

	/**
	 * Der affine Translationsvektor
	 */
	@NotNull
	private final Vector3 translation = Vector3.createNew();

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
		scale = factor;
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
		final float invScale = 1.0f / scale; // TODO: Ausgiebigst testen!
		final float x = (vector.x * rotation[0] + vector.y * rotation[3] + vector.z * rotation[6]) * invScale;
		final float y = (vector.x * rotation[1] + vector.y * rotation[4] + vector.z * rotation[7]) * invScale;
		final float z = (vector.x * rotation[2] + vector.y * rotation[5] + vector.z * rotation[8]) * invScale;
		vector.set(x, y, z);
	}
}
