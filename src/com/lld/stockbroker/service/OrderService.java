package com.lld.stockbroker.service;

import com.lld.stockbroker.exception.PortfolioException;
import com.lld.stockbroker.model.Order;
import com.lld.stockbroker.model.OrderData;
import com.lld.stockbroker.model.User;

public interface OrderService {
    Order executeBuy(OrderData orderData, User user) throws PortfolioException;
    Order executeSell(OrderData orderData, User user) throws PortfolioException;
}
