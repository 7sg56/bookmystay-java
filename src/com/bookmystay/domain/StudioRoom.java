package com.bookmystay.domain;

/**
 * Concrete class representing a Studio Room.
 * A compact yet functional room designed for efficiency and comfort.
 */
public class StudioRoom extends Room {

    private static final String ROOM_TYPE = "Studio";
    private static final int NUMBER_OF_BEDS = 1;
    private static final int SIZE = 200;
    private static final double PRICE = 60.00;

    /**
     * Creates a new StudioRoom with predefined characteristics.
     */
    public StudioRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to studio rooms.
     *
     * @return a description of the studio room
     */
    @Override
    public String getDescription() {
        return "A compact and efficient studio room perfect for short stays. " +
               "Features a comfortable bed, basic amenities, compact workspace, " +
               "and essential comforts at an affordable price point.";
    }
}
