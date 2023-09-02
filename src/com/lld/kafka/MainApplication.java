package com.lld.kafka;

import com.lld.kafka.model.Consumer;
import com.lld.kafka.model.ConsumerGroup;
import com.lld.kafka.model.Producer;
import com.lld.kafka.model.Topic;
import com.lld.kafka.service.ConsumerService;
import com.lld.kafka.service.TopicService;
import com.lld.kafka.service.impl.ConsumerServiceImpl;
import com.lld.kafka.service.impl.TopicServiceImpl;
import com.lld.kafka.worker.ConsumerWorker;
import com.lld.kafka.worker.ProducerWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {

    private static void test1() {
        TopicService topicService = new TopicServiceImpl();
        Topic topic = topicService.createTopic("my-topic", 3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Producer producer = new Producer();
        executorService.execute(new ProducerWorker(topic, producer, topicService));

        ConsumerGroup consumerGroup = new ConsumerGroup();
        ConsumerService consumerService = new ConsumerServiceImpl();

        Consumer consumer1 = new Consumer(consumerGroup);
        consumerService.subscribe(topic, consumer1);
        executorService.execute(new ConsumerWorker(consumer1, consumerService));

        Consumer consumer2 = new Consumer(consumerGroup);
        consumerService.subscribe(topic, consumer2);
        executorService.execute(new ConsumerWorker(consumer2, consumerService));

        Consumer consumer3 = new Consumer(consumerGroup);
        consumerService.subscribe(topic, consumer3);
        executorService.execute(new ConsumerWorker(consumer3, consumerService));

        // Consumer 4 should remain idle as we have only 3 partitions in the topic
        Consumer consumer4 = new Consumer(consumerGroup);
        consumerService.subscribe(topic, consumer4);
        executorService.execute(new ConsumerWorker(consumer4, consumerService));

        executorService.shutdown();
    }

    public static void main(String[] args) {
        test1();
    }
}
