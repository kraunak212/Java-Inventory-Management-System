package com.inventory.model;

public abstract class Item {

    private String itemId;
    private String name;
    private int quantity;
    private double price;
    private Supplier supplier;

    public Item() {
    }

    public Item(String itemId, String name, int quantity, double price, Supplier supplier) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int stockQuantity) {
        this.quantity = stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double itemPrice) {
        this.price = itemPrice;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public abstract boolean isReorderRequired();

    public abstract double calculateValuation();
}