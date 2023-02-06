package converter.exceptions;

import converter.SupportedCurrencies;

/**
 * Thrown when an application attempts to use incorrect (not supported) currency.
 * These include:
 * - currency is a null;
 * - currency is a empty;
 * - currency not supported (this currency not contains in SupportedCurrencies enum.
 *
 * @see    SupportedCurrencies
 *
 */
public class UnsupportedCurrencyException extends RuntimeException {
    
	/**
	 * Constructor with message.
	 * <p>
	 * Message: "Exception: Incorrectly specified currency".
	 *
	 * @see    SupportedCurrencies
	 *
	 */
	public UnsupportedCurrencyException() {
		super ("Exception: Incorrectly specified currency");
	}
}
