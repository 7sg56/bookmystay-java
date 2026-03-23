package com.bookmystay.domain;

/**
 * Abstract class representing a generalized room concept.
 * Defines common attributes and behavior shared by all room types.
 * This class cannot be instantiated directly - only concrete room subclasses can be created.
 */
public abstract class Room {

    protected final String roomType;
    protected final int numberOfBeds;
    protected final int size;        // size in square feet
    protected final double price;     // price per night

    /**
     * Constructor for the Room abstract class.
     *
     * @param roomType    the type identifier of the room (e.g., "Single", "Double", "Suite")
     * @param numberOfBeds the number of beds in the room
     * @param size        the size of the room in square feet
     * @param price       the price per night for this room type
     */
    protected Room(String roomType, int numberOfBeds, int size, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.price = price;
    }

    /**
     * Returns a detailed description of the room.
     * Concrete classes must provide their own implementation.
     *
     * @return a string describing the room's specific features
     */
    public abstract String getDescription();

    /**
     * Gets the room type identifier.
     *
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Gets the number of beds in the room.
     *
     * @return the number of beds
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Gets the size of the room in square feet.
     *
     * @return the room size
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the price per night for this room.
     *
     * @return the price per night
     */
    public double getPrice() {
        return price;
    }

    /**
     * Displays all room details to the console.
     */
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("  Description: " + getDescription());
        System.out.println("  Number of Beds: " + numberOfBeds);
        System.out.println("  Size: " + size + " sq ft");
        System.out.println("  Price: $" + price + "/night");
    }

    @Override
    public String toString() {
        return roomType + " Room - " + numberOfBeds + " beds, " + size + " sq ft, $" + price + "/night";
    }
}
