package converterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		converter = mock (Converter.class);
    }
	
	// USD -> EUR
	@Test                                               
    @DisplayName("Conversion 15.3 USD with 15.11% fee to 11.82 EUR should work")   
    void testConversion_USDtoEUR_decimalAmountAndDecimalFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (15.3f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (15.11f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (11.82f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (11.82f).setScale (2, RoundingMode.HALF_UP), // 1. It should be only one call of tested object inside test. You can use parameterized tests
				converter.conversionUSDtoEUR (
						new BigDecimal (15.3f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (15.11f).setScale (2, RoundingMode.HALF_UP)),  
                "Conversion 15.3 USD -> 11.82 EUR should work");
	}
	
	@Test                                               
    @DisplayName("Conversion 18.73 USD with 12% fee to 15.00 EUR should work")   
    void testConversion_USDtoEUR_decimalAmountIntegerFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (18.73f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (12f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (15.00f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (15.00f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (18.73f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (12f).setScale (2, RoundingMode.HALF_UP)), 
                "Conversion 18.73 USD -> 15.00 EUR should work");
	}
	
	@Test                                               
    @DisplayName("Conversion 10 USD with 15.12% fee to 7.72 EUR should work")   
    void testConversion_USDtoEUR_integerAmountDecimalFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (15.12f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (7.72f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (7.72f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (15.12f).setScale (2, RoundingMode.HALF_UP)),
                "Conversion 10 USD -> 7.72 EUR should work");
	}
	
	@Test                                               
    @DisplayName("Conversion 7 USD with 10% fee to 5.73 EUR should work")   
    void testConversion_USDtoEUR_integerAmountAndIntegerFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (7f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (5.73f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (5.73f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (7f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP)),
                "Conversion 7 USD -> 5.73 EUR should work");
	}
	
	// EUR -> USD
	@Test                                               
    @DisplayName("Conversion 15.11 EUR with 10.23% fee to 12.34 USD should work")   
    void testConversion_EURtoUSD_decimalAmountAndDecimalFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (15.11f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (10.23f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (12.34f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (12.34f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (15.11f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (10.23f).setScale (2, RoundingMode.HALF_UP)),
                "Conversion 15.11 EUR -> 12.34 USD should work");
	}
	
	@Test                                               
    @DisplayName("Conversion 7.15 EUR with 3% fee to 7.00 USD should work")   
    void testConversion_EURtoUSD_decimalAmountIntegerFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (7.15f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (3f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (7.00f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (7.00f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (7.15f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (3f).setScale (2, RoundingMode.HALF_UP)),
                "Conversion 7.15 EUR -> 7.00 USD should work");
	}
	
	@Test                                               
    @DisplayName("Conversion 5 EUR with 4.32% fee to 4.83 USD should work")   
    void testConversion_EURtoUSD_integerAmountDecimalFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (5f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (4.32f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (4.83f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (4.83f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (5f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (4.32f).setScale (2, RoundingMode.HALF_UP)),
                "Conversion 5 EUR -> 4.83 USD should work");
	}
	
	@Test                                               
    @DisplayName("Conversion 10 EUR with 15% fee to 8.59 USD should work")   
    void testConversion_EURtoUSD_integerAmountAndIntegerFee() throws AmountOrExchangeException {
		when (converter.conversionUSDtoEUR(
				new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn(new BigDecimal (8.59f).setScale (2, RoundingMode.HALF_UP));
		
		assertEquals(new BigDecimal (8.59f).setScale (2, RoundingMode.HALF_UP),
				converter.conversionUSDtoEUR (
						new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)),
                "Conversion 10 EUR -> 8.59 USD should work");
	}
	
	// Test Exceptions USD -> EUR
	@Test                                         
    @DisplayName("Exceptions conversion 0 USD with 12% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_zeroAmountUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (12f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (12f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion zeroAmount USD -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion -18.73 USD with 15% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_negativeAmountUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				new BigDecimal (-18.73f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						new BigDecimal (-18.73f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion negativeAmount USD -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 25.75 USD with -35% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_negativeFeeUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				new BigDecimal (25.75f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (-35f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						new BigDecimal (25.75f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (-35f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion USD negativeFee -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 45.73 USD with 100% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_oneHundredFeeUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				new BigDecimal (45.73f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						new BigDecimal (45.73f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion USD oneHundredFee -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 55.73 USD with 100.01% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_moreOneHundredFeeUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				new BigDecimal (55.73f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						new BigDecimal (55.73f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion USD moreOneHundredFee -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion \"null\" USD with 11% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_nullAmountUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				null, 
				new BigDecimal (11f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						null, 
						new BigDecimal (11f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion \"null\" USD -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 12.73 USD with \"null\"% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_nullFeeUSD () throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionUSDtoEUR(
				new BigDecimal (12.73f).setScale (2, RoundingMode.HALF_UP), 
				null);

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionUSDtoEUR (
						new BigDecimal (12.73f).setScale (2, RoundingMode.HALF_UP), 
						null
						), "Exception conversion USD \"null\" -> EUR should work");
	}
	
	// Test Exceptions EUR -> USD
	@Test                                         
    @DisplayName("Exceptions conversion 0 EUR with 19% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_zeroAmountEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (19f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (19f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion zeroAmount EUR -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion -17.33 EUR with 17% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_negativeAmountEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				new BigDecimal (-17.33f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (17f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						new BigDecimal (-17.33f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (17f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion negativeAmount EUR -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 15.44 EUR with -15% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_negativeFeeEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				new BigDecimal (15.44f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (-15f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						new BigDecimal (15.44f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (-15f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion EUR negativeFee -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 25 EUR with 100% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_oneHundredFeeEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				new BigDecimal (25f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						new BigDecimal (25f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion EUR oneHundredFee -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 74 EUR with 100.01% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_moreOneHundredFeeEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				new BigDecimal (74f).setScale (2, RoundingMode.HALF_UP), 
				new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						new BigDecimal (74f).setScale (2, RoundingMode.HALF_UP), 
						new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion EUR moreOneHundredFee -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion \"null\" EUR with 9% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_nullAmountEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				null, 
				new BigDecimal (9f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						null, 
						new BigDecimal (9f).setScale (2, RoundingMode.HALF_UP)
						), "Exception conversion \"null\" EUR -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions conversion 83 EUR with \"null\"% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_nullFeeEUR() throws AmountOrExchangeException {
		doThrow(new AmountOrExchangeException()).
		when (converter).conversionEURtoUSD(
				new BigDecimal (83f).setScale (2, RoundingMode.HALF_UP), 
				null);

		assertThrows (AmountOrExchangeException.class,		
				() -> converter.conversionEURtoUSD (
						new BigDecimal (83f).setScale (2, RoundingMode.HALF_UP), 
						null
						), "Exception conversion EUR \"null\" -> USD should work");
	}
	
}
