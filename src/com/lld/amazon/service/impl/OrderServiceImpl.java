package com.lld.amazon.service.impl;

import com.lld.amazon.constant.OrderState;
import com.lld.amazon.constant.PaymentStatus;
import com.lld.amazon.model.*;
import com.lld.amazon.service.ETAService;
import com.lld.amazon.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private ETAService etaService;

    public OrderServiceImpl(ETAService etaService) {
        this.etaService = etaService;
    }

    @Override
    public Order create(Cart cart) {
        List<QuantifiedCartOrOrder> quantifiedOrderItems = new ArrayList<>(cart.getCartOrOrderQuantities());
        List<OrderItemDelivery> etas = new ArrayList<>();

        for (QuantifiedCartOrOrder item: quantifiedOrderItems) {
            etas.add(new OrderItemDelivery(
                    item.getOrderItem(),
                    etaService.calculateETA(item.getOrderItem().getChosenVariety().getSeller().getAddress(),
                            cart.getDeliveryAddress()),
                    OrderState.ORDER_PLACED
            ));
        }

        Payment payment = new Payment(cart.getPaymentMethod(), cart.getCartCost(), PaymentStatus.TO_BE_INITIATED);
        return new Order(quantifiedOrderItems, etas, payment);
    }

    @Override
    public Payment pay(Order order) {
        order.getPayment().markPaymentInProgress();
        // Order service should talk to payment service here
        // Depending on the result, mark success or failure
        order.getPayment().markPaymentSuccess();
        return order.getPayment();
    }
}
