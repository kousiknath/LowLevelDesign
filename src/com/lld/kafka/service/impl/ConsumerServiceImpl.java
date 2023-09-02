package com.lld.kafka.service.impl;

import com.lld.kafka.model.Consumer;
import com.lld.kafka.model.Message;
import com.lld.kafka.model.Partition;
import com.lld.kafka.model.Topic;
import com.lld.kafka.service.ConsumerGroupService;
import com.lld.kafka.service.ConsumerService;

import java.util.Objects;

public class ConsumerServiceImpl implements ConsumerService {
    private ConsumerGroupService consumerGroupService;

    public ConsumerServiceImpl() {
        this.consumerGroupService = new ConsumerGroupServiceImpl();
    }

    @Override
    public Partition subscribe(Topic topic, Consumer consumer) {
        Objects.requireNonNull(topic);
        Objects.requireNonNull(consumer);

        return this.consumerGroupService.registerConsumer(topic, consumer);
    }

    @Override
    public Message consume(Consumer consumer) {
        return this.consumerGroupService.getAMessage(consumer);
    }
}
