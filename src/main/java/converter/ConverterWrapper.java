package converter;

import java.math.BigDecimal;

public class ConverterWrapper implements ConstantsConverter {
	
	private final static String TEMPLATE_CONSTANT = "%s %s = %s %s";
	private final static String LIST_ARGUMENTS = ", %s, %s, %s, %s";
	
	private final Converter converter = new Converter (); 
	
	public String convert (BigDecimal amount, String currency, BigDecimal fee)
			throws CurrencyException, AmountOrExchangeException {
		
		if (fee.doubleValue() >= 100 || fee.doubleValue() < 0 || 
				amount.doubleValue() <= 0 ||
				amount.equals(null) || fee.equals(null)) {
			throw new AmountOrExchangeException ();
		}
		
		if (currency.equals(null) || currency.isEmpty() ||
				(!currency.equalsIgnoreCase(USD) && !currency.equalsIgnoreCase(EUR))) {
			throw new CurrencyException ();
		}	
		
		StringBuilder result = new StringBuilder ()
				.append(TEMPLATE_CONSTANT)
				.append(LIST_ARGUMENTS)
				.append(amount);

		if (currency.equalsIgnoreCase (USD)) {
			result
				.append(USD)
				.append(converter.conversionUSDtoEUR (amount, fee))
				.append(EUR);
		}
		
		if (currency.equalsIgnoreCase (EUR)) {
			result
				.append(EUR)
				.append(converter.conversionEURtoUSD (amount, fee))
				.append(USD);
		}
		
		return String.format(result.toString());
	}

}
