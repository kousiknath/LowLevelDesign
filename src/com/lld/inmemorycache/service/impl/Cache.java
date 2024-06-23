package com.lld.inmemorycache.service.impl;

import com.lld.inmemorycache.service.AbstractCache;
import com.lld.inmemorycache.service.EvictionPolicy;
import com.lld.inmemorycache.service.Storage;

public class Cache extends AbstractCache {

    public Cache(Storage storage, EvictionPolicy evictionPolicy, int capacity) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
    }

    @Override
    public boolean put(String key, String value) {
        if (key == null || value == null)
            return false;
        if (storage.size() >= capacity) {
            // we need to evict keys because the capacity is full.
            String keyToEvict = evictionPolicy.getKeyToEvict();
            boolean status = storage.remove(keyToEvict);
            if (status) {
                // eviction complete.
                evictionPolicy.keyEvicted(keyToEvict);
            } else {
                // eviction failed.
                // Multiple options here:
                // 1. throw exception: not a good option perf wise to throw exceptions.
                // 2. ignore the add and expect user to retry
                // 3. execute random eviction policy.
                // Implementing Option 2.
                return false;
            }
        }

        // space present
        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
        return true;
    }

    // Returns null if Key is not found.
    @Override
    public String get(String key) {
        String value = storage.get(key);
        // no point updating statistics for a key that is not present.
        // in future maybe we can get some information out of it but for now, skipping it.
        if (value != null) {
            evictionPolicy.keyAccessed(key);
        }
        return value;
    }

    @Override
    public boolean remove(String key) {

        boolean status = storage.remove(key);
        if (status) {
            evictionPolicy.keyEvicted(key);
        }
        return status;
    }
}
