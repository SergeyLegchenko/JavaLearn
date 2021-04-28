package com.company.lsa.lab8;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("======== Lab 8 =======");
        //todo надо попробовать его в деле - ИМХО он не работает!!

        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        HashMapImpl<String, Integer> map1 = new HashMapImpl<>();

        map.put("JOHN",12);
        map.put("ANN",14);
        map.put("SERJ",10);
        map.put("JOHN",5);

        System.out.println(map);

        System.out.println("Replace JOHN 12 to 5");
        map.remove("JOHN");
        map.put("JOHN",5);
        System.out.println(map);
        System.out.println("Has SERJ? "+map.containsKey("SERJ"));
        System.out.println("Has 5? "+map.containsValue(5));

        System.out.println("Remove JOHN");
        map.remove("JOHN");
        System.out.println("Has JOHN? "+map.containsKey("JOHN"));
        System.out.println("Has SERJ? "+map.containsKey("SERJ"));

        System.out.println("======================");

        map.put("Россия",1000);
        map.put("Россия ",1001);
        map.put("Russian Federation",2000);
        map.put("USA",2);
        map.put("Albania",100);
        map.put("Gorgia",150);
        map.put("И всякая другая хрень",3);

        System.out.println(map);
    }
}
