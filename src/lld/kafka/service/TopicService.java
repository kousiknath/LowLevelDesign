package lld.kafka.service;

import lld.kafka.model.Message;
import lld.kafka.model.MessageMetadata;
import lld.kafka.model.Topic;

public interface TopicService {
    Topic createTopic(String name, int numPartitions);
    MessageMetadata publishMessage(Topic topic, Message message);
}
