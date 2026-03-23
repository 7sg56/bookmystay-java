package com.bookmystay;

import com.bookmystay.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the BookMyStay Hotel Booking Management System.
 * Features an interactive menu to browse different room types.
 *
 * @author BookMyStay Team
 * @version 2.0
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Room availability stored using simple variables
    private static int studioRoomAvailability = 8;
    private static int singleRoomAvailability = 5;
    private static int doubleRoomAvailability = 3;
    private static int deluxeRoomAvailability = 4;
    private static int familyRoomAvailability = 2;
    private static int oceanViewRoomAvailability = 3;
    private static int suiteRoomAvailability = 2;
    private static int penthouseRoomAvailability = 1;

    public static void main(String[] args) {
        Initialize.init();
        runMainMenu();
    }

    /**
     * Displays and processes the main menu options.
     */
    private static void runMainMenu() {
        while (true) {
            displayMainMenu();
            int choice = getIntegerInput("Enter your choice (0 to exit): ");

            switch (choice) {
                case 0:
                    System.out.println("Thank you for using BookMyStay. Goodbye!");
                    scanner.close();
                    return;
                case 1:
                    displayAllRooms();
                    break;
                case 2:
                    displayRoomByMenu();
                    break;
                case 3:
                    displayRoomSummary();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("=".repeat(60));
        System.out.println("           BookMyStay - Hotel Booking System");
        System.out.println("=".repeat(60));
        System.out.println("Main Menu:");
        System.out.println("  1. View All Room Types");
        System.out.println("  2. View Specific Room Type");
        System.out.println("  3. View Room Availability Summary");
        System.out.println("  0. Exit");
        System.out.println("=".repeat(60));
    }

    /**
     * Displays all room types with their details and availability.
     */
    private static void displayAllRooms() {
        System.out.println("\n--- All Available Room Types ---\n");

        displayRoom(new StudioRoom(), studioRoomAvailability);
        System.out.println();

        displayRoom(new SingleRoom(), singleRoomAvailability);
        System.out.println();

        displayRoom(new DoubleRoom(), doubleRoomAvailability);
        System.out.println();

        displayRoom(new DeluxeRoom(), deluxeRoomAvailability);
        System.out.println();

        displayRoom(new FamilyRoom(), familyRoomAvailability);
        System.out.println();

        displayRoom(new OceanViewRoom(), oceanViewRoomAvailability);
        System.out.println();

        displayRoom(new SuiteRoom(), suiteRoomAvailability);
        System.out.println();

        displayRoom(new PenthouseRoom(), penthouseRoomAvailability);
    }

    /**
     * Displays a sub-menu for selecting and viewing a specific room type.
     */
    private static void displayRoomByMenu() {
        System.out.println("\n--- Select Room Type ---\n");
        System.out.println("  1. Studio Room      - $60/night");
        System.out.println("  2. Single Room      - $75/night");
        System.out.println("  3. Double Room      - $120/night");
        System.out.println("  4. Deluxe Room      - $150/night");
        System.out.println("  5. Family Room      - $200/night");
        System.out.println("  6. Ocean View Room  - $180/night");
        System.out.println("  7. Suite Room       - $250/night");
        System.out.println("  8. Penthouse Room   - $500/night");
        System.out.println("  0. Back to Main Menu");

        int choice = getIntegerInput("\nEnter your choice: ");

        switch (choice) {
            case 1:
                System.out.println();
                displayRoom(new StudioRoom(), studioRoomAvailability);
                break;
            case 2:
                System.out.println();
                displayRoom(new SingleRoom(), singleRoomAvailability);
                break;
            case 3:
                System.out.println();
                displayRoom(new DoubleRoom(), doubleRoomAvailability);
                break;
            case 4:
                System.out.println();
                displayRoom(new DeluxeRoom(), deluxeRoomAvailability);
                break;
            case 5:
                System.out.println();
                displayRoom(new FamilyRoom(), familyRoomAvailability);
                break;
            case 6:
                System.out.println();
                displayRoom(new OceanViewRoom(), oceanViewRoomAvailability);
                break;
            case 7:
                System.out.println();
                displayRoom(new SuiteRoom(), suiteRoomAvailability);
                break;
            case 8:
                System.out.println();
                displayRoom(new PenthouseRoom(), penthouseRoomAvailability);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Displays a summary of all room availabilities.
     */
    private static void displayRoomSummary() {
        System.out.println("\n--- Room Availability Summary ---\n");

        List<RoomSummary> summaries = new ArrayList<>();
        summaries.add(new RoomSummary("Studio", studioRoomAvailability));
        summaries.add(new RoomSummary("Single", singleRoomAvailability));
        summaries.add(new RoomSummary("Double", doubleRoomAvailability));
        summaries.add(new RoomSummary("Deluxe", deluxeRoomAvailability));
        summaries.add(new RoomSummary("Family", familyRoomAvailability));
        summaries.add(new RoomSummary("Ocean View", oceanViewRoomAvailability));
        summaries.add(new RoomSummary("Suite", suiteRoomAvailability));
        summaries.add(new RoomSummary("Penthouse", penthouseRoomAvailability));

        System.out.println("Room Type        | Available");
        System.out.println("-----------------+----------");
        int total = 0;
        for (RoomSummary summary : summaries) {
            System.out.printf("%-16s | %d%n", summary.roomType, summary.available);
            total += summary.available;
        }
        System.out.println("-----------------+----------");
        System.out.printf("Total            | %d%n", total);
    }

    /**
     * Displays room details along with its availability.
     *
     * @param room      the room to display
     * @param available the number of available rooms of this type
     */
    private static void displayRoom(Room room, int available) {
        room.displayDetails();
        System.out.println("  Available: " + available + " room(s)");
    }

    /**
     * Reads an integer input from the user with validation.
     *
     * @param prompt the message to display to the user
     * @return the validated integer input
     */
    private static int getIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Helper class to store room summary information.
     */
    private static class RoomSummary {
        String roomType;
        int available;

        RoomSummary(String roomType, int available) {
            this.roomType = roomType;
            this.available = available;
        }
    }
}
