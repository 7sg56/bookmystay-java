package com.bookmystay.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Centralized room inventory manager using HashMap for O(1) availability lookups.
 * This class provides a single source of truth for room availability,
 * replacing scattered variables with a consistent data structure.
 *
 * @author BookMyStay Team
 * @version 3.0
 */
public class RoomInventory {

    // Centralized inventory storage using HashMap
    // Key: Room type name, Value: Available count
    private final Map<String, Integer> inventory;

    /**
     * Initializes the inventory with default room availability.
     */
    public RoomInventory() {
        this.inventory = new HashMap<>();
        initializeDefaultInventory();
    }

    /**
     * Initializes the inventory with default room availability values.
     */
    private void initializeDefaultInventory() {
        inventory.put("Studio", 8);
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Deluxe", 4);
        inventory.put("Family", 2);
        inventory.put("Ocean View", 3);
        inventory.put("Suite", 2);
        inventory.put("Penthouse", 1);
    }

    /**
     * Registers or updates the availability for a specific room type.
     *
     * @param roomType   the room type identifier
     * @param available  the number of available rooms
     */
    public void setAvailability(String roomType, int available) {
        inventory.put(roomType, available);
    }

    /**
     * Gets the current availability for a specific room type.
     * O(1) lookup time using HashMap.
     *
     * @param roomType the room type identifier
     * @return the number of available rooms, or 0 if not found
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Decrements the availability for a specific room type by 1.
     * Returns true if successful, false if no rooms available.
     *
     * @param roomType the room type identifier
     * @return true if booking succeeded, false otherwise
     */
    public boolean bookRoom(String roomType) {
        Integer current = inventory.get(roomType);
        if (current == null || current <= 0) {
            return false;
        }
        inventory.put(roomType, current - 1);
        return true;
    }

    /**
     * Increments the availability for a specific room type by 1.
     * Used when a booking is cancelled.
     *
     * @param roomType the room type identifier
     */
    public void cancelBooking(String roomType) {
        Integer current = inventory.get(roomType);
        if (current != null) {
            inventory.put(roomType, current + 1);
        }
    }

    /**
     * Returns the total number of available rooms across all types.
     *
     * @return total available rooms
     */
    public int getTotalAvailable() {
        return inventory.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Returns a copy of all room types with their availability.
     * Returns a copy to prevent external modification of internal state.
     *
     * @return map of room types to availability
     */
    public Map<String, Integer> getAllAvailability() {
        return new HashMap<>(inventory);
    }

    /**
     * Checks if a room type is available (at least one room).
     *
     * @param roomType the room type identifier
     * @return true if at least one room is available
     */
    public boolean isAvailable(String roomType) {
        return getAvailability(roomType) > 0;
    }

    /**
     * Displays the current inventory state to console.
     */
    public void displayInventory() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ROOM INVENTORY STATUS                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  Room Type        │ Available  │ Status                │");
        System.out.println("├────────────────────────────────────────────────────────┤");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String roomType = entry.getKey();
            int available = entry.getValue();
            String status = available > 0 ? "Available" : "Fully Booked";
            System.out.printf("│  %-16s │ %-10d │ %-21s│%n", roomType, available, status);
        }

        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.printf("│  %-16s │ %-10d │                      │%n", "TOTAL", getTotalAvailable());
        System.out.println("└────────────────────────────────────────────────────────┘");
    }
}
