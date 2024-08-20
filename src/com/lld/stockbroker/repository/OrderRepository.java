package com.lld.stockbroker.repository;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.model.Entry;
import com.lld.stockbroker.model.Order;
import com.lld.stockbroker.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private Map<User, Map<AssetType, Order>> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    public Order addToOrder(AssetType assetType, Entry entry, User user) {
        this.orders.putIfAbsent(user, new HashMap<>());
        this.orders.get(user).putIfAbsent(assetType, new Order(assetType));
        this.orders.get(user).get(assetType).addEntry(entry);
        return this.orders.get(user).get(assetType);
    }

    public Order getOrders(AssetType assetType, User user) {
        return this.orders.getOrDefault(user, new HashMap<>())
                .getOrDefault(assetType, new Order(assetType));
    }

    public Map<User, Map<AssetType, Order>> getAllOrders() {
        return Collections.unmodifiableMap(this.orders);
    }
}
