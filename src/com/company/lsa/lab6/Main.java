package com.company.lsa.lab6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static final int MAX_PARCELS = 5;

    public static void main(String[] args) {
        // write your code here
        System.out.println("======== Lab 6 ========");
        System.out.println("    Enter Products:");
        System.out.println("  : Name Cost Count <Enter> (or enter 'exit' or 'q' for exit)");
        System.out.println("=======================");
        System.out.print("1 : ");

        Parcel[] masOfParcel = new Parcel[MAX_PARCELS];

        Scanner sc = new Scanner(System.in);
        int count = 0;
        while (sc.hasNext() && count < MAX_PARCELS) {
            String s = sc.nextLine();
            if ("exit".equalsIgnoreCase(s) || "q".equalsIgnoreCase(s)) {
                break;
            }
            count++;
            System.out.print(count + 1 + " : ");

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
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Retry!");
                continue;
            }

            boolean productAlreadyExists = false;
            for (int i = 0; i < MAX_PARCELS; i++) {
                if (masOfParcel[i] == null) continue;
                if (productName.equalsIgnoreCase(masOfParcel[i].getProductName()) && (masOfParcel[i].getCosts() == productCost)) {
//                    masOfParcel[i].setCosts(productCost);  //лишнее, т.к. мы цену уже сравнили - она такая же
                    masOfParcel[i].addCounts(productCount);
                    productAlreadyExists = true;
                    break;
                }
            }

            if (!productAlreadyExists) {
                for (int i = 0; i < MAX_PARCELS; i++) {
                    if (masOfParcel[i] == null) {
                        masOfParcel[i] = new Parcel(productName, productCost, productCount);
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(masOfParcel));
        Parcel[] sortedParcels = new Parcel[MAX_PARCELS];
        System.arraycopy(masOfParcel, 0, sortedParcels, 0, MAX_PARCELS);

        Arrays.sort(sortedParcels, Comparator.nullsLast(Comparator.naturalOrder()));

        System.out.println(Arrays.toString(sortedParcels));
//        System.out.println(sortedParcels.toString());

        long sum = 0;
        for (int i = 0; i < MAX_PARCELS; i++) {
            if (masOfParcel[i] == null) continue;
            sum += masOfParcel[i].getValue();
        }
        System.out.print("Sum: ");
        System.out.println(sum);

        for (int j = 0; j <= 3; j++)
            takePopular(masOfParcel);
    }

    static void takePopular(Parcel[] masOfParcel) {
        int indexOfMostPopularProduct = 0;
        for (int i = 0; i < MAX_PARCELS; i++) {
            if (masOfParcel[i] == null) continue;
            if (masOfParcel[i].getCounts() > masOfParcel[indexOfMostPopularProduct].getCounts()) {
                indexOfMostPopularProduct = i;
            }
        }

        System.out.println("Most popular product is " + masOfParcel[indexOfMostPopularProduct]);
        masOfParcel[indexOfMostPopularProduct] = null;
    }
}