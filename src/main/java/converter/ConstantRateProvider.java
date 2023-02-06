package converter;

import converter.interfaces.IRateProvider;

import java.math.BigDecimal;

/**
 * The ConstantRateProvider class implements interface IRateProvider.
 * <p>
 * There are no requirements for IRateProvider implementation
 * but interfaces without the implementation are considered a code smell sometimes.
 *
 * @see         IRateProvider interface.
 *
 */
public class ConstantRateProvider implements IRateProvider {

    /**
     * Returns the dollar (USD) exchange rate for euros (EUR).
     * Data type - BigDecimal.
     *
     * @return      BigDecimal data type, giving the exchange rate for convert USD to EUR.
     * @see         IRateProvider interface, const RATE_USD_TO_EUR.
     *
     */
    @Override
    public BigDecimal getRateUsdToEur() {
        return RATE_USD_TO_EUR;
    }

    /**
     * Returns the euros (EUR) exchange rate for dollar (USD).
     * Data type - BigDecimal.
     *
     * @return      BigDecimal data type, giving the exchange rate for convert EUR to USD.
     * @see         IRateProvider interface, const RATE_EUR_TO_USD.
     *
     */
    @Override
    public BigDecimal getRateEurToUsd() {
        return RATE_EUR_TO_USD;
    }
}
