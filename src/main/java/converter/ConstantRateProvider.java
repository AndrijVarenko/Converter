package converter;

import converter.interfaces.IRateProvider;

import java.math.BigDecimal;

/**
 * There are no requirements for RateProvider implementation
 * but interfaces without the implementation are considered a code smell sometimes
 */
public class ConstantRateProvider implements IRateProvider {
    @Override
    public BigDecimal getRateUsdToEur() {
        return RATE_USD_TO_EUR;
    }

    @Override
    public BigDecimal getRateEurToUsd() {
        return RATE_EUR_TO_USD;
    }
}
