package lld.kafka.service.impl;

import lld.kafka.model.Message;
import lld.kafka.model.MessageMetadata;
import lld.kafka.model.Topic;
import lld.kafka.repository.TopicRepository;
import lld.kafka.service.TopicService;
import lld.kafka.service.routing.strategy.RoutingStrategy;
import lld.kafka.service.routing.strategy.impl.RoundRobinRoutingStrategy;

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
