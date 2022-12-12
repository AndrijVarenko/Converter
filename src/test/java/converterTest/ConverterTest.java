package converterTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.AmountOrExchangeException;
import converter.Converter;

class ConverterTest {
	
	Converter converter;
	
	@BeforeEach                                         
    void setUp() {
		converter = new Converter ();
    }
	
	@Test
    @DisplayName("conversionUSDtoEUR() normal conversion should work")
    void conversionUSDtoEUR_Test() {
		assertAll ("conversionUSDtoEUR() normal conversion",
				() -> assertEquals(new BigDecimal ("11.82").setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal ("15.3").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("15.11").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals(new BigDecimal ("15.00").setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal ("18.73").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("12").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals(new BigDecimal ("7.72").setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal ("10").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("15.12").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals(new BigDecimal ("5.73").setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal ("7").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("10").setScale (2, RoundingMode.HALF_UP)))
		);
	}

	@Test
	@DisplayName("conversionUSDtoEUR() Exception should work")
	void conversionUSDtoEUR_ExceptionTest() {
		assertAll ("conversionUSDtoEUR() Exceptions",
				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal ("0").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("12").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						BigDecimal.valueOf(-18.73f).setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("15").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal ("25.75").setScale (2, RoundingMode.HALF_UP),
						BigDecimal.valueOf(-35f).setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal ("45.73").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("100").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal ("55.73").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("100.01").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						null,
						new BigDecimal ("11").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionUSDtoEUR (
						new BigDecimal ("12.73").setScale (2, RoundingMode.HALF_UP),
						null))
		);
	}

	@Test
	@DisplayName("conversionEURtoUSD() normal conversion should work")
	void conversionEURtoUSD_Test() {
		assertAll ("conversionEURtoUSD() normal conversion",
				() -> assertEquals(new BigDecimal ("13.70").setScale (2, RoundingMode.HALF_UP),
						converter.conversionEURtoUSD (
								new BigDecimal ("15.11").setScale (2, RoundingMode.HALF_UP),
								new BigDecimal ("10.23").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals(new BigDecimal ("7.00").setScale (2, RoundingMode.HALF_UP),
						converter.conversionEURtoUSD (
								new BigDecimal ("7.15").setScale (2, RoundingMode.HALF_UP),
								new BigDecimal ("3").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals(new BigDecimal ("4.83").setScale (2, RoundingMode.HALF_UP),
						converter.conversionEURtoUSD (
								new BigDecimal ("5").setScale (2, RoundingMode.HALF_UP),
								new BigDecimal ("4.32").setScale (2, RoundingMode.HALF_UP))),

				() -> assertEquals(new BigDecimal ("8.59").setScale (2, RoundingMode.HALF_UP),
						converter.conversionEURtoUSD (
								new BigDecimal ("10").setScale (2, RoundingMode.HALF_UP),
								new BigDecimal ("15").setScale (2, RoundingMode.HALF_UP)))
		);
	}

	@Test
	@DisplayName("conversionEURtoUSD() Exception should work")
	void conversionEURtoUSD_ExceptionTest() {
		assertAll ("conversionEURtoUSD() Exceptions",
				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal ("0").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("19").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						BigDecimal.valueOf(-17.33f).setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("17").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal ("15.44").setScale (2, RoundingMode.HALF_UP),
						BigDecimal.valueOf(-15f).setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal ("25").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("100").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal ("74").setScale (2, RoundingMode.HALF_UP),
						new BigDecimal ("100.01").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						null,
						new BigDecimal ("9").setScale (2, RoundingMode.HALF_UP))),

				() -> assertThrows (AmountOrExchangeException.class, () -> converter.conversionEURtoUSD (
						new BigDecimal ("83").setScale (2, RoundingMode.HALF_UP),
						null))
		);
	}
}
