package com.lld.inmemorycache;

import com.lld.inmemorycache.service.EvictionPolicy;
import com.lld.inmemorycache.service.impl.Cache;
import com.lld.inmemorycache.service.impl.InMemoryStorage;
import com.lld.inmemorycache.service.impl.policy.LRUEvictionPolicy;

import java.util.Objects;

public class MainApplication {

    public static void main(String[] args)
    {
        InMemoryStorage inMemoryStorage = new InMemoryStorage();
        EvictionPolicy lruEvictionPolicy = new LRUEvictionPolicy();
        Cache cache = new Cache(inMemoryStorage, lruEvictionPolicy, 2);

        // Test 1
        cache.put("c", "1");
        cache.put("a", "1");
        cache.put("a", "2");
        assert Objects.equals(cache.get("a"), "2");

        cache.put("b", "3");
        cache.put("b", "4");
        assert Objects.equals(cache.get("b"), "4");

    }
}
