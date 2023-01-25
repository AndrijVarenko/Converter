package converter.classes;

import converter.exceptions.AmountOrExchangeException;
import converter.interfaces.IConstantsConverter;
import converter.interfaces.IRateProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converter implements IConstantsConverter {

	private int newScale = 2;
	private final BigDecimal ONE_HUNDRED = new BigDecimal ("100.0").setScale (newScale, RoundingMode.HALF_UP);
	private final BigDecimal ONE = new BigDecimal ("1.0").setScale (newScale, RoundingMode.HALF_UP);

	private BigDecimal conversion (BigDecimal amount, BigDecimal fee, BigDecimal rate)
			throws AmountOrExchangeException {

		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException (MESSAGE_AMOUNT_OR_EXCHANGE_EXCEPTION);
		}

		/*
		 * Result = amount * rate * (1 - fee / 100%)
		 * fee => [0; 100)
		 * amount => [0; +∞)
		 */

		return scale (amount.multiply(rate).multiply((ONE.subtract(fee.divide(ONE_HUNDRED)))), newScale);
	}

	//Create Java Doc
	public BigDecimal conversionUSDtoEUR (BigDecimal amount, BigDecimal fee)
			throws AmountOrExchangeException {
		
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException (MESSAGE_AMOUNT_OR_EXCHANGE_EXCEPTION);
		}

		return conversion (amount, fee, IRateProvider.getRateUsdToEur());
	}

	//Create Java Doc
	public BigDecimal conversionEURtoUSD (BigDecimal amount, BigDecimal fee)
			throws AmountOrExchangeException {
		
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException (MESSAGE_AMOUNT_OR_EXCHANGE_EXCEPTION);
		}
		
		return conversion (amount, fee, IRateProvider.getRateEurToUsd());
	}

	private boolean isNotValid (BigDecimal amount, BigDecimal fee) {
		return amount == null || fee == null ||
				fee.doubleValue() >= 100 || fee.doubleValue() < 0 ||
				amount.doubleValue() <= 0;
	}

	public int getScale () {
		return newScale;
	}

	public void setScale (int newScale) {
		this.newScale = newScale;
	}

	private BigDecimal scale (BigDecimal bigDecimal, int newScale) {
		return bigDecimal.setScale (newScale, RoundingMode.HALF_UP);
	}

}
