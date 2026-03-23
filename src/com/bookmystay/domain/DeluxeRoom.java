package com.bookmystay.domain;

/**
 * Concrete class representing a Deluxe Room.
 * A deluxe room offers enhanced amenities and more space than a standard room.
 */
public class DeluxeRoom extends Room {

    private static final String ROOM_TYPE = "Deluxe";
    private static final int NUMBER_OF_BEDS = 1;
    private static final int SIZE = 320;
    private static final double PRICE = 150.00;

    /**
     * Creates a new DeluxeRoom with predefined characteristics.
     */
    public DeluxeRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to deluxe rooms.
     *
     * @return a description of the deluxe room
     */
    @Override
    public String getDescription() {
        return "A premium deluxe room with extra space and upgraded amenities. " +
               "Features a king-size bed, balcony with view, premium bedding, " +
               "minibar, coffee station, high-speed Wi-Fi, and luxury toiletries.";
    }
}
