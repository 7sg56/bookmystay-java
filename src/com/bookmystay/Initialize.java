package com.bookmystay;

/**
 * Handles application initialization - displays welcome message.
 *
 * @author BookMyStay Team
 * @version 1.0
 */
public class Initialize {

    /**
     * Initializes the application - displays welcome message.
     */
    public static void init() {
        displayWelcome();
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
        System.out.println("║              HOTEL BOOKING SYSTEM v1.0                     ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
}
