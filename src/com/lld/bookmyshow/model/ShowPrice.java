package com.lld.bookmyshow.model;

public class ShowPrice {
    private double price;
    private String currency;

    public ShowPrice(double price, String currency) {
        this.price = price;
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return this.currency;
    }
}

