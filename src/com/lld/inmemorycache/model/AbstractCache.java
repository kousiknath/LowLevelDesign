package com.lld.inmemorycache.model;

public abstract class AbstractCache {
    public IStorage storage;
    public IEvictionPolicy evictionPolicy;
    public int capacity;
    public abstract boolean put(String key, String value);
    public abstract String get(String key);
    public abstract boolean remove(String key);

}
