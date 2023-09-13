package com.lld.parkinglot.src.model;

import java.util.UUID;

public class Payment {
    private String referenceNumber;
    private User user;
    private Fee fee;

    public Payment() {
        this.referenceNumber = UUID.randomUUID().toString();
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }
}
