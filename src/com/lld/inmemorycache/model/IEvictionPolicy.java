package com.lld.inmemorycache.model;

public interface IEvictionPolicy {
    // Update statistics for the key which was accessed.
    public void keyAccessed(String key);
    // Update statistics for the key which was evicted.
    public void keyEvicted(String key);
    public String getKeyToEvict();
}
