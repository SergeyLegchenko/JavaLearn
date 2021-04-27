package com.company.lsa.lesson3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String[] OPERATIONS = {"+", "-", "/", "*", "!", "q"};   // доступные операции - указаны тут

    public static void main(String[] args) {
        // write your code here
        System.out.println("=====Lesson3=====");

        Scanner in = new Scanner(System.in);
        double result;

        String operation = "";
        do {
            try {
                operation = getOperation(in);

                if (!operation.equalsIgnoreCase("q")) {
                    // считываем первое значение (проверку качества и формата ввода номеров делать не будем)
                    System.out.print("Input first number: ");
                    double num1 = in.nextDouble();

                    if (operation.equals("!")) {     // если операция факториал (унарная)
                        System.out.printf("Your number: %d %s = %d%n", (int) num1, operation, (int) factorial(num1));
                    } else {              // если операция бинарная
                        System.out.print("Input second number: ");
                        // считываем второй оператор
                        double num2 = in.nextDouble();
                        // вычисляем операцию
                        result = switch (operation) {
                            case "*" -> num1 * num2;
                            case "+" -> num1 + num2;
                            case "/" -> num1 / num2;
                            case "-" -> num1 - num2;
                            default -> throw new IllegalStateException("Unexpected value: " + operation);   // так вообще не бывает, но мало ли..
                        };
                        System.out.printf("Your number: %.2f %s %.2f = %.2f%n", num1, operation, num2, result);
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input number!");
            } catch (ArithmeticException ex) {
                System.out.print("Divide by zero!!!");
            }
        } while (!operation.equalsIgnoreCase("q"));
        in.close();
    }

    private static double factorial(double num1) {
        double result;
        result = 1;
        // считаем факториал
        for (int i = 1; i <= num1; i++)
            result = result * i;
        // выводим факториал, причем учитываем, что он у нас целый
        return result;
    }

    private static String getOperation(Scanner in) {
        String operation;
        do {
            // печатаем список доступных операций
            System.out.print("Input operation (");
            for (String op : OPERATIONS) {
                System.out.print(op);
            }
            System.out.print("): ");

            // считываем операцию
            operation = in.next();
            // проверяем что операция в списке допустимых
            for (String op : OPERATIONS) {
                if (op.equals(operation)) {
                    return operation;
                }
            }
        } while (true);  // выходим только если операция допустима
    }
}
