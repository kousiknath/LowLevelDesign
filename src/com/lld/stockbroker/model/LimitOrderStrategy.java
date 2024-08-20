package com.lld.stockbroker.model;

public class LimitOrderStrategy implements OrderStrategy {
    private Double buyAtPrice;
    private Integer quantity;

    public LimitOrderStrategy(Double price, Integer quantity) {
        this.buyAtPrice = price;
        this.quantity = quantity;
    }

    @Override
    public Double getAmount() {
        return this.buyAtPrice * this.quantity;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }
}
