package com.bookmystay.usecases;

import com.bookmystay.domain.DoubleRoom;
import com.bookmystay.domain.Room;
import com.bookmystay.domain.SingleRoom;
import com.bookmystay.domain.SuiteRoom;

/**
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Entry point for demonstrating object modeling through inheritance and abstraction.
 * This use case shows how room types are represented using polymorphism and
 * how availability is tracked using simple variables (not data structures).
 *
 * Concepts demonstrated:
 * - Abstract Class (Room cannot be instantiated directly)
 * - Inheritance (SingleRoom, DoubleRoom, SuiteRoom extend Room)
 * - Polymorphism (Room references handle different concrete implementations)
 * - Encapsulation (Room attributes are private/protected)
 * - Static Availability Representation (using simple variables)
 */
public class UseCase2RoomInitialization {

    // Static availability representation using simple variables
    // This demonstrates the limitation of hardcoded state management
    private static int singleRoomAvailability = 5;
    private static int doubleRoomAvailability = 3;
    private static int suiteRoomAvailability = 2;

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  Use Case 2: Basic Room Types & Static Availability");
        System.out.println("=".repeat(60));
        System.out.println();

        // Create room objects for each room type
        // Polymorphism: All room objects are referenced using the Room type
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display room details and availability information
        System.out.println("--- Available Room Types ---");
        System.out.println();

        displayRoomWithAvailability(singleRoom, singleRoomAvailability);
        System.out.println();

        displayRoomWithAvailability(doubleRoom, doubleRoomAvailability);
        System.out.println();

        displayRoomWithAvailability(suiteRoom, suiteRoomAvailability);
        System.out.println();

        System.out.println("--- Summary ---");
        System.out.println("Total available rooms: " +
                (singleRoomAvailability + doubleRoomAvailability + suiteRoomAvailability));
        System.out.println();
        System.out.println("Note: Availability is stored using simple variables.");
        System.out.println("      This approach has limitations for dynamic inventory management.");
        System.out.println("=".repeat(60));
    }

    /**
     * Displays room details along with its availability.
     * Demonstrates polymorphism - the same method works for all Room subtypes.
     *
     * @param room       the room to display
     * @param available  the number of available rooms of this type
     */
    private static void displayRoomWithAvailability(Room room, int available) {
        room.displayDetails();
        System.out.println("  Available: " + available + " room(s)");
    }
}
