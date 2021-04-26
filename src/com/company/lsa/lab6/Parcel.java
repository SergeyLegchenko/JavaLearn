package com.company.lsa.lab6;

import java.util.Arrays;

public class Parcel implements Comparable<Parcel>{
    private String ProductName;
    private int Costs;
    private int Counts;

    public Parcel() {
        ProductName = "";
        Costs = 0;
        Counts = 0;
    }

    public Parcel(String productName, int costs, int counts) {
        ProductName = productName;
        Costs = costs;
        Counts = counts;
    }

    public void setProduct(String productName, int costs, int counts) {
        ProductName = productName;
        Costs = costs;
        Counts = counts;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getCosts() {
        return Costs;
    }

    public void setCosts(int costs) {
        Costs = costs;
    }

    public int getCounts() {
        return Counts;
    }

    public void setCounts(int counts) {
        Counts = counts;
    }

    public void addCounts(int counts) {
        Counts += counts;
    }

    @Override
    public String toString() {
        return ProductName+"($"+Costs+"):"+Counts;
    }

    public int getValue() {
        return Costs*Counts;
    }

    public int compareTo(Parcel p){
        if (p == null)return -1000;
        int res = String.CASE_INSENSITIVE_ORDER.compare(ProductName, p.getProductName());
        if (res == 0) {
            res = ProductName.compareTo(p.getProductName());
        }
        if (res == 0) {
            res = Costs-p.getCosts();
        }
        if (res == 0) {
            res = Counts-p.getCounts();
        }
        return res;
    }

}
