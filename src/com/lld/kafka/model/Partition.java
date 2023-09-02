package com.lld.kafka.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Partition {
    private String id;
    private Topic topic;
    private List<Message> messages;
    private Lock lock;

    public Partition(Topic topic) {
        this.id = UUID.randomUUID().toString();
        this.topic = topic;
        this.messages = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public String getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public int addMessage(Message message) {
        int offset;

        try {
            lock.lock();

            this.messages.add(message);
            offset = this.messages.size() - 1;
        } finally {
            lock.unlock();
        }

        return offset;
    }

    public Message getMessageAtOffset(int offset) {
        if (offset >= messages.size()) {
            return null;
        }

        return this.messages.get(offset);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Partition other = (Partition) o;
        return this.id.equals(other.id);
    }
}
