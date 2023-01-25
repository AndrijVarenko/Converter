package converter.classes;

import converter.exceptions.AmountOrExchangeException;
import converter.exceptions.CurrencyException;
import converter.interfaces.IConstantsConverter;

import java.math.BigDecimal;

public class ConverterWrapper implements IConstantsConverter {

	private final Converter converter = new Converter ();

	//Create Java Doc
	public String convert (BigDecimal amount, String currency, BigDecimal fee)
			throws CurrencyException, AmountOrExchangeException {
		
		if (isNotValidAmountOrFee(amount, fee)) {
			throw new AmountOrExchangeException (MESSAGE_AMOUNT_OR_EXCHANGE_EXCEPTION);
		}
		
		if (isNotValidCurrency(currency)) {
			throw new CurrencyException (MESSAGE_CURRENCY_EXCEPTION);
		}

		String result;

		//Create currency list (enum)
		switch (currency.toUpperCase()) {
			case USD:
				result = String.format("%s %s = %s %s", amount, USD, converter.conversionUSDtoEUR(amount, fee), EUR);
				break;
			case EUR:
				result = String.format("%s %s = %s %s", amount, EUR, converter.conversionEURtoUSD(amount, fee), USD);
				break;
			default:
				throw new CurrencyException (MESSAGE_CURRENCY_EXCEPTION);
		}

		return result;
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
