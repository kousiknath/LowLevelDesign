package com.lld.multithreadedlogging.service;

import com.lld.multithreadedlogging.model.message.WritableMessage;

import java.util.List;

public interface QueueService {
    void publish(List<WritableMessage> message) throws InterruptedException;
    WritableMessage consume() throws InterruptedException;
}
