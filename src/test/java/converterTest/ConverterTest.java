package converterTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.exceptions.AmountOrExchangeException;
import converter.classes.Converter;

class ConverterTest {
	
	private Converter converter;
	private final int SCALE = 2;
	
	@BeforeEach                                         
    void setUp() {
		converter = new Converter ();
    }
	
	@Test
    @DisplayName("conversionUSDtoEUR() normal conversion should work")
    void conversionUSDtoEUR_Test() {
		assertAll ("conversionUSDtoEUR() normal conversion",
				() -> assertEquals (scale ("11.821", SCALE),
				converter.conversionUSDtoEUR (scale ("15.3024", SCALE), scale ("15.11246", SCALE))),

				() -> assertEquals (scale ("15.00", SCALE),
				converter.conversionUSDtoEUR (scale ("18.733645", SCALE), scale ("12", SCALE))),

				() -> assertEquals (scale ("7.7248", SCALE),
				converter.conversionUSDtoEUR (scale ("10", SCALE), scale ("15.12", SCALE))),

				() -> assertEquals (scale ("5.7348", SCALE),
				converter.conversionUSDtoEUR (scale ("7", SCALE), scale ("10", SCALE)))
		);
	}

	@Test
	@DisplayName("conversionUSDtoEUR() Exception should work")
	void conversionUSDtoEUR_ExceptionTest() {
		assertAll ("conversionUSDtoEUR() Exceptions",
				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						scale ("0", SCALE), scale ("12", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						scale ("-18.73", SCALE), scale ("15", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						scale ("25.75", SCALE), scale ("-35", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						scale ("45.73", SCALE), scale ("100", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						scale ("55.73", SCALE), scale ("100.01", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						null, scale ("11", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						scale ("12.73", SCALE), null))
		);
	}

	@Test
	@DisplayName("conversionEURtoUSD() normal conversion should work")
	void conversionEURtoUSD_Test() {
		assertAll ("conversionEURtoUSD() normal conversion",
				() -> assertEquals(scale ("13.70", SCALE),
						converter.conversionEURtoUSD (scale ("15.11", SCALE), scale ("10.23", SCALE))),

				() -> assertEquals(scale ("7.00", SCALE),
						converter.conversionEURtoUSD (scale ("7.15", SCALE), scale ("3", SCALE))),

				() -> assertEquals(scale ("4.83", SCALE),
						converter.conversionEURtoUSD (scale ("5", SCALE), scale ("4.32", SCALE))),

				() -> assertEquals(scale ("8.59", SCALE),
						converter.conversionEURtoUSD (scale ("10", SCALE), scale ("15", SCALE)))
		);
	}

	@Test
	@DisplayName("conversionEURtoUSD() Exception should work")
	void conversionEURtoUSD_ExceptionTest() {
		assertAll ("conversionEURtoUSD() Exceptions",
				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						scale ("0", SCALE), scale ("19", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						scale ("-17.33", SCALE), scale ("17", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						scale ("15.44", SCALE), scale ("-15", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						scale ("25", SCALE), scale ("100", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						scale ("74", SCALE), scale ("100.01", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						null, scale ("9", SCALE))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						scale ("83", SCALE),
						null))
		);
	}

	private BigDecimal scale (String bigDecimal, int newScale) {
		return new BigDecimal (bigDecimal).setScale (newScale, RoundingMode.HALF_UP);
	}

	private BigDecimal scale (String bigDecimal) {
		return new BigDecimal (bigDecimal).setScale (SCALE, RoundingMode.HALF_UP);
	}
}
