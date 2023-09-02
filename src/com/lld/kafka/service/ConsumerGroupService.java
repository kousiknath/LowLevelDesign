package com.lld.kafka.service;

import com.lld.kafka.model.Consumer;
import com.lld.kafka.model.Message;
import com.lld.kafka.model.Partition;
import com.lld.kafka.model.Topic;

public interface ConsumerGroupService {
    Partition registerConsumer(Topic topic, Consumer consumer);
    Message getAMessage(Consumer consumer);
}
