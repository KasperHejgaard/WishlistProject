package com.example.wishlist.model;

public class Wish {
    private int wishID;
    private String name;
    private int quantity;
    private String description;
    private double price;

    public Wish () {

    }

    public Wish(String name, int quantity, String description, double price) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }
}



