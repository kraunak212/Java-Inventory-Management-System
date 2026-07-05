package com.inventory.service;

import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.InvalidItemDataException;
import com.inventory.exception.ItemNotFoundException;
import com.inventory.model.Item;

import java.util.List;

public interface InventoryService {

    void addItem(Item item) throws InvalidItemDataException;

    void removeItem(String itemId) throws ItemNotFoundException;

    void updateStock(String itemId, int qtyChange)
            throws ItemNotFoundException, InsufficientStockException;

    Item getItem(String itemId) throws ItemNotFoundException;
    Item getItemByName(String itemName) throws ItemNotFoundException;

    List<Item> getAllItems();

    List<Item> getReorderList();

    double getTotalInventoryValuation();
}