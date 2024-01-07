package com.lld.inmemorycache.service;

public interface Storage {
    public boolean put(String key, String value);
    public String get(String key);
    public boolean remove(String key);
    public int size();
}
