package com.lld.inmemorycache.service;

public interface EvictionPolicy {
    // Update statistics for the key which was accessed.
    public void keyAccessed(String key);
    // Update statistics for the key which was evicted.
    public void keyEvicted(String key);
    public String getKeyToEvict();
}
