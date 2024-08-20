package com.lld.stockbroker.model;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.constant.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private List<Entry> entries;
    private OrderStatus orderStatus;
    private AssetType assetType;

    public Order(AssetType assetType) {
        this.entries = new ArrayList<>();
        this.orderStatus = OrderStatus.OPEN;
        this.assetType = assetType;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public void markOrderExecuted() {
        this.orderStatus = OrderStatus.EXECUTED;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.entries.toArray());
    }
}
