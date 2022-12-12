package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converter implements RateProvider, ConstantsConverter {
	
	private BigDecimal conversion (BigDecimal amount, BigDecimal fee, BigDecimal rate)
			throws AmountOrExchangeException {
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException ();
		}
		
		final BigDecimal oneHundred = new BigDecimal ("100.0")
				.setScale(1, RoundingMode.HALF_UP);
		
		final BigDecimal netto = oneHundred
				.subtract(fee)
				.setScale (2, RoundingMode.HALF_UP);
		
		final BigDecimal amountBigDecimal = amount
				.setScale (2, RoundingMode.HALF_UP);
		
		return netto.multiply(amountBigDecimal.multiply(rate))
				.divide(oneHundred)
				.setScale (2, RoundingMode.HALF_UP);
	}

	public BigDecimal conversionUSDtoEUR (BigDecimal amount, BigDecimal fee)
			throws AmountOrExchangeException {
		
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException ();
		}
		
		final BigDecimal rate = BigDecimal.valueOf(RateProvider.getRateUSDtoEUR())
				.setScale (2, RoundingMode.HALF_UP);
		
		return conversion (amount, fee, rate);
	}
	
	public BigDecimal conversionEURtoUSD (BigDecimal amount, BigDecimal fee) throws AmountOrExchangeException {
		
		if (isNotValid(amount, fee)) {
			throw new AmountOrExchangeException ();
		}
		
		final BigDecimal rate = BigDecimal.valueOf(RateProvider.getRateEURtoUSD())
				.setScale (2, RoundingMode.HALF_UP);
		
		return conversion (amount, fee, rate);
	}

	private boolean isNotValid (BigDecimal amount, BigDecimal fee) {
		return amount == null || fee == null ||
				fee.doubleValue() >= 100 || fee.doubleValue() < 0 ||
				amount.doubleValue() <= 0;
	}

}
