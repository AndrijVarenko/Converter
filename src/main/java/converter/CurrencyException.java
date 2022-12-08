package converter;

public class CurrencyException extends Exception {
    
	private static final long serialVersionUID = 1L;

	/**
	 * Author: Andrii Varenko
	 */
	
	@Override
	public String toString () {
        return "Incorrectly specified currency\n";
    }
}
