package converter.classes;

import converter.ConstantRateProvider;
import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.exceptions.UnsupportedCurrencyException;

import java.math.BigDecimal;

public class ConverterWrapper {

	public static final String USD = "USD";
	public static final String EUR = "EUR";
	private final Converter converter = new Converter ( new ConstantRateProvider(), 2);

	//Create Java Doc
	public String convert (BigDecimal amount, String currency, BigDecimal fee)
			throws UnsupportedCurrencyException, InvalidAmountOrExchangeRateException {
		
		if (isNotValidAmountOrFee(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}
		
		if (isNotValidCurrency(currency)) {
			throw new UnsupportedCurrencyException();
		}

		//TODO Create currency list (enum)
		switch (currency.toUpperCase()) {
			case USD:
				return String.format("%s %s = %s %s", amount, USD, converter.conversionUSDtoEUR(amount, fee), EUR);
			case EUR:
				return String.format("%s %s = %s %s", amount, EUR, converter.conversionEURtoUSD(amount, fee), USD);
			default:
				throw new UnsupportedCurrencyException();
		}
	}

	private boolean isNotValidAmountOrFee(BigDecimal amount, BigDecimal fee) {
		return (amount == null || fee == null ||
				fee.doubleValue() >= 100 || fee.doubleValue() < 0 ||
				amount.doubleValue() <= 0);
	}

	private boolean isNotValidCurrency (String currency) {
		return (currency == null || currency.isEmpty() ||
				(!currency.equalsIgnoreCase(USD) && !currency.equalsIgnoreCase(EUR)));
	}

}
