package converter.exceptions;

public class AmountOrExchangeException extends RuntimeException {
	
	/**
	 * Constructor with message.
	 *
	 * @param message message, that explains cause of the Exception.
	 */
	public AmountOrExchangeException (String message) {
		super (message);
	}
}
