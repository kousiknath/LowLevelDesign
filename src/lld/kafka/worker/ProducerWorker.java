package lld.kafka.worker;

import lld.kafka.model.Message;
import lld.kafka.model.MessageMetadata;
import lld.kafka.model.Producer;
import lld.kafka.model.Topic;
import lld.kafka.service.TopicService;

import java.util.Random;

public class ProducerWorker implements Runnable {
    Topic topic;
    Producer producer;
    TopicService topicService;

    public ProducerWorker(Topic topic, Producer producer, TopicService topicService) {
        this.topic = topic;
        this.producer = producer;
        this.topicService = topicService;
    }

    @Override
    public void run() {
        while (true) {
            Message message = new Message("random message " + new Random().nextInt(100000));
            System.out.println("[Thread - " + Thread.currentThread().getName() + "] Producer + " + producer.toString() + "producing message = "
                    + message + " to topic " + topic.getName());
            MessageMetadata messageMetadata = this.topicService.publishMessage(topic, message);
            System.out.println("Message published to: " + messageMetadata);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
