package labs.task01;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        double result = 0;
        String operation = "";

        while (true) {
            System.out.println("Введите число:");
            if (!scanner.hasNext()) {
                return;
            }

            if (!scanner.hasNextDouble()) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("s")) {
                    return;
                }
                if (input.equalsIgnoreCase("c")) {
                    result = 0;
                    operation = "";
                } else {
                    System.out.println("Некорректное число. Попробуйте ещё раз.");
                }
                continue;
            }

            double number = scanner.nextDouble();
            if (operation.isEmpty()) {
                result = number;
            } else if (operation.equals("/") && number == 0) {
                System.out.println("На ноль делить нельзя.");
            } else {
                switch (operation) {
                    case "+" -> result += number;
                    case "-" -> result -= number;
                    case "*" -> result *= number;
                    case "/" -> result /= number;
                }
                System.out.println("Результат: " + result);
            }

            while (true) {
                System.out.println("Введите операцию:");
                if (!scanner.hasNext()) {
                    return;
                }
                String input = scanner.next();
                if (input.equalsIgnoreCase("s")) {
                    return;
                }
                if (input.equalsIgnoreCase("c")) {
                    result = 0;
                    operation = "";
                    break;
                }
                if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
                    operation = input;
                    break;
                }
                System.out.println("Некорректная операция. Введите +, -, * или /.");
            }
        }
    }
}
