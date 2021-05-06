package com.luxoft.lab12;

public class Product {

    private final int id;
    private String description;
    private float rate;
    private int quantity;

    public Product(int id, String description, float rate, int quantity) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Id: " + id + " Name: " + description + " Price: " + rate + " Quantity: " + quantity;
    }
}

