package com.lld.kafka.service.routing.strategy.impl;

import com.lld.kafka.model.Message;
import com.lld.kafka.model.MessageMetadata;
import com.lld.kafka.model.Partition;
import com.lld.kafka.service.routing.strategy.RoutingStrategy;
import com.lld.kafka.model.Topic;

import java.util.Random;

public class RoundRobinRoutingStrategy implements RoutingStrategy {
    @Override
    public MessageMetadata route(Topic topic, Message message) {
        Partition partition = topic.getPartitions().get(new Random().nextInt(topic.getMaxPartitions()));
        int atOffset = partition.addMessage(message);
        return new MessageMetadata(partition, atOffset);
    }
}
