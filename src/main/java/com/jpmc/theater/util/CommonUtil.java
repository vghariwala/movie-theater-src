package com.jpmc.theater.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * A common util class to provide the common functionality used across application.
 * <p>Since it is util class, it cannot be extended or instantiated, and it should provide only static methods.
 */
public final class CommonUtil {

    private CommonUtil() {
    }

    /**
     * A method to transform {@link Duration} instance to Human Readable format, e.g. 1 hour 25 minutes
     *
     * @param duration {@link Duration} to transform to human-readable format
     * @return a human-readable format of {@link Duration}
     */
    public static String humanReadableFormat(Duration duration) {
	long hour = duration.toHours();
	long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(hour);

	return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin,
			handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private static String handlePlural(long value) {
	if (value == 1) {
	    return "";
	} else {
	    return "s";
	}
    }

}
