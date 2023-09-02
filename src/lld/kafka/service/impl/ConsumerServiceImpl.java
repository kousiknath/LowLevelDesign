package lld.kafka.service.impl;

import lld.kafka.model.Consumer;
import lld.kafka.model.Message;
import lld.kafka.model.Partition;
import lld.kafka.model.Topic;
import lld.kafka.service.ConsumerGroupService;
import lld.kafka.service.ConsumerService;

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
