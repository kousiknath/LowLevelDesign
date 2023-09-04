package com.lld.bookmyshow.exception;

public class InvalidSeatStatusException extends Exception{

    public InvalidSeatStatusException() {
        super();
    }

    public InvalidSeatStatusException(String msg) {
        super(msg);
    }
}
