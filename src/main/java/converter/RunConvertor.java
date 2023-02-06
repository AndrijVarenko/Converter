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
        boolean re_input;
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
                re_input = false;
            } catch (InvalidAmountOrExchangeRateException | UnsupportedCurrencyException ignore) {
                re_input = true;
                System.out.println(MESSAGE_INCORRECT_DATA_INPUT);
            }
        } while (re_input);

    }

    private void start (String input_amount, String input_currency, String input_fee) {

        try {
            amount = new BigDecimal(input_amount);
        } catch (NumberFormatException numberFormatException) {
            System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "amount", "amount");
        }

        try {
            fee = new BigDecimal(input_fee);
        } catch (NumberFormatException numberFormatException) {
            System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "fee", "fee");
        }

        print (amount, input_currency, fee);
    }

    private void start (String input_amount, String input_currency) {

        try {
            amount = new BigDecimal(input_amount);
        } catch (NumberFormatException numberFormatException) {
            System.out.printf ("Incorrect '%s'. Please, repeat Enter '%s'\n", "amount", "amount");
        }

        print (amount, input_currency, BigDecimal.ZERO);
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

        print (amount, input_currency, fee);
    }

    private void print (BigDecimal amount, String input_currency, BigDecimal fee) {
        System.out.format ("\nYou input:" +
                        "\namount: '%s'" +
                        "\ninput_currency: '%s'" +
                        "\nfee: '%s'" +
                        "\nResult: '%s'\n",
                amount, input_currency, fee, new ConverterWrapper().convert (amount, input_currency, fee));
    }
}
