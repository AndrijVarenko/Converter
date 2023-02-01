package converter;

import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.exceptions.UnsupportedCurrencyException;

import java.math.BigDecimal;

import static converter.SupportedCurrencies.EUR;
import static converter.SupportedCurrencies.USD;

public class ConverterWrapper {

	private int scale = 2;
	private final Converter converter = new Converter ( new ConstantRateProvider(), scale);

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
		switch (SupportedCurrencies.valueOf (currency.toUpperCase())) {
			case USD:
				return String.format("%s %s = %s %s", amount, USD, converter.conversionUSDtoEUR(amount, fee), EUR);
			case EUR:
				return String.format("%s %s = %s %s", amount, EUR, converter.conversionEURtoUSD(amount, fee), USD);
			default:
				throw new UnsupportedCurrencyException();
		}
	}

	private boolean isNotValidAmountOrFee(BigDecimal amount, BigDecimal fee) {
		return 	amount == null ||
				amount.doubleValue() <= 0 ||
				fee == null ||
				fee.doubleValue() >= 100 ||
				fee.doubleValue() < 0;
	}

	private boolean isNotValidCurrency (String currency) {
		if (currency == null ||	currency.isEmpty()) {
			return true;
		}

		for (SupportedCurrencies c : SupportedCurrencies.values()) {
			try {
				if (c.equals (SupportedCurrencies.valueOf (currency.toUpperCase()))) {
					return false;
				}
			} catch (IllegalArgumentException ignored) { }
		}

		return true;
	}

}
