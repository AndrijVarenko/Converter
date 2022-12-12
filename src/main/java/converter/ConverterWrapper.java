package converter;

import java.math.BigDecimal;

public class ConverterWrapper implements ConstantsConverter {

	private final Converter converter = new Converter (); 
	
	public String convert (BigDecimal amount, String currency, BigDecimal fee)
			throws CurrencyException, AmountOrExchangeException {
		
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException (MESSAGE_AMOUNT_OR_EXCHANGE_EXCEPTION);
		}
		
		if (currency == null || currency.isEmpty() ||
				(!currency.equalsIgnoreCase(USD) && !currency.equalsIgnoreCase(EUR))) {
			throw new CurrencyException (MESSAGE_CURRENCY_EXCEPTION);
		}

		String result = null;

		if (currency.equalsIgnoreCase (USD)) {
			result = String.format("%s %s = %s %s", amount, USD, converter.conversionUSDtoEUR(amount, fee), EUR);
		}
		
		if (currency.equalsIgnoreCase (EUR)) {
			result = String.format("%s %s = %s %s", amount, EUR, converter.conversionEURtoUSD(amount, fee), USD);
		}
		
		return result;
	}

	private boolean isNotValid (BigDecimal amount, BigDecimal fee) {
		return (amount == null || fee == null ||
				fee.doubleValue() >= 100 || fee.doubleValue() < 0 ||
				amount.doubleValue() <= 0);
	}

}
