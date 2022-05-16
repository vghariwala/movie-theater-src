package com.jpmc.theater.discount;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

/**
 * An interface to calculate the discount based on different rules
 */
public interface Discount {
    /**
     * Calculate the discount for a specified {@link Movie} & {@link Showing} instance.
     * <p>The implementing classes provide unique implementation based on the rule.
     *
     * @param movie   an instance of {@link Movie} to calculate the discount
     * @param showing an instance of {@link Showing} to calculate the discount
     * @return the discount amount
     */
    double calculate(Movie movie, Showing showing);
}
