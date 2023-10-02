package com.lld.inmemorycache.model.impl;

import com.lld.inmemorycache.model.AbstractCache;
import com.lld.inmemorycache.model.IEvictionPolicy;
import com.lld.inmemorycache.model.IStorage;

public class Cache extends AbstractCache {

    public Cache(IStorage storage, IEvictionPolicy evictionPolicy, int capacity)
    {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
    }
    @Override
    public boolean put(String key, String value) {
        if(key == null || value == null)
            return false;
        if(storage.count() >= capacity)
        {
            // we need to evict keys because the capacity is full.
            String keyToEvict = evictionPolicy.getKeyToEvict();
            boolean status = storage.remove(keyToEvict);
            if(status)
            {
                // eviction complete.
                evictionPolicy.keyEvicted(keyToEvict);
            }
            else {
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
        return storage.get(key);
    }

    @Override
    public boolean remove(String key) {
        return storage.remove(key);
    }
}
