package com.lld.pizzapricing.model;

public class MoneyImpl implements Money {
    private String currency;
    private Double amount;
    public MoneyImpl(String currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public double amount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "MoneyImpl{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
