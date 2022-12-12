package converter;

import java.math.BigDecimal;

public class ConverterWrapper implements ConstantsConverter {
	
	private final static String TEMPLATE_CONSTANT = "%s %s = %s %s";
	private final static String LIST_ARGUMENTS = ", %s, %s, %s, %s";
	private final static char SPACE = ' ';
	private final static char EQUALS = '=';
	
	private final Converter converter = new Converter (); 
	
	public String convert (BigDecimal amount, String currency, BigDecimal fee)
			throws CurrencyException, AmountOrExchangeException {
		
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException ();
		}
		
		if (currency == null || currency.isEmpty() ||
				(!currency.equalsIgnoreCase(USD) && !currency.equalsIgnoreCase(EUR))) {
			throw new CurrencyException ();
		}	
		
		StringBuilder result = new StringBuilder ()
				//.append(TEMPLATE_CONSTANT)
				//.append(LIST_ARGUMENTS)
				.append(amount);

		if (currency.equalsIgnoreCase (USD)) {
			result
					.append(SPACE)
					.append(USD)
					.append(SPACE)
					.append(EQUALS)
					.append(SPACE)
					.append(converter.conversionUSDtoEUR (amount, fee))
					.append(SPACE)
					.append(EUR);
		}
		
		if (currency.equalsIgnoreCase (EUR)) {
			result
					.append(SPACE)
					.append(EUR)
					.append(SPACE)
					.append(EQUALS)
					.append(SPACE)
					.append(converter.conversionEURtoUSD (amount, fee))
					.append(SPACE)
					.append(USD);
		}
		
		return result.toString();
	}

	private boolean isNotValid (BigDecimal amount, BigDecimal fee) {
		return (amount == null || fee == null ||
				fee.doubleValue() >= 100 || fee.doubleValue() < 0 ||
				amount.doubleValue() <= 0);
	}

}
