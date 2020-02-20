package exceptions;

/**
 * @author shweta
 * 
 * Exception to throw when capacity is not greater than 1
 *
 */
public class InvalidCapacityException extends Exception {
	public InvalidCapacityException(String message) {
		super(message);
	}
}
