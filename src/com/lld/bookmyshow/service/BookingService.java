package com.lld.bookmyshow.service;

import com.lld.bookmyshow.exception.BookingNotFoundException;
import com.lld.bookmyshow.exception.BookingValidationException;
import com.lld.bookmyshow.exception.InvalidSeatStatusException;
import com.lld.bookmyshow.model.Booking;
import com.lld.bookmyshow.model.Payment;
import com.lld.bookmyshow.model.Show;
import com.lld.bookmyshow.model.User;

public interface BookingService {
    Booking hold(User user, Show show, int noOfSeats) throws BookingValidationException;
    Booking book(User user, Booking booking, Payment payment) throws BookingNotFoundException, InvalidSeatStatusException, BookingValidationException;
    Booking cancel(User user, Booking booking) throws BookingValidationException, BookingNotFoundException, InvalidSeatStatusException;
}
