package com.lld.amazon.service;

import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.*;

public interface CartService {
    // Each user will have only one cart
    Cart addToCart(Customer customer, Product product, ProductVariety variety, int quantity) throws PricingException;
    // Apply a delivery option in checkout also
    Order checkout(Cart cart, PaymentMethod paymentMethod, Address deliveryAddress, DeliveryOption deliveryOption) throws PricingException;
}
