package converter;

public interface RateProvider {
	
	static float getRateUSDtoEUR () {
		return rateUSDtoEUR;
	};
	static float getRateEURtoUSD () {
		return rateEURtoUSD;
	};
	
	float rateUSDtoEUR = 0.91f;
	float rateEURtoUSD = 1.01f;

}
