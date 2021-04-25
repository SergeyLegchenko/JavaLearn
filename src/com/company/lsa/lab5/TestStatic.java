package com.company.lsa.lab5;

public class TestStatic {
    static int i;

    static {
        System.out.println("Init");
        i=10;
    }

    public static int getI() {
        return i;
    }

}
