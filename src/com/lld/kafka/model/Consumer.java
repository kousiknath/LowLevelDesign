package com.lld.kafka.model;

import java.util.UUID;

public class Consumer {
    String id;
    ConsumerGroup consumerGroup;

    public Consumer(ConsumerGroup consumerGroup) {
        this.id = UUID.randomUUID().toString();
        this.consumerGroup = consumerGroup;
        this.consumerGroup.addConsumer(this);
    }

    public ConsumerGroup getConsumerGroup() {
        return this.consumerGroup;
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

        Consumer other = (Consumer) o;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}
