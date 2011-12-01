package de.widemeadows.projectcore.transformation;

import de.widemeadows.projectcore.math.Ray3;
import de.widemeadows.projectcore.math.Vector3;
import de.widemeadows.projectcore.math.mock.FloatMath;
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
	 * Bezieht die X-Translation
	 * @return Translation in X-Richtung
	 */
	public float getTranslationX() {
		return translation[0];
	}

	/**
	 * Setzt die X-Translation
	 * @param value Der Wert
	 */
	public void setTranslationX(final float value) {
		translation[0] = value;
	}

	/**
	 * Ergänzt die X-Translation
	 *
	 * @param value Der Wert
	 */
	public void translateX(final float value) {
		translation[0] += value;
	}

	/**
	 * Bezieht die Y-Translation
	 * @return Translation in Y-Richtung
	 */
	public float getTranslationY() {
		return translation[1];
	}

	/**
	 * Setzt die Y-Translation
	 *
	 * @param value Der Wert
	 */
	public void setTranslationY(final float value) {
		translation[1] = value;
	}

	/**
	 * Ergänzt die Y-Translation
	 *
	 * @param value Der Wert
	 */
	public void translateY(final float value) {
		translation[1] += value;
	}

	/**
	 * Bezieht die Z-Translation
	 * @return Translation in Z-Richtung
	 */
	public float getTranslationZ() {
		return translation[2];
	}

	/**
	 * Setzt die Z-Translation
	 *
	 * @param value Der Wert
	 */
	public void setTranslationZ(final float value) {
		translation[2] = value;
	}

	/**
	 * Ergänzt die Z-Translation
	 *
	 * @param value Der Wert
	 */
	public void translateZ(final float value) {
		translation[2] += value;
	}

	/**
	 * Setzt die Translation
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
	 * Ergänzt die Translation
	 *
	 * @param x Der Wert
	 * @param y Der Wert
	 * @param z Der Wert
	 */
	public void translate(final float x, final float y, final float z) {
		translation[0] += x;
		translation[1] += y;
		translation[2] += z;
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

	/**
	 * Setzt die Rotation um die X-Achse
	 *
	 * @param theta Der Winkel in radians
	 */
	public void setRotationX(final float theta) {
		setRotationX(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Setzt die Rotation um die X-Achse
	 *
	 * @param theta Der Winkel in radians
	 */
	public void rotateX(final float theta) {
		rotateX(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Setzt die Rotation um die Y-Achse
	 *
	 * @param theta Der Winkel in radians
	 */
	public void setRotationY(final float theta) {
		setRotationY(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Setzt die Rotation um die Z-Achse
	 *
	 * @param theta Der Winkel in radians
	 */
	public void setRotationZ(final float theta) {
		setRotationZ(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Setzt die Rotation um die X-Achse
	 *
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 */
	private void setRotationX(final float cosTheta, final float sinTheta) {
		/*
		return Matrix4.createNew().set(
				1.0f, 0.0f, 0.0f, 0.0f,
				0.0f, cosTheta, sinTheta, 0.0f,
				0.0f, -sinTheta, cosTheta, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
		 */

		rotation[0] = 1;
		rotation[3] = 0;
		rotation[6] = 0;

		rotation[1] = 0;
		rotation[4] = cosTheta;
		rotation[7] = sinTheta;

		rotation[2] = 0;
		rotation[5] = -sinTheta;
		rotation[8] = cosTheta;
	}

	/**
	 * Setzt die Rotation um die X-Achse
	 *
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 */
	private void rotateX(final float cosTheta, final float sinTheta) {
		/*
		return Matrix4.createNew().set(
				1.0f, 0.0f, 0.0f, 0.0f,
				0.0f, cosTheta, sinTheta, 0.0f,
				0.0f, -sinTheta, cosTheta, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
		 */

		final float[] newRotation = new float[9];
		newRotation[0] = 1;
		newRotation[3] = 0;
		newRotation[6] = 0;

		newRotation[1] = 0;
		newRotation[4] = cosTheta;
		newRotation[7] = sinTheta;

		newRotation[2] = 0;
		newRotation[5] = -sinTheta;
		newRotation[8] = cosTheta;

		multiply3x3FromLeft(rotation, newRotation);
	}

	/**
	 * Setzt die Rotation um die Y-Achse
	 *
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 */
	private void setRotationY(final float cosTheta, final float sinTheta) {
		/*
		return Matrix4.createNew().set(
				cosTheta, 0.0f, -sinTheta, 0.0f,
				0.0f, 1.0f, 0.0f, 0.0f,
				sinTheta, 0.0f, cosTheta, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
		 */

		rotation[0] = cosTheta;
		rotation[3] = 0;
		rotation[6] = -sinTheta;

		rotation[1] = 0;
		rotation[4] = 1;
		rotation[7] = 0;

		rotation[2] = sinTheta;
		rotation[5] = 0;
		rotation[8] = cosTheta;
	}

	/**
	 * Setzt die Rotation um die Z-Achse
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 */
	private void setRotationZ(final float cosTheta, final float sinTheta) {
		/*
		return Matrix4.createNew().set(
				cosTheta, sinTheta, 0.0f, 0.0f,
				-sinTheta, cosTheta, 0.0f, 0.0f,
				0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
		 */

		rotation[0] = cosTheta;
		rotation[3] = sinTheta;
		rotation[6] = 0;

		rotation[1] = -sinTheta;
		rotation[4] = cosTheta;
		rotation[7] = 0;

		rotation[2] = 0;
		rotation[5] = 0;
		rotation[8] = 1;
	}

	/**
	 * Rotiert gemäß Euler Roll-Pitch-Yaw.
	 * <p/>
	 * Es wird erst um X-, dann um Y- und zuletzt um Z-Achse rotiert.
	 *
	 * @param rollX  Der Rollwinkel (Rotation um X) in radians
	 * @param pitchY Der Nickwinkel (Rotation um Y) in radians
	 * @param yawZ   Der Gierwinkel (Rotation um Z) in radians
	 */
	public void setRotation(final float rollX, final float pitchY, final float yawZ) {
		float cr = (float) Math.cos(rollX); // Φ
		float sr = (float) Math.sin(rollX);

		float cp = (float) Math.cos(pitchY); // Θ
		float sp = (float) Math.sin(pitchY);

		float cy = (float) Math.cos(yawZ); // Ψ
		float sy = (float) Math.sin(yawZ);

		setRotationRPY(cr, sr, cp, sp, cy, sy);
	}

	/**
	 * Rotiert gemäß Euler Roll-Pitch-Yaw
	 * <p/>
	 * Es wird erst um X-, dann um Y- und zuletzt um Z-Achse rotiert.
	 *
	 * @param cosRoll  Der Kosinus des Rollwinkel (Rotation um X)
	 * @param sinRoll  Der Sinus des Rollwinkel (Rotation um X)
	 * @param cosPitch Der Kosinus des Nickwinkel (Rotation um Y)
	 * @param sinPitch Der Sinus des Nickwinkel (Rotation um Y)
	 * @param cosYaw   Der Kosinus des Gierwinkel (Rotation um Z)
	 * @param sinYaw   Der Sinus des Gierwinkel (Rotation um Z)
	 */
	private void setRotationRPY(final float cosRoll, final float sinRoll, final float cosPitch, final float sinPitch, final float cosYaw, final float sinYaw) {

		rotation[0] = cosPitch * cosYaw;
		rotation[3] = cosPitch * sinYaw;
		rotation[6] = -sinPitch;

		rotation[1] = sinRoll * sinPitch * cosYaw - cosRoll * sinYaw;
		rotation[4] = sinRoll * sinPitch * sinYaw + cosRoll * cosYaw;
		rotation[7] = sinRoll * cosPitch;

		rotation[2] = cosRoll * sinPitch * cosYaw + sinRoll * sinYaw;
		rotation[5] = cosRoll * sinPitch * sinYaw - sinRoll * cosYaw;
		rotation[8] = cosRoll * cosPitch;
	}

	/**
	 * Multipliziert zwei 3x3-Matrizen miteinander
	 * 
	 * @param left Die linke Matrix
	 * @param right Die rechte Matrix 
	 */
	private static void multiply3x3FromLeft(@NotNull final float[] right, @NotNull final float[] left) {

		//                           Spalte 1           Spalte 2           Spalte 3
		//
		// | a b c |   | r s t |   | a*r + b*u + c*x    a*s + b*v + c*y    a*t + b*w + c*z |
		// | d e f | X | u v w | = | d*r + e*u + f*x    d*s + e*v + f*y    d*t + e*w + f*z |
		// | g h i |   | x y z |   | g*r + h*u + i*x    g*s + h*v + i*y    g*t + h*w + i*z |

		// | 1 4 7 |   | 1 4 7 |   | 1*1 + 4*2 + 7*3    1*4 + 4*5 + 7*6    1*7 + 4*8 + 7*9 |
		// | 2 5 8 | X | 2 5 8 | = | 2*1 + 5*2 + 8*3    2*4 + 5*5 + 8*6    2*7 + 5*8 + 8*9 |
		// | 3 6 9 |   | 3 6 9 |   | 3*1 + 6*2 + 9*3    3*4 + 6*5 + 9*6    3*7 + 6*8 + 9*9 |

		final float[] multiplied = new float[9];

		multiplied[0] = (left[0] * right[0]) + (left[3] * right[1]) + (left[6] * right[2]);
		multiplied[3] = (left[0] * right[3]) + (left[3] * right[4]) + (left[6] * right[5]);
		multiplied[6] = (left[0] * right[6]) + (left[3] * right[7]) + (left[6] * right[8]);

		multiplied[1] = (left[1] * right[0]) + (left[4] * right[1]) + (left[7] * right[2]);
		multiplied[4] = (left[1] * right[3]) + (left[4] * right[4]) + (left[7] * right[5]);
		multiplied[7] = (left[1] * right[6]) + (left[4] * right[7]) + (left[7] * right[8]);

		multiplied[2] = (left[2] * right[0]) + (left[5] * right[1]) + (left[8] * right[2]);
		multiplied[5] = (left[2] * right[3]) + (left[5] * right[4]) + (left[8] * right[5]);
		multiplied[8] = (left[2] * right[6]) + (left[5] * right[7]) + (left[8] * right[8]);
		
		System.arraycopy(multiplied, 0, right, 0, 9);
	}
}
