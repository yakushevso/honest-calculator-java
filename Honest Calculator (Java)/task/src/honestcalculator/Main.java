package honestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(Messages.ENTER);
            String[] calc = readInput();

            if (isNotNumber(calc[0]) || isNotNumber(calc[2])) {
                System.out.println(Messages.ERROR_NUMBER);
            } else if (!isValidOperation(calc[1])) {
                System.out.println(Messages.ERROR_OPERATION);
            } else {
                double x = Double.parseDouble(calc[0]);
                double y = Double.parseDouble(calc[2]);
                String operation = calc[1];

                if (isDivisionByZero(y, operation)) {
                    System.out.println(Messages.DIVISION_ZERO);
                    continue;
                }

                System.out.println(calc(x, y, operation));

                return;
            }
        }
    }

    private static double calc(double x, double y, String operation) {
        return switch (operation) {
            case "+" ->  x + y;
            case "-" ->  x - y;
            case "*" ->  x * y;
            case "/" ->  x / y;
            default -> 0;
        };
    }

    private static boolean isDivisionByZero(double y, String operation) {
        return "/".equals(operation) && y == 0;
    }

    private static boolean isNotNumber(String s) {
        try {
            Double.parseDouble(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static boolean isValidOperation(String s) {
        String[] operations = {"+", "-", "*", "/"};
        return Arrays.asList(operations).contains(s);
    }

    private static String[] readInput() {
        return sc.nextLine().split(" ");
    }
}
