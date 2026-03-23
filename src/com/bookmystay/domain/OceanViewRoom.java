package com.bookmystay.domain;

/**
 * Concrete class representing an Ocean View Room.
 * A room with stunning ocean views, perfect for a memorable stay.
 */
public class OceanViewRoom extends Room {

    private static final String ROOM_TYPE = "Ocean View";
    private static final int NUMBER_OF_BEDS = 1;
    private static final int SIZE = 300;
    private static final double PRICE = 180.00;

    /**
     * Creates a new OceanViewRoom with predefined characteristics.
     */
    public OceanViewRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to ocean view rooms.
     *
     * @return a description of the ocean view room
     */
    @Override
    public String getDescription() {
        return "Wake up to breathtaking ocean views in this beautiful room. " +
               "Features a large picture window, comfortable bed, private balcony, " +
               "premium amenities, sunrise viewing deck, and beach access.";
    }
}
