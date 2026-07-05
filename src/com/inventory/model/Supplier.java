package com.inventory.model;

public class Supplier {

    private String supplierId;
    private String name;
    private String email;

    public Supplier() {
    }

    public Supplier(String supplierId, String name, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.email = email;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String supplierName) {
        this.name = supplierName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String supplierEmail) {
        this.email = supplierEmail;
    }

    @Override
    public String toString() {
        return "Supplier ID : " + supplierId +
                "\nSupplier Name : " + name +
                "\nEmail : " + email;
    }
}