package com.lld.inmemorycache.model.impl;

import com.lld.inmemorycache.model.IEvictionPolicy;

public class LRUEvictionPolicy implements IEvictionPolicy {
    @Override
    public void keyAccessed(String key) {

    }

    @Override
    public void keyEvicted(String key) {

    }

    @Override
    public String getKeyToEvict() {
        return null;
    }
}
