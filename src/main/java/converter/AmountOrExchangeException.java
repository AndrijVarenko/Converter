package converter;

import java.io.Serial;

public class AmountOrExchangeException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */

	public AmountOrExchangeException () {
	}

	/**
	 * Constructor with message.
	 *
	 * @param message message, that explains cause of the Exception.
	 */
	public AmountOrExchangeException (String message) {
		super (message);
	}
}
