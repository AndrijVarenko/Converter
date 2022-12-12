package converter;

import java.io.Serial;

public class CurrencyException extends RuntimeException {
    
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CurrencyException () {
	}

	/**
	 * Constructor with message.
	 *
	 * @param message message, that explains cause of the Exception.
	 */
	public CurrencyException (String message) {
		super (message);
	}
}
