package com.lld.kafka.service.impl;

import com.lld.kafka.model.Message;
import com.lld.kafka.model.MessageMetadata;
import com.lld.kafka.model.Topic;
import com.lld.kafka.repository.TopicRepository;
import com.lld.kafka.service.TopicService;
import com.lld.kafka.service.routing.strategy.RoutingStrategy;
import com.lld.kafka.service.routing.strategy.impl.RoundRobinRoutingStrategy;

import java.util.Objects;

public class TopicServiceImpl implements TopicService {
    private TopicRepository topicRepository;
    private RoutingStrategy routingStrategy;

    public TopicServiceImpl() {
        this.topicRepository = new TopicRepository();
        this.routingStrategy = new RoundRobinRoutingStrategy();
    }

    @Override
    public Topic createTopic(String name, int numPartitions) {
        Topic topic = new Topic(name, numPartitions);
        return this.topicRepository.addTopic(topic);
    }

    @Override
    public MessageMetadata publishMessage(Topic topic, Message message) {
        Objects.requireNonNull(topic);
        Objects.requireNonNull(message);

        return this.routingStrategy.route(topic, message);
    }
}
