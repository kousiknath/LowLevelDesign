package com.lld.amazon.model;

import com.lld.amazon.constant.PaymentMethodType;
import com.lld.amazon.exception.PricingException;
import com.lld.amazon.service.CartOrOrderPricingService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cart {
    private Set<QuantifiedCartOrOrder> cartOrOrderQuantities;
    private PaymentMethod paymentMethod;
    private Address deliveryAddress;
    private DeliveryOption deliveryOption;
    private CartOrOrderPricing cartOrOrderPricing;
    private int totalItems;
    private Customer customer;
    public Cart(Customer customer) {
        this.customer = customer;
        this.cartOrOrderQuantities = new HashSet<>();
    }

    public void addToCart(QuantifiedCartOrOrder item, CartOrOrderPricingService cartOrOrderPricingService) throws PricingException {
        this.cartOrOrderQuantities.add(item);
        this.cartOrOrderPricing = cartOrOrderPricingService.calculateCartPrice(this);
        this.totalItems = countItems();
    }

    public void applyMiscellaneousCharges(CartOrOrderPricingService cartOrOrderPricingService, List<KV> data) throws PricingException {
        this.cartOrOrderPricing.applyExtraCartOrOrderPricing(cartOrOrderPricingService.applyMiscellaneousCartPrice(this, data));
    }

    private int countItems() {
        int items = 0;

        for (QuantifiedCartOrOrder item: cartOrOrderQuantities) {
            items += item.getQuantity();
        }

        return items;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<QuantifiedCartOrOrder> getCartOrOrderQuantities() {
        return Collections.unmodifiableSet(cartOrOrderQuantities);
    }

    public Money getCartCost() { // TODO: Model amount as money combined with currency
        return this.cartOrOrderPricing.getTotalAmount();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }
}
