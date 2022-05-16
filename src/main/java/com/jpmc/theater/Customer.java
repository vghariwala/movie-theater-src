package com.jpmc.theater;

import lombok.Data;

/**
 * A customer instance used to make the movie reservation
 */
@Data public class Customer {

    /**
     * A customer name
     */
    private final String name;

    /**
     * A unique customer id
     */
    private final String id;

    /**
     * @param name customer name
     * @param id   customer id
     */
    public Customer(String name, String id) {
	this.id = id; // NOTE - id is not used anywhere at the moment
	this.name = name;
    }
}