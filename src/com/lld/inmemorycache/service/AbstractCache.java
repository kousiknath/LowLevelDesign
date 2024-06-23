package com.lld.inmemorycache.service;

import com.lld.inmemorycache.service.EvictionPolicy;
import com.lld.inmemorycache.service.Storage;

public abstract class AbstractCache {
    public Storage storage;
    public EvictionPolicy evictionPolicy;
    public int capacity;
    public abstract boolean put(String key, String value);
    public abstract String get(String key);
    public abstract boolean remove(String key);

}
