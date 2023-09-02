package lld.kafka.service.routing.strategy;

import lld.kafka.model.Message;
import lld.kafka.model.MessageMetadata;
import lld.kafka.model.Topic;

public interface RoutingStrategy {
    MessageMetadata route(Topic topic, Message message);
}
