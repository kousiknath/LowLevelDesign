package com.lld.amazon.service;

import com.lld.amazon.model.Cart;
import com.lld.amazon.model.Order;
import com.lld.amazon.model.Payment;

public interface OrderService {
    Order create(Cart cart);
    Payment pay(Order order);
}
