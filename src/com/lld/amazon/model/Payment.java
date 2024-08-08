package com.lld.amazon.model;

import com.lld.amazon.constant.PaymentStatus;

import java.util.List;

public class Payment {
    private PaymentMethod paymentMethod;
    private Money totalAmount;
    private PaymentStatus paymentStatus;
    private Double platformFee;
    private List<KV> misc;

    public Payment(PaymentMethod paymentMethod, Money totalAmount, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPlatformFee(Double platformFee) {
        this.platformFee = platformFee;
    }

    public void setMisc(List<KV> misc) {
        this.misc = misc;
    }

    public void markPaymentInProgress() {
        this.paymentStatus = PaymentStatus.IN_PROGRESS;
    }

    public void markPaymentSuccess() {
        this.paymentStatus = PaymentStatus.SUCCESS;
    }
}
