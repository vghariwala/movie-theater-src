package com.jpmc.theater;

import java.time.LocalDate;

/**
 * A singleton instance of {@link LocalDateProvider}
 */
public class LocalDateProvider {

    // private constructor to avoid instantiating the instance of this class from other class(es)
    private LocalDateProvider() {
    }

    /**
     * Returns the singleton instance of {@link  LocalDateProvider}
     *
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
	return SingletonHolder.INSTANCE;
    }

    /**
     * Returns the current date
     *
     * @return the current date
     */
    public LocalDate currentDate() {
	return LocalDate.now();
    }

    /**
     * A helper class to instantiate and hold the singleton instance
     */
    private static class SingletonHolder {
	private static final LocalDateProvider INSTANCE = new LocalDateProvider();
    }
}
