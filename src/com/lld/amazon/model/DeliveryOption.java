package com.lld.amazon.model;

import com.lld.amazon.constant.DeliveryType;

public class DeliveryOption {
    private DeliveryType deliveryType;
    private DeliveryETA eta;
    private String extraMessage;

    public DeliveryOption(DeliveryType deliveryType, DeliveryETA eta) {
        this.deliveryType = deliveryType;
        this.eta = eta;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public DeliveryETA getEta() {
        return eta;
    }

    public String getExtraMessage() {
        return extraMessage;
    }

    public void setExtraMessage(String extraMessage) {
        this.extraMessage = extraMessage;
    }
}
