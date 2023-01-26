package converterTest;

import java.math.BigDecimal;

import converter.exceptions.UnsupportedCurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.classes.ConverterWrapper;

import static org.junit.jupiter.api.Assertions.*;

class ConverterWrapperTest {

	private final int SCALE = 2;
	private ConverterWrapper converterWrapper;
	
	@BeforeEach                                         
    void setUp() {
		converterWrapper = new ConverterWrapper ();
    }
	
	@Test
    @DisplayName("convert() should work")
    void convertTest () {
		//USD -> EUR
		assertAll ("USD -> EUR convert() normal conversion",
				() -> assertEquals("15.31 USD = 12.24 EUR",
				converterWrapper.convert (new BigDecimal("15.31"),"USD", new BigDecimal("12.11"))),

				() -> assertEquals("7.32 USD = 5.93 EUR",
				converterWrapper.convert (new BigDecimal("7.32"),"usd", new BigDecimal("11"))),

				() -> assertEquals("9.00 USD = 7.00 EUR",
				converterWrapper.convert (new BigDecimal("9"),"USd", new BigDecimal("14.55"))),

				() -> assertEquals("3.00 USD = 2.32 EUR",
				converterWrapper.convert (new BigDecimal("3"),"uSD", new BigDecimal("15")))
		);

		//EUR -> USD
		assertAll ("EUR -> USD convert() normal conversion",
				() -> assertEquals("14.21 EUR = 12.84 USD",
				converterWrapper.convert (new BigDecimal("14.21"),"Eur", new BigDecimal("10.51"))),

				() -> assertEquals("5.44 EUR = 4.94 USD",
				converterWrapper.convert (new BigDecimal("5.44"),"eUR", new BigDecimal("10"))),

				() -> assertEquals("12.00 EUR = 10.89 USD",
				converterWrapper.convert (new BigDecimal("12"),"EuR", new BigDecimal("10.15"))),

				() -> assertEquals("7.00 EUR = 5.87 USD",
				converterWrapper.convert (new BigDecimal("7"),"EUR", new BigDecimal("17")))
		);
	}

	@Test
	@DisplayName("convert() Exception should work")
	void convertExceptionTest () {
		// Test Exceptions USD -> EUR
		assertAll ("USD -> EUR convert() Exception should work",
				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("0"),"USD", new BigDecimal("15"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("-15.23"),"USD", new BigDecimal("17"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("19.33"),"USD", new BigDecimal("-10"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("20.03"),"USD", new BigDecimal("100"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("17.73"),"USD", new BigDecimal("100.01"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						null,"USD", new BigDecimal("10"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("14.23"),"USD", null)),

				() -> assertThrows (UnsupportedCurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal("54.11"),null, new BigDecimal("81"))),

				() -> assertThrows (UnsupportedCurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal("28.23"),"", new BigDecimal("29"))),

				() -> assertThrows (UnsupportedCurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal("33.99"),"notUSD", new BigDecimal("32")))
		);

		// Test Exceptions EUR -> USD
		assertAll ("EUR -> USD convert() Exception should work",
				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("0"),"EUR", new BigDecimal("19"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("-37.33"),"EUR", new BigDecimal("27"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("53.44"),"EUR", new BigDecimal("-16"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("16"),"EUR", new BigDecimal("100"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("24"),"EUR", new BigDecimal("100.01"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						null,"EUR", new BigDecimal("15"))),

				() -> assertThrows (InvalidAmountOrExchangeRateException.class, () -> converterWrapper.convert (
						new BigDecimal("95"),"EUR", null)),

				() -> assertThrows (UnsupportedCurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal("31.22"),null, new BigDecimal("55"))),

				() -> assertThrows (UnsupportedCurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal("58.23"),"", new BigDecimal("42"))),

				() -> assertThrows (UnsupportedCurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal("57.88"),"notEUR", new BigDecimal("28")))
		);
	}
}
