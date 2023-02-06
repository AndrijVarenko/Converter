package converter;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.exceptions.InvalidAmountOrExchangeRateException;

class ConverterTest {
	
	private Converter converter;
	
	@BeforeEach                                         
    void setUp() {
		converter = new Converter (new ConstantRateProvider(), 2);
    }
	
	@Test
    @DisplayName("conversionUSDtoEUR() normal conversion should work")
    void conversionUSDtoEUR_Test() {
		assertAll ("conversionUSDtoEUR() normal conversion",
				() -> assertEquals (new BigDecimal("11.82"),
				converter.conversionUSDtoEUR (new BigDecimal("15.3024"), new BigDecimal("15.11246"))),

				() -> assertEquals (new BigDecimal("15.00"),
				converter.conversionUSDtoEUR (new BigDecimal("18.733645"), new BigDecimal("12"))),

				() -> assertEquals (new BigDecimal("7.72"),
				converter.conversionUSDtoEUR (new BigDecimal("10"), new BigDecimal("15.12"))),

				() -> assertEquals (new BigDecimal("5.73"),
				converter.conversionUSDtoEUR (new BigDecimal("7"), new BigDecimal("10")))
		);
	}

	@Test
	@DisplayName("conversionUSDtoEUR() Exception should work")
	void conversionUSDtoEUR_ExceptionTest() {
		assertAll ("conversionUSDtoEUR() Exceptions",
				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal("0"), new BigDecimal("12"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal("-18.73"), new BigDecimal("15"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal("25.75"), new BigDecimal("-35"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal("45.73"), new BigDecimal("100"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal("55.73"), new BigDecimal("100.01"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						null, new BigDecimal("11"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal("12.73"), null))
		);
	}

	@Test
	@DisplayName("conversionEURtoUSD() normal conversion should work")
	void conversionEURtoUSD_Test() {
		assertAll ("conversionEURtoUSD() normal conversion",
				() -> assertEquals(new BigDecimal("13.70"),
						converter.conversionEURtoUSD (new BigDecimal("15.11"), new BigDecimal("10.23"))),

				() -> assertEquals(new BigDecimal("7.00"),
						converter.conversionEURtoUSD (new BigDecimal("7.15"), new BigDecimal("3"))),

				() -> assertEquals(new BigDecimal("4.83"),
						converter.conversionEURtoUSD (new BigDecimal("5"), new BigDecimal("4.32"))),

				() -> assertEquals(new BigDecimal("8.59"),
						converter.conversionEURtoUSD (new BigDecimal("10"), new BigDecimal("15")))
		);
	}

	@Test
	@DisplayName("conversionEURtoUSD() Exception should work")
	void conversionEURtoUSD_ExceptionTest() {
		assertAll ("conversionEURtoUSD() Exceptions",
				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal("0"), new BigDecimal("19"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal("-17.33"), new BigDecimal("17"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal("15.44"), new BigDecimal("-15"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal("25"), new BigDecimal("100"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal("74"), new BigDecimal("100.01"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						null, new BigDecimal("9"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal("83"),
						null))
		);
	}
}
