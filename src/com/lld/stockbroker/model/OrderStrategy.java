package com.lld.stockbroker.model;

public interface OrderStrategy {
    Double getAmount();
    Integer getQuantity();
}
