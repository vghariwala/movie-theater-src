package com.jpmc.theater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpmc.theater.gson.DurationSerializer;
import com.jpmc.theater.gson.LocalDateTimeSerializer;
import com.jpmc.theater.util.CommonUtil;
import com.jpmc.theater.util.ConsoleTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A {@link Theater} class providing {@link Showing} and functionality to make reservation
 */
public class Theater {

    /**
     * the choice type of 'EXIT'
     */
    private static final String EXIT = "0";

    /**
     * the choice type of 'PRINT_TEXT_SCHEDULE'
     */
    private static final String PRINT_TEXT_SCHEDULE = "1";

    /**
     * the choice type of 'PRINT_JSON_SCHEDULE'
     */
    private static final String PRINT_JSON_SCHEDULE = "2";

    /**
     * the choice type of 'RESERVE_MOVIE_WITH_SPECIAL_DISCOUNT'
     */
    private static final String RESERVE_MOVIE_WITH_SPECIAL_DISCOUNT = "3";

    /**
     * the choice type of 'RESERVE_MOVIE_WITH_SEQUENCE_DISCOUNT'
     */
    private static final String RESERVE_MOVIE_WITH_SEQUENCE_DISCOUNT = "4";

    /**
     * the choice type of 'RESERVE_MOVIE_WITH_EARLY_BIRD_DISCOUNT'
     */
    private static final String RESERVE_MOVIE_WITH_EARLY_BIRD_DISCOUNT = "5";

    /**
     * the choice type of 'RESERVE_MOVIE_WITH_NO_DISCOUNT'
     */
    private static final String RESERVE_MOVIE_WITH_NO_DISCOUNT = "6";

    /**
     * A singleton instance of {@link LocalDateProvider}
     */
    private final LocalDateProvider provider;

    /**
     * A {@link Showing} schedule with movie title, running time, ticket price etc
     */
    private final List<Showing> schedules;

    /**
     * A {@link Map} of {@link Showing} schedule with corresponding sequence as key
     */
    private final Map<Integer, Showing> showingMap;

