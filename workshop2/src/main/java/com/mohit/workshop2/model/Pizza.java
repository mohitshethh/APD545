package com.mohit.workshop2.model;

import java.util.Arrays;
import java.util.List;

public class Pizza {
    private String size;
    private String crust;
    private List<String> toppings;
    private Double totalPrice;

    private static final List<String> meatToppings = Arrays.asList("Pepperoni", "Bacon", "Dacon", "Ground Beef", "Ham", "Grilled Chicken", "Shredded Chicken", "Dried Shrimps");

    public Pizza(String size, String crust, List<String> toppings) {
        this.size = size;
        this.crust = crust;
        this.toppings = toppings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public double calculateTotalPrice(String size, String crust, List<String> toppings) {

        double price = 0;

        switch (size) {
            case "Small":
                price = 7.0;  // Example: $8 for small
                break;
            case "Medium":
                price = 10.0; // Example: $10 for medium
                break;
            case "Large":
                price = 13.0; // Example: $12 for large
                break;
            case "Extra Large":
                price = 15.0; // Example: $14 for extra large
                break;
        }

        // Calculate the price for toppings
        for (String topping : toppings) {
            if (meatToppings.contains(topping)) {
                price += 2.15; // $2 for each meat topping
            } else {
                price += 1.10; // $1 for each non-meat topping
            }
        }

        return price; // Including 13% tax
    }
}
