package com.lld.bookmyshow.exception;

public class BookingValidationException extends Exception {
    public BookingValidationException() {
        super();
    }

    public BookingValidationException(String msg) {
        super(msg);
    }
}