package de.widemeadows.projectcore.math;

import de.widemeadows.projectcore.cache.annotations.ReturnsCachedValue;
import de.widemeadows.projectcore.math.mock.FloatMath;
import org.jetbrains.annotations.NotNull;

/**
 * Factory für {@link Matrix4}
 */
public class MatrixFactory {

	/**
	 * Versteckter Konstruktor
	 */
	private MatrixFactory() {}

	/**
	 * Erzeugt eine rechtshändige Matrix zur Rotation um die X-Achse
	 *
	 * <h3>RH-/LH-System</h3>
	 * <a href="http://www.cprogramming.com/tutorial/3d/rotationMatrices.html">Rotations in Three Dimensions</a>
	 *
	 * @param theta Der Winkel in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationX(final float theta) {
		return getRotationX(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Erzeugt eine rechtshändige Matrix zur Rotation um die X-Achse
	 *
	 * <h3>RH-/LH-System</h3>
	 * <a href="http://www.cprogramming.com/tutorial/3d/rotationMatrices.html">Rotations in Three Dimensions</a>
	 *
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationX(float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationX(final float cosTheta, final float sinTheta) {
		return Matrix4.createNew().set(
				1.0f, 0.0f, 0.0f, 0.0f,
				0.0f, cosTheta, sinTheta, 0.0f,
				0.0f, -sinTheta, cosTheta, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
	}

	/**
	 * Erzeugt eine rechtshändige Matrix zur Rotation um die Y-Achse
	 *
	 * <h3>RH-/LH-System</h3>
	 * <a href="http://www.cprogramming.com/tutorial/3d/rotationMatrices.html">Rotations in Three Dimensions</a>
	 *
	 * @param theta Der Winkel in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationY(final float theta) {
		return getRotationY(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Erzeugt eine rechtshändige Matrix zur Rotation um die Y-Achse
	 *
	 * <h3>RH-/LH-System</h3>
	 * <a href="http://www.cprogramming.com/tutorial/3d/rotationMatrices.html">Rotations in Three Dimensions</a>
	 *
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationY(float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationY(final float cosTheta, final float sinTheta) {
		return Matrix4.createNew().set(
				cosTheta, 0.0f, -sinTheta, 0.0f,
				0.0f, 1.0f, 0.0f, 0.0f,
				sinTheta, 0.0f, cosTheta, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
	}

	/**
	 * Erzeugt eine rechtshändige Matrix zur Rotation um die Z-Achse
	 *
	 * <h3>RH-/LH-System</h3>
	 * <a href="http://www.cprogramming.com/tutorial/3d/rotationMatrices.html">Rotations in Three Dimensions</a>
	 *
	 * @param theta Der Winkel in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationZ(final float theta) {
		return getRotationZ(FloatMath.cos(theta), FloatMath.sin(theta));
	}

	/**
	 * Erzeugt eine rechtshändige Matrix zur Rotation um die Z-Achse
	 *
	 * <h3>RH-/LH-System</h3>
	 * <a href="http://www.cprogramming.com/tutorial/3d/rotationMatrices.html">Rotations in Three Dimensions</a>
	 *
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationZ(float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationZ(final float cosTheta, final float sinTheta) {
		return Matrix4.createNew().set(
				cosTheta, sinTheta, 0.0f, 0.0f,
				-sinTheta, cosTheta, 0.0f, 0.0f,
				0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
	}

	/**
	 * Erzeugt eine Matrix zur Rotation um eine Achse
	 *
	 * @param axis  Die Achse
	 * @param theta Der Winkel in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationAxisAngle(@NotNull Vector3 axis, float theta) {
		float cos = (float) Math.cos(theta);
		float sin = (float) Math.sin(theta);
		return getRotationAxisAngle(axis, cos, sin);
	}

	/**
	 * Erzeugt eine Matrix zur Rotation um eine Achse
		 *
	 * @param axis     Die Achse
	 * @param cosTheta Der Kosinus des Winkels
	 * @param sinTheta Der Sinus des Winkels
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationAxisAngle(@NotNull Vector3 axis, float cosTheta, float sinTheta) {
		// pre-calculate squared
		float xx = axis.x * axis.x;
		float yy = axis.y * axis.y;
		float zz = axis.z * axis.z;

		// pre-calculate axis combinations
		float xy = axis.x * axis.y;
		float xz = axis.x * axis.z;
		float yz = axis.y * axis.z;

		// pre-calculate axes and angle functions
		float xsin = axis.x * sinTheta;
		float ysin = axis.y * sinTheta;
		float zsin = axis.z * sinTheta;
		float xcos = axis.x * cosTheta;
		float ycos = axis.y * cosTheta;

		/*
		return new Matrix4D(
			xx * (1 - cos) + cos, xy * (1 - cos) + zsin, xz * (1 - cos) - ysin, 0.0d,
			xy * (1 - cos) - zsin, yy * (1 - cos) + cos, yz * (1 - cos) + xsin, 0.0d,
			xz * (1 - cos) + ysin, yz * (1 - cos) + xsin, zz * (1 - cos) + cos, 0.0d,
			0.0d, 0.0d, 0.0d, 1.0d);
		*/

