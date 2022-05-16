package com.jpmc.theater.discount;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

/**
 * A concrete implementation of {@link Discount} interface to calculate the discount based on special movie code
 */
public class SpecialDiscount implements Discount {

    /**
     * A special movie code to apply a discount
     */
    private static final int MOVIE_CODE_SPECIAL = 1;

    @Override public double calculate(Movie movie, Showing showing) {
	if (MOVIE_CODE_SPECIAL == movie.getSpecialCode()) {
	    return movie.getTicketPrice() * 0.2;  // 20% discount for special movie
	} else {
	    return 0;
	}
    }
}
