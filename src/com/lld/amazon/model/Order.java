package com.lld.amazon.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private List<QuantifiedCartOrOrder> orders;
    // Each item in the order can have its own delivery ETA
    private List<OrderItemDelivery> orderItemDeliveries;
    private Payment payment;

    public Order(List<QuantifiedCartOrOrder> orders, List<OrderItemDelivery> orderItemDeliveries, Payment payment) {
        this.id = UUID.randomUUID().toString();
        this.orders = orders;
        this.orderItemDeliveries = orderItemDeliveries;
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public Money getOrderTotal() {
        return this.payment.getTotalAmount();
    }

    public Payment getPayment() {
        return payment;
    }

    public List<OrderItemDelivery> getOrderItemDeliveries() {
        return Collections.unmodifiableList(this.orderItemDeliveries);
    }
}
