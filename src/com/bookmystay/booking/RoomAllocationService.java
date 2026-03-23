package com.bookmystay.booking;

import com.bookmystay.inventory.RoomInventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Manages room allocation with uniqueness enforcement to prevent double-booking.
 * Uses Set for room IDs and HashMap for room type tracking.
 *
 * Key Concepts:
 * - Set Data Structure: Enforces uniqueness of room IDs
 * - Uniqueness Enforcement: No room assigned more than once
 * - Atomic Operations: Assignment and inventory update together
 * - Immediate Synchronization: Inventory updated immediately
 *
 * @author BookMyStay Team
 * @version 6.0
 */
public class RoomAllocationService {

    private final RoomInventory inventory;
    private final Map<String, Set<String>> allocatedRooms; // roomType -> set of room IDs
    private final Set<String> allAllocatedIds; // all room IDs across all types
    private int totalAllocations;
    private int allocationCounter;

    /**
     * Creates a new room allocation service.
     *
     * @param inventory the inventory to track allocations against
     */
    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
        this.allAllocatedIds = new HashSet<>();
        this.totalAllocations = 0;
        this.allocationCounter = 0;
    }

    /**
     * Attempts to allocate a room for a reservation request.
     * This is an atomic operation - room assignment and inventory update occur together.
     *
     * @param reservation the reservation request to process
     * @return the allocation result (success or failure with details)
     */
    public AllocationResult allocateRoom(Reservation reservation) {
        String roomType = reservation.getRoomType();

        // Check if room type is available
        if (!inventory.isAvailable(roomType)) {
            return new AllocationResult(false, reservation,
                    "No " + roomType + " rooms available", null);
        }

        // Generate unique room ID
        String roomId = generateRoomId(roomType);

        // Check if room ID is already allocated (uniqueness enforcement)
        if (allAllocatedIds.contains(roomId)) {
            return new AllocationResult(false, reservation,
                    "Room ID collision detected - please try again", null);
        }

        // Perform allocation - atomic operation
        // 1. Assign room ID
        allocatedRooms.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);
        allAllocatedIds.add(roomId);

        // 2. Update inventory immediately
        inventory.bookRoom(roomType);

        // 3. Update counters
        totalAllocations++;

        return new AllocationResult(true, reservation,
                "Room allocated successfully", roomId);
    }

    /**
     * Checks if a specific room ID is already allocated.
     *
     * @param roomId the room ID to check
     * @return true if the room ID is allocated
     */
    public boolean isRoomIdAllocated(String roomId) {
        return allAllocatedIds.contains(roomId);
    }

    /**
     * Gets the set of allocated room IDs for a specific room type.
     *
     * @param roomType the room type
     * @return set of allocated room IDs for that type
     */
    public Set<String> getAllocatedRoomsForType(String roomType) {
        return new HashSet<>(allocatedRooms.getOrDefault(roomType, new HashSet<>()));
    }

    /**
     * Gets all allocated room IDs across all room types.
     *
     * @return set of all allocated room IDs
     */
    public Set<String> getAllAllocatedRoomIds() {
        return new HashSet<>(allAllocatedIds);
    }

    /**
     * Gets the total number of rooms allocated.
     *
     * @return total allocations made
     */
    public int getTotalAllocations() {
        return totalAllocations;
    }

    /**
     * Gets the number of allocated rooms for a specific type.
     *
     * @param roomType the room type
     * @return number of allocated rooms of that type
     */
    public int getAllocationCountForType(String roomType) {
        Set<String> rooms = allocatedRooms.get(roomType);
        return rooms != null ? rooms.size() : 0;
    }

    /**
     * Checks for potential double-booking (same room ID to different reservations).
     *
     * @return true if double-booking is detected
     */
    public boolean hasDoubleBooking() {
        return allAllocatedIds.size() < totalAllocations;
    }

    /**
     * Clears all allocations (for testing/reset purposes).
     */
    public void clearAllocations() {
        allocatedRooms.clear();
        allAllocatedIds.clear();
        totalAllocations = 0;
    }

    /**
     * Generates a unique room ID based on room type and a counter.
     *
     * @param roomType the room type
     * @return a unique room ID
     */
    private String generateRoomId(String roomType) {
        allocationCounter++;
        String prefix = roomType.toUpperCase().replace(" ", "");
        return prefix + "-" + String.format("%04d", allocationCounter);
    }

    /**
     * Displays the current allocation state.
     */
    public void displayAllocationState() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ROOM ALLOCATION STATUS                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.printf("│  Total Allocations: %-34d│%n", getTotalAllocations());
        System.out.printf("│  Double Booking Detected: %-25s│%n", hasDoubleBooking() ? "YES" : "NO");
        System.out.println("└────────────────────────────────────────────────────────┘");

        if (!allocatedRooms.isEmpty()) {
            System.out.println();
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│  ALLOCATED ROOMS BY TYPE                            │");
            System.out.println("├────────────────────────────────────────────────────────┤");

            for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
                String roomType = entry.getKey();
                Set<String> roomIds = entry.getValue();
                System.out.printf("│  %-16s : %-35s│%n", roomType, roomIds);
            }

            System.out.println("└────────────────────────────────────────────────────────┘");
        }
    }

    /**
     * Represents the result of a room allocation attempt.
     */
    public static class AllocationResult {
        private final boolean success;
        private final Reservation reservation;
        private final String message;
        private final String roomId;

        public AllocationResult(boolean success, Reservation reservation, String message, String roomId) {
            this.success = success;
            this.reservation = reservation;
            this.message = message;
            this.roomId = roomId;
        }

        public boolean isSuccess() {
            return success;
        }

        public Reservation getReservation() {
            return reservation;
        }

        public String getMessage() {
            return message;
        }

        public String getRoomId() {
            return roomId;
        }
    }
}
