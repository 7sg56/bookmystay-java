package com.bookmystay;

import com.bookmystay.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the BookMyStay Hotel Booking Management System.
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
            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 0:
                    displayExitMessage();
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
                    displayError("Invalid choice!");
            }
            System.out.println();
        }
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│              MAIN MENU                     │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  1. View All Room Types                     │");
        System.out.println("│  2. View Specific Room Type                 │");
        System.out.println("│  3. View Room Availability Summary          │");
        System.out.println("│  0. Exit                                    │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  Enter your choice (0-3):                   │");
        System.out.println("└────────────────────────────────────────────┘");
    }

    /**
     * Displays the exit message.
     */
    private static void displayExitMessage() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║               Thank you for using BookMyStay!              ║");
        System.out.println("║                         Goodbye!                          ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    /**
     * Displays an error message.
     */
    private static void displayError(String message) {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│  ERROR: " + message + " ".repeat(29 - message.length()) + "│");
        System.out.println("└────────────────────────────────────────────┘");
    }

    /**
     * Displays the header for displaying rooms.
     */
    private static void displayRoomHeader(String title) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              " + title + " ".repeat(48 - title.length()) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    /**
     * Displays a room type in ASCII box style.
     */
    private static void displayRoomBox(Room room, int available) {
        String type = room.getRoomType();
        String desc = room.getDescription();
        String truncatedDesc = desc.length() > 54 ? desc.substring(0, 54) : desc;

        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.printf("│  %s ROOM%s│%n", type, " ".repeat(44 - type.length()));
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│  Description:                                             │");
        System.out.printf("│    %s%s  │%n", truncatedDesc, " ".repeat(54 - truncatedDesc.length()));
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.printf("│  Beds: %-4d  Size: %-4d sq ft  Price: $%-8.2f/night    │%n",
                room.getNumberOfBeds(), room.getSize(), room.getPrice());
        System.out.printf("│  Available: %d room(s)%35s│%n", available, " ");
        System.out.println("└────────────────────────────────────────────────────────┘");
    }

    /**
     * Displays all room types with their details and availability.
     */
    private static void displayAllRooms() {
        displayRoomHeader("AVAILABLE ROOM TYPES");
        System.out.println();

        displayRoomBox(new StudioRoom(), studioRoomAvailability);
        System.out.println();

        displayRoomBox(new SingleRoom(), singleRoomAvailability);
        System.out.println();

        displayRoomBox(new DoubleRoom(), doubleRoomAvailability);
        System.out.println();

        displayRoomBox(new DeluxeRoom(), deluxeRoomAvailability);
        System.out.println();

        displayRoomBox(new FamilyRoom(), familyRoomAvailability);
        System.out.println();

        displayRoomBox(new OceanViewRoom(), oceanViewRoomAvailability);
        System.out.println();

        displayRoomBox(new SuiteRoom(), suiteRoomAvailability);
        System.out.println();

        displayRoomBox(new PenthouseRoom(), penthouseRoomAvailability);
    }

    /**
     * Displays a sub-menu for selecting and viewing a specific room type.
     */
    private static void displayRoomByMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              SELECT ROOM TYPE                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  1. Studio Room      - $60/night  (200 sq ft)          │");
        System.out.println("│  2. Single Room      - $75/night  (250 sq ft)          │");
        System.out.println("│  3. Double Room      - $120/night (350 sq ft)          │");
        System.out.println("│  4. Deluxe Room      - $150/night (320 sq ft)          │");
        System.out.println("│  5. Family Room      - $200/night (500 sq ft)          │");
        System.out.println("│  6. Ocean View Room  - $180/night (300 sq ft)          │");
        System.out.println("│  7. Suite Room       - $250/night (600 sq ft)          │");
        System.out.println("│  8. Penthouse Room   - $500/night (1200 sq ft)         │");
        System.out.println("│  0. Back to Main Menu                                     │");
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│  Enter your choice (0-8):                               │");
        System.out.println("└────────────────────────────────────────────────────────┘");

        int choice = getIntegerInput("");

        System.out.println();
        switch (choice) {
            case 1:
                displayRoomBox(new StudioRoom(), studioRoomAvailability);
                break;
            case 2:
                displayRoomBox(new SingleRoom(), singleRoomAvailability);
                break;
            case 3:
                displayRoomBox(new DoubleRoom(), doubleRoomAvailability);
                break;
            case 4:
                displayRoomBox(new DeluxeRoom(), deluxeRoomAvailability);
                break;
            case 5:
                displayRoomBox(new FamilyRoom(), familyRoomAvailability);
                break;
            case 6:
                displayRoomBox(new OceanViewRoom(), oceanViewRoomAvailability);
                break;
            case 7:
                displayRoomBox(new SuiteRoom(), suiteRoomAvailability);
                break;
            case 8:
                displayRoomBox(new PenthouseRoom(), penthouseRoomAvailability);
                break;
            case 0:
                return;
            default:
                displayError("Invalid room type selection!");
        }
    }

    /**
     * Displays a summary of all room availabilities.
     */
    private static void displayRoomSummary() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ROOM AVAILABILITY SUMMARY                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  Room Type        │ Available  │ Price / Night         │");
        System.out.println("├────────────────────────────────────────────────────────┤");

        List<RoomSummary> summaries = new ArrayList<>();
        summaries.add(new RoomSummary("Studio", studioRoomAvailability, 60.00));
        summaries.add(new RoomSummary("Single", singleRoomAvailability, 75.00));
        summaries.add(new RoomSummary("Double", doubleRoomAvailability, 120.00));
        summaries.add(new RoomSummary("Deluxe", deluxeRoomAvailability, 150.00));
        summaries.add(new RoomSummary("Family", familyRoomAvailability, 200.00));
        summaries.add(new RoomSummary("Ocean View", oceanViewRoomAvailability, 180.00));
        summaries.add(new RoomSummary("Suite", suiteRoomAvailability, 250.00));
        summaries.add(new RoomSummary("Penthouse", penthouseRoomAvailability, 500.00));

        int total = 0;
        for (RoomSummary summary : summaries) {
            System.out.printf("│  %-16s │ %-10d │ $%-20.2f│%n",
                    summary.roomType, summary.available, summary.price);
            total += summary.available;
        }

        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.printf("│  %-16s │ %-10d │                      │%n", "TOTAL", total);
        System.out.println("└────────────────────────────────────────────────────────┘");
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
                displayError("Invalid input!");
            }
        }
    }

    /**
     * Helper class to store room summary information.
     */
    private static class RoomSummary {
        String roomType;
        int available;
        double price;

        RoomSummary(String roomType, int available, double price) {
            this.roomType = roomType;
            this.available = available;
            this.price = price;
        }
    }
}
