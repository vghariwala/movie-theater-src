# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

------------------------------------
### Improved Code Base

* Start the program by launching `Theater`
* The console will display following:
```
Please choose from the following options:
Exit                                                 ---> 0
Print Schedule (Text Format)                         ---> 1
Print Schedule (JSON Format)                         ---> 2
Reserve Movie with Special Discount (20% off)        ---> 3
Reserve Movie with Sequence 1 Discount ($3 off)      ---> 4
Reserve Movie with Early Bird Discount (25% off)     ---> 5
Reserve Movie with *NO* Discount                     ---> 6
Please enter either '0', '1', '2', '3', '4', '5' or '6' as your choice.
Enter your choice:
```
* Entering an appropriate choice will display the choice specific results.
* The code coverage is available via Jacoco plugin.

#### Logic
* Various `Discount` are concrete classes and available to `Movie` class and maximum discount is applied to the ticket price.
* There is a `ConsoleTable` class which is used to print the `Showing` schedule and `Reservation` in a nice tabular format.
```
+-------------+--------------+-------------------+----------------------------+
| Movie Title | Ticket Price | Number of tickets | Total fee (after discount) |
+-------------+--------------+-------------------+----------------------------+
| The Batman  | $9.0         | 6                 | $54.0                      |
+-------------+--------------+-------------------+----------------------------+
```
* `ConsoleTable` class is also used to print the `Showing` schedule in a Tabular Text Format for greater readability.
```
+----------+------------------+-------------------------+-------------------+-------+
| Sequence | Show Start Time  | Movie                   | Run Time          | Fee   |
+----------+------------------+-------------------------+-------------------+-------+
| 1        | 2022-05-16T09:00 | Turning Red             | 1 hour 25 minutes | $11.0 |
| 2        | 2022-05-16T11:00 | Spider-Man: No Way Home | 1 hour 30 minutes | $12.5 |
| 3        | 2022-05-16T12:50 | The Batman              | 1 hour 35 minutes | $9.0  |
| 4        | 2022-05-16T14:30 | Turning Red             | 1 hour 25 minutes | $11.0 |
| 5        | 2022-05-16T16:10 | Spider-Man: No Way Home | 1 hour 30 minutes | $12.5 |
| 6        | 2022-05-16T17:50 | The Batman              | 1 hour 35 minutes | $9.0  |
| 7        | 2022-05-16T19:30 | Turning Red             | 1 hour 25 minutes | $11.0 |
| 8        | 2022-05-16T21:10 | Spider-Man: No Way Home | 1 hour 30 minutes | $12.5 |
| 9        | 2022-05-16T23:00 | The Batman              | 1 hour 35 minutes | $9.0  |
+----------+------------------+-------------------------+-------------------+-------+
```
* The JSON format of `Showing` schedule is also printing the required details.
```
[
  {
    "movie": {
      "title": "Turning Red",
      "runningTime": "1 hour 25 minutes",
      "ticketPrice": 11.0
    },
    "sequenceOfTheDay": 1,
    "showStartTime": "2022-05-16T09:00"
  },
  {
    "movie": {
      "title": "Spider-Man: No Way Home",
      "runningTime": "1 hour 30 minutes",
      "ticketPrice": 12.5
    },
    "sequenceOfTheDay": 2,
    "showStartTime": "2022-05-16T11:00"
  },
  {
    "movie": {
      "title": "The Batman",
      "runningTime": "1 hour 35 minutes",
      "ticketPrice": 9.0
    },
    "sequenceOfTheDay": 3,
    "showStartTime": "2022-05-16T12:50"
  },
  {
    "movie": {
      "title": "Turning Red",
      "runningTime": "1 hour 25 minutes",
      "ticketPrice": 11.0
    },
    "sequenceOfTheDay": 4,
    "showStartTime": "2022-05-16T14:30"
  },
  {
    "movie": {
      "title": "Spider-Man: No Way Home",
      "runningTime": "1 hour 30 minutes",
      "ticketPrice": 12.5
    },
    "sequenceOfTheDay": 5,
    "showStartTime": "2022-05-16T16:10"
  },
  {
    "movie": {
      "title": "The Batman",
      "runningTime": "1 hour 35 minutes",
      "ticketPrice": 9.0
    },
    "sequenceOfTheDay": 6,
    "showStartTime": "2022-05-16T17:50"
  },
  {
    "movie": {
      "title": "Turning Red",
      "runningTime": "1 hour 25 minutes",
      "ticketPrice": 11.0
    },
    "sequenceOfTheDay": 7,
    "showStartTime": "2022-05-16T19:30"
  },
  {
    "movie": {
      "title": "Spider-Man: No Way Home",
      "runningTime": "1 hour 30 minutes",
      "ticketPrice": 12.5
    },
    "sequenceOfTheDay": 8,
    "showStartTime": "2022-05-16T21:10"
  },
  {
    "movie": {
      "title": "The Batman",
      "runningTime": "1 hour 35 minutes",
      "ticketPrice": 9.0
    },
    "sequenceOfTheDay": 9,
    "showStartTime": "2022-05-16T23:00"
  }
]
```
