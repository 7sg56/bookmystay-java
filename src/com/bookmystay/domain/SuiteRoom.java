package com.bookmystay.domain;

/**
 * Concrete class representing a Suite Room.
 * A suite is a premium room with multiple rooms and luxury amenities.
 */
public class SuiteRoom extends Room {

    private static final String ROOM_TYPE = "Suite";
    private static final int NUMBER_OF_BEDS = 2;
    private static final int SIZE = 600;
    private static final double PRICE = 250.00;

    /**
     * Creates a new SuiteRoom with predefined characteristics.
     */
    public SuiteRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to suite rooms.
     *
     * @return a description of the suite room
     */
    @Override
    public String getDescription() {
        return "A luxurious suite offering the ultimate comfort and privacy. " +
               "Includes a separate living area, bedroom with premium bedding, " +
               "spacious bathroom with jacuzzi, mini-bar, premium entertainment system, " +
               "and stunning views. Perfect for special occasions or extended stays.";
    }
}
