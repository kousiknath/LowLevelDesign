package com.lld.stockbroker.constant;

public enum OrderType {
    BUY("BUY"),
    SELL("SELL");

    private String type;
    OrderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
