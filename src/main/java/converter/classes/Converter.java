package converter.classes;

import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.interfaces.IRateProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converter {
	private final IRateProvider rateProvider;
	private int scale = 2;
	private final BigDecimal ONE_HUNDRED = new BigDecimal ("100.0").setScale(scale, RoundingMode.HALF_UP);
	private final BigDecimal ONE = new BigDecimal ("1.0").setScale(scale, RoundingMode.HALF_UP);

	public Converter(IRateProvider rateProvider, int scale) {
		this.rateProvider = rateProvider;
		this.scale = scale;
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

		return amount.multiply(rate).multiply((ONE.subtract(fee.divide(ONE_HUNDRED)))).setScale(scale, RoundingMode.HALF_UP);
	}

	//FIXME Create Java Doc
	public BigDecimal conversionUSDtoEUR (BigDecimal amount, BigDecimal fee)
			throws InvalidAmountOrExchangeRateException {
		
		if (isNotValid(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}

		return convert(amount, fee, rateProvider.getRateUsdToEur());
	}

	//TODO Create Java Doc
	public BigDecimal conversionEURtoUSD (BigDecimal amount, BigDecimal fee)
			throws InvalidAmountOrExchangeRateException {
		
		if (isNotValid(amount, fee)) {
			throw new InvalidAmountOrExchangeRateException();
		}
		
		return convert(amount, fee, rateProvider.getRateEurToUsd());
	}

	//NIT: I'd prefer isValid checks everywhere
	private boolean isNotValid (BigDecimal amount, BigDecimal fee) {
		return amount == null || fee == null ||
				fee.doubleValue() >= 100 || fee.doubleValue() < 0 ||
				amount.doubleValue() <= 0;
	}

}
