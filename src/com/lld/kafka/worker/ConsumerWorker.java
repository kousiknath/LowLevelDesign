package com.lld.kafka.worker;

import com.lld.kafka.model.Consumer;
import com.lld.kafka.model.Message;
import com.lld.kafka.service.ConsumerService;

public class ConsumerWorker implements Runnable {
    Consumer consumer;
    ConsumerService consumerService;

    public ConsumerWorker(Consumer consumer, ConsumerService consumerService) {
        this.consumer = consumer;
        this.consumerService = consumerService;
    }

    @Override
    public void run() {
        while (true) {
            Message message = consumerService.consume(consumer);
            System.out.println("[Thread-" + Thread.currentThread().getName() + "] consumer "
                    + consumer + " consumed message = " + message);

            try {
                Thread.sleep(1200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
