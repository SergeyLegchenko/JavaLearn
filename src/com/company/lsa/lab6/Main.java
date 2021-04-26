package com.company.lsa.lab6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static final int nCount = 5;

    public static void main(String[] args) {
        // write your code here
        System.out.println("======== Lab 6 ========");
        System.out.println("    Enter Products:");
        System.out.println("  : Name Cost Count <Enter> (or enter 'exit' or 'q' for exit)");
        System.out.println("=======================");
        System.out.print(  "1 : ");

        Parcel[] masOfParcel = new Parcel[nCount];

        Scanner sc = new Scanner (System.in);
        int count = 0;
        while(sc.hasNext() && count < nCount) {
            String s = sc.nextLine();
            if("exit".equalsIgnoreCase(s) || "q".equalsIgnoreCase(s)) {
                break;
            }
            count++;
            System.out.print(count+1+" : ");

            String[] parts = s.split(" ");
            if (parts.length != 3) {
                System.out.println("Wrong number of arguments! Retry!");
                continue;
            }

            String productName = parts[0];
            int productCost;
            int productCount;
            try {
                productCost = Integer.parseInt(parts[1]);
                productCount = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                System.out.println("Retry!");
                continue;
            }

            boolean productAlreadyExists = false;
            for (int i = 0; i < nCount; i++) {
                if(masOfParcel[i] == null) continue;
                if(productName.equalsIgnoreCase(masOfParcel[i].getProductName()) && (masOfParcel[i].getCosts() == productCost)) {
//                    masOfParcel[i].setCosts(productCost);  //лишнее, т.к. мы цену уже сравнили - она такая же
                    masOfParcel[i].addCounts(productCount) ;
                    productAlreadyExists = true;
                    break;
                }
            }

            if(!productAlreadyExists) {
                for (int i = 0; i < nCount; i++) {
                    if(masOfParcel[i] == null){
                        masOfParcel[i] = new Parcel(productName, productCost, productCount);
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(masOfParcel));
        Parcel[] sortedParcels = new Parcel[nCount];
        System.arraycopy(masOfParcel, 0, sortedParcels, 0, nCount);

        Arrays.sort(sortedParcels, new Comparator<>() {
                @Override
                public int compare(Parcel p1, Parcel p2) {
                    if ((p1 == null) && (p2 == null))return 0;
                    if (p1 == null)return 1000;
                    if (p2 == null)return -1000;

                    return p1.compareTo(p2);
                }
            }
        );

        System.out.println(Arrays.toString(sortedParcels));

        long sum = 0;
        for (int i = 0; i < nCount; i++) {
            if (masOfParcel[i] == null) continue;
            sum += masOfParcel[i].getValue();
        }
        System.out.print("Sum: ");
        System.out.println(sum);

        TakePopular(masOfParcel);
        for (int j = 0; j < 3; j++)
            TakePopular(masOfParcel);
    }

    static void TakePopular (Parcel[] masOfParcel) {
        int indexOfMostPopularProduct = 0;
        for (int i = 0; i < nCount; i++) {
            if (masOfParcel[i] == null) continue;
            if(masOfParcel[i].getCounts() > masOfParcel[indexOfMostPopularProduct].getCounts()) {
                indexOfMostPopularProduct = i;
            }
        }

        System.out.println("Most popular product is " + masOfParcel[indexOfMostPopularProduct]);
        masOfParcel[indexOfMostPopularProduct] = null;
    }
}