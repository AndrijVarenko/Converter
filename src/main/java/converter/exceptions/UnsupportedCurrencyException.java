package converter.exceptions;

public class UnsupportedCurrencyException extends RuntimeException {
    
	/**
	 * Constructor with message.
	 *
	 */
	public UnsupportedCurrencyException() {
		super ("Exception: Incorrectly specified currency");
	}
}
