package com.bookmystay.booking;

/**
 * Represents a guest's booking request/reservation.
 * Contains guest information and room preferences.
 *
 * Key Concepts:
 * - Immutable data - once created, reservation details cannot change
 * - Encapsulates booking intent - captures all relevant request data
 *
 * @author BookMyStay Team
 * @version 5.0
 */
public class Reservation {

    private final String reservationId;
    private final String guestName;
    private final String roomType;
    private final int nights;
    private final long requestTime;

    /**
     * Creates a new reservation request.
     *
     * @param reservationId unique identifier for this reservation
     * @param guestName    name of the guest making the request
     * @param roomType     preferred room type
     * @param nights       number of nights requested
     */
    public Reservation(String reservationId, String guestName, String roomType, int nights) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
        this.requestTime = System.currentTimeMillis();
    }

    /**
     * Gets the unique reservation identifier.
     *
     * @return the reservation ID
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Gets the guest name.
     *
     * @return the guest name
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Gets the requested room type.
     *
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Gets the number of nights requested.
     *
     * @return the number of nights
     */
    public int getNights() {
        return nights;
    }

    /**
     * Gets the timestamp when this request was made.
     *
     * @return the request timestamp in milliseconds
     */
    public long getRequestTime() {
        return requestTime;
    }

    /**
     * Gets a formatted string of the request time.
     *
     * @return formatted request time
     */
    public String getFormattedRequestTime() {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(requestTime));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s room (%d nights)",
                reservationId, guestName, roomType, nights);
    }
}
