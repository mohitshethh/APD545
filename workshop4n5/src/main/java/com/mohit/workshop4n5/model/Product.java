package com.mohit.workshop4n5.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import java.util.Random;

public class Product {

    private String id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private final List<Part> associatedParts;


    public Product(String name, double price, int stock, int min, int max) {
        this.id = generateUniqueId();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = new ArrayList<>();
    }
    public Product() {
        this.associatedParts = new ArrayList<>();
    }
    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }

    public void setAssociatedParts(ObservableList<Part> items) {
        associatedParts.clear();
        associatedParts.addAll(items);
    }

    private String generateUniqueId() {
        Random random = new Random();
        int id = random.nextInt(900000) + 100000; // This will generate a random 6-digit number
        return String.valueOf(id);
    }
}