package com.bookmystay.usecases;

import com.bookmystay.booking.BookingRequestQueue;
import com.bookmystay.booking.Reservation;
import com.bookmystay.booking.RoomAllocationService;
import com.bookmystay.inventory.RoomInventory;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Entry point for demonstrating safe room allocation with uniqueness enforcement.
 * This use case shows how Set prevents double-booking.
 *
 * Concepts demonstrated:
 * - Set Data Structure: Enforces uniqueness of room IDs
 * - Uniqueness Enforcement: No room assigned more than once
 * - Atomic Operations: Assignment and inventory update together
 * - Immediate Synchronization: Inventory updated immediately
 * - HashMap Mapping: Room types to allocated room IDs
 *
 * @author BookMyStay Team
 * @version 6.1
 */
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  Use Case 6: Reservation Confirmation & Room Allocation");
        System.out.println("=".repeat(60));
        System.out.println();

        // Initialize services
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue requestQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService(inventory);

        // Display initial inventory
        displaySection("INITIAL INVENTORY");
        inventory.displayInventory();
        System.out.println();

        // Queue some booking requests
        displaySection("QUEUING BOOKING REQUESTS");
        queueRequests(requestQueue);
        System.out.println();

        // Display queue state
        displaySection("REQUEST QUEUE STATE");
        requestQueue.displayQueueState();
        System.out.println();

        // Process booking requests with allocation
        displaySection("PROCESSING BOOKING REQUESTS WITH ALLOCATION");
        processRequests(requestQueue, allocationService);
        System.out.println();

        // Display allocation state
        displaySection("ALLOCATION STATE");
        allocationService.displayAllocationState();
        System.out.println();

        // Display inventory after allocations
        displaySection("INVENTORY AFTER ALLOCATIONS");
        inventory.displayInventory();
        System.out.println();

        // Demonstrate double-booking prevention
        demonstrateDoubleBookingPrevention(allocationService);
        System.out.println();

        // Demonstrate handling unavailable requests
        demonstrateUnavailableHandling(inventory, allocationService);
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("Key Takeaways:");
        System.out.println("  - Set enforces uniqueness of room IDs");
        System.out.println("  - Atomic operations prevent partial/inconsistent state");
        System.out.println("  - Immediate inventory synchronization");
        System.out.println("  - Double-booking prevented by design");
        System.out.println("=".repeat(60));
    }

    /**
     * Queues sample booking requests.
     */
    private static void queueRequests(BookingRequestQueue queue) {
        System.out.println("Adding booking requests to queue:");
        System.out.println();

        queue.enqueue(new Reservation("RES-001", "Alice Johnson", "Suite", 3));
        System.out.println("  ✓ [RES-001] Alice Johnson - Suite room (3 nights)");

        queue.enqueue(new Reservation("RES-002", "Bob Smith", "Double", 2));
        System.out.println("  ✓ [RES-002] Bob Smith - Double room (2 nights)");

        queue.enqueue(new Reservation("RES-003", "Carol Davis", "Penthouse", 5));
        System.out.println("  ✓ [RES-003] Carol Davis - Penthouse room (5 nights)");

        queue.enqueue(new Reservation("RES-004", "David Wilson", "Single", 1));
        System.out.println("  ✓ [RES-004] David Wilson - Single room (1 night)");

        queue.enqueue(new Reservation("RES-005", "Eva Martinez", "Family", 4));
        System.out.println("  ✓ [RES-005] Eva Martinez - Family room (4 nights)");
    }

    /**
     * Processes queued requests with room allocation.
     */
    private static void processRequests(BookingRequestQueue queue, RoomAllocationService allocator) {
        System.out.println("Processing requests (FIFO order) with allocation:");
        System.out.println();

        int processed = 0;
        while (queue.hasPendingRequests()) {
            Reservation reservation = queue.peek();
            System.out.println("Processing: " + reservation);
            System.out.println("  Guest: " + reservation.getGuestName());
            System.out.println("  Room Type: " + reservation.getRoomType());
            System.out.println("  Nights: " + reservation.getNights());

            // Attempt allocation
            RoomAllocationService.AllocationResult result = allocator.allocateRoom(reservation);

            if (result.isSuccess()) {
                System.out.println("  ✓ SUCCESS: " + result.getMessage());
                System.out.println("  └─> Assigned Room ID: " + result.getRoomId());
            } else {
                System.out.println("  ✗ FAILED: " + result.getMessage());
            }

            // Remove from queue regardless of allocation result
            queue.dequeue();
            processed++;
            System.out.println();
        }

        System.out.println("Processed " + processed + " requests.");
    }

    /**
     * Demonstrates double-booking prevention.
     */
    private static void demonstrateDoubleBookingPrevention(RoomAllocationService allocator) {
        displaySection("DOUBLE-BOOKING PREVENTION");

        System.out.println("Checking for double-booking:");
        System.out.println();
        System.out.println("  Double Booking Detected: " +
                (allocator.hasDoubleBooking() ? "YES ⚠️" : "NO ✓"));

        System.out.println();
        System.out.println("All allocated room IDs are unique:");
        System.out.println("  " + allocator.getAllAllocatedRoomIds());
        System.out.println();
        System.out.println("No two reservations share the same room ID.");
    }

    /**
     * Demonstrates handling of unavailable room requests.
     */
    private static void demonstrateUnavailableHandling(RoomInventory inventory,
                                                RoomAllocationService allocator) {
        displaySection("UNAVAILABLE ROOM REQUEST HANDLING");

        System.out.println("Attempting to allocate when room type is unavailable:");
        System.out.println();

        // Book the only Penthouse
        inventory.bookRoom("Penthouse");
        System.out.println("  Booked the only Penthouse room...");
        System.out.println("  Penthouse availability: " + inventory.getAvailability("Penthouse"));
        System.out.println();

        // Try to allocate another Penthouse
        Reservation request = new Reservation("RES-999", "Test User", "Penthouse", 2);
        RoomAllocationService.AllocationResult result = allocator.allocateRoom(request);

        System.out.println("Allocating: " + request);
        System.out.println("  Result: " + result.isSuccess());
        System.out.println("  Message: " + result.getMessage());
        System.out.println("  Room ID: " + (result.getRoomId() != null ? result.getRoomId() : "N/A"));
    }

    /**
     * Displays a section header with ASCII art.
     */
    private static void displaySection(String title) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  " + title + " ".repeat(52 - title.length()) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