		return Matrix4.createNew().set(
				xx - axis.x * xcos + cosTheta, xy - axis.y * xcos + zsin, xz - axis.y * xcos - ysin, 0.0f,
				xy - axis.y * xcos - zsin, yy - axis.y * ycos + cosTheta, yz - axis.z * ycos + xsin, 0.0f,
				xz - axis.z * xcos + ysin, yz - axis.z * ycos + xsin, zz - zz * cosTheta + cosTheta, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
	}

	/**
	 * Erzeugt eine Matrix zur progressiven Rotation basierend auf Winkelgeschwindigkeit
	 *
	 * @param deltaX Winkelgeschwindigkeit in X-Richtung in radians/frame
	 * @param deltaY Winkelgeschwindigkeit in Y-Richtung in radians/frame
	 * @param deltaZ Winkelgeschwindigkeit in Z-Richtung in radians/frame
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getProgressiveRotation(float deltaX, float deltaY, float deltaZ) {
		return Matrix4.createNew().set(
				0.0f, -deltaZ, deltaY, 0.0f,
				deltaZ, 0.0f, -deltaX, 0.0f,
				-deltaY, deltaX, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f);
	}

	/**
	 * Erzeugt eine Matrix zur Rotation gemäß Euler-ZXZ
	 *
	 * @param z  Der Winkel um die Z-Achse in radians
	 * @param x1 Der Winkel um die X'-Achse in radians
	 * @param z2 Der Winkel um die Z''-Achse in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationEulerZXZ(float z, float x1, float z2) {
		float cz = (float) Math.cos(z);
		float sz = (float) Math.sin(z);

		float cx1 = (float) Math.cos(x1);
		float sx1 = (float) Math.sin(x1);

		float cz2 = (float) Math.cos(z2);
		float sz2 = (float) Math.sin(z2);

		return Matrix4.createNew().set(
				cz * cz2 - sz * cx1 * sz2, -cz * sz2 - sz * cx1 * cz2, sz * sx1, 0f,
				sz * cz2 + cz * cx1 * sz2, cz * cx1 * cz2 - sz * sz2, -cz * sx1, 0f,
				sx1 * sz2, sx1 * cz2, cx1, 0f,
				0f, 0f, 0f, 1f
		);

	}

	/**
	 * Erzeugt eine Matrix zur Rotation gemäß Euler-ZYZ
	 *
	 * @param z  Der Winkel um die Z-Achse in radians
	 * @param y1 Der Winkel um die Y'-Achse in radians
	 * @param z2 Der Winkel um die Z''-Achse in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerRPY(float, float, float)
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationEulerZYZ(float z, float y1, float z2) {
		float cz = (float) Math.cos(z);
		float sz = (float) Math.sin(z);

		float cy1 = (float) Math.cos(y1);
		float sy1 = (float) Math.sin(y1);

		float cz2 = (float) Math.cos(z2);
		float sz2 = (float) Math.sin(z2);

		return Matrix4.createNew().set(
				-sz * sz2 + cz * cy1 * cz2, -sz * cz2 - cz * cy1 * sz2, cz * sy1, 0f,
				cz * sz2 + sz * cy1 * cz2, cz * cz2 - sz * cy1 * sz2, sz * sy1, 0f,
				-sy1 * cz2, sy1 * sz2, cy1, 0f,
				0f, 0f, 0f, 1f
		);

	}

	/**
	 * Erzeugt eine Matrix zur Rotation gemäß Euler-Roll-Pitch-Yaw
	 *
	 * @param rollX  Der Rollwinkel in radians
	 * @param pitchY Der Nickwinkel in radians
	 * @param yawZ   Der Gierwinkel in radians
	 * @return Die Rotationsmatrix
	 * @see MatrixFactory#getRotationEulerZXZ(float, float, float)
	 * @see MatrixFactory#getRotationEulerZYZ(float, float, float)
	 * @see MatrixFactory#getRotationX(float)
	 * @see MatrixFactory#getRotationY(float)
	 * @see MatrixFactory#getRotationZ(float)
	 * @see MatrixFactory#getProgressiveRotation(float, float, float)
	 * @see MatrixFactory#getRotationAxisAngle(Vector3, float)
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getRotationEulerRPY(float rollX, float pitchY, float yawZ) {
		float cr = (float) Math.cos(rollX); // Φ
		float sr = (float) Math.sin(rollX);

		float cp = (float) Math.cos(pitchY); // Θ
		float sp = (float) Math.sin(pitchY);

		float cy = (float) Math.cos(yawZ); // Ψ
		float sy = (float) Math.sin(yawZ);

		return Matrix4.createNew().set(
				cp * cy, cp * sy, -sp, 0f,
				sr * sp * cy - cr * sy, sr * sp * sy + cr * cy, sr * cp, 0f,
				cr * sp * cy + sr * sy, cr * sp * sy - sr * cy, cr * cp, 0f,
				0f, 0f, 0f, 1f
		);
	}

	/**
	 * Bezieht eine vollständige Transformationsmatrix.
	 * Die Transformationen werden in der Reihenfolge Skalierung, Rotation, Translation angewandt,
	 *
	 * @param scaling     Der Skalierungsvektor
	 * @param rotation    Der Rotationsvektor (roll-pitch-yaw)
	 * @param translation Der translationsvektor
	 * @return Die Transformationsmatrix
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getTransformation(@NotNull Vector3 scaling, @NotNull Vector3 rotation, @NotNull Vector3 translation) {
		float cr = (float) Math.cos(rotation.x); // Φ
		float sr = (float) Math.sin(rotation.x);

		float cp = (float) Math.cos(rotation.y); // Θ
		float sp = (float) Math.sin(rotation.y);

		float cy = (float) Math.cos(rotation.z); // Ψ
		float sy = (float) Math.sin(rotation.z);

		return Matrix4.createNew().set(
				scaling.x * cp * cy, scaling.x * cp * sy, scaling.x * (-sp), 0f,
				-scaling.y * (sr * sp * cy - cr * sy), scaling.y * (sr * sp * sy + cr * cy), scaling.y * sr * cp, 0f,
				scaling.z * (cr * sp * cy + sr * sy), scaling.z * (cr * sp * sy - sr * cy), scaling.z * cr * cp, 0f,
				translation.x, translation.y, translation.z, 1f
		);
	}

	/**
	 * Bezieht eine vollständige Transformationsmatrix.
	 * Die Transformationen werden in der Reihenfolge Skalierung, Rotation, Translation angewandt,
	 *
	 * @param scaling     Der Skalierungsvektor
	 * @param rotation    Der Rotationsvektor (roll-pitch-yaw)
	 * @param translation Der translationsvektor
	 * @return Die Transformationsmatrix
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getTransformation(float scaling, @NotNull Vector3 rotation, @NotNull Vector3 translation) {
		float cr = (float) Math.cos(rotation.x); // Φ
		float sr = (float) Math.sin(rotation.x);

		float cp = (float) Math.cos(rotation.y); // Θ
		float sp = (float) Math.sin(rotation.y);

		float cy = (float) Math.cos(rotation.z); // Ψ
		float sy = (float) Math.sin(rotation.z);

		return Matrix4.createNew().set(
				scaling * cp * cy, scaling * cp * sy, scaling * (-sp), 0f,
				-scaling * (sr * sp * cy - cr * sy), scaling * (sr * sp * sy + cr * cy), scaling * sr * cp, 0f,
				scaling * (cr * sp * cy + sr * sy), scaling * (cr * sp * sy - sr * cy), scaling * cr * cp, 0f,
				translation.x, translation.y, translation.z, 1f
		);
	}

	/**
	 * Bezieht eine vollständige Transformationsmatrix.
	 * Die Transformationen werden in der Reihenfolge Rotation, Translation angewandt.
	 *
	 * @param rotation    Der Rotationsvektor (roll-pitch-yaw)
	 * @param translation Der translationsvektor
	 * @return Die Transformationsmatrix
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getTransformation(@NotNull Vector3 rotation, @NotNull Vector3 translation) {
		float cr = (float) Math.cos(rotation.x); // Φ
		float sr = (float) Math.sin(rotation.x);

		float cp = (float) Math.cos(rotation.y); // Θ
		float sp = (float) Math.sin(rotation.y);

		float cy = (float) Math.cos(rotation.z); // Ψ
		float sy = (float) Math.sin(rotation.z);

		return Matrix4.createNew().set(
				cp * cy, cp * sy, (-sp), 0f,
				-(sr * sp * cy - cr * sy), (sr * sp * sy + cr * cy), sr * cp, 0f,
				(cr * sp * cy + sr * sy), (cr * sp * sy - sr * cy), cr * cp, 0f,
				translation.x, translation.y, translation.z, 1f
		);
	}

	/**
	 * Bezieht eine Translationsmatrix
	 *
	 * @param translation Der translationsvektor
	 * @return Die Transformationsmatrix
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getTranslation(@NotNull Vector3 translation) {
		return getTranslation(translation.x, translation.y, translation.z);
	}

	/**
	 * Bezieht eine Translationsmatrix
	 *
	 * @param x Der Translationsvektor (X-Komponente)
	 * @param y Der Translationsvektor (Y-Komponente)
	 * @param z Der Translationsvektor (Z-Komponente)
	 * @return Die Transformationsmatrix
	 */
	@NotNull
	@ReturnsCachedValue
	public static Matrix4 getTranslation(float x, float y, float z) {
		return Matrix4.createNew().set(
				1.0f, 0.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 1.0f, 0.0f,
				x, y, z, 1f
		);
	}

}
