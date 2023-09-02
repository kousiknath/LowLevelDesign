package lld.kafka.service;

import lld.kafka.model.Consumer;
import lld.kafka.model.Message;
import lld.kafka.model.Partition;
import lld.kafka.model.Topic;

public interface ConsumerGroupService {
    Partition registerConsumer(Topic topic, Consumer consumer);
    Message getAMessage(Consumer consumer);
}
