package com.lld.multithreadedlogging.service.impl;

import com.lld.multithreadedlogging.configuration.Config;
import com.lld.multithreadedlogging.model.message.WritableMessage;
import com.lld.multithreadedlogging.service.QueueService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueServiceImpl implements QueueService {
    private ArrayBlockingQueue<WritableMessage> blockingQueue;
    private int capacity;

    public QueueServiceImpl(int cap) {
        this.capacity = Math.max(cap, Config.DEFAULT_CAPACITY);
        this.blockingQueue = new ArrayBlockingQueue<>(this.capacity);
    }

    @Override
    public void publish(List<WritableMessage> messages) throws InterruptedException {
        Objects.requireNonNull(messages);

        for (WritableMessage message: messages) {
            this.blockingQueue.offer(message, Config.DEFAULT_PUBLISH_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public WritableMessage consume() throws InterruptedException {
        return this.blockingQueue.poll(Config.DEFAULT_CONSUMPTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }
}
