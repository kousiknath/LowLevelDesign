package com.lld.stockbroker.model;

public class MarketOrderStrategy implements OrderStrategy {
    private Double amount;
    private Integer quantity;

    public MarketOrderStrategy(Double amount, Integer quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    @Override
    public Double getAmount() {
        return this.amount;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }
}
