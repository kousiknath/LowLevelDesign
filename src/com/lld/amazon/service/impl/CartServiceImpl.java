package com.lld.amazon.service.impl;

import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.*;
import com.lld.amazon.service.CartOrOrderPricingService;
import com.lld.amazon.service.CartService;
import com.lld.amazon.service.OrderService;
import com.lld.amazon.service.PricingService;

import java.util.*;

public class CartServiceImpl implements CartService {
    private Map<Customer, Cart> carts;
    private CartOrOrderPricingService cartOrOrderPricingService;
    private OrderService orderService;

    public CartServiceImpl(PricingService pricingService, OrderService orderService, Customer customer, List<KV> customerPrefs) {
        this.carts = new HashMap<>();
        this.cartOrOrderPricingService = new CartOrOrderPricingServiceImpl(pricingService, customer, customerPrefs);
        this.orderService = orderService;
    }

    @Override
    public Cart addToCart(Customer customer, Product product, ProductVariety variety, int quantity) throws PricingException {
        Cart userCart = carts.getOrDefault(customer, new Cart(customer));
        CartOrOrderItem cartOrOrderItem = new CartOrOrderItem(product, variety);
        QuantifiedCartOrOrder quantifiedItem = new QuantifiedCartOrOrder(cartOrOrderItem, quantity);
        userCart.addToCart(quantifiedItem, cartOrOrderPricingService);

        return userCart;
    }

    @Override
    public Order checkout(Cart cart, PaymentMethod paymentMethod, Address deliveryAddress,
                          DeliveryOption deliveryOption) throws PricingException {
        cart.setDeliveryAddress(deliveryAddress);
        cart.setPaymentMethod(paymentMethod);
        cart.setDeliveryOption(deliveryOption);

        cart.applyMiscellaneousCharges(this.cartOrOrderPricingService, Collections.emptyList());

        return this.orderService.create(cart);
    }
}
