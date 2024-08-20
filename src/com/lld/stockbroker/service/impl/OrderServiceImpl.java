package com.lld.stockbroker.service.impl;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.exception.PortfolioException;
import com.lld.stockbroker.model.*;
import com.lld.stockbroker.repository.OrderRepository;
import com.lld.stockbroker.service.OrderService;
import com.lld.stockbroker.service.PortfolioService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private PortfolioService portfolioService;
    private OrderRepository orderRepository;

    public OrderServiceImpl(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
        this.orderRepository = new OrderRepository();

        new Thread(new OrderExecutor(this.portfolioService, this.orderRepository)).start();
    }

    @Override
    public Order executeBuy(OrderData orderData, User user) throws PortfolioException {
        if (orderData == null || user == null) {
            throw new PortfolioException("Invalid data or user");
        }

        return this.orderRepository.addToOrder(orderData.getAssetType(),
                new EquityHoldingEntry(
                        orderData.getCompany(),
                        orderData.getBuyingStrategy().getQuantity(),
                        orderData.getBuyingStrategy().getAmount()),
                user);
    }

    @Override
    public Order executeSell(OrderData orderData, User user) {
        return null;
    }
}


// TODO: Handle concurrency
class OrderExecutor implements Runnable {
    private PortfolioService portfolioService;
    private OrderRepository orderRepository;

    public OrderExecutor(PortfolioService portfolioService, OrderRepository orderRepository) {
        this.portfolioService = portfolioService;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Map<User, Map<AssetType, Order>> orders = this.orderRepository.getAllOrders();
            // For now, we will execute all the orders which are in "OPEN" state
            // There might be different order executors for different asset type
            // Here, we just execute any order every five seconds.

            try {
                for (User user : orders.keySet()) {
                    Map<AssetType, Order> mixedOrders = orders.get(user);
                    for (Order order : mixedOrders.values()) {
                        // Call Exchange API.
                        order.markOrderExecuted();

                        for (Entry entry : order.getEntries()) {
                            this.portfolioService.addToEquityHolding(order.getAssetType(), entry.getCompany(),
                                    entry.getQuantity(), entry.getTotalInvestedAmount(), user);
                        }
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
