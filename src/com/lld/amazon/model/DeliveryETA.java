package com.lld.amazon.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeliveryETA {
    private LocalDateTime eta;

    public DeliveryETA(LocalDateTime eta) {
        this.eta = eta;
    }

    public String getETAHumanReadable() {
        return  eta. format(DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
