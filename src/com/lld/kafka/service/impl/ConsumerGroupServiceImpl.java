package com.lld.kafka.service.impl;

import com.lld.kafka.model.*;
import com.lld.kafka.service.ConsumerGroupService;

public class ConsumerGroupServiceImpl implements ConsumerGroupService {
    @Override
    public Partition registerConsumer(Topic topic, Consumer consumer) {
        Partition allocatedPartition = null;
        ConsumerGroup consumerGroup = fetchConsumerGroup(consumer);

        if (consumerGroup.getConsumerCount() > topic.getMaxPartitions()) {
            // No need to allocate partition to the new consumer as there are already consumers consuming them.
            // This consumer will remain idle
            return allocatedPartition;
        }

        for (Partition topicPartition: topic.getPartitions()) {
            if (!consumerGroup.isPartitionAllocated(topicPartition)) {
                allocatedPartition = consumerGroup.allocatePartition(topicPartition, consumer);
                break;
            }
        }

        return allocatedPartition;
    }

    private ConsumerGroup fetchConsumerGroup(Consumer consumer) {
        ConsumerGroup consumerGroup = consumer.getConsumerGroup();
        if (consumerGroup == null) {
            consumerGroup = ConsumerGroup.getDefaultInstance();
        }

        return consumerGroup;
    }

    @Override
    public Message getAMessage(Consumer consumer) {
        ConsumerGroup consumerGroup = fetchConsumerGroup(consumer);
        return consumerGroup.getMessageForConsumer(consumer);
    }
}
