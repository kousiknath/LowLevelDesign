package com.lld.multithreadedlogging.worker;

import com.lld.multithreadedlogging.logger.InternalLogger;
import com.lld.multithreadedlogging.logger.factory.InternalLoggerSimpleFactory;
import com.lld.multithreadedlogging.model.message.WritableMessage;
import com.lld.multithreadedlogging.service.QueueService;

public class Consumer implements Runnable {
    private QueueService queueService;

    public Consumer(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                WritableMessage message = this.queueService.consume();
                InternalLogger logger = InternalLoggerSimpleFactory.getLoggerInstance(message.getMedium());
                logger.log(message);

                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
