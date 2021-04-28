package com.company.lsa.lab8;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("======== Lab 8 =======");
        //todo надо попробовать его в деле - ИМХО он не работает!!

        HashMapImpl<String, Integer> map = new HashMapImpl<>();

        map.put("JOHN",12);
        map.put("ANN",14);
        map.put("SERJ",10);
        map.put("JOHN",5);

        System.out.println(map.toString());

        System.out.println("Replace JOHN 12 to 5");
        map.remove("JOHN");
        map.put("JOHN",5);
        System.out.println(map.toString());
        System.out.println("Has SERJ? "+map.containsKey("SERJ"));
        System.out.println("Has 5? "+map.containsValue(5));

        System.out.println("Remove JOHN");
        map.remove("JOHN");
        System.out.println("Has JOHN? "+map.containsKey("JOHN"));
        System.out.println("Has SERJ? "+map.containsKey("SERJ"));
    }
}
