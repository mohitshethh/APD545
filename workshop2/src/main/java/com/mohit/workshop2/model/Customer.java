package com.mohit.workshop2.model;

public class Customer {
    private String name;
    private String phoneNumber;
    private int quantity;

    public Customer(String name, String phoneNumber, int quantity) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
