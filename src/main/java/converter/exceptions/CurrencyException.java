package converter.exceptions;

public class CurrencyException extends RuntimeException {
    
	/**
	 * Constructor with message.
	 *
	 * @param message message, that explains cause of the Exception.
	 */
	public CurrencyException (String message) {
		super (message);
	}
}
