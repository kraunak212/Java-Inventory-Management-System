package com.inventory.service;

import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.InvalidItemDataException;
import com.inventory.exception.ItemNotFoundException;
import com.inventory.model.Item;
import com.inventory.model.PerishableItem;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {

    private Map<String, Item> inventory = new HashMap<>();

    @Override
    public void addItem(Item item) throws InvalidItemDataException {

        if (item == null) {
            throw new InvalidItemDataException("Item cannot be null.");
        }

        if (!item.getItemId().matches("INV-\\d{4}")) {
            throw new InvalidItemDataException("Invalid Item ID.");
        }

        if (item.getPrice() <= 0) {
            throw new InvalidItemDataException("Price must be greater than zero.");
        }

        if (item.getQuantity() < 0) {
            throw new InvalidItemDataException("Quantity cannot be negative.");
        }

        if (item.getSupplier() == null) {
            throw new InvalidItemDataException("Supplier cannot be null.");
        }

        String email = item.getSupplier().getEmail();

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidItemDataException("Invalid Supplier Email.");
        }

        if (item instanceof PerishableItem) {

            PerishableItem perishableItem = (PerishableItem) item;

            if (!perishableItem.getExpiryDate().isAfter(LocalDate.now())) {
                throw new InvalidItemDataException("Expiry date must be in future.");
            }
        }

        inventory.put(item.getItemId(), item);
    }

    @Override
    public void removeItem(String itemId) throws ItemNotFoundException {

        if (!inventory.containsKey(itemId)) {
            throw new ItemNotFoundException("Item not found.");
        }

        inventory.remove(itemId);
    }

    @Override
    public void updateStock(String itemId, int qtyChange)
            throws ItemNotFoundException, InsufficientStockException {

        Item item = inventory.get(itemId);

        if (item == null) {
            throw new ItemNotFoundException("Item not found.");
        }

        int updatedQuantity = item.getQuantity() + qtyChange;

        if (updatedQuantity < 0) {
            throw new InsufficientStockException("Insufficient stock.");
        }

        item.setQuantity(updatedQuantity);
    }

    @Override
    public Item getItem(String itemId) throws ItemNotFoundException {

        if (!inventory.containsKey(itemId)) {
            throw new ItemNotFoundException("Item not found.");
        }

        return inventory.get(itemId);
    }

    @Override
    public Item getItemByName(String itemName) throws ItemNotFoundException {

        for (Item item : inventory.values()) {

            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }

        }

        throw new ItemNotFoundException("Item not found.");
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public List<Item> getReorderList() {

        List<Item> reorderItems = new ArrayList<>();

        for (Item item : inventory.values()) {
            if (item.isReorderRequired()) {
                reorderItems.add(item);
            }
        }

        return reorderItems;
    }

    @Override
    public double getTotalInventoryValuation() {

        double totalValue = 0;

        for (Item item : inventory.values()) {
            totalValue += item.calculateValuation();
        }

        return totalValue;
    }
}