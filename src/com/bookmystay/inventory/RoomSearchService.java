package com.bookmystay.inventory;

import com.bookmystay.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Read-only search service for room availability.
 * Provides safe access to inventory data without modifying system state.
 *
 * Key Concepts:
 * - Read-Only Access: Search operations don't alter inventory
 * - Defensive Programming: Filters unavailable rooms before display
 * - Separation of Concerns: Isolated from booking/modification logic
 *
 * @author BookMyStay Team
 * @version 4.0
 */
public class RoomSearchService {

    private final RoomInventory inventory;

    /**
     * Creates a search service with access to the inventory.
     *
     * @param inventory the inventory to search
     */
    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Searches for all available room types.
     * Returns only rooms with availability > 0.
     * This is a read-only operation - inventory state is unchanged.
     *
     * @return list of available room search results
     */
    public List<RoomSearchResult> searchAvailableRooms() {
        List<RoomSearchResult> results = new ArrayList<>();

        // Define all room types with their room objects
        addRoomIfAvailable(results, new StudioRoom());
        addRoomIfAvailable(results, new SingleRoom());
        addRoomIfAvailable(results, new DoubleRoom());
        addRoomIfAvailable(results, new DeluxeRoom());
        addRoomIfAvailable(results, new FamilyRoom());
        addRoomIfAvailable(results, new OceanViewRoom());
        addRoomIfAvailable(results, new SuiteRoom());
        addRoomIfAvailable(results, new PenthouseRoom());

        return results;
    }

    /**
     * Searches for a specific room type by name.
     * Returns null if room type doesn't exist or has zero availability.
     *
     * @param roomType the room type to search for
     * @return search result or null if not available
     */
    public RoomSearchResult searchByRoomType(String roomType) {
        Room room = getRoomByType(roomType);
        if (room == null) {
            return null;
        }

        int available = inventory.getAvailability(roomType);
        if (available <= 0) {
            return null; // Not available
        }

        return new RoomSearchResult(room, available);
    }

    /**
     * Checks if a room type is available (at least one room).
     * Read-only operation.
     *
     * @param roomType the room type to check
     * @return true if at least one room is available
     */
    public boolean isAvailable(String roomType) {
        return inventory.isAvailable(roomType);
    }

    /**
     * Gets the number of available rooms for a specific type.
     * Read-only operation.
     *
     * @param roomType the room type to check
     * @return number of available rooms, or 0 if not found
     */
    public int getAvailableCount(String roomType) {
        return inventory.getAvailability(roomType);
    }

    /**
     * Gets the total count of all available rooms.
     * Read-only operation.
     *
     * @return total available rooms across all types
     */
    public int getTotalAvailableRooms() {
        return inventory.getTotalAvailable();
    }

    /**
     * Adds a room to results only if it has availability > 0.
     * Defensive programming - filters out unavailable options.
     *
     * @param results  the list to add to
     * @param room     the room to check
     */
    private void addRoomIfAvailable(List<RoomSearchResult> results, Room room) {
        String roomType = room.getRoomType();
        int available = inventory.getAvailability(roomType);

        if (available > 0) {
            results.add(new RoomSearchResult(room, available));
        }
    }

    /**
     * Creates a Room object for a given room type string.
     *
     * @param roomType the room type identifier
     * @return the corresponding Room object, or null if unknown
     */
    private Room getRoomByType(String roomType) {
        return switch (roomType) {
            case "Studio" -> new StudioRoom();
            case "Single" -> new SingleRoom();
            case "Double" -> new DoubleRoom();
            case "Deluxe" -> new DeluxeRoom();
            case "Family" -> new FamilyRoom();
            case "Ocean View" -> new OceanViewRoom();
            case "Suite" -> new SuiteRoom();
            case "Penthouse" -> new PenthouseRoom();
            default -> null;
        };
    }

    /**
     * Immutable search result containing room details and availability.
     * Defensive data class - cannot be modified after creation.
     */
    public static class RoomSearchResult {
        private final Room room;
        private final int available;

        public RoomSearchResult(Room room, int available) {
            this.room = room;
            this.available = available;
        }

        public Room getRoom() {
            return room;
        }

        public int getAvailable() {
            return available;
        }

        @Override
        public String toString() {
            return String.format("%s - $%.2f/night (%d available)",
                    room.getRoomType(), room.getPrice(), available);
        }
    }
}
