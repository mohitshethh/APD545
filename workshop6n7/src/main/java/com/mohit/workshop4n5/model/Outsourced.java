package com.mohit.workshop4n5.model;

public class Outsourced extends Part {
    private String companyName="";

    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name,price,stock,min,max);
        this.companyName = companyName;
    }

    public Outsourced() {
        // no-arg constructor
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
