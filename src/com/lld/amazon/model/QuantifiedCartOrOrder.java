package com.lld.amazon.model;

// A single chosen product with quantity information
public class QuantifiedCartOrOrder {
    private CartOrOrderItem orderItem;
    private int quantity;

    public QuantifiedCartOrOrder(CartOrOrderItem item, int quantity) {
        this.orderItem = item;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartOrOrderItem getOrderItem() {
        return orderItem;
    }
}
