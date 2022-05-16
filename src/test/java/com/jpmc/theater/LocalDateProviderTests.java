package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    @Test void makeSureCurrentTime() {
	System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
	assertEquals(LocalDate.now(), LocalDateProvider.singleton().currentDate());
    }
}
