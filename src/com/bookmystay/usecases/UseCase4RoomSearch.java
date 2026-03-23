package com.bookmystay.usecases;

import com.bookmystay.domain.Room;
import com.bookmystay.inventory.RoomInventory;
import com.bookmystay.inventory.RoomSearchService;
import com.bookmystay.inventory.RoomSearchService.RoomSearchResult;

import java.util.List;

/**
 * Use Case 4: Room Search & Availability Check
 *
 * Entry point for demonstrating read-only room search functionality.
 * This use case shows how to safely query inventory without modifying state.
 *
 * Concepts demonstrated:
 * - Read-Only Access: Search operations don't alter inventory
 * - Defensive Programming: Filters unavailable rooms before display
 * - Separation of Concerns: Search isolated from booking/modification logic
 * - Domain Model Usage: Room objects provide descriptive information
 * - Validation Logic: Only available rooms shown to guests
 *
 * @author BookMyStay Team
 * @version 4.1
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  Use Case 4: Room Search & Availability Check");
        System.out.println("=".repeat(60));
        System.out.println();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create search service with read-only access
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Display initial inventory state
        displayHeader("INITIAL INVENTORY STATE");
        inventory.displayInventory();
        System.out.println();

        // Search for all available rooms
        demonstrateAllRoomSearch(searchService);
        System.out.println();

        // Search for specific room type
        demonstrateSpecificRoomSearch(searchService);
        System.out.println();

        // Demonstrate unavailable room handling
        demonstrateUnavailableRoom(searchService);
        System.out.println();

        // Show that inventory remains unchanged (read-only)
        displayHeader("INVENTORY STATE AFTER ALL SEARCHES (UNCHANGED)");
        inventory.displayInventory();
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("Key Takeaways:");
        System.out.println("  - Search operations are read-only - no state changes");
        System.out.println("  - Defensive programming filters unavailable options");
        System.out.println("  - Clear separation between search and booking logic");
        System.out.println("  - Domain model provides room details, inventory provides counts");
        System.out.println("=".repeat(60));
    }

    /**
     * Demonstrates searching for all available room types.
     */
    private static void demonstrateAllRoomSearch(RoomSearchService searchService) {
        displayHeader("SEARCH: ALL AVAILABLE ROOMS");

        List<RoomSearchResult> results = searchService.searchAvailableRooms();

        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  Room Type        │ Beds │ Size   │ Price    │ Available │");
        System.out.println("├────────────────────────────────────────────────────────┤");

        for (RoomSearchResult result : results) {
            Room room = result.getRoom();
            System.out.printf("│  %-16s │ %-4d │ %-6d │ $%-7.2f │ %-9d │%n",
                    room.getRoomType(),
                    room.getNumberOfBeds(),
                    room.getSize(),
                    room.getPrice(),
                    result.getAvailable());
        }

        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.printf("│  %-16s │ %-4s │ %-6s │ %-9s │ %-9d │%n",
                "TOTAL", "-", "-", "-", searchService.getTotalAvailableRooms());
        System.out.println("└────────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("Search returned " + results.size() + " room type(s) with availability.");
    }

    /**
     * Demonstrates searching for a specific room type.
     */
    private static void demonstrateSpecificRoomSearch(RoomSearchService searchService) {
        displayHeader("SEARCH: SPECIFIC ROOM TYPE");

        String roomType = "Penthouse";
        RoomSearchResult result = searchService.searchByRoomType(roomType);

        System.out.println("Searching for: " + roomType);
        System.out.println();

        if (result != null) {
            Room room = result.getRoom();
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│  Found: " + roomType + " ROOM" + " ".repeat(35 - roomType.length()) + "│");
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.printf("│  Beds: %-4d  Size: %-4d sq ft  Price: $%-8.2f/night    │%n",
                    room.getNumberOfBeds(), room.getSize(), room.getPrice());
            System.out.printf("│  Available: %d room(s)%35s│%n", result.getAvailable(), " ");
            System.out.println("└────────────────────────────────────────────────────────┘");
        } else {
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│  Result: Room type '" + roomType + "' is not available     │");
            System.out.println("└────────────────────────────────────────────────────────┘");
        }
    }

    /**
     * Demonstrates handling of unavailable rooms.
     */
    private static void demonstrateUnavailableRoom(RoomSearchService searchService) {
        displayHeader("SEARCH: UNAVAILABLE ROOM TYPE");

        String roomType = "NonExistent";
        RoomSearchResult result = searchService.searchByRoomType(roomType);

        System.out.println("Searching for: " + roomType);
        System.out.println();

        if (result == null) {
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│  Result: Room type '" + roomType + "' not found           │");
            System.out.println("│          or has zero availability.                       │");
            System.out.println("└────────────────────────────────────────────────────────┘");
        }
    }

    /**
     * Displays a section header with ASCII art.
     */
    private static void displayHeader(String title) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  " + title + " ".repeat(52 - title.length()) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
