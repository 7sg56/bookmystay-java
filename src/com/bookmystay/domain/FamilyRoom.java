package com.bookmystay.domain;

/**
 * Concrete class representing a Family Room.
 * A family room is designed to accommodate families with multiple beds and child-friendly amenities.
 */
public class FamilyRoom extends Room {

    private static final String ROOM_TYPE = "Family";
    private static final int NUMBER_OF_BEDS = 3;
    private static final int SIZE = 500;
    private static final double PRICE = 200.00;

    /**
     * Creates a new FamilyRoom with predefined characteristics.
     */
    public FamilyRoom() {
        super(ROOM_TYPE, NUMBER_OF_BEDS, SIZE, PRICE);
    }

    /**
     * Provides a description specific to family rooms.
     *
     * @return a description of the family room
     */
    @Override
    public String getDescription() {
        return "A spacious family room designed for comfort and convenience. " +
               "Includes a queen bed and two twin beds, sofa bed, dining area, " +
               "kitchenette, entertainment system, and kid-friendly amenities.";
    }
}
