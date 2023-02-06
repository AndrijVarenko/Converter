package converter.exceptions;

/**
 * Thrown when an application attempts to use incorrect data. These include:
 * - amount is a null;
 * - amount is a negative;
 * - exchange (fee) is a null;
 * - exchange (fee) is a negative;
 * - exchange (fee) is equals or more than 100 (units of measurement - percent).
 *
 * @see RuntimeException
 *
 */
public class InvalidAmountOrExchangeRateException extends RuntimeException {
	
	/**
	 * Constructor with message.
	 * <p>
	 * Message: "Exception: Incorrectly specified amount/exchange".
	 *
	 * @see RuntimeException
	 *
	 */
	public InvalidAmountOrExchangeRateException() {
		super ("Exception: Incorrectly specified amount/exchange");
	}
}
