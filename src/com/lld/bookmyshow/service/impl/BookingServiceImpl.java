package com.lld.bookmyshow.service.impl;

import com.lld.bookmyshow.constant.BookingStatus;
import com.lld.bookmyshow.constant.PaymentStatus;
import com.lld.bookmyshow.constant.SeatStatus;
import com.lld.bookmyshow.constant.Threshold;
import com.lld.bookmyshow.exception.BookingNotFoundException;
import com.lld.bookmyshow.exception.BookingValidationException;
import com.lld.bookmyshow.exception.InvalidSeatStatusException;
import com.lld.bookmyshow.model.*;
import com.lld.bookmyshow.repository.BookingRepository;
import com.lld.bookmyshow.service.BookingService;

import java.util.*;

public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    public BookingServiceImpl() {
        this.bookingRepository = new BookingRepository();
    }

    @Override
    public Booking hold(User user, Show show, int noOfSeats) throws BookingValidationException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(show);

        if (noOfSeats > Threshold.MAX_SEATS_ALLOWED) {
            throw new BookingValidationException("More than " + Threshold.MAX_SEATS_ALLOWED + " seats can't be booked");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHall(show.getHall());
        booking.setReferenceNumber(UUID.randomUUID().toString());
        booking.setNoOfRequestedSeats(noOfSeats);
        booking.setShow(show);
        booking.setShowPrice(show.getShowPrice());

        Hall hall = show.getHall();
        List<Seat> seats = hall.getSeats();

        try {
            booking.getShow().getLock().lock();

            List<Seat> availableSeats = getAvailableSeats(show, seats, noOfSeats);
            if (availableSeats.size() < noOfSeats) {
                booking.setStatus(BookingStatus.UN_FULFILLED);
                booking.setSeats(Collections.emptyList());
                return booking;
            }

            this.bookingRepository.putSeatsOnStatus(show, availableSeats, SeatStatus.ON_HOLD);
            booking.setStatus(BookingStatus.ON_HOLD);
            booking.setSeats(availableSeats);
            this.bookingRepository.addBooking(booking);
        } finally {
            booking.getShow().getLock().unlock();
        }

        return booking;
    }

    private List<Seat> getAvailableSeats(Show show, List<Seat> seats, int noOfSeats) {
        List<Seat> available = new ArrayList<>();
        Map<Seat, SeatStatus> seatStatus = this.bookingRepository.getSeatCurrentStatusFor(show);

        for (Seat seat: seats) {
            if (seatStatus.getOrDefault(seat, SeatStatus.AVAILABLE) == SeatStatus.AVAILABLE) {
                available.add(seat);

                noOfSeats--;
                if (noOfSeats == 0) {
                    break;
                }
            }
        }

        return available;
    }

    @Override
    public Booking book(User user, Booking booking, Payment payment) throws BookingNotFoundException, InvalidSeatStatusException, BookingValidationException {
        validateUser(user, booking);
        validateBooking(booking, new HashSet<>(List.of(BookingStatus.ON_HOLD)));
        validatePayment(payment);

        try {
            booking.getShow().getLock().lock(); // We take lock on the particular show instance while booking seats
            // to prevent concurrent booking. Since, the booking always happens for the given show, this locking would work.

            Booking retrievedBooking = this.bookingRepository.getBookingByReferenceNo(booking.getReferenceNumber());
            if (retrievedBooking == null) {
                throw new BookingNotFoundException("Booking is not found in the system");
            }

            validateSeatStatus(booking.getShow(), booking.getSeats(), new HashSet<>(List.of(SeatStatus.ON_HOLD)));
            this.bookingRepository.putSeatsOnStatus(booking.getShow(), booking.getSeats(), SeatStatus.BOOKED);

            booking.setStatus(BookingStatus.BOOKED);
            booking.setNoOfFulfilledSeats(booking.getSeats().size());
            booking.setPayment(payment);
        } finally {
            booking.getShow().getLock().unlock();
        }

        return booking;
    }

    private void validateUser(User user, Booking booking) throws BookingValidationException {
        Objects.requireNonNull(user);
        if (!user.equals(booking.getUser())) {
            throw new BookingValidationException("Invalid user editing the booking");
        }
    }

    private void validateBooking(Booking booking, Set<BookingStatus> expectedStatus) throws BookingValidationException {
        Objects.requireNonNull(booking);

        if (!expectedStatus.contains(booking.getStatus())) {
            throw new BookingValidationException("Booking status is not on hold");
        }

        if (booking.getSeats().size() < 1) {
            throw new BookingValidationException("At least 1 seat should be there to book");
        }

        if (booking.getShowPrice() == null || booking.getShowPrice().getPrice() <= 0.0) {
            throw new BookingValidationException("Invalid seat price provided");
        }
    }

    private void validatePayment(Payment payment) throws BookingValidationException {
        Objects.requireNonNull(payment);

        if (payment.getReferenceId() == null) {
            throw new BookingValidationException("Payment reference can not be null");
        }

        if (payment.getStatus() != PaymentStatus.SUCCESS) {
            throw new BookingValidationException("Payment did not succeed");
        }

        if (payment.getAmount() <= 0.0) {
            throw new BookingValidationException("Payment is not made for the booking");
        }
    }

    private void validateSeatStatus(Show show, List<Seat> seats, Set<SeatStatus> expectedSeatStatus) throws InvalidSeatStatusException {
        Map<Seat, SeatStatus> seatStatusMap = this.bookingRepository.getSeatCurrentStatusFor(show);
        for (Seat seat: seats) {
            if (!seatStatusMap.containsKey(seat) || !expectedSeatStatus.contains(seatStatusMap.get(seat))) {
                throw new InvalidSeatStatusException("Seat status is invalid");
            }
        }
    }

    @Override
    public Booking cancel(User user, Booking booking) throws BookingValidationException, BookingNotFoundException, InvalidSeatStatusException {
        validateUser(user, booking);
        validateBooking(booking, new HashSet<>(List.of(BookingStatus.ON_HOLD, BookingStatus.BOOKED)));

        try {
            booking.getShow().getLock().lock();

            Booking retrievedBooking = this.bookingRepository.getBookingByReferenceNo(booking.getReferenceNumber());
            if (retrievedBooking == null) {
                throw new BookingNotFoundException("Booking is not found in the system");
            }

            validateSeatStatus(booking.getShow(), booking.getSeats(), new HashSet<>(List.of(SeatStatus.ON_HOLD, SeatStatus.BOOKED)));
            this.bookingRepository.putSeatsOnStatus(booking.getShow(), booking.getSeats(), SeatStatus.AVAILABLE);

            booking.setStatus(BookingStatus.CANCELED);
        } finally {
            booking.getShow().getLock().unlock();
        }

        return booking;
    }
}
