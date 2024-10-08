package honestcalculator;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        float memory = 0;

        while (true) {
            System.out.println(Messages.ENTER);
            String[] calc = readInputNum();

            if ("M".equals(calc[0])) {
                calc[0] = String.valueOf(memory);
            }

            if ("M".equals(calc[2])) {
                calc[2] = String.valueOf(memory);
            }

            if (isValidNum(calc)) {
                float x = Float.parseFloat(calc[0]);
                float y = Float.parseFloat(calc[2]);
                String operation = calc[1];

                check(x, y, operation);

                if (isDivisionByZero(y, operation)) {
                    System.out.println(Messages.DIVISION_ZERO);
                    continue;
                }

                float result = calc(x, y, operation);
                System.out.printf(Locale.US, "%.1f\n", result);

                if (promptYesNo(Messages.SAVE_RESULT)) {
                    if (isOneDigit(result)) {
                        int msg_index = 10;

                        while (msg_index <= 12) {
                            System.out.println(Messages.valueOf("MSG_" + msg_index));
                            String answer = readInputSave();

                            if ("y".equals(answer)) {
                                msg_index++;
                            } else if ("n".equals(answer)) {
                                break;
                            }
                        }

                        if (msg_index > 12) {
                            memory = result;
                        }
                    } else {
                        memory = result;
                    }
                }


                if (!promptYesNo(Messages.CONTINUE)) {
                    break;
                }
            }
        }
    }

    private static void check(float x, float y, String operation) {
        String msg = "";

        if (isOneDigit(x) && isOneDigit(y)) {
            msg = msg + Messages.MSG_6;
        }

        if ((x == 1 || y == 1) && "*".equals(operation)) {
            msg = msg + Messages.MSG_7;
        }

        if ((x == 0 || y == 0) && ("*".equals(operation)
                || "+".equals(operation)
                || "-".equals(operation))) {
            msg = msg + Messages.MSG_8;
        }

        if (!msg.isEmpty()) {
            msg = Messages.MSG_9 + msg;
            System.out.println(msg);
        }
    }

    private static boolean isOneDigit(float v) {
        return v > -10 && v < 10 && v % 1 == 0;
    }

    private static boolean isValidNum(String[] calc) {
        if (isNotNumber(calc[0]) || isNotNumber(calc[2])) {
            System.out.println(Messages.ERROR_NUMBER);
            return false;
        } else if (!isValidOperation(calc[1])) {
            System.out.println(Messages.ERROR_OPERATION);
            return false;
        }

        return true;
    }

    private static float calc(float x, float y, String operation) {
        return switch (operation) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };
    }

    private static boolean isDivisionByZero(float y, String operation) {
        return "/".equals(operation) && y == 0;
    }

    private static boolean isNotNumber(String s) {
        try {
            Float.parseFloat(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static boolean isValidOperation(String s) {
        String[] operations = {"+", "-", "*", "/"};
        return Arrays.asList(operations).contains(s);
    }

    private static String readInputSave() {
        return sc.nextLine();
    }

    private static String[] readInputNum() {
        return sc.nextLine().split(" ");
    }

    private static boolean promptYesNo(Messages message) {
        while (true) {
            System.out.println(message);
            String answer = readInputSave();

            if ("y".equals(answer)) {
                return true;
            } else if ("n".equals(answer)) {
                return false;
            }
        }
    }
}
