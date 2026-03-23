package com.bookmystay.domain;

/**
 * Concrete class representing a Penthouse Room.
 * The most exclusive accommodation with panoramic views and luxury amenities.
 */
public class PenthouseRoom extends Room {

    private static final String ROOM_TYPE = "Penthouse";
    private static final int NUMBER_OF_BEDS = 3;
    private static final int SIZE = 1200;
    private static final double PRICE = 500.00;

    /**
     * Creates a new PenthouseRoom with predefined characteristics.
     */
    public PenthouseRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to penthouse rooms.
     *
     * @return a description of the penthouse room
     */
    @Override
    public String getDescription() {
        return "Experience ultimate luxury in our exclusive penthouse suite. " +
               "Features panoramic city views, multiple bedrooms, private terrace, " +
               "personal butler service, home theater, private elevator access, " +
               "and world-class concierge service.";
    }
}
