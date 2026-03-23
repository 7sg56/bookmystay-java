package com.bookmystay;

/**
 * Hotel Booking Management System - Main Entry Point.
 *
 * @author BookMyStay Team
 * @version 1.0
 */
public class Main {

    /**
     * Main method - application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        initialize();
    }

    /**
     * Initializes the application - displays welcome message and menu.
     */
    public static void initialize() {
        displayWelcome();
        displayMenu();
    }

    /**
     * Displays the welcome message with ASCII art.
     */
    public static void displayWelcome() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║   ██╗   ██╗███████╗██╗  ██╗███████╗██████╗                 ║");
        System.out.println("║   ██║   ██║██╔════╝██║ ██╔╝██╔════╝██╔══██╗                ║");
        System.out.println("║   ██║   ██║█████╗  █████╔╝ █████╗  ██████╔╝                ║");
        System.out.println("║   ╚██╗ ██╔╝██╔══╝  ██╔═██╗ ██╔══╝  ██╔══██╗                ║");
        System.out.println("║    ╚████╔╝ ███████╗██║  ██╗███████╗██║  ██║                ║");
        System.out.println("║     ╚═══╝  ╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝                ║");
        System.out.println("║                                                            ║");
        System.out.println("║              HOTEL BOOKING SYSTEM v1.0                    ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Displays the main menu options.
     */
    public static void displayMenu() {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│              MAIN MENU                     │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  1. Book a Room                            │");
        System.out.println("│  2. View Available Rooms                   │");
        System.out.println("│  3. Cancel Booking                         │");
        System.out.println("│  4. View All Bookings                      │");
        System.out.println("│  5. Exit                                    │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  Enter your choice (1-5):                  │");
        System.out.println("└────────────────────────────────────────────┘");
    }
}