    /**
     * A constructor to initialize {@link Theater} instance
     *
     * @param provider A singleton instance of {@link LocalDateProvider}
     */
    public Theater(LocalDateProvider provider) {
	this.provider = provider;

	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
	Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
	schedules = List.of(new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
			new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
			new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
			new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
			new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
			new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
			new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
			new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
			new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));
	showingMap = schedules.stream().collect(Collectors.toMap(Showing::getSequenceOfTheDay, Function.identity()));
    }

    /**
     * Reserves a number of tickets for a particular showing for a given customer
     *
     * @param customer       a {@link Customer} making the reservation
     * @param sequence       a {@link Showing} for which this reservation is being made
     * @param howManyTickets a number of tickets for this reservation
     * @return a {@link Reservation} instance
     */
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
	if (customer == null) {
	    throw new IllegalStateException("A customer is required to make the reservation");
	}

	if (howManyTickets < 1) {
	    throw new IllegalStateException("At least one ticket is required ot make the reservation");
	}

	Showing showing = showingMap.get(sequence);

	if (showing == null) {
	    throw new IllegalStateException("Not able to find any showing for given sequence " + sequence);
	}

	return new Reservation(customer, showing, howManyTickets);
    }

    /**
     * Prints the {@link Showing} schedule in Text Format
     */
    public void printScheduleTextFormat() {
	ConsoleTable table = new ConsoleTable();
	table.setShowVerticalLines(true);
	table.setHeaders("Sequence", "Show Start Time", "Movie", "Run Time", "Fee");
	schedules.forEach(schedule -> table.addRow(String.valueOf(schedule.getSequenceOfTheDay()),
			schedule.getShowStartTime().toString(), schedule.getMovie().getTitle(),
			CommonUtil.humanReadableFormat(schedule.getMovie().getRunningTime()),
			"$".concat(String.valueOf(schedule.getMovieFee()))));
	table.print();
    }

    /**
     * Prints the {@link Showing} schedule in JSON Format
     */
    public void printScheduleJsonFormat() {
	Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
			.registerTypeAdapter(Duration.class, new DurationSerializer()).create();
	System.out.println(gson.toJson(schedules));
    }

    /**
     * Prints the {@link Showing} schedule in Text Format
     */
    @SuppressWarnings("unused") public void printSchedule() {
	System.out.println(provider.currentDate());
	System.out.println("===================================================");
	schedules.forEach(s -> System.out.println(
			s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " "
					+ CommonUtil.humanReadableFormat(s.getMovie().getRunningTime()) + " $"
					+ s.getMovieFee()));
	System.out.println("===================================================");
    }

    /**
     * An entry point of this application
     */
    public static void main(String[] args) {

	try {
	    String choice;
	    do {
		displayOptions();
		choice = readInput();
		processChoice(choice);
	    } while (!EXIT.equals(choice));

	} catch (Exception e) {
	    System.err.println(e.getMessage());
	    System.exit(1);
	}
	System.out.println("\nFinished Processing !!!!!");
	System.exit(0);
    }

    /**
     * Displays the choice option to the user
     */
    private static void displayOptions() {
	System.out.println("-----------------------------------------");
	System.out.println("Please choose from the following options:");
	System.out.println("Exit                                                 ---> 0");
	System.out.println("Print Schedule (Text Format)                         ---> 1");
	System.out.println("Print Schedule (JSON Format)                         ---> 2");
	System.out.println("Reserve Movie with Special Discount (20% off)        ---> 3");
	System.out.println("Reserve Movie with Sequence 1 Discount ($3 off)      ---> 4");
	System.out.println("Reserve Movie with Early Bird Discount (25% off)     ---> 5");
	System.out.println("Reserve Movie with *NO* Discount                     ---> 6");
	System.out.println("Please enter either '0', '1', '2', '3', '4', '5' or '6' as your choice.");
	System.out.print("Enter your choice: ");
    }

    /**
     * Reads the console input by the user
     *
     * @return the console input by the user
     */
    private static String readInput() {
	String input = null;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	try {
	    input = br.readLine();
	} catch (IOException ioe) {
	    System.err.println("IO error while trying to read your choice!");
	    System.exit(1);
	}
	return input;
    }

    /**
     * Process the user entered choice
     *
     * @param choice the user entered choice value
     */
    private static void processChoice(String choice) {
	Theater theater = new Theater(LocalDateProvider.singleton());
	ConsoleTable table = new ConsoleTable();
	table.setShowVerticalLines(true);
	table.setHeaders("Movie Title", "Ticket Price", "Number of tickets", "Total fee (after discount)");

	if (PRINT_TEXT_SCHEDULE.equals(choice)) {
	    theater.printScheduleTextFormat();
	} else if (PRINT_JSON_SCHEDULE.equals(choice)) {
	    theater.printScheduleJsonFormat();
	} else if (RESERVE_MOVIE_WITH_SPECIAL_DISCOUNT.equals(choice)) {
	    Reservation reservation = theater.reserve(new Customer("John", "1"), 5, 3);
	    Showing showing = theater.showingMap.get(5);
	    table.addRow(showing.getMovie().getTitle(), "$".concat(String.valueOf(showing.getMovie().getTicketPrice())),
			    "3", "$".concat(String.valueOf(reservation.totalFee())));
	    table.print();
	    System.out.println();
	} else if (RESERVE_MOVIE_WITH_SEQUENCE_DISCOUNT.equals(choice)) {
	    Reservation reservation = theater.reserve(new Customer("John", "1"), 1, 4);
	    Showing showing = theater.showingMap.get(1);
	    table.addRow(showing.getMovie().getTitle(), "$".concat(String.valueOf(showing.getMovie().getTicketPrice())),
			    "4", "$".concat(String.valueOf(reservation.totalFee())));
	    table.print();
	    System.out.println();
	} else if (RESERVE_MOVIE_WITH_EARLY_BIRD_DISCOUNT.equals(choice)) {
	    Reservation reservation = theater.reserve(new Customer("John", "1"), 4, 5);
	    Showing showing = theater.showingMap.get(4);
	    table.addRow(showing.getMovie().getTitle(), "$".concat(String.valueOf(showing.getMovie().getTicketPrice())),
			    "5", "$".concat(String.valueOf(reservation.totalFee())));
	    table.print();
	    System.out.println();
	} else if (RESERVE_MOVIE_WITH_NO_DISCOUNT.equals(choice)) {
	    Reservation reservation = theater.reserve(new Customer("John", "1"), 6, 6);
	    Showing showing = theater.showingMap.get(6);
	    table.addRow(showing.getMovie().getTitle(), "$".concat(String.valueOf(showing.getMovie().getTicketPrice())),
			    "6", "$".concat(String.valueOf(reservation.totalFee())));
	    table.print();
	    System.out.println();
	}
    }

}
