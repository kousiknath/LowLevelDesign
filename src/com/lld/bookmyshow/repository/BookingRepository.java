package com.lld.bookmyshow.repository;

import com.lld.bookmyshow.constant.SeatStatus;
import com.lld.bookmyshow.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {
    // Availability of seat is a function of `Show`, not a function of `Seat` itself.
    // If you want to keep an `availabilityStatus` attribute in a `Seat`, that would be more
    // of like a transient attribute. Keeping a map of Show to Seat Availability status is more
    // convenient
    private ConcurrentHashMap<Show, ConcurrentHashMap<Seat, SeatStatus>> seatsCurrentStatus;
    private ConcurrentHashMap<String, Booking> bookings;

    public BookingRepository() {
        this.seatsCurrentStatus = new ConcurrentHashMap<>();
        this.bookings = new ConcurrentHashMap<>();
    }

    public Map<Seat, SeatStatus> getSeatCurrentStatusFor(Show show) {
        return this.seatsCurrentStatus.getOrDefault(show, new ConcurrentHashMap<>());
    }

    public void putSeatsOnStatus(Show show, List<Seat> seats, SeatStatus status) {
        this.seatsCurrentStatus.putIfAbsent(show, new ConcurrentHashMap<>());

        Map<Seat, SeatStatus> seatStatusMap = this.seatsCurrentStatus.get(show);
        for (Seat seat: seats) {
            seatStatusMap.put(seat, status);
        }
    }

    public void addBooking(Booking booking) {
        if (booking.getReferenceNumber() != null) {
            this.bookings.put(booking.getReferenceNumber(), booking);
        }
    }

    public Booking getBookingByReferenceNo(String referenceNumber) {
        return this.bookings.get(referenceNumber);
    }
}
