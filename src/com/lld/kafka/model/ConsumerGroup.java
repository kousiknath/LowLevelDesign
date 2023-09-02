package com.lld.kafka.model;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerGroup {
    private String groupId;
    private Set<Consumer> consumers;
    private Map<Partition, Integer> currentOffset;
    private Map<Partition, Integer> committedOffset; // Can be used to contain the committed offsets just for crash recovery purpose
    private Map<Consumer, Partition> consumerPartitionMap;

    private static ConsumerGroup defaultInstance;

    private Lock lock;

    public static ConsumerGroup getDefaultInstance() {
        if (defaultInstance == null) {
            defaultInstance = new ConsumerGroup();
            defaultInstance.groupId = "default-consumer-group";
        }

        return defaultInstance;
    }

    public ConsumerGroup() {
        this.groupId = UUID.randomUUID().toString();
        this.consumers = new HashSet<>();
        this.currentOffset = new HashMap<>();
        this.committedOffset = new HashMap<>();
        this.consumerPartitionMap = new HashMap<>();
        this.lock = new ReentrantLock();
    }

    public void addConsumer(Consumer consumer) {
        Objects.requireNonNull(consumer);
        this.consumers.add(consumer); // Irrespective of partition assignment, add the consumer to the group
    }

    public int getConsumerCount() {
        return this.consumers.size();
    }

    public boolean isPartitionAllocated(Partition partition) {
        return new HashSet<>(this.consumerPartitionMap.values()).contains(partition);
    }

    public Partition allocatePartition(Partition partition, Consumer consumer) {
        this.currentOffset.put(partition, -1);
        this.consumerPartitionMap.put(consumer, partition);
        return partition;
    }

    public Message getMessageForConsumer(Consumer consumer) {
        Message message = null;
        Partition partition = this.consumerPartitionMap.get(consumer);
        if (partition == null) {
            return message;
        }

        try {
            lock.lock(); // Check if we need this lock really as in our design, a single consumer is always
            // mapped to a single partition. So, there is no chance of overriding partition data by multiple threads

            int lastOffset = this.currentOffset.get(partition);
            int nextOffset = lastOffset + 1;

            message = partition.getMessageAtOffset(nextOffset);
            if (message == null) {
                // no more message
                return null;
            }

            this.currentOffset.put(partition, nextOffset);
        } finally {
            lock.unlock();
        }

        return message;
    }

    @Override
    public int hashCode() {
        return groupId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (o == this) {
            return true;
        }

        ConsumerGroup other = (ConsumerGroup) o;
        return this.groupId.equals(other.groupId);
    }
}
