package com.lld.stockbroker.repository;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.model.*;

import java.util.*;

public class PortfolioRepository {
    private Map<User, Map<AssetType, WatchList>> watchLists;
    private Map<User, Map<AssetType, Holding>> holdings;

    public PortfolioRepository() {
        this.watchLists = new HashMap<>();
        this.holdings = new HashMap<>();
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

    public Holding addToHoldings(AssetType assetType, Entry entry, User user) {
        this.holdings.putIfAbsent(user, new HashMap<>());
        this.holdings.get(user).putIfAbsent(assetType, new AnyHolding());
        this.holdings.get(user).get(assetType).addEntry(entry);
        return this.holdings.get(user).get(assetType);
    }

    public Holding getHoldings(AssetType assetType, User user) {
        return this.holdings.getOrDefault(user, new HashMap<>())
                .getOrDefault(assetType, new AnyHolding());
    }
}
