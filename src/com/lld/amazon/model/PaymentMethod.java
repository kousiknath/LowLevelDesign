package com.lld.amazon.model;

import com.lld.amazon.constant.PaymentMethodType;

import java.util.List;

public class PaymentMethod {
    private PaymentMethodType methodType;
    // Credit card, debit card, net banking or UPI details
    // to be put in key value fashion
    private List<KV> details;

    public PaymentMethod(PaymentMethodType methodType, List<KV> details) {
        this.methodType = methodType;
        this.details = details;
    }

    public PaymentMethodType getMethodType() {
        return methodType;
    }

    public List<KV> getDetails() {
        return details;
    }
}
