package com.company.lsa.lesson3;

import java.util.Locale;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("=====Lesson3=====");

        Scanner in = new Scanner(System.in);
        double res = 0;

        boolean opGood=false;
        String[] ops = {"+", "-", "/", "*", "!", "q"};   // доступные операции - указаны тут
        String oper="";
        do {
            try {
                do {
                    // печатаем список доступных операций
                    System.out.print("Input operation (");
                    for (String op : ops) System.out.print(op);
                    System.out.print("): ");

                    // считываем операцию
                    oper = in.next();
                    // проверяем что операция в списке допустимых
                    for (String op : ops) {
                        opGood = oper.toLowerCase().equals(op);
                        if (opGood) break;
                    }
                } while (!opGood);  // выходим только если операция допустима

                if (!oper.equalsIgnoreCase("q")) {
                    // считываем первое значение (проверку качества и формата ввода номеров делать не будем)
                    System.out.print("Input first number: ");
                    double num1 = in.nextDouble();

                    if (oper.equals("!")) {     // если операция факториал (унарная)
                        res = 1;
                        // считаем факториал
                        int i;
                        for (i = 1; i <= num1; i++)
                            res = res * i;
                        // выводим факториал, причем учитываем, что он у нас целый
                        System.out.printf("Your number: %d" + oper + " = %d\n", i - 1, (int) res);
                    } else {              // если операция бинарная
                        System.out.print("Input second number: ");
                        // считываем второй оператор
                        double num2 = in.nextDouble();
                        // вычисляем операцию
                        boolean err = false;
                        switch (oper.toLowerCase()) {
                            case "*":
                                res = num1 * num2;
                                break;
                            case "+":
                                res = num1 + num2;
                                break;
                            case "/":
                                if (num2 != 0) {
                                    res = num1 / num2;
                                } else {
                                    err = true;
                                    System.out.print("Divide by zero!!!");
                                }
                                break;
                            case "-":
                                res = num1 - num2;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + oper);   // так вообще не бывает, но мало ли..
                        }

                        if (!err) System.out.printf("Your number: %.2f " + oper + " %.2f = %.2f\n", num1, num2, res);
                    }
                }
            }
            catch (InputMismatchException ex)
            {
                System.out.println("Wrong input number!");
            }
        } while (!oper.equalsIgnoreCase("q"));
        in.close();
    }
}
