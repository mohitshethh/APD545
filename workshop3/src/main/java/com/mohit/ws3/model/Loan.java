package com.mohit.ws3.model;

public class Loan {
    private int periodMonths;
    private double interestRate;
    private String paymentFrequency;
    private double amount;
    public Customer customer;
    public Vehicle vehicle;
    private int downPayment;

    public Loan(int periodMonths, double interestRate, String paymentFrequency, double amount, Customer customer, Vehicle vehicle) {
        this.periodMonths = periodMonths;
        this.interestRate = interestRate;
        this.paymentFrequency = paymentFrequency;
        this.amount = amount;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    // Getters and setters
    public int getPeriodMonths() {
        return periodMonths;
    }

    public void setPeriodMonths(int periodMonths) {
        this.periodMonths = periodMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getVehicleType() {
        return vehicle.getType();
    }

    public void setVehicleType(String vehicleType) {
        this.vehicle = vehicle;
    }
}
