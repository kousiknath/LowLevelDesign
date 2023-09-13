package com.lld.parkinglot.src.model;

public class Fee {
    private double fee;
    private long duration;
    private String currency;

    public Fee(double fee, long duration, String currency) {
        this.fee = fee;
        this.duration = duration;
        this.currency = currency;
    }

    public double getFee() {
        return fee;
    }

    public long getDuration() {
        return duration;
    }

    public String getCurrency() {
        return currency;
    }
}
