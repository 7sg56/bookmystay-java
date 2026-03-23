package com.bookmystay.domain;

/**
 * Concrete class representing a Double Room.
 * A double room is designed for two people with additional space and amenities.
 */
public class DoubleRoom extends Room {

    private static final String ROOM_TYPE = "Double";
    private static final int NUMBER_OF_BEDS = 2;
    private static final int SIZE = 350;
    private static final double PRICE = 120.00;

    /**
     * Creates a new DoubleRoom with predefined characteristics.
     */
    public DoubleRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to double rooms.
     *
     * @return a description of the double room
     */
    @Override
    public String getDescription() {
        return "A spacious double room ideal for couples or friends traveling together. " +
               "Features two comfortable beds, a seating area, private bathroom, " +
               "work desk, and premium amenities including complimentary Wi-Fi.";
    }
}
