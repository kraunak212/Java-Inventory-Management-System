package com.inventory.model;

public class ElectronicsItem extends Item {

    private int warrantyMonths;

    public ElectronicsItem() {
    }

    public ElectronicsItem(String itemId, String name, int quantity, double price,
                           Supplier supplier, int warrantyMonths) {
        super(itemId, name, quantity, price, supplier);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public boolean isReorderRequired() {
        return getQuantity() < 5;
    }

    @Override
    public double calculateValuation() {
        return getQuantity() * getPrice();
    }

    @Override
    public String toString() {
        return "Electronics Item" +
                "\nItem ID : " + getItemId() +
                "\nName : " + getName() +
                "\nQuantity : " + getQuantity() +
                "\nPrice : " + getPrice() +
                "\nWarranty : " + warrantyMonths + " Months" +
                "\nSupplier : " + getSupplier().getName();
    }
}