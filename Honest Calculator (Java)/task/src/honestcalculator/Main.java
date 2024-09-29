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
                return;
            }
        }
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
