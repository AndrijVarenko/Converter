package converter;

/**
 * The Main class created to run the project.
 *
 */
public class Main {

    /**
     * Method main(String[] args) run project and sending
     * input arguments to method start (String[] args)
     * RunConvertor class. The array arguments (args)
     * should contain amount argument and
     * currency (fee - if fee not equals zero).
     * <p>
     * The method receives incoming arguments and displays the
     * console converting results.
     *
     * @param args  String array arguments.
     *
     * @see         RunConvertor
     * @see         ConverterWrapper
     *
     */
    public static void main(String[] args) {
        new RunConvertor().start(args);
    }

}
