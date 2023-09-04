package com.lld.bookmyshow;

import com.lld.bookmyshow.constant.AudioExperienceType;
import com.lld.bookmyshow.constant.PaymentStatus;
import com.lld.bookmyshow.constant.VideoExperienceType;
import com.lld.bookmyshow.exception.BookingValidationException;
import com.lld.bookmyshow.model.*;
import com.lld.bookmyshow.service.BookingService;
import com.lld.bookmyshow.service.SearchService;
import com.lld.bookmyshow.service.ShowService;
import com.lld.bookmyshow.service.impl.BookingServiceImpl;
import com.lld.bookmyshow.service.impl.SearchServiceImpl;
import com.lld.bookmyshow.service.impl.ShowServiceImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class MainApplication {

    private static void test1() throws BookingValidationException {
        List<String> movieNames = Arrays.asList(
                "The Equalizer 3",
                "Barbie",
                "Mission Impossible Dead Reckoning",
                "Oppenheimer",
                "Asteroid City",
                "Gran Turismo",
                "Blue Beetle",
                "Goldfish"
        );
        List<Movie> movies = new ArrayList<>();
        for (String name: movieNames) {
            movies.add(new Movie(name));
        }

        Location location = new Location("Bengaluru", "560095", "Dummy Address");

        List<String> theatreNames = Arrays.asList(
                "PVR Vega City",
                "PVR Nexus Kormangala",
                "INOX Mantri Square",
                "Cinepolis Nexus Shantiniketan",
                "INOX Galleria Mall"
        );

        List<Theatre> theatres = new ArrayList<>();
        for (String name: theatreNames) {
            theatres.add(new Theatre(name, location, 5));
        }

        List<Screen> screens = Arrays.asList(
                new Screen(VideoExperienceType.IMAX_2D, AudioExperienceType.DOLBY_DIGITAL),
                new Screen(VideoExperienceType.IMAX_3D, AudioExperienceType.DOLBY_ATOMS),
                new Screen(VideoExperienceType.TWO_D, AudioExperienceType.FX_SOUND),
                new Screen(VideoExperienceType.ICE, AudioExperienceType.VIPER)
        );

        List<ShowPrice> showPrices = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            showPrices.add(new ShowPrice(new Random().nextDouble(1000.0), "INR"));
        }

        for (Theatre theatre: theatres) {
            for (int h = 0; h < theatre.getTotalHalls(); h++) {
                Hall hall = new Hall(String.valueOf(h), theatre, screens.get(new Random().nextInt(screens.size())), 100);
                theatre.addHall(hall);
                addSeats(hall);
            }
        }

        List<Show> shows = new ArrayList<>();
        List<String> chosenMovieNames = new ArrayList<>();

        for (Theatre theatre: theatres) {
            for (Hall hall: theatre.getHalls()) {
                // adding shows for a single date: 04-Sep-2023
                Instant startTime = Instant.parse("2023-09-04T06:00:00.00Z");
                Instant endTime = Instant.parse("2023-09-04T23:59:00.00Z");
                Instant time = startTime;

                while (time.compareTo(endTime) < 0) {
                    Instant timePlusDelta = time.plus(Duration.ofMinutes(180));
                    System.out.println("Start Time: " + time.toEpochMilli());
                    Movie chosenMovie = movies.get(new Random().nextInt(movies.size()));
                    chosenMovieNames.add(chosenMovie.getName());

                    shows.add(new Show(hall, chosenMovie,
                            showPrices.get(new Random().nextInt(showPrices.size())),
                            time.toEpochMilli(),
                            timePlusDelta.toEpochMilli()));
                    timePlusDelta = timePlusDelta.plus(Duration.ofMinutes(30));
                    time = timePlusDelta;
                }
            }
        }

        ShowService showService = new ShowServiceImpl();

        for (Show show: shows) {
            showService.addShow(show);
        }

        SearchService searchService = new SearchServiceImpl(showService);
        Instant searchDate = Instant.parse("2023-09-04T00:00:00.00Z");
        List<Show> searchedShows = searchService.search(
                chosenMovieNames.get(new Random().nextInt(chosenMovieNames.size())), searchDate);
        System.out.println("Search result size = " + searchedShows.size());
        for(Show show: searchedShows) {
            System.out.println(show);
        }

        User user1 = new User("test1", "test1@gmail.com");
        User user2 = new User("test2", "test2@gmail.com");

        Show showToBook = searchedShows.get(new Random().nextInt(searchedShows.size()));
        BookingService bookingService = new BookingServiceImpl();

        Booking booking1 = bookingService.hold(user1, showToBook, 4);
        System.out.println("Booking status for user " + user1.getName() + " = " + booking1.getStatus().name());
        printSeats(booking1); // Row 1, Seat No - 1, 2, 3, 4 should be on hold

        Payment payment1 = new Payment();
        payment1.setAmount(booking1.getShowPrice().getPrice());
        payment1.setBooking(booking1);
        payment1.setUser(user1);
        payment1.setStatus(PaymentStatus.SUCCESS);
        payment1.setReferenceId(UUID.randomUUID().toString());

        try {
            booking1 = bookingService.book(user1, booking1, payment1);
            System.out.println("Booking status for user " + user1.getName() + " = " + booking1.getStatus().name());
            printSeats(booking1); // Row 1, Seat No - 1, 2, 3, 4 should be booked
        } catch (Exception ex) {
            System.out.println("Exception for booking " + booking1.getReferenceNumber() + ", error = " + ex.getLocalizedMessage());
        }

        try {
            booking1 = bookingService.cancel(user1, booking1);
            System.out.println("Booking status for user " + user1.getName() + " = " + booking1.getStatus().name());
            printSeats(booking1); // Row 1, Seat No - 1, 2, 3, 4 should be cancelled
        } catch (Exception ex) {
            System.out.println("Exception for booking " + booking1.getReferenceNumber() + ", error = " + ex.getLocalizedMessage());
        }

        Booking booking2 = bookingService.hold(user2, showToBook, 4);
        System.out.println("Booking status for user " + user2.getName() + " = " + booking2.getStatus().name());
        printSeats(booking2); // Row 1, Seat No - 1, 2, 3, 4 should be on hold since they previously got cancelled

        Booking booking3 = bookingService.hold(user2, showToBook, 5);
        System.out.println("Booking status for user " + user2.getName() + " = " + booking3.getStatus().name());
        printSeats(booking3); // Row 1, Seat No - 5, 6, 7, 8, 9 should be on hold
    }

    private static void addSeats(Hall hall) {
        for (int row = 1; row <= 10; row++) {
            for (int seatNo = 1; seatNo <= 10; seatNo++) {
                hall.addSeat(new Seat(String.valueOf(row), String.valueOf(seatNo)));
            }
        }
    }

    private static void printSeats(Booking booking) {
        if (booking.getSeats() != null && !booking.getSeats().isEmpty()) {
            for (Seat seat: booking.getSeats()) {
                System.out.println("Seat = " + seat);
            }
        }
    }

    public static void main(String[] args) throws BookingValidationException {
        test1();
    }
}
