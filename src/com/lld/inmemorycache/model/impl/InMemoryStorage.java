package com.lld.inmemorycache.model.impl;

import com.lld.inmemorycache.model.IStorage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryStorage implements IStorage {
    private ConcurrentHashMap<String, String> _storage;
    private static ReentrantLock lock;

    public InMemoryStorage() {
        _storage = new ConcurrentHashMap<>();
        // fairness: first come, first served.
        lock = new ReentrantLock(true);
    }

    @Override
    public boolean put(String key, String value) {
        lock.lock();
        try {
            // access _storage. do not allow an exception here.
            _storage.put(key, value);
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public String get(String key) {
        if(_storage.containsKey(key))
        {
            return _storage.get(key);
        }
        return null;
    }

    @Override
    public boolean remove(String key) {
        lock.lock();
        try {
            // access _storage. do not allow an exception here.
            _storage.remove(key);
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public int count()
    {
        return _storage.size();
    }
}