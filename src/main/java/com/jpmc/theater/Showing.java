package com.jpmc.theater;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * A {@link Showing} representing the {@link Movie}, start time of showing and sequence of it in a day
 */
@Data public class Showing {

    /**
     * A {@link Movie} in the {@link Showing}
     */
    @NonNull @Expose private final Movie movie;

    /**
     * A sequence of the {@link Showing}
     */
    @NonNull @Expose private final int sequenceOfTheDay;

    /**
     * A show start time
     */
    @NonNull @Expose private final LocalDateTime showStartTime;

    @SuppressWarnings("unused") public boolean isSequence(int sequence) {
	return this.sequenceOfTheDay == sequence;
    }

    /**
     * A {@link Movie} ticket price
     *
     * @return a {@link Movie} ticket price
     */
    public double getMovieFee() {
	return movie.getTicketPrice();
    }

    /**
     * Calculates the total ticket price after applying any discounts for given number of audience count
     *
     * @param audienceCount a number of audience for calculating the ticket price
     * @return the total ticket price after applying any discounts for given number of audience count
     */
    public double calculateFee(int audienceCount) {
	return movie.calculateTicketPrice(this) * audienceCount;
    }
}
