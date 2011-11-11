package de.widemeadows.projectcore.math;

/**
 * Exception, die in einer Matrix auftritt
 * @see Matrix4
 * 
 * User: sunside
 * Date: 29.07.11
 * Time: 15:05
 */
public class MatrixException extends RuntimeException {

	/** @inheritDoc */
	public MatrixException() {
		super();
	}

	/** @inheritDoc */
	public MatrixException(String detailMessage) {
		super(detailMessage);
	}

	/** @inheritDoc */
	public MatrixException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	/** @inheritDoc */
	public MatrixException(Throwable throwable) {
		super(throwable);
	}
}
