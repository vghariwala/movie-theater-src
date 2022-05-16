package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {

    @Test void specialMovieWith20PercentDiscount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
	Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
	assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithSequence1Discount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
	assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithSequence2Discount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
	assertEquals(10.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithSequence7Discount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
	assertEquals(11.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithEarlyBird11AmDiscount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
	assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithEarlyBird4PmDiscount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
	assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithEarlyBirdBetween11AmAnd4PmDiscount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
	assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test void movieWithNoDiscount() {
	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
	Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
	assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }


}
