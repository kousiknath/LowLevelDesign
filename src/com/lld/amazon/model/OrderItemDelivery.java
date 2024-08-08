package com.lld.amazon.model;

import com.lld.amazon.constant.OrderState;

public class OrderItemDelivery {
    private CartOrOrderItem item;
    // ETA for this specific order item which might change depending on
    // circumstances. This ETA might not be necessarily the same as the
    // corresponding cart item ETA shown
    private DeliveryETA deliveryETA;
    private OrderState state;

    public OrderItemDelivery(CartOrOrderItem item, DeliveryETA deliveryETA, OrderState state) {
        this.item = item;
        this.deliveryETA = deliveryETA;
        this.state = state;
    }

    public CartOrOrderItem getItem() {
        return item;
    }

    public DeliveryETA getDeliveryETA() {
        return deliveryETA;
    }
}
