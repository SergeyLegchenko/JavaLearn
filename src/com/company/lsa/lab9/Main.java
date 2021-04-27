package com.company.lsa.lab9;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("======== Lab 9 =======");
        System.out.println("    Enter Products:");
        System.out.println("  : Buyer Item Count <Enter> (or enter 'exit' or 'q' for exit)");
        System.out.println("=======================");
        System.out.print("1 : ");

        TreeMap<String, TreeMap<String, Integer>> clients = new TreeMap<>();

        Scanner sc = new Scanner(System.in);
        int countN = 0;
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("exit") || s.equals("q")) {
                break;
            }
            countN++;

            String[] parts = s.split(" ");
            if (parts.length != 3) {
                countN--;
                System.out.println("Wrong number of arguments! Retry!");
                System.out.print(countN + 1 + " : ");
                continue;
            }
            System.out.print(countN + 1 + " : ");

            String name = parts[0];
            String productName = parts[1];
            Integer count;

            try {
                count = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex) {
                countN--;
                System.out.println(ex.getMessage());
                System.out.println("Wrong count! Retry!");
                System.out.print(countN + 1 + " : ");
                continue;
            }

            clients.putIfAbsent(name, new TreeMap<>());
            TreeMap<String, Integer> temp = clients.get(name);
            temp.putIfAbsent(productName, 0);

            Integer oldCount = temp.get(productName);
            temp.put(productName, oldCount + count);
        }

        for (Map.Entry<String, TreeMap<String, Integer>> entry : clients.entrySet()) {
            String key = entry.getKey();
            TreeMap<String, Integer> value = entry.getValue();
            //Выводим имя покупателя
            System.out.println(key + ":");
            for (Map.Entry<String, Integer> product : value.entrySet()) {
                String keyProduct = product.getKey();
                Integer valueProduct = product.getValue();
                System.out.println(keyProduct + " " + valueProduct);
            }
        }
    }
}
