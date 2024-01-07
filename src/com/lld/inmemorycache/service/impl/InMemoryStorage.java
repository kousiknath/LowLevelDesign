package com.lld.inmemorycache.service.impl;

import com.lld.inmemorycache.service.Storage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryStorage implements Storage {
    private ConcurrentHashMap<String, String> storage;
    private static ReentrantLock lock;

    public InMemoryStorage() {
        storage = new ConcurrentHashMap<>();
        // fairness: first come, first served.
        lock = new ReentrantLock(true);
    }

    @Override
    public boolean put(String key, String value) {
        lock.lock();
        try {
            // access _storage. do not allow an exception here.
            storage.put(key, value);
        } catch (Exception ex) {
            return false;
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public String get(String key) {
        if (storage.containsKey(key)) {
            return storage.get(key);
        }
        return null;
    }

    @Override
    public boolean remove(String key) {
        lock.lock();
        try {
            // access _storage. do not allow an exception here.
            storage.remove(key);
        } catch (Exception ex) {
            return false;
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
