package com.lld.stockbroker.service;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.exception.PortfolioException;
import com.lld.stockbroker.model.*;


public interface PortfolioService {
    WatchList addToWatchList(AssetType assetType, Company company, User user) throws PortfolioException;
    Holding addToEquityHolding(AssetType assetType, Company company, Integer quantity, Double totalPrice, User user)
            throws PortfolioException;
    Order buy(OrderData orderData, User user) throws PortfolioException;
    Holding getHoldings(AssetType assetType, User user);
    void getPositions(AssetType assetType, User user);
    void getOrders(AssetType assetType, User user);
    WatchList getWatchList(AssetType assetType, User user);
}
