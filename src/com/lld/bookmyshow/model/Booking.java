package com.lld.bookmyshow.model;

import com.lld.bookmyshow.constant.BookingStatus;

import java.util.List;

public class Booking {
    private String referenceNumber;
    private User user;
    private Hall hall;
    private int noOfRequestedSeats;
    private int noOfFulfilledSeats;
    private List<Seat> seats;
    private Show show;
    private ShowPrice showPrice;
    private BookingStatus status;
    private Payment payment;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getNoOfRequestedSeats() {
        return noOfRequestedSeats;
    }

    public void setNoOfRequestedSeats(int noOfRequestedSeats) {
        this.noOfRequestedSeats = noOfRequestedSeats;
    }

    public int getNoOfFulfilledSeats() {
        return noOfFulfilledSeats;
    }

    public void setNoOfFulfilledSeats(int noOfFulfilledSeats) {
        this.noOfFulfilledSeats = noOfFulfilledSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public ShowPrice getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(ShowPrice showPrice) {
        this.showPrice = showPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
