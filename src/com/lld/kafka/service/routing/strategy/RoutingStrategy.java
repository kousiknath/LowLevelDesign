package com.lld.kafka.service.routing.strategy;

import com.lld.kafka.model.Message;
import com.lld.kafka.model.MessageMetadata;
import com.lld.kafka.model.Topic;

public interface RoutingStrategy {
    MessageMetadata route(Topic topic, Message message);
}
