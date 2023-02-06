package converter.interfaces;

import java.math.BigDecimal;

/**
 * Contains exchange rate values supported currencies.
 *
 */
public interface IRateProvider {

	/**
	 * BigDecimal RATE_USD_TO_EUR - the value exchange rate
	 * the dollars (USD) to euro (EUR).
	 * <p>
	 * BigDecimal RATE_USD_TO_EUR = '0.91'.
	 *
	 */
	BigDecimal RATE_USD_TO_EUR = new BigDecimal ("0.91");
	/**
	 * BigDecimal RATE_EUR_TO_USD - the value exchange rate
	 * the euros (EUR) to dollar (USD).
	 * <p>
	 * BigDecimal RATE_EUR_TO_USD = '1.01'.
	 *
	 */
	BigDecimal RATE_EUR_TO_USD = new BigDecimal ("1.01");

	/**
	 * Returns the value exchange rate dollars (USD) to euro (EUR).
	 *
	 * @return		exchange rate dollars (USD) to euro (EUR).
	 *
	 */
	BigDecimal getRateUsdToEur();


	/**
	 * Returns the value exchange rate euros (EUR) to dollar (USD).
	 *
	 * @return		exchange rate euros (EUR) to dollar (USD).
	 *
	 */
	BigDecimal getRateEurToUsd();

}
