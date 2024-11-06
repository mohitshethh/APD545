package com.mohit.ws3.model;

public class Customer {
    private String name;
    private String phoneNumber;
    private String city;
    private String province;

    public Customer(String name, String phoneNumber, String city, String province) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.province = province;
    }

    // Getters and setters
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
