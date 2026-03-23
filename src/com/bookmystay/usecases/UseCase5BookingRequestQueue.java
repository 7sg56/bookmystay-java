package com.bookmystay.usecases;

import com.bookmystay.booking.BookingRequestQueue;
import com.bookmystay.booking.Reservation;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Entry point for demonstrating FIFO-based booking request handling.
 * This use case shows how a Queue ensures fair processing of requests.
 *
 * Concepts demonstrated:
 * - Queue Data Structure: LinkedList for FIFO ordering
 * - FIFO Principle: First-come-first-served fairness
 * - Request Ordering: Automatic preservation of arrival order
 * - Decoupling: Request intake separated from allocation
 * - No Inventory Mutation: Queue only stores requests, no bookings made
 *
 * @author BookMyStay Team
 * @version 5.1
 */
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  Use Case 5: Booking Request (First-Come-First-Served)");
        System.out.println("=".repeat(60));
        System.out.println();

        // Create booking request queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Display initial empty queue state
        displaySection("INITIAL QUEUE STATE (EMPTY)");
        queue.displayQueueState();
        System.out.println();

        // Simulate multiple guests arriving simultaneously
        demonstrateSimultaneousRequests(queue);
        System.out.println();

        // Show queue state after requests
        displaySection("QUEUE STATE AFTER REQUESTS");
        queue.displayQueueState();
        System.out.println();

        // Demonstrate FIFO processing
        demonstrateFIFOProcessing(queue);
        System.out.println();

        // Show queue state after processing
        displaySection("QUEUE STATE AFTER PROCESSING");
        queue.displayQueueState();
        System.out.println();

        // Demonstrate more requests arriving
        demonstrateAdditionalRequests(queue);
        System.out.println();

        // Final queue state
        displaySection("FINAL QUEUE STATE");
        queue.displayQueueState();
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("Key Takeaways:");
        System.out.println("  - Queue ensures FIFO (First-Come-First-Served) fairness");
        System.out.println("  - Requests are processed in arrival order automatically");
        System.out.println("  - No inventory mutation during request intake");
        System.out.println("  - Decoupled from allocation - requests stored first");
        System.out.println("=".repeat(60));
    }

    /**
     * Demonstrates simultaneous booking requests from multiple guests.
     */
    private static void demonstrateSimultaneousRequests(BookingRequestQueue queue) {
        displaySection("SIMULTANEOUS BOOKING REQUESTS");

        System.out.println("Guests arriving simultaneously:");
        System.out.println();

        queue.enqueue(new Reservation("RES-001", "Alice Johnson", "Suite", 3));
        System.out.println("  ✓ Alice Johnson requested Suite room (3 nights)");

        queue.enqueue(new Reservation("RES-002", "Bob Smith", "Double", 2));
        System.out.println("  ✓ Bob Smith requested Double room (2 nights)");

        queue.enqueue(new Reservation("RES-003", "Carol Davis", "Penthouse", 5));
        System.out.println("  ✓ Carol Davis requested Penthouse room (5 nights)");

        queue.enqueue(new Reservation("RES-004", "David Wilson", "Single", 1));
        System.out.println("  ✓ David Wilson requested Single room (1 night)");

        queue.enqueue(new Reservation("RES-005", "Eva Martinez", "Family", 4));
        System.out.println("  ✓ Eva Martinez requested Family room (4 nights)");

        System.out.println();
        System.out.println("All requests have been queued in arrival order.");
    }

    /**
     * Demonstrates FIFO processing of queued requests.
     */
    private static void demonstrateFIFOProcessing(BookingRequestQueue queue) {
        displaySection("FIFO REQUEST PROCESSING");

        System.out.println("Processing requests (first-come-first-served):");
        System.out.println();

        int processed = 0;
        while (processed < 3 && queue.hasPendingRequests()) {
            Reservation next = queue.peek();
            System.out.printf("  Processing: %s%n", next);
            System.out.printf("    Guest: %s, Room Type: %s, Nights: %d%n",
                    next.getGuestName(), next.getRoomType(), next.getNights());

            queue.dequeue();
            processed++;
            System.out.println("  ✓ Request removed from queue");
            System.out.println();
        }

        System.out.println("Processed " + processed + " requests from the front of the queue.");
    }

    /**
     * Demonstrates additional requests arriving after some processing.
     */
    private static void demonstrateAdditionalRequests(BookingRequestQueue queue) {
        displaySection("ADDITIONAL REQUESTS");

        System.out.println("More guests arriving:");
        System.out.println();

        queue.enqueue(new Reservation("RES-006", "Frank Garcia", "Deluxe", 2));
        System.out.println("  ✓ Frank Garcia requested Deluxe room (2 nights)");

        queue.enqueue(new Reservation("RES-007", "Grace Lee", "Ocean View", 3));
        System.out.println("  ✓ Grace Lee requested Ocean View room (3 nights)");

        System.out.println();
        System.out.println("New requests have been added to the end of the queue.");
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
