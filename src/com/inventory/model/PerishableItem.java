package com.inventory.model;

import java.time.LocalDate;

public class PerishableItem extends Item {

    private LocalDate expiryDate;

    public PerishableItem() {
    }

    public PerishableItem(String itemId, String name, int quantity, double price,
                          Supplier supplier, LocalDate expiryDate) {
        super(itemId, name, quantity, price, supplier);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isReorderRequired() {
        return getQuantity() < 15 ||
                expiryDate.isBefore(LocalDate.now().plusDays(7));
    }

    @Override
    public double calculateValuation() {
        if (expiryDate.isBefore(LocalDate.now())) {
            return 0.0;
        }
        return getQuantity() * getPrice();
    }

    @Override
    public String toString() {
        return "Perishable Item\n" +
                "Item ID : " + getItemId() +
                "\nName : " + getName() +
                "\nQuantity : " + getQuantity() +
                "\nPrice : " + getPrice() +
                "\nExpiry Date : " + expiryDate +
                "\nSupplier : " + getSupplier().getName();
    }
}