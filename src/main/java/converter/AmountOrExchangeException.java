package converter;

public class AmountOrExchangeException extends Exception { //Usually exception class names end with ..Exception
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Author: Andrii Varenko
	 */
	
	@Override
	public String toString () {
        return "Incorrectly specified amount/exchange\n";
    }
}
