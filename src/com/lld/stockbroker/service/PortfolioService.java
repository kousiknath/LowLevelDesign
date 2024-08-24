package com.lld.stockbroker.service;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.exception.NotImplementedException;
import com.lld.stockbroker.exception.PortfolioException;
import com.lld.stockbroker.model.*;


public interface PortfolioService {
    WatchList addToWatchList(AssetType assetType, Company company, User user) throws PortfolioException;
    Holding addToEquityHolding(AssetType assetType, Company company, Integer quantity, Double totalPrice, User user)
            throws PortfolioException, NotImplementedException;
    Order buy(OrderData orderData, User user) throws PortfolioException;
    Order sell(OrderData orderData, User user) throws PortfolioException;
    Holding getHoldings(AssetType assetType, User user);
    Position getPositions(AssetType assetType, User user);
    Order getOrders(AssetType assetType, User user);
    WatchList getWatchList(AssetType assetType, User user);
}
