package com.bookmystay.booking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages booking requests using a FIFO Queue for fair processing.
 * Requests are stored in arrival order and processed first-come-first-served.
 *
 * Key Concepts:
 * - Queue Data Structure: LinkedList implements Queue for FIFO ordering
 * - FIFO Principle: First request in, first request out
 * - Fairness: No request can bypass another in the queue
 * - Decoupling: Request intake separated from allocation
 *
 * @author BookMyStay Team
 * @version 5.0
 */
public class BookingRequestQueue {

    private final Queue<Reservation> requestQueue;
    private int totalRequestsReceived;

    /**
     * Creates a new booking request queue.
     */
    public BookingRequestQueue() {
        this.requestQueue = new LinkedList<>();
        this.totalRequestsReceived = 0;
    }

    /**
     * Adds a booking request to the queue.
     * The request is added to the end of the queue.
     * No inventory modification occurs at this stage.
     *
     * @param reservation the reservation request to add
     */
    public void enqueue(Reservation reservation) {
        requestQueue.add(reservation);
        totalRequestsReceived++;
    }

    /**
     * Retrieves and removes the next booking request from the queue.
     * Returns null if the queue is empty.
     *
     * @return the next reservation, or null if empty
     */
    public Reservation dequeue() {
        return requestQueue.poll();
    }

    /**
     * Peeks at the next booking request without removing it.
     * Returns null if the queue is empty.
     *
     * @return the next reservation, or null if empty
     */
    public Reservation peek() {
        return requestQueue.peek();
    }

    /**
     * Gets the number of pending requests in the queue.
     *
     * @return the number of pending requests
     */
    public int getPendingCount() {
        return requestQueue.size();
    }

    /**
     * Gets the total number of requests received (including processed ones).
     *
     * @return total requests received
     */
    public int getTotalRequestsReceived() {
        return totalRequestsReceived;
    }

    /**
     * Checks if there are any pending requests.
     *
     * @return true if queue has pending requests
     */
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }

    /**
     * Clears all pending requests from the queue.
     */
    public void clear() {
        requestQueue.clear();
    }

    /**
     * Displays the current state of the request queue.
     */
    public void displayQueueState() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            BOOKING REQUEST QUEUE STATUS                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.printf("│  Pending Requests: %-32d│%n", getPendingCount());
        System.out.printf("│  Total Requests Received: %-24d│%n", getTotalRequestsReceived());
        System.out.println("└────────────────────────────────────────────────────────┘");

        if (hasPendingRequests()) {
            System.out.println();
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│  PENDING REQUESTS (FIFO Order)                      │");
            System.out.println("├────────────────────────────────────────────────────────┤");

            int position = 1;
            for (Reservation res : requestQueue) {
                System.out.printf("│  %d. %-50s│%n", position++, truncate(res.toString(), 50));
            }

            System.out.println("└────────────────────────────────────────────────────────┘");
        } else {
            System.out.println();
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│  No pending requests in queue                         │");
            System.out.println("└────────────────────────────────────────────────────────┘");
        }
    }

    /**
     * Truncates a string to a maximum length.
     *
     * @param str   the string to truncate
     * @param limit the maximum length
     * @return truncated string
     */
    private String truncate(String str, int limit) {
        return str.length() > limit ? str.substring(0, limit - 3) + "..." : str;
    }
}
