package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TheaterTests {

    @Test void totalFeeForNoCustomer() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	try {
	    theater.reserve(null, 5, 3);
	    fail("Expected exception is not thrown");
	} catch (IllegalStateException e) {
	    assertEquals("A customer is required to make the reservation", e.getLocalizedMessage());
	}
    }

    @Test void totalFeeForNoShowing() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	try {
	    theater.reserve(john, 10, 3);
	    fail("Expected exception is not thrown");
	} catch (IllegalStateException e) {
	    assertEquals("Not able to find any showing for given sequence 10", e.getLocalizedMessage());
	}
    }

    @Test void totalFeeForNoTickets() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	try {
	    theater.reserve(john, 4, 0);
	    fail("Expected exception is not thrown");
	} catch (IllegalStateException e) {
	    assertEquals("At least one ticket is required ot make the reservation", e.getLocalizedMessage());
	}
    }

    @Test void totalFeeForCustomerWithSpecialDiscount() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	Reservation reservation = theater.reserve(john, 5, 3);
	assertEquals(30, reservation.totalFee());
    }

    @Test void totalFeeForCustomerWithSequence1Discount() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	Reservation reservation = theater.reserve(john, 1, 3);
	assertEquals(24, reservation.totalFee());
    }

    @Test void totalFeeForCustomerWithSequence7Discount() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	Reservation reservation = theater.reserve(john, 7, 3);
	assertEquals(30, reservation.totalFee());
    }

    @Test void totalFeeForCustomerWithEarlyBird11AmDiscount() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	Reservation reservation = theater.reserve(john, 2, 4);
	assertEquals(37.5, reservation.totalFee());
    }

    @Test void totalFeeForCustomerWithNoDiscount() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	Customer john = new Customer("John Doe", "id-12345");
	Reservation reservation = theater.reserve(john, 9, 4);
	assertEquals(36, reservation.totalFee());
    }

    @Test void printMovieScheduleTextFormat() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	theater.printScheduleTextFormat();
    }

    @Test void printMovieScheduleJsonFormat() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	theater.printScheduleJsonFormat();
    }

    @Test void printMovieScheduleOriginal() {
	Theater theater = new Theater(LocalDateProvider.singleton());
	theater.printSchedule();
    }

}
