package com.lld.stockbroker.service.impl;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.constant.OrderType;
import com.lld.stockbroker.exception.NotImplementedException;
import com.lld.stockbroker.exception.PortfolioException;
import com.lld.stockbroker.model.*;
import com.lld.stockbroker.repository.PortfolioRepository;
import com.lld.stockbroker.service.OrderService;
import com.lld.stockbroker.service.PortfolioService;


public class PortfolioServiceImpl implements PortfolioService {
    private OrderService orderService;
    private PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl() {
        this.orderService = new OrderServiceImpl(this);
        this.portfolioRepository = new PortfolioRepository();
    }

    @Override
    public WatchList addToWatchList(AssetType assetType, Company company, User user) throws PortfolioException {
        if (assetType == null || company == null || user == null) {
            throw new PortfolioException("Invalid asset type or company or user");
        }

        Entry entry = new EquityWatchListEntry(company);
        return this.portfolioRepository.addToWatchList(assetType, entry, user);
    }

    @Override
    public Holding addToEquityHolding(AssetType assetType, Company company, Integer quantity, Double investedAmount, User user)
            throws PortfolioException, NotImplementedException {
        if (assetType == null || company == null || quantity == null || investedAmount == null || user == null) {
            throw new PortfolioException("Invalid data!");
        }

        if (quantity <= 0 || investedAmount <= 0) {
            throw new PortfolioException("Invalid data!");
        }

        Entry entry = new EquityHoldingEntry(company, quantity, investedAmount);
        return this.portfolioRepository.addToHoldings(assetType, entry, user);
    }

    @Override
    public Order buy(OrderData orderData, User user) throws PortfolioException {
        this.portfolioRepository.addToPosition(
                orderData.getAssetType(),
                orderData.getCompany().getCompanyTicker().getStockSymbol(),
                OrderType.BUY,
                new EquityPositionEntry(
                        orderData.getCompany(),
                        orderData.getBuyingStrategy().getQuantity(),
                        orderData.getBuyingStrategy().getAmount()),
                user);

        return this.orderService.executeBuy(orderData, user);
    }

    @Override
    public Order sell(OrderData orderData, User user) throws PortfolioException {
        this.portfolioRepository.addToPosition(
                orderData.getAssetType(),
                orderData.getCompany().getCompanyTicker().getStockSymbol(),
                OrderType.SELL,
                new EquityPositionEntry(
                        orderData.getCompany(),
                        orderData.getBuyingStrategy().getQuantity(),
                        orderData.getBuyingStrategy().getAmount()),
                user);

        return this.orderService.executeSell(orderData, user);
    }

    @Override
    public Holding getHoldings(AssetType assetType, User user) {
        return this.portfolioRepository.getHoldings(assetType, user);
    }

    @Override
    public Position getPositions(AssetType assetType, User user) {
        return this.portfolioRepository.getPositions(assetType, user);
    }

    @Override
    public Order getOrders(AssetType assetType, User user) {
        return null;
    }

    @Override
    public WatchList getWatchList(AssetType assetType, User user) {
        return this.portfolioRepository.getWatchList(assetType, user);
    }
}
