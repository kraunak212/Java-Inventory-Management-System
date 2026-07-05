package com.inventory;

import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.InvalidItemDataException;
import com.inventory.exception.ItemNotFoundException;
import com.inventory.model.ElectronicsItem;
import com.inventory.model.PerishableItem;
import com.inventory.model.Supplier;
import com.inventory.service.InventoryService;
import com.inventory.service.InventoryServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryService inventoryService = new InventoryServiceImpl();

        Supplier supplier1 = new Supplier("SUP-101", "ABC Traders", "abc@gmail.com");
        Supplier supplier2 = new Supplier("SUP-102", "XYZ Electronics", "xyz@gmail.com");

        try {

            inventoryService.addItem(
                    new PerishableItem(
                            "INV-1001",
                            "Milk",
                            20,
                            60,
                            supplier1,
                            LocalDate.now().plusDays(5)
                    )
            );

            inventoryService.addItem(
                    new ElectronicsItem(
                            "INV-1002",
                            "Laptop",
                            10,
                            55000,
                            supplier2,
                            24
                    )
            );

        } catch (InvalidItemDataException e) {
            System.out.println(e.getMessage());
        }

        int choice;

        do {

            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add New Item (Perishable/Electronics)");
            System.out.println("2. Update Stock Level (Add/Deduct)");
            System.out.println("3. Search Item (by ID or Name)");
            System.out.println("4. Display All Items");
            System.out.println("5. View Low Stock / Reorder List");
            System.out.println("6. View Total Inventory Valuation");
            System.out.println("7. Remove an Item");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\n1. Perishable Item");
                    System.out.println("2. Electronics Item");
                    System.out.print("Select Item Type : ");
                    int itemType = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Item ID : ");
                    String itemId = sc.nextLine();

                    System.out.print("Enter Item Name : ");
                    String itemName = sc.nextLine();

                    System.out.print("Enter Quantity : ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Price : ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Supplier ID : ");
                    String supplierId = sc.nextLine();

                    System.out.print("Enter Supplier Name : ");
                    String supplierName = sc.nextLine();

                    System.out.print("Enter Supplier Email : ");
                    String supplierEmail = sc.nextLine();

                    Supplier supplier = new Supplier(supplierId, supplierName, supplierEmail);

                    try {

                        if (itemType == 1) {

                            System.out.print("Enter Expiry Date (yyyy-mm-dd) : ");
                            LocalDate expiryDate = LocalDate.parse(sc.nextLine());

                            inventoryService.addItem(
                                    new PerishableItem(
                                            itemId,
                                            itemName,
                                            quantity,
                                            price,
                                            supplier,
                                            expiryDate
                                    )
                            );

                        } else if (itemType == 2) {

                            System.out.print("Enter Warranty (Months) : ");
                            int warranty = sc.nextInt();

                            inventoryService.addItem(
                                    new ElectronicsItem(
                                            itemId,
                                            itemName,
                                            quantity,
                                            price,
                                            supplier,
                                            warranty
                                    )
                            );

                        } else {

                            System.out.println("Invalid Item Type.");
                            break;

                        }

                        System.out.println("Item Added Successfully.");

                    } catch (InvalidItemDataException e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 2:

                    sc.nextLine();

                    System.out.print("Enter Item ID : ");
                    String updateItemId = sc.nextLine();

                    System.out.println("1. Add Stock");
                    System.out.println("2. Deduct Stock");
                    System.out.print("Choose Option : ");
                    int stockOption = sc.nextInt();

                    System.out.print("Enter Quantity : ");
                    int stockQuantity = sc.nextInt();

                    if (stockOption == 2) {
                        stockQuantity = -stockQuantity;
                    }

                    try {

                        inventoryService.updateStock(updateItemId, stockQuantity);
                        System.out.println("Stock Updated Successfully.");

                    } catch (ItemNotFoundException | InsufficientStockException e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 3:

                    sc.nextLine();

                    System.out.println("1. Search By ID");
                    System.out.println("2. Search By Name");
                    System.out.print("Choose Option : ");
                    int searchOption = sc.nextInt();
                    sc.nextLine();

                    try {

                        if (searchOption == 1) {

                            System.out.print("Enter Item ID : ");
                            String searchItemId = sc.nextLine();

                            System.out.println(inventoryService.getItem(searchItemId));

                        } else if (searchOption == 2) {

                            System.out.print("Enter Item Name : ");
                            String searchItemName = sc.nextLine();

                            System.out.println(inventoryService.getItemByName(searchItemName));

                        } else {

                            System.out.println("Invalid Option.");

                        }

                    } catch (ItemNotFoundException e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 4:

                    if (inventoryService.getAllItems().isEmpty()) {

                        System.out.println("No Items Found.");

                    } else {

                        for (var item : inventoryService.getAllItems()) {

                            System.out.println("--------------------------------");
                            System.out.println(item);

                        }

                    }

                    break;

                case 5:

                    if (inventoryService.getReorderList().isEmpty()) {

                        System.out.println("No Items Found.");

                    } else {

                        for (var item : inventoryService.getReorderList()) {

                            System.out.println("--------------------------------");
                            System.out.println(item);

                        }

                    }

                    break;

                case 6:

                    System.out.println("Total Inventory Valuation : ₹ "
                            + inventoryService.getTotalInventoryValuation());

                    break;

                case 7:

                    sc.nextLine();

                    System.out.print("Enter Item ID : ");
                    String removeItemId = sc.nextLine();

                    try {

                        inventoryService.removeItem(removeItemId);
                        System.out.println("Item Removed Successfully.");

                    } catch (ItemNotFoundException e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 8:

                    System.out.println("Thank You for Using Inventory Management System.");
                    break;

                default:
                    System.out.println("Invalid Choice.");

            }

        } while (choice != 8);

        sc.close();
    }
}