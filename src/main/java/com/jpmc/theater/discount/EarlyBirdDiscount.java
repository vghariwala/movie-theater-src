package com.jpmc.theater.discount;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.LocalTime;

/**
 * A concrete implementation of {@link Discount} interface to calculate the discount based on showing between 11 am and 4 pm.
 * <p>Here the assumption is both bounds are inclusive.
 */
public class EarlyBirdDiscount implements Discount {

    /**
     * An instance of {@link LocalTime} representing 11 am
     */
    private static final LocalTime ELEVEN_AM = LocalTime.of(10, 59, 59);

    /**
     * An instance of {@link LocalTime} representing 4 pm
     */
    private static final LocalTime FOUR_PM = LocalTime.of(16, 0, 1);

    @Override public double calculate(Movie movie, Showing showing) {
	double earlyBirdDiscount = 0;
	LocalTime showingTime = showing.getShowStartTime().toLocalTime();

	if (showingTime.isAfter(ELEVEN_AM) && showingTime.isBefore(FOUR_PM)) {
	    earlyBirdDiscount = movie.getTicketPrice() * 0.25;
	}

	return earlyBirdDiscount;
    }
}
