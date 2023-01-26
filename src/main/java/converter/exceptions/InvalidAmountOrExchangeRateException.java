package converter.exceptions;

public class InvalidAmountOrExchangeRateException extends RuntimeException {
	
	/**
	 * Constructor with message.
	 *
	 */
	public InvalidAmountOrExchangeRateException() {
		super ("Exception: Incorrectly specified amount/exchange");
	}
}
