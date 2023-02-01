package converter;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RunConvertor {

    private static final ConverterWrapper converterWrapper = new ConverterWrapper();
    private static BigDecimal amount;
    private static BigDecimal fee;
    private static String input_currency;

    /**
     * start():
     * If input data incorrect ('amount' and/or 'fee' not BigDecimal) - restart data enter.
     * @param 'amount' (type - BigDecimal)
     * @param 'input currency' (type - String)
     * @param 'fee' (type - BigDecimal)
     *
     *
     * @return void (print: 'Result' conversion 'input currency')
     */
    public static void start () {
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
        System.out.format ("\nResult: '%s'\n", converterWrapper.convert (amount, input_currency, fee));
    }
}
