package converter;

import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.interfaces.IRateProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Converter class is designed to convert the amount of currency specified
 * by the user to the opposite, taking into account the commission (fee).
 * <p>
 * For example, the user sets a conversion of dollars (USD) - they convert
 * to the euros (EUR) opposite. If the user as a currency for conversion
 * sets the euro (EUR), then the currency will be dollars (USD).
 *
 */
public class Converter {
	private final IRateProvider rateProvider;
	private int scale = 2;
	private final BigDecimal ONE_HUNDRED = new BigDecimal (100); // 100

	/**
	 * Constructor 'Converter(IRateProvider rateProvider, int scale)'
	 * set values 'rateProvider' and 'scale'.
	 *
	 * @param rateProvider	set a IRateProvider data type.
	 * @param scale			the int data type (min = '0', max = '2E31 - 1' '').
	 * @see					IRateProvider
	 * @see					BigDecimal
	 *
	 */
	public Converter(IRateProvider rateProvider, int scale) {
		this.rateProvider = rateProvider;
		this.scale = scale;
	}

	/**
	 * Returns an BigDecimal object.
	 * The amount argument must specify a quantity of currency USD
	 * for convert to EUR (not negative and not null). The fee
	 * argument must specify a currency exchange commission (in percent).
	 * <p>
	 * This method returns the number of EUR depending on the
	 * amount USD, the fee and the exchange rate.
	 *
	 * @param amount	BigDecimal data type, giving the quantity of currency USD for convert to EUR.
	 * @param fee		BigDecimal data type, giving the value currency exchange commission in percent.
	 * @return 			BigDecimal data type, number of EUR depending on the amount USD, the fee and
	 * 					the exchange rate.
	 * @throws 			InvalidAmountOrExchangeRateException if the amount USD is null or negative or
	 * 					fee is null, negative or over one hundred.
	 * @see				ConstantRateProvider
	 *
	 */
	public BigDecimal conversionUSDtoEUR (BigDecimal amount, BigDecimal fee)
			throws InvalidAmountOrExchangeRateException {
		
		if (isNotValid(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}

		return convert(amount, fee, rateProvider.getRateUsdToEur());
	}

	/**
	 * Returns an BigDecimal object.
	 * The amount argument must specify a quantity of currency EUR
	 * for convert to USD (not negative and not null). The fee
	 * argument must specify a currency exchange commission (in percent).
	 * <p>
	 * This method returns the number of USD depending on the
	 * amount EUR, the fee and the exchange rate.
	 *
	 * @param amount	BigDecimal data type, giving the quantity of currency EUR for convert to USD.
	 * @param fee		BigDecimal data type, giving the value currency exchange commission in percent.
	 * @return 			BigDecimal data type, number of USD depending on the amount EUR, the fee and
	 * 					the exchange rate.
	 * @throws 			InvalidAmountOrExchangeRateException if the amount EUR is null or negative or
	 * 					fee is null, negative or over one hundred.
	 * @see				ConstantRateProvider
	 *
	 */
	public BigDecimal conversionEURtoUSD (BigDecimal amount, BigDecimal fee)
			throws InvalidAmountOrExchangeRateException {
		
		if (isNotValid(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}
		
		return convert(amount, fee, rateProvider.getRateEurToUsd());
	}

	private BigDecimal convert(BigDecimal amount, BigDecimal fee, BigDecimal rate)
			throws InvalidAmountOrExchangeRateException {

		if (isNotValid(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}

		/*
		 * Result = amount * rate * (1 - fee / 100%)
		 * fee => [0; 100)
		 * amount => [0; +∞)
		 */

		return amount.multiply(rate).multiply((BigDecimal.ONE.subtract(fee.divide(ONE_HUNDRED))))
				.setScale(scale, RoundingMode.HALF_UP);
	}

	private boolean isNotValid (BigDecimal amount, BigDecimal fee) {
		return 	amount == null ||
				amount.doubleValue() <= 0 ||
				fee == null ||
				fee.doubleValue() >= 100 ||
				fee.doubleValue() < 0;
	}

}
