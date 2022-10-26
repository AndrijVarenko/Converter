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
import converter.ConverterWrapper;
import converter.CurrencyException;

class ConverterWrapperTest {
	
	ConverterWrapper converterWrapper; //Why don't you mock external implementation?
	
	@BeforeEach                                         
    void setUp() {
		converterWrapper = mock(ConverterWrapper.class);
    }
	
	// USD -> EUR
	@Test                                               
    @DisplayName("ConverterWrapper 15.31 USD with 12.11% fee to 12.24 EUR should work")   
    void testconverterWrapper_USDtoEUR_decimalAmountAndDecimalFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (15.31f).setScale (2, RoundingMode.HALF_UP),
				"USD",
				new BigDecimal (12.11f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("15.31 USD = 12.24 EUR");
		
		assertEquals("15.31 USD = 12.24 EUR",
				converterWrapper.convert (
						new BigDecimal (15.31f).setScale (2, RoundingMode.HALF_UP),
						"USD",
						new BigDecimal (12.11f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 15.31 USD -> 12.24 EUR should work");
	}
	
	@Test                                               
    @DisplayName("ConverterWrapper 7.32 USD with 11% fee to 6.58 EUR should work")   
    void testconverterWrapper_USDtoEUR_decimalAmountIntegerFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (7.32f).setScale (2, RoundingMode.HALF_UP),
				"usd",
				new BigDecimal (11f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("7.32 USD = 6.58 EUR");
		
		assertEquals("7.32 USD = 6.58 EUR",
				converterWrapper.convert (
						new BigDecimal (7.32f).setScale (2, RoundingMode.HALF_UP),
						"usd",
						new BigDecimal (11f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 7.32 USD -> 6.58 EUR should work");
	}
	
	@Test                                               
    @DisplayName("ConverterWrapper 9 USD with 14.55% fee to 7.77 EUR should work")   
    void testconverterWrapper_USDtoEUR_integerAmountDecimalFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (9f).setScale (2, RoundingMode.HALF_UP),
				"USd",
				new BigDecimal (14.55f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("9 USD = 7.77 EUR");
		
		assertEquals("9 USD = 7.77 EUR",
				converterWrapper.convert (
						new BigDecimal (9f).setScale (2, RoundingMode.HALF_UP),
						"USd",
						new BigDecimal (14.55f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 9 USD -> 7.77 EUR should work");
	}
	
	@Test                                               
    @DisplayName("ConverterWrapper 3 USD with 15% fee to 2.58 EUR should work")   
    void testconverterWrapper_USDtoEUR_integerAmountAndIntegerFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (3f).setScale (2, RoundingMode.HALF_UP),
				"uSD",
				new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("3 USD = 2.58 EUR");
		
		assertEquals("3 USD = 2.58 EUR",
				converterWrapper.convert (
						new BigDecimal (3f).setScale (2, RoundingMode.HALF_UP),
						"uSD",
						new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 3 USD -> 2.58 EUR should work");
	}
	
	// EUR -> USD
	@Test                                               
    @DisplayName("ConverterWrapper 14.21 EUR with 10.51% fee to 12.84 USD should work")   
    void testconverterWrapper_EURtoUSD_decimalAmountAndDecimalFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (14.21f).setScale (2, RoundingMode.HALF_UP),
				"Eur",
				new BigDecimal (10.51f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("14.21 EUR = 12.84 USD");
		
		assertEquals("14.21 EUR = 12.84 USD",
				converterWrapper.convert (
						new BigDecimal (14.21f).setScale (2, RoundingMode.HALF_UP),
						"Eur",
						new BigDecimal (10.51f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 14.21 EUR -> 12.84 USD should work");
	}
	
	@Test                                               
    @DisplayName("ConverterWrapper 5.44 EUR with 10% fee to 4.94 USD should work")   
    void testconverterWrapper_EURtoUSD_decimalAmountIntegerFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (5.44f).setScale (2, RoundingMode.HALF_UP),
				"eUR",
				new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("5.44 EUR = 4.94 USD");
		
		assertEquals("5.44 EUR = 4.94 USD",
				converterWrapper.convert (
						new BigDecimal (5.44f).setScale (2, RoundingMode.HALF_UP),
						"eUR",
						new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 5.44 EUR -> 4.94 USD should work");
	}
	
	@Test                                               
    @DisplayName("ConverterWrapper 12 EUR with 10.15% fee to 10.89 USD should work")   
    void testconverterWrapper_EURtoUSD_integerAmountDecimalFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (12f).setScale (2, RoundingMode.HALF_UP),
				"EuR",
				new BigDecimal (10.15f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("12 EUR = 10.89 USD");
		
		assertEquals("12 EUR = 10.89 USD",
				converterWrapper.convert (
						new BigDecimal (12f).setScale (2, RoundingMode.HALF_UP),
						"EuR",
						new BigDecimal (10.15f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 12 EUR -> 10.89 USD should work");
	}
	
	@Test                                               
    @DisplayName("ConverterWrapper 7 EUR with 17% fee to 5.87 USD should work")   
    void testconverterWrapper_EURtoUSD_integerAmountAndIntegerFee () throws CurrencyException, AmountOrExchangeException {
		
		when (converterWrapper.convert(
				new BigDecimal (7f).setScale (2, RoundingMode.HALF_UP),
				"EUR",
				new BigDecimal (17f).setScale (2, RoundingMode.HALF_UP)))
		.thenReturn("7 EUR = 5.87 USD");
		
		assertEquals("7 EUR = 5.87 USD",
				converterWrapper.convert (
						new BigDecimal (7f).setScale (2, RoundingMode.HALF_UP),
						"EUR",
						new BigDecimal (17f).setScale (2, RoundingMode.HALF_UP)),  
                "ConverterWrapper 7 EUR -> 5.87 USD should work");
	}
	
	// Test Exceptions USD -> EUR
	@Test                                         
    @DisplayName("Exceptions converterWrapper 0 USD with 15% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_zeroAmountUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
				"USD",
				new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
						"USD",
						new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper zeroAmount USD -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper -15.23 USD with 17% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_negativeAmountUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (-15.23f).setScale (2, RoundingMode.HALF_UP), 
				"USD",
				new BigDecimal (17f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (-15.23f).setScale (2, RoundingMode.HALF_UP), 
						"USD",
						new BigDecimal (17f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper negativeAmount USD -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 19.33 USD with -10% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_negativeFeeUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (19.33f).setScale (2, RoundingMode.HALF_UP), 
				"USD",
				new BigDecimal (-10f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (19.33f).setScale (2, RoundingMode.HALF_UP), 
						"USD",
						new BigDecimal (-10f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper USD negativeFee -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 20.03 USD with 100% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_oneHundredFeeUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (20.03f).setScale (2, RoundingMode.HALF_UP), 
				"USD",
				new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (20.03f).setScale (2, RoundingMode.HALF_UP), 
						"USD",
						new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper USD oneHundredFee -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 17.73 USD with 100.01% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_moreOneHundredFeeUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (17.73f).setScale (2, RoundingMode.HALF_UP), 
				"USD",
				new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (17.73f).setScale (2, RoundingMode.HALF_UP), 
						"USD",
						new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper USD moreOneHundredFee -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper \"null\" USD with 10% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_nullAmountUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				null, 
				"USD",
				new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						null, 
						"USD",
						new BigDecimal (10f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper \"null\" USD -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 14.23 USD with \"null\"% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_nullFeeUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (14.23f).setScale (2, RoundingMode.HALF_UP), 
				"USD",
				null);

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (14.23f).setScale (2, RoundingMode.HALF_UP), 
						"USD",
						null
						), "Exception converterWrapper USD \"null\" -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 54.11 \"null\" with 81% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_nullCurrencyUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (54.11f).setScale (2, RoundingMode.HALF_UP), 
				null,
				new BigDecimal (81f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (54.11f).setScale (2, RoundingMode.HALF_UP), 
						null,
						new BigDecimal (81f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper 54.11 \"null\" -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 28.23 \"\" with 29% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_currencyIsEmptyUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (28.23f).setScale (2, RoundingMode.HALF_UP), 
				"",
				new BigDecimal (29f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (28.23f).setScale (2, RoundingMode.HALF_UP), 
						"",
						new BigDecimal (29f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper 28.23 \"\" -> EUR should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 33.99 notUSD with 32% fee to EUR should work")   
    void testConversion_USDtoEUR_Exception_notUSD () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (33.99f).setScale (2, RoundingMode.HALF_UP), 
				"notUSD",
				new BigDecimal (32f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (33.99f).setScale (2, RoundingMode.HALF_UP), 
						"notUSD",
						new BigDecimal (32f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper notUSD -> EUR should work");
	}
	
	// Test Exceptions EUR -> USD
	@Test                                         
    @DisplayName("Exceptions converterWrapper 0 EUR with 19% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_zeroAmountEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
				"EUR",
				new BigDecimal (19f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (0f).setScale (2, RoundingMode.HALF_UP), 
						"EUR",
						new BigDecimal (19f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper zeroAmount EUR -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper -37.33 EUR with 27% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_negativeAmountEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (-37.33f).setScale (2, RoundingMode.HALF_UP), 
				"EUR",
				new BigDecimal (27f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (-37.33f).setScale (2, RoundingMode.HALF_UP), 
						"EUR",
						new BigDecimal (27f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper negativeAmount EUR -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 53.44 EUR with -16% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_negativeFeeEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (53.44f).setScale (2, RoundingMode.HALF_UP), 
				"EUR",
				new BigDecimal (-16f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (53.44f).setScale (2, RoundingMode.HALF_UP), 
						"EUR",
						new BigDecimal (-16f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper EUR negativeFee -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 16 EUR with 100% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_oneHundredFeeEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (16f).setScale (2, RoundingMode.HALF_UP), 
				"EUR",
				new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (16f).setScale (2, RoundingMode.HALF_UP), 
						"EUR",
						new BigDecimal (100f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper EUR oneHundredFee -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 24 EUR with 100.01% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_moreOneHundredFeeEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (24f).setScale (2, RoundingMode.HALF_UP), 
				"EUR",
				new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (24f).setScale (2, RoundingMode.HALF_UP), 
						"EUR",
						new BigDecimal (100.01f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper EUR moreOneHundredFee -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper \"null\" EUR with 15% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_nullAmountEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				null, 
				"EUR",
				new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						null, 
						"EUR",
						new BigDecimal (15f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper \"null\" EUR -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 95 EUR with \"null\"% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_nullFeeEUR() throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (95f).setScale (2, RoundingMode.HALF_UP), 
				"EUR",
				null);

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (95f).setScale (2, RoundingMode.HALF_UP), 
						"EUR",
						null
						), "Exception converterWrapper EUR \"null\" -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 31.22 \"null\" with 55% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_nullCurrencyEUR () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (31.22f).setScale (2, RoundingMode.HALF_UP), 
				null,
				new BigDecimal (55f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (31.22f).setScale (2, RoundingMode.HALF_UP), 
						null,
						new BigDecimal (55f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper 31.22 \"null\" -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 58.23 \"\" with 42% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_currencyIsEmptyEUR () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (58.23f).setScale (2, RoundingMode.HALF_UP), 
				"",
				new BigDecimal (42f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (58.23f).setScale (2, RoundingMode.HALF_UP), 
						"",
						new BigDecimal (42f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper 58.23 \"\" -> USD should work");
	}
	
	@Test                                         
    @DisplayName("Exceptions converterWrapper 57.88 notEUR with 28% fee to USD should work")   
    void testConversion_EURtoUSD_Exception_notEUR () throws AmountOrExchangeException, CurrencyException {
		doThrow(new AmountOrExchangeException()).
		when (converterWrapper).convert(
				new BigDecimal (57.88f).setScale (2, RoundingMode.HALF_UP), 
				"notEUR",
				new BigDecimal (28f).setScale (2, RoundingMode.HALF_UP));

		assertThrows (AmountOrExchangeException.class,		
				() -> converterWrapper.convert (
						new BigDecimal (57.88f).setScale (2, RoundingMode.HALF_UP), 
						"notEUR",
						new BigDecimal (28f).setScale (2, RoundingMode.HALF_UP)
						), "Exception converterWrapper notEUR -> USD should work");
	}
	
}
