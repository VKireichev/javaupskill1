import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner IN = new Scanner(System.in);
    private static final String USAGE =
            "Enter arithmetic expression: 'a + b' 'a - b' 'a * b' 'a / b' 'a !';  where a and b are integer numbers.";
    private static int number1;
    private static int number2;
    private static String operation;

    private static long plus() {
        return (long) number1 + number2;
    }

    private static long minus() {
        return (long) number1 - number2;
    }

    private static long multiply() {
        return (long) number1 * number2;
    }

    private static long factorial() {
        long res = 1;
        for (int i = 2; i <= number1; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(USAGE);
        while (true) {
            // Read user input line and parse it.
            Status nOfTokens = readLine();
            if (nOfTokens == Status.EXIT) {                        // EXIT sign
                return;
            }
            if (nOfTokens == Status.WRONG_INPUT) {                         // Wrong input
                continue;
            }

            // Execute operation and print result.
            switch (operation) {
                case "+" -> System.out.println(plus());
                case "-" -> System.out.println(minus());
                case "*" -> System.out.println(multiply());
                case "/" -> System.out.println(divideWithCheckingZero());
                case "!" -> System.out.println(factorial());
                default -> System.out.println("Invalid operation.");
            }
        }
    }

    private static String divideWithCheckingZero() {
        if (number2 == 0) {
            return "Error.Division by zero! ";
        }
        return Long.toString(number1 / number2);
    }

    private static Status readLine() {
        String inputString = "";
        Pattern expression;
        Matcher matcher;

        try {
            expression = Pattern.compile("(-?[0-9]+) *([*+-/!]) *(-?[0-9]*) *");  // pattern: number1 operation [number2]
            while ("".equals(inputString)) {       // Read non empty input line.
                System.out.print(":> ");            // Print a prompt
                inputString = IN.nextLine();
            }
            if ("q".equals(inputString)) {         // EXIT sign
                return Status.EXIT;
            }
            matcher = expression.matcher(inputString);
            if (matcher.find()) {                  // Pattern is found. Extract operation and numbers
                operation = matcher.group(2);
                number1 = Integer.parseInt(matcher.group(1));
                if (!"!".equals(operation)) {
                    number2 = Integer.parseInt(matcher.group(3));
                }
                return Status.OK;                            // Return OK.
            }
            System.out.println(USAGE);
            return Status.WRONG_INPUT;                               // Wrong input sign
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number");
            return Status.WRONG_INPUT;                               // Wrong input
        } catch (Exception e) {
            System.out.println("Internal error");
            return Status.EXIT;                              // Exit
        }
    }

    private enum Status {
        OK, WRONG_INPUT, EXIT
    }
}
     
                         