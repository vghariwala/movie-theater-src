package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTests {

    @Test void totalFeeWithSpecialDiscount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 7,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
	assertEquals(30, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithSequence1Discount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 1,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
	assertEquals(28.5, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithSequence2Discount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 2,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
	assertEquals(31.5, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithSequence7Discount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 7,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
	assertEquals(34.5, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithEarlyBird11AmDiscount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 1,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
	assertEquals(28.125, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithEarlyBird4PmDiscount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 7,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
	assertEquals(28.125, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithEarlyBirdBetween11AmAnd4PmDiscount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 7,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
	assertEquals(28.125, new Reservation(customer, showing, 3).totalFee());
    }

    @Test void totalFeeWithNoDiscount() {
	Customer customer = new Customer("John Doe", "unused-id");
	Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 3,
			LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0)));
	assertEquals(37.5, new Reservation(customer, showing, 3).totalFee());
    }

}
