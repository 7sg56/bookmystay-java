package com.bookmystay.usecases;

import com.bookmystay.inventory.RoomInventory;

/**
 * Use Case 3: Centralized Room Inventory Management
 *
 * Entry point for demonstrating HashMap-based centralized inventory management.
 * This use case shows how replacing scattered variables with a centralized
 * HashMap improves state consistency and system scalability.
 *
 * Concepts demonstrated:
 * - HashMap for O(1) availability lookups
 * - Single source of truth for inventory state
 * - Encapsulation of inventory logic
 * - Scalability - adding new room types requires only map insertion
 *
 * @author BookMyStay Team
 * @version 3.1
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  Use Case 3: Centralized Room Inventory Management");
        System.out.println("=".repeat(60));
        System.out.println();

        // Initialize the inventory component
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory state
        System.out.println("--- Initial Inventory State ---");
        System.out.println();
        inventory.displayInventory();
        System.out.println();

        // Demonstrate O(1) lookup operations
        demonstrateLookups(inventory);
        System.out.println();

        // Demonstrate booking operations
        demonstrateBooking(inventory);
        System.out.println();

        // Demonstrate cancellation operations
        demonstrateCancellation(inventory);
        System.out.println();

        // Demonstrate adding a new room type (scalability)
        demonstrateScalability(inventory);
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("Key Takeaways:");
        System.out.println("  - HashMap provides O(1) lookups for availability");
        System.out.println("  - Single source of truth eliminates inconsistent state");
        System.out.println("  - Adding new room types requires only map insertion");
        System.out.println("  - Encapsulation protects inventory from external modifications");
        System.out.println("=".repeat(60));
    }

    /**
     * Demonstrates O(1) lookup operations.
     */
    private static void demonstrateLookups(RoomInventory inventory) {
        System.out.println("--- O(1) Lookup Operations ---");
        System.out.println("Studio rooms available: " + inventory.getAvailability("Studio"));
        System.out.println("Suite rooms available: " + inventory.getAvailability("Suite"));
        System.out.println("Penthouse available? " + (inventory.isAvailable("Penthouse") ? "Yes" : "No"));
    }

    /**
     * Demonstrates booking operations with controlled updates.
     */
    private static void demonstrateBooking(RoomInventory inventory) {
        System.out.println("--- Booking Operations ---");

        System.out.println("\nAttempting to book 2 Studio rooms:");
        System.out.println("  Booking 1st Studio: " + (inventory.bookRoom("Studio") ? "Success" : "Failed"));
        System.out.println("  Booking 2nd Studio: " + (inventory.bookRoom("Studio") ? "Success" : "Failed"));

        System.out.println("\nAttempting to book a Penthouse room:");
        System.out.println("  Booking Penthouse: " + (inventory.bookRoom("Penthouse") ? "Success" : "Failed"));

        System.out.println("\nAttempting to book another Penthouse (should fail):");
        System.out.println("  Booking Penthouse: " + (inventory.bookRoom("Penthouse") ? "Success" : "Failed"));

        System.out.println("\nUpdated inventory state:");
        inventory.displayInventory();
    }

    /**
     * Demonstrates cancellation operations.
     */
    private static void demonstrateCancellation(RoomInventory inventory) {
        System.out.println("--- Cancellation Operations ---");

        System.out.println("\nCancelling one Studio booking:");
        inventory.cancelBooking("Studio");

        System.out.println("\nUpdated inventory state:");
        inventory.displayInventory();
    }

    /**
     * Demonstrates scalability by adding a new room type.
     */
    private static void demonstrateScalability(RoomInventory inventory) {
        System.out.println("--- Scalability - Adding New Room Type ---");

        System.out.println("\nAdding 'Presidential' suite type with 1 availability:");
        inventory.setAvailability("Presidential", 1);

        System.out.println("\nUpdated inventory state:");
        inventory.displayInventory();
    }
}
