package com.bookmystay;

import com.bookmystay.domain.*;
import com.bookmystay.inventory.RoomInventory;
import com.bookmystay.booking.BookingRequestQueue;
import com.bookmystay.booking.Reservation;
import com.bookmystay.booking.RoomAllocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for BookMyStay Hotel Booking Management System.
 *
 * @author BookMyStay Team
 * @version 2.0
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Services
    private static RoomInventory inventory;
    private static BookingRequestQueue requestQueue;
    private static RoomAllocationService allocationService;

    /**
     * Initializes the application services.
     */
    private static void initializeServices() {
        inventory = new RoomInventory();
        requestQueue = new BookingRequestQueue();
        allocationService = new RoomAllocationService(inventory);
    }

    public static void main(String[] args) {
        initializeServices();
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
                case 4:
                    bookRoom();
                    break;
                case 5:
                    viewBookingQueue();
                    break;
                case 6:
                    viewAllocations();
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
        System.out.println("│  4. Book a Room                            │");
        System.out.println("│  5. View Booking Queue                       │");
        System.out.println("│  6. View Allocated Rooms                    │");
        System.out.println("│  0. Exit                                    │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  Enter your choice (0-6):                   │");
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
     * Displays a section header with ASCII art.
     */
    private static void displayHeader(String title) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              " + title + " ".repeat(48 - title.length()) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
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

        displayRoomBox(new StudioRoom(), inventory.getAvailability("Studio"));
        System.out.println();

        displayRoomBox(new SingleRoom(), inventory.getAvailability("Single"));
        System.out.println();

        displayRoomBox(new DoubleRoom(), inventory.getAvailability("Double"));
        System.out.println();

        displayRoomBox(new DeluxeRoom(), inventory.getAvailability("Deluxe"));
        System.out.println();

        displayRoomBox(new FamilyRoom(), inventory.getAvailability("Family"));
        System.out.println();

        displayRoomBox(new OceanViewRoom(), inventory.getAvailability("Ocean View"));
        System.out.println();

        displayRoomBox(new SuiteRoom(), inventory.getAvailability("Suite"));
        System.out.println();

        displayRoomBox(new PenthouseRoom(), inventory.getAvailability("Penthouse"));
    }

    /**
     * Displays a sub-menu for selecting and viewing a specific room type.
     */
    private static void displayRoomByMenu() {
        displayHeader("SELECT ROOM TYPE");
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
        Room room = switch (choice) {
            case 1 -> new StudioRoom();
            case 2 -> new SingleRoom();
            case 3 -> new DoubleRoom();
            case 4 -> new DeluxeRoom();
            case 5 -> new FamilyRoom();
            case 6 -> new OceanViewRoom();
            case 7 -> new SuiteRoom();
            case 8 -> new PenthouseRoom();
            case 0 -> null;
            default -> null;
        };

        if (room != null) {
            displayRoomBox(room, inventory.getAvailability(room.getRoomType()));
        }
    }

    /**
     * Displays a summary of all room availabilities.
     */
    private static void displayRoomSummary() {
        displayHeader("ROOM AVAILABILITY SUMMARY");
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  Room Type        │ Available  │ Price / Night         │");
        System.out.println("├────────────────────────────────────────────────────────┤");

        List<RoomSummary> summaries = new ArrayList<>();
        summaries.add(new RoomSummary("Studio", inventory.getAvailability("Studio"), 60.00));
        summaries.add(new RoomSummary("Single", inventory.getAvailability("Single"), 75.00));
        summaries.add(new RoomSummary("Double", inventory.getAvailability("Double"), 120.00));
        summaries.add(new RoomSummary("Deluxe", inventory.getAvailability("Deluxe"), 150.00));
        summaries.add(new RoomSummary("Family", inventory.getAvailability("Family"), 200.00));
        summaries.add(new RoomSummary("Ocean View", inventory.getAvailability("Ocean View"), 180.00));
        summaries.add(new RoomSummary("Suite", inventory.getAvailability("Suite"), 250.00));
        summaries.add(new RoomSummary("Penthouse", inventory.getAvailability("Penthouse"), 500.00));

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
     * Handles the book room flow.
     */
    private static void bookRoom() {
        displayHeader("BOOK A ROOM");

        // Get guest name
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│  Guest Information                               │");
        System.out.println("├────────────────────────────────────────────┤");
        String guestName = getStringInput("│  Enter your name: ");

        // Select room type
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  Select Room Type                              │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  1. Studio Room      - $60/night              │");
        System.out.println("│  2. Single Room      - $75/night              │");
        System.out.println("│  3. Double Room      - $120/night             │");
        System.out.println("│  4. Deluxe Room      - $150/night             │");
        System.out.println("│  5. Family Room      - $200/night             │");
        System.out.println("│  6. Ocean View Room  - $180/night             │");
        System.out.println("│  7. Suite Room       - $250/night             │");
        System.out.println("│  8. Penthouse Room   - $500/night             │");
        System.out.println("│  0. Cancel                                     │");
        System.out.println("├────────────────────────────────────────────┤");
        String roomTypeChoice = getStringInput("│  Enter your choice (0-8): ");

        int roomChoice = parseRoomChoice(roomTypeChoice);
        if (roomChoice == 0) {
            return;
        }

        String roomType = getRoomTypeByChoice(roomChoice);
        Room room = getRoomByChoice(roomChoice);

        // Show selected room details
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  Selected Room                                       │");
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│  Type: " + roomType + " ".repeat(46 - roomType.length()) + "│");
        System.out.printf("│  Price: $%.2f/night" + " ".repeat(35) + "│%n", room.getPrice());
        System.out.printf("│  Available: %d room(s)%35s│%n", inventory.getAvailability(roomType), " ");
        System.out.println("└────────────────────────────────────────────────────────┘");

        if (!inventory.isAvailable(roomType)) {
            System.out.println();
            System.out.println("┌────────────────────────────────────────────┐");
            System.out.println("│  Sorry, no " + roomType + " rooms available     │");
            System.out.println("└────────────────────────────────────────────┘");
            return;
        }

        // Get number of nights
        System.out.println();
        int nights = getIntegerInput("Enter number of nights: ");

        // Confirm booking
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│  Confirm Booking                               │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│  Guest: %s" + " ".repeat(39 - guestName.length()) + "│%n", guestName);
        System.out.printf("│  Room: %s" + " ".repeat(39 - roomType.length()) + "│%n", roomType);
        System.out.printf("│  Nights: %d" + " ".repeat(35) + "│%n", nights);
        double totalCost = room.getPrice() * nights;
        System.out.printf("│  Total Cost: $%.2f" + " ".repeat(28) + "│%n", totalCost);
        System.out.println("├────────────────────────────────────────────┤");
        String confirm = getStringInput("│  Confirm booking? (y/n): ");

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("└────────────────────────────────────────────┘");
            System.out.println("  Booking cancelled.");
            return;
        }

        System.out.println("└────────────────────────────────────────────┘");

        // Create reservation
        String reservationId = generateReservationId();
        Reservation reservation = new Reservation(reservationId, guestName, roomType, nights);

        // Queue and process
        requestQueue.enqueue(reservation);
        RoomAllocationService.AllocationResult result = allocationService.allocateRoom(reservation);
        requestQueue.dequeue();

        // Display result
        displayBookingResult(result, totalCost);
    }

    /**
     * Displays the booking result.
     */
    private static void displayBookingResult(RoomAllocationService.AllocationResult result, double totalCost) {
        displayHeader("BOOKING RESULT");

        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  Reservation ID: " + result.getReservation().getReservationId() + " ".repeat(24) + "│");
        System.out.printf("│  Guest: %s" + " ".repeat(44 - result.getReservation().getGuestName().length()) + "│%n",
                result.getReservation().getGuestName());
        System.out.printf("│  Room Type: %s" + " ".repeat(38 - result.getReservation().getRoomType().length()) + "│%n",
                result.getReservation().getRoomType());
        System.out.printf("│  Nights: %d" + " ".repeat(42) + "│%n", result.getReservation().getNights());
        System.out.printf("│  Total Cost: $%.2f" + " ".repeat(32) + "│%n", totalCost);

        System.out.println("├────────────────────────────────────────────────────────┤");

        if (result.isSuccess()) {
            System.out.println("│  Status: CONFIRMED ✓                              │");
            System.out.println("│  Room ID: " + result.getRoomId() + " ".repeat(33) + "│");
            System.out.println("│                                                      │");
            System.out.println("│  Thank you for your booking!                       │");
        } else {
            System.out.println("│  Status: FAILED ✗                                 │");
            System.out.println("│  Reason: " + result.getMessage() + " ".repeat(31 - result.getMessage().length()) + "│");
        }

        System.out.println("└────────────────────────────────────────────────────────┘");
    }

    /**
     * Displays the current booking queue.
     */
    private static void viewBookingQueue() {
        displayHeader("BOOKING REQUEST QUEUE");
        requestQueue.displayQueueState();
    }

    /**
     * Displays allocated rooms.
     */
    private static void viewAllocations() {
        displayHeader("ALLOCATED ROOMS");
        allocationService.displayAllocationState();
    }

    /**
     * Reads a string input from the user.
     *
     * @param prompt the message to display
     * @return the string input
     */
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Reads an integer input from the user with validation.
     *
     * @param prompt the message to display
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
     * Parses room choice and returns the integer value.
     */
    private static int parseRoomChoice(String choice) {
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Gets room type string by choice.
     */
    private static String getRoomTypeByChoice(int choice) {
        return switch (choice) {
            case 1 -> "Studio";
            case 2 -> "Single";
            case 3 -> "Double";
            case 4 -> "Deluxe";
            case 5 -> "Family";
            case 6 -> "Ocean View";
            case 7 -> "Suite";
            case 8 -> "Penthouse";
            default -> "";
        };
    }

    /**
     * Gets Room object by choice.
     */
    private static Room getRoomByChoice(int choice) {
        return switch (choice) {
            case 1 -> new StudioRoom();
            case 2 -> new SingleRoom();
            case 3 -> new DoubleRoom();
            case 4 -> new DeluxeRoom();
            case 5 -> new FamilyRoom();
            case 6 -> new OceanViewRoom();
            case 7 -> new SuiteRoom();
            case 8 -> new PenthouseRoom();
            default -> null;
        };
    }

    /**
     * Generates a unique reservation ID.
     */
    private static String generateReservationId() {
        return "RES-" + String.format("%06d", (int)(Math.random() * 1000000));
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
