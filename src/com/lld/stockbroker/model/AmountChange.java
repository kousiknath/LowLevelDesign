package com.lld.stockbroker.model;

import com.lld.stockbroker.constant.TimeUnit;

public class AmountChange {
    private Double value;
    private TimeUnit timeUnit;

    public AmountChange(Double value, TimeUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    public Double getValue() {
        return value;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
