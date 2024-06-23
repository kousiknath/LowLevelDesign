package com.lld.inmemorycache.service.impl.policy;

import com.lld.inmemorycache.model.DoublyLinkedList;
import com.lld.inmemorycache.service.EvictionPolicy;
import com.lld.inmemorycache.model.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LRUEvictionPolicy implements EvictionPolicy {
    private DoublyLinkedList keys;
    private HashMap<String, Node> mapper;

    private ReentrantLock lock;

    public LRUEvictionPolicy() {
        keys = new DoublyLinkedList();
        mapper = new LinkedHashMap<>();
        lock = new ReentrantLock(true);
    }

    @Override
    public void keyAccessed(String key) {
        lock.lock();
        try {
            // key is already present.
            if (mapper.containsKey(key)) {
                // access the node and move it to the front.
                Node keyNode = mapper.get(key);
                // delete the node.
                keys.delete(keyNode);
                // add to front.
                keys.addFront(key);
            } else {
                // first time encountering this key.
                Node front = keys.addFront(key);
                mapper.put(key, front);
            }

        } catch (Exception ex) {
            // do something here.
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void keyEvicted(String key) {
        lock.lock();
        try {
            if (mapper.containsKey(key)) {
                Node keyNode = mapper.get(key);
                keys.delete(keyNode);
                mapper.remove(key);
            }
        } catch (Exception ex) {
            // do something here.

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getKeyToEvict() {
        if (keys.count() > 0) return keys.last().data;
        return null;
    }
}
