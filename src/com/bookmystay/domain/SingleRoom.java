package com.bookmystay.domain;

/**
 * Concrete class representing a Single Room.
 * A single room is designed for one person with basic amenities.
 */
public class SingleRoom extends Room {

    private static final String ROOM_TYPE = "Single";
    private static final int NUMBER_OF_BEDS = 1;
    private static final int SIZE = 250;
    private static final double PRICE = 75.00;

    /**
     * Creates a new SingleRoom with predefined characteristics.
     */
    public SingleRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to single rooms.
     *
     * @return a description of the single room
     */
    @Override
    public String getDescription() {
        return "A cozy single room perfect for solo travelers. " +
               "Includes a comfortable single bed, private bathroom, " +
               "work desk, and complimentary Wi-Fi.";
    }
}
