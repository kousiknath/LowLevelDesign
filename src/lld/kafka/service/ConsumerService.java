package lld.kafka.service;

import lld.kafka.model.Consumer;
import lld.kafka.model.Message;
import lld.kafka.model.Partition;
import lld.kafka.model.Topic;

import java.util.List;

public interface ConsumerService {
    Partition subscribe(Topic topic, Consumer consumer);
    Message consume(Consumer consumer); // Clarify if you need to fetch in bulk
}
