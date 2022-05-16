package com.jpmc.theater.discount;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

/**
 * A concrete implementation of {@link Discount} interface to calculate the discount based on show sequence
 */
public class SequenceDiscount implements Discount {

    @Override public double calculate(Movie movie, Showing showing) {
	double sequenceDiscount = 0;
	int showSequence = showing.getSequenceOfTheDay();

	if (showSequence == 1) {
	    sequenceDiscount = 3; // $3 discount for 1st show
	} else if (showSequence == 2) {
	    sequenceDiscount = 2; // $2 discount for 2nd show
	} else if (showSequence == 7) {
	    sequenceDiscount = 1; // $1 discount for 7th show
	}

	return sequenceDiscount;
    }
}
