package converterTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import converter.CurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.AmountOrExchangeException;
import converter.ConverterWrapper;

import static org.junit.jupiter.api.Assertions.*;

class ConverterWrapperTest {
	
	ConverterWrapper converterWrapper;
	
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
				converterWrapper.convert (
						new BigDecimal ("15.31").setScale (2, RoundingMode.HALF_UP),
						"USD",
						new BigDecimal ("12.11").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals("7.32 USD = 5.93 EUR",
				converterWrapper.convert (
						new BigDecimal ("7.32").setScale (2, RoundingMode.HALF_UP),
						"usd",
						new BigDecimal ("11").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals("9.00 USD = 7.00 EUR",
				converterWrapper.convert (
						new BigDecimal ("9").setScale (2, RoundingMode.HALF_UP),
						"USd",
						new BigDecimal ("14.55").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals("3.00 USD = 2.32 EUR",
				converterWrapper.convert (
						new BigDecimal ("3").setScale (2, RoundingMode.HALF_UP),
						"uSD",
						new BigDecimal ("15").setScale (2, RoundingMode.HALF_UP)))
		);

		//EUR -> USD
		assertAll ("EUR -> USD convert() normal conversion",
				() -> assertEquals("14.21 EUR = 12.84 USD",
				converterWrapper.convert (
						new BigDecimal ("14.21").setScale (2, RoundingMode.HALF_UP),
						"Eur",
						new BigDecimal ("10.51").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals("5.44 EUR = 4.94 USD",
				converterWrapper.convert (
						new BigDecimal ("5.44").setScale (2, RoundingMode.HALF_UP),
						"eUR",
						new BigDecimal ("10").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals("12.00 EUR = 10.89 USD",
				converterWrapper.convert (
						new BigDecimal ("12").setScale (2, RoundingMode.HALF_UP),
						"EuR",
						new BigDecimal ("10.15").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals("7.00 EUR = 5.87 USD",
				converterWrapper.convert (
						new BigDecimal ("7").setScale (2, RoundingMode.HALF_UP),
						"EUR",
						new BigDecimal ("17").setScale (2, RoundingMode.HALF_UP)))
		);
	}

	@Test
	@DisplayName("convert() Exception should work")
	void convertExceptionTest () {
		// Test Exceptions USD -> EUR
		assertAll ("USD -> EUR convert() Exception should work",
				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("0").setScale (2, RoundingMode.HALF_UP),
						"USD",
						new BigDecimal ("15").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						BigDecimal.valueOf(-15.23f).setScale (2, RoundingMode.HALF_UP),
						"USD",
						new BigDecimal ("17").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("19.33").setScale (2, RoundingMode.HALF_UP),
						"USD",
						BigDecimal.valueOf(-10f).setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("20.03").setScale (2, RoundingMode.HALF_UP),
						"USD",
						new BigDecimal ("100").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("17.73").setScale (2, RoundingMode.HALF_UP),
						"USD",
						new BigDecimal ("100.01").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						null,
						"USD",
						new BigDecimal ("10").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("14.23").setScale (2, RoundingMode.HALF_UP),
						"USD",
						null)),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal ("54.11").setScale (2, RoundingMode.HALF_UP),
						null,
						new BigDecimal ("81").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal ("28.23").setScale (2, RoundingMode.HALF_UP),
						"",
						new BigDecimal ("29").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal ("33.99").setScale (2, RoundingMode.HALF_UP),
						"notUSD",
						new BigDecimal ("32").setScale (2, RoundingMode.HALF_UP)))
		);

		// Test Exceptions EUR -> USD
		assertAll ("EUR -> USD convert() Exception should work",
				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("0").setScale (2, RoundingMode.HALF_UP),
						"EUR",
						new BigDecimal ("19").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						BigDecimal.valueOf(-37.33f).setScale (2, RoundingMode.HALF_UP),
						"EUR",
						new BigDecimal ("27").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("53.44").setScale (2, RoundingMode.HALF_UP),
						"EUR",
						BigDecimal.valueOf(-16f).setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("16").setScale (2, RoundingMode.HALF_UP),
						"EUR",
						new BigDecimal ("100").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("24").setScale (2, RoundingMode.HALF_UP),
						"EUR",
						new BigDecimal ("100.01").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						null,
						"EUR",
						new BigDecimal ("15").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						new BigDecimal ("95").setScale (2, RoundingMode.HALF_UP),
						"EUR",
						null)),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal ("31.22").setScale (2, RoundingMode.HALF_UP),
						null,
						new BigDecimal ("55").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal ("58.23").setScale (2, RoundingMode.HALF_UP),
						"",
						new BigDecimal ("42").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						new BigDecimal ("57.88").setScale (2, RoundingMode.HALF_UP),
						"notEUR",
						new BigDecimal ("28").setScale (2, RoundingMode.HALF_UP)))
		);
	}
}
