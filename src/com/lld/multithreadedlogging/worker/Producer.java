package com.lld.multithreadedlogging.worker;

import com.lld.multithreadedlogging.constant.SupportedLogMedium;
import com.lld.multithreadedlogging.model.message.ApplicationAwareMessage;
import com.lld.multithreadedlogging.logger.ApplicationAwareLogger;
import com.lld.multithreadedlogging.service.QueueService;
import com.lld.multithreadedlogging.logger.impl.ApplicationAwareLoggerImpl;

import java.util.Arrays;
import java.util.Random;

public class Producer implements Runnable {
    private QueueService queueService;

    public Producer(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void run() {
        while (true) {
            ApplicationAwareLogger logger = new ApplicationAwareLoggerImpl(this.queueService);

            for (int i = 0; i < 100; i++) {
                try {
                    logger.info(new ApplicationAwareMessage("info - " + new Random().nextInt(10000),
                            Arrays.asList(SupportedLogMedium.FILE, SupportedLogMedium.DB), Thread.currentThread()));
                    logger.debug(new ApplicationAwareMessage("debug - " + new Random().nextInt(10000),
                            Arrays.asList(SupportedLogMedium.FILE, SupportedLogMedium.DB), Thread.currentThread()));
                    logger.warn(new ApplicationAwareMessage("warn - " + new Random().nextInt(10000),
                            Arrays.asList(SupportedLogMedium.FILE, SupportedLogMedium.DB), Thread.currentThread()));
                    logger.error(new ApplicationAwareMessage("error - " + new Random().nextInt(10000),
                            Arrays.asList(SupportedLogMedium.FILE, SupportedLogMedium.DB), Thread.currentThread()));

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
