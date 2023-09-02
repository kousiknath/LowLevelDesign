package lld.kafka.repository;

import lld.kafka.model.Topic;

import java.util.HashMap;
import java.util.Map;

public class TopicRepository {
    private Map<String, Topic> topics; // topic name to Topic objects map

    public TopicRepository() {
        this.topics = new HashMap<>();
    }

    public Topic addTopic(Topic topic) {
        if (topics.containsKey(topic.getName())) {
            return null;
        }

        this.topics.put(topic.getName(), topic);
        return topic;
    }
}
