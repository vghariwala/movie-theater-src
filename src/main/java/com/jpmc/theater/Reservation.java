package com.jpmc.theater;

import lombok.Data;
import lombok.NonNull;

/**
 * A reservation class to make the movie reservation
 */
@Data public class Reservation {

    /**
     * A {@link Customer} making the reservation
     */
    @NonNull private Customer customer;

    /**
     * A reservation for the specific {@link Showing}
     */
    @NonNull private Showing showing;

    /**
     * Total number of tickets under this reservation
     */
    @NonNull private int audienceCount;

    /**
     * A total fee for this reservation
     */
    public double totalFee() {
	return showing.calculateFee(audienceCount);
    }
}