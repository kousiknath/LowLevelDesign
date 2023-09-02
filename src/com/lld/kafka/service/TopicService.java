package com.lld.kafka.service;

import com.lld.kafka.model.Message;
import com.lld.kafka.model.MessageMetadata;
import com.lld.kafka.model.Topic;

public interface TopicService {
    Topic createTopic(String name, int numPartitions);
    MessageMetadata publishMessage(Topic topic, Message message);
}
