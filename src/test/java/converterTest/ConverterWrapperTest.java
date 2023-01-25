package converterTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import converter.exceptions.CurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.exceptions.AmountOrExchangeException;
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
				converterWrapper.convert (scale ("15.31", SCALE),"USD", scale ("12.11", SCALE))),

				() -> assertEquals("7.32 USD = 5.93 EUR",
				converterWrapper.convert (scale ("7.32", SCALE),"usd", scale ("11", SCALE))),

				() -> assertEquals("9.00 USD = 7.00 EUR",
				converterWrapper.convert (scale ("9", SCALE),"USd", scale ("14.55", SCALE))),

				() -> assertEquals("3.00 USD = 2.32 EUR",
				converterWrapper.convert (scale ("3", SCALE),"uSD", scale ("15", SCALE)))
		);

		//EUR -> USD
		assertAll ("EUR -> USD convert() normal conversion",
				() -> assertEquals("14.21 EUR = 12.84 USD",
				converterWrapper.convert (scale ("14.21", SCALE),"Eur", scale ("10.51", SCALE))),

				() -> assertEquals("5.44 EUR = 4.94 USD",
				converterWrapper.convert (scale ("5.44", SCALE),"eUR", scale ("10", SCALE))),

				() -> assertEquals("12.00 EUR = 10.89 USD",
				converterWrapper.convert (scale ("12", SCALE),"EuR", scale ("10.15", SCALE))),

				() -> assertEquals("7.00 EUR = 5.87 USD",
				converterWrapper.convert (scale ("7", SCALE),"EUR", scale ("17", SCALE)))
		);
	}

	@Test
	@DisplayName("convert() Exception should work")
	void convertExceptionTest () {
		// Test Exceptions USD -> EUR
		assertAll ("USD -> EUR convert() Exception should work",
				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("0", SCALE),"USD", scale ("15", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("-15.23", SCALE),"USD", scale ("17", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("19.33", SCALE),"USD", scale ("-10", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("20.03", SCALE),"USD", scale ("100", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("17.73", SCALE),"USD", scale ("100.01", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						null,"USD", scale ("10", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("14.23", SCALE),"USD", null)),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						scale ("54.11", SCALE),null, scale ("81", SCALE))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						scale ("28.23", SCALE),"", scale ("29", SCALE))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						scale ("33.99", SCALE),"notUSD", scale ("32", SCALE)))
		);

		// Test Exceptions EUR -> USD
		assertAll ("EUR -> USD convert() Exception should work",
				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("0", SCALE),"EUR", scale ("19", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("-37.33", SCALE),"EUR", scale ("27", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("53.44", SCALE),"EUR", scale ("-16", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("16", SCALE),"EUR", scale ("100", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("24", SCALE),"EUR", scale ("100.01", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						null,"EUR", scale ("15", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converterWrapper.convert (
						scale ("95", SCALE),"EUR", null)),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						scale ("31.22", SCALE),null, scale ("55", SCALE))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						scale ("58.23", SCALE),"", scale ("42", SCALE))),

				() -> assertThrows (CurrencyException.class, () -> converterWrapper.convert (
						scale ("57.88", SCALE),"notEUR", scale ("28", SCALE)))
		);
	}

	private BigDecimal scale (String bigDecimal, int newScale) {
		return new BigDecimal (bigDecimal).setScale (newScale, RoundingMode.HALF_UP);
	}

	private BigDecimal scale (String bigDecimal) {
		return new BigDecimal (bigDecimal).setScale (SCALE, RoundingMode.HALF_UP);
	}
}
