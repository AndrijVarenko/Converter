package converter;

import converter.exceptions.InvalidAmountOrExchangeRateException;
import converter.exceptions.UnsupportedCurrencyException;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The RunConvertor class created to data input.
 *
 */
public class RunConvertor {

    private BigDecimal amount;
    private BigDecimal fee;
    private String input_currency;
    private String output_currency = "";

    /**
     * Method start (String[] args) sending input arguments to method
     * convert (amount, input_currency, fee) ConverterWrapper class.
     * The array arguments (args) should contain amount argument
     * and currency (fee - if fee not equals zero).
     * <p>
     * The method receives incoming arguments and displays the
     * console converting results.
     *
     * @param args  String array arguments (every argument not 'null' and not empty).
     *              Array args may be 'null' or empty.
     * @see         ConverterWrapper
     *
     */
    public void start (String[] args) {
        String MESSAGE_INCORRECT_DATA_INPUT = "Incorrect data input. Please, repeat Enter data input.";
        boolean reInput;
        do {
            try {
                if (args != null) {
                    for (String s : args) {
                        if (s == null || s.isEmpty()) {
                            System.out.println(MESSAGE_INCORRECT_DATA_INPUT);
                            start();
                            return;
                        }
                    }

                    switch (args.length) {
                        case 4:
                            start(args[0], args[1], args[2], args[3]);
                            break;
                        case 3:
                            start(args[0], args[1], args[2]);
                            break;
                        case 2:
                            start(args[0], args[1]);
                            break;
                        default:
                            if (args.length != 0) {
                                System.out.println(MESSAGE_INCORRECT_DATA_INPUT);
                            }
                            start();
                            break;
                    }
                } else {
                    start();
                }
                print (amount, input_currency, output_currency, fee);
                reInput = false;
            } catch (InvalidAmountOrExchangeRateException | UnsupportedCurrencyException ignore) {
                reInput = true;
                System.out.println(MESSAGE_INCORRECT_DATA_INPUT);
            }
        } while (reInput);

    }

    private void start (String amount, String input_currency, String output_currency, String fee) {

        start (amount, input_currency, fee);

        this.output_currency = output_currency;
    }

    private void start (String amount, String input_currency, String fee) {

        start (amount, input_currency);

        try {
            SupportedCurrencies.valueOf(fee.toUpperCase());
            this.output_currency = fee;
            //If 'fee' is 'output currency'
            //For example: 5.1 USD EUR => amount = 5.1, input_currency = USD, output_currency = EUR, fee = 0 (%);
        } catch (IllegalArgumentException ignored) {
            try {
                this.fee = new BigDecimal(fee);
                //If 'fee' is 'fee'
                //For example: 7.3 USD 10 => amount = 7.3, input_currency = USD, output_currency = EUR, fee = 10 (%);
            } catch (NumberFormatException numberFormatException) {
                System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "fee", "fee");
            }
        }
    }

    private void start (String amount, String input_currency) {

        this.input_currency = input_currency;

        try {
            this.amount = new BigDecimal(amount);
        } catch (NumberFormatException numberFormatException) {
            System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "amount", "amount");
        }

        this.fee = BigDecimal.ZERO;
    }

    private void start() {
        Scanner scanner = new Scanner (System.in);

        System.out.println ("Enter amount");
        boolean re_input;
        do {
            try {
                amount = new BigDecimal(scanner.next());
                re_input = false;
            } catch (NumberFormatException | NoSuchElementException | IllegalStateException exception) {
                re_input = true;
                System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "amount", "amount");
            }
        } while (re_input);

        System.out.println ("Enter input currency");
        do {
            try {
                input_currency = scanner.next();
                re_input = false;
            } catch (NoSuchElementException | IllegalStateException exception) {
                re_input = true;
                System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n",
                        "input currency", "input currency");
            }
        } while (re_input);

        System.out.println ("Enter fee");
        do {
            try {
                fee = new BigDecimal(scanner.next());
                re_input = false;
            } catch (NumberFormatException | NoSuchElementException | IllegalStateException exception) {
                re_input = true;
                System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "fee", "fee");
            }
        } while (re_input);

        scanner.close();
    }

    private void print (BigDecimal amount, String input_currency, String output_currency, BigDecimal fee) {
        String print_output_currency = output_currency == null || output_currency.isEmpty() ? "" :
                String.format ("output_currency: '%s'\n", output_currency);

        System.out.format("\nYou input:\n" +
                            "amount: '%s'\n" +
                            "input_currency: '%s'\n" +
                            "%s" +
                            "fee: '%s'\n\n" +
                            "Result: '%s'\n",
                    amount, input_currency, print_output_currency, fee,
                    new ConverterWrapper().convert(amount, input_currency, fee));

    }
}
