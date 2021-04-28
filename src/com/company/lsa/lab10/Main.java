package com.company.lsa.lab10;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("===== Lesson10 =====");

        Integer[] mi = {1, 2, 3, 4, 5, 6, 7, 8, 100, 20, 10};
        GenericListAgregator<Integer> m = new GenericListAgregator<>(mi);
        System.out.println("Min: " + m.getMinValue());
        System.out.println("Max: " + m.getMaxValue());
        System.out.println("Avg: " + m.getAvgValue());
    }
}
