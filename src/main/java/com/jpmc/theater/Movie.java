package com.jpmc.theater;

import com.google.gson.annotations.Expose;
import com.jpmc.theater.discount.Discount;
import com.jpmc.theater.discount.EarlyBirdDiscount;
import com.jpmc.theater.discount.SequenceDiscount;
import com.jpmc.theater.discount.SpecialDiscount;
import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.List;

/**
 * A movie class to represent the movie title, running time and ticket price
 */
@Data public class Movie {

    /**
     * A movie title
     */
    @NonNull @Expose private final String title;

    /**
     * A running time of the movie
     */
    @NonNull @Expose private final Duration runningTime;

    /**
     * A ticket price of the movie
     */
    @NonNull @Expose private final double ticketPrice;

    /**
     * A code to identify movie as special
     */
    @NonNull private final int specialCode;

    /**
     * A list of {@link Discount} instances to apply to the ticket price if available
     */
    private final List<Discount> discounts = List.of(new SpecialDiscount(), new SequenceDiscount(),
		    new EarlyBirdDiscount());

    /**
     * Calculates the ticket price af the movie for a specified {@link Showing} after applying any available discounts
     *
     * @param showing an instance of {@link Showing} to consider applying any available discounts
     * @return the ticket price af the movie for a specified {@link Showing} after applying any available discounts
     */
    public double calculateTicketPrice(Showing showing) {
	return ticketPrice - discounts.stream().map(discount -> discount.calculate(this, showing))
			.mapToDouble(Double::doubleValue).max().orElse(0);
    }

}