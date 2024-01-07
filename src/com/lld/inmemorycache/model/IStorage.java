package com.lld.inmemorycache.model;

public interface IStorage {
    public boolean put(String key, String value);
    public String get(String key);
    public boolean remove(String key);
    public int size();
}
