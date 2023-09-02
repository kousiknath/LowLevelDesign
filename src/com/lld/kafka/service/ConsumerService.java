package com.lld.kafka.service;

import com.lld.kafka.model.Consumer;
import com.lld.kafka.model.Message;
import com.lld.kafka.model.Partition;
import com.lld.kafka.model.Topic;

public interface ConsumerService {
    Partition subscribe(Topic topic, Consumer consumer);
    Message consume(Consumer consumer); // Clarify if you need to fetch in bulk
}
