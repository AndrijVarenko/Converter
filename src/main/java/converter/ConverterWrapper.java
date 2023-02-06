package converter;

import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.exceptions.UnsupportedCurrencyException;

import java.math.BigDecimal;

import static converter.SupportedCurrencies.EUR;
import static converter.SupportedCurrencies.USD;

/**
 * The ConverterWrapper class is designed to determine the currency
 * to be converted and transmits this information to the Converter class,
 * which already converts the amount of the currency determined by
 * the user to the opposite, taking into account the commission (fee).
 * <p>
 * For example, the user indicates the currency he wants to convert.
 * ConverterWrapper receives and processes this information,
 * determines that it is for currency and whether it is in the list
 * of supported currencies, and transmits the classroom information.
 * If the specified currency is not supported, the class method
 * convert (BigDecimal amount, String currency, BigDecimal fee) will
 * return the UnsupportedCurrencyException error. If the amount of
 * currency (null or excellent) or commission (null, witches or equals
 * or more than 100 [units of measurement - percent]) is incorrect,
 * the method convert (BigDecimal amount, String currency, BigDecimal fee)
 * will return the InvalidAmountOrExchangeRateException error.
 *
 */
public class ConverterWrapper {

	private final int SCALE = 2;
	private final Converter converter = new Converter ( new ConstantRateProvider(), SCALE);

	/**
	 * Returns a String object.
	 * The amount argument must specify a quantity of currency USD
	 * for convert to EUR (not negative and not null). The currency
	 * argument must specify a currency for convert. The fee
	 * argument must specify a currency exchange commission (in percent).
	 * <p>
	 * This method returns the String result of currency depending on the
	 * amount input currency, the fee and the exchange rate.
	 *
	 * @param amount	BigDecimal data type, the quantity of currency input for convert
	 *                  to currency output.
	 * @param currency	String data type, the currency for convert.
	 * @param fee		BigDecimal data type, the value currency exchange commission in percent.
	 * @return			BigDecimal data type, number of output currency depending on the amount
	 * 					input currency, the fee and the exchange rate.
	 * @throws 			UnsupportedCurrencyException if the input currency not supported.
	 * @throws 			InvalidAmountOrExchangeRateException if the amount input currency is null or
	 * 					negative or fee is null, negative or over one hundred.
	 * @see				SupportedCurrencies enum.
	 *
	 */
	public String convert (BigDecimal amount, String currency, BigDecimal fee)
			throws UnsupportedCurrencyException, InvalidAmountOrExchangeRateException {
		
		if (isNotValidAmountOrFee(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}
		
		if (isNotValidCurrency(currency)) {
			throw new UnsupportedCurrencyException();
		}

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

		try {
			SupportedCurrencies.valueOf(currency.toUpperCase());
			return false;
		} catch (IllegalArgumentException ignored) {
			return true;
		}

	}

}
