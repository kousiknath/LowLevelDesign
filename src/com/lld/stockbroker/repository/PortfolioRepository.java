package com.lld.stockbroker.repository;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.constant.OrderType;
import com.lld.stockbroker.exception.NotImplementedException;
import com.lld.stockbroker.model.*;

import java.util.*;

public class PortfolioRepository {
    private Map<User, Map<AssetType, WatchList>> watchLists;
    private Map<User, Map<AssetType, Holding>> holdings;
    private Map<User, Map<AssetType, Position>> positions;

    public PortfolioRepository() {
        this.watchLists = new HashMap<>();
        this.holdings = new HashMap<>();
        this.positions = new HashMap<>();
    }

    public WatchList addToWatchList(AssetType assetType, Entry entry, User user) {
        this.watchLists.putIfAbsent(user, new HashMap<>());
        this.watchLists.get(user).putIfAbsent(assetType, new WatchList());
        this.watchLists.get(user).get(assetType).addEntry(entry);
        return this.watchLists.get(user).get(assetType);
    }

    public WatchList getWatchList(AssetType assetType, User user) {
        return this.watchLists.getOrDefault(user, new HashMap<>())
                .getOrDefault(assetType, new WatchList());
    }

    public Holding addToHoldings(AssetType assetType, Entry entry, User user) throws NotImplementedException {
        this.holdings.putIfAbsent(user, new HashMap<>());
        this.holdings.get(user).putIfAbsent(assetType, new AnyHolding());
        this.holdings.get(user).get(assetType).addEntry(entry);
        return this.holdings.get(user).get(assetType);
    }

    public Holding getHoldings(AssetType assetType, User user) {
        return this.holdings.getOrDefault(user, new HashMap<>())
                .getOrDefault(assetType, new AnyHolding());
    }

    public Position addToPosition(AssetType assetType, String symbol, OrderType orderType, Entry entry, User user) {
        this.positions.putIfAbsent(user, new HashMap<>());
        this.positions.get(user).putIfAbsent(assetType, new Position());
        this.positions.get(user).get(assetType).addEntry(symbol, orderType, entry);
        return this.positions.get(user).get(assetType);
    }

    public Position getPositions(AssetType assetType, User user) {
        return this.positions.getOrDefault(user, new HashMap<>())
                .getOrDefault(assetType, new Position());
    }
}
