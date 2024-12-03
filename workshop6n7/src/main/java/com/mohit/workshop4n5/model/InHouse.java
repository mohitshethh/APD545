package com.mohit.workshop4n5.model;

public class InHouse extends Part {
    private int machineId = 0;

    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(name,price,stock,min,max);
        this.machineId = machineId;
    }

    public InHouse() {
        // no-arg constructor
    }

    public int getMachine() {
        return machineId;
    }

    public void setMachine(int machineId) {
        this.machineId = machineId;
    }
}
