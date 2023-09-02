package com.lld.kafka.model;

public class MessageMetadata {
    Partition partition;
    int offset;

    public MessageMetadata(Partition partition, int offset) {
        this.partition = partition;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Partition = " + partition.getId() + " offset = " + offset;
    }
}
