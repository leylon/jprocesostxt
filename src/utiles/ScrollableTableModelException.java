/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utiles;

/**
 *
 * @author Alex
 */
public class ScrollableTableModelException extends RuntimeException {
	/**
	 *
	 */
	public ScrollableTableModelException() {
		super();
	}

	/**
	 * @param message
	 */
	public ScrollableTableModelException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ScrollableTableModelException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ScrollableTableModelException(String message, Throwable cause) {
		super(message, cause);
	}
}

