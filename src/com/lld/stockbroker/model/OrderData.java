package com.lld.stockbroker.model;

import com.lld.stockbroker.constant.AssetType;

public class OrderData {
    private Company company;
    private OrderStrategy orderStrategy;
    private AssetType assetType;

    public OrderData(Company company, OrderStrategy strategy, AssetType assetType) {
        this.company = company;
        this.orderStrategy = strategy;
        this.assetType = assetType;
    }

    public Company getCompany() {
        return company;
    }

    public OrderStrategy getBuyingStrategy() {
        return orderStrategy;
    }

    public AssetType getAssetType() {
        return assetType;
    }
}
