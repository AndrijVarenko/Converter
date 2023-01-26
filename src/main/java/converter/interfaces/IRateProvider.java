package converter.interfaces;

import java.math.BigDecimal;

public interface IRateProvider {

	BigDecimal RATE_USD_TO_EUR = new BigDecimal ("0.91");
	BigDecimal RATE_EUR_TO_USD = new BigDecimal ("1.01");
	//Create Java Doc
	 BigDecimal getRateUsdToEur();

	//Create Java Doc
	BigDecimal getRateEurToUsd();

}
