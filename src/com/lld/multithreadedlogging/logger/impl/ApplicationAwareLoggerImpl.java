package com.lld.multithreadedlogging.logger.impl;

import com.lld.multithreadedlogging.constant.LogLevel;
import com.lld.multithreadedlogging.constant.SupportedLogMedium;
import com.lld.multithreadedlogging.model.message.ApplicationAwareMessage;
import com.lld.multithreadedlogging.model.message.WritableMessage;
import com.lld.multithreadedlogging.logger.ApplicationAwareLogger;
import com.lld.multithreadedlogging.service.QueueService;

import java.util.ArrayList;
import java.util.List;

/**
 *  Applications will talk to this logger instance to publish message to the system
 */
public class ApplicationAwareLoggerImpl implements ApplicationAwareLogger {
    QueueService queueService;

    public ApplicationAwareLoggerImpl(QueueService queueService) {
        this.queueService = queueService;
    }
    @Override
    public void info(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException {
        this.queueService.publish(getWritableMessages(applicationAwareMessage, LogLevel.INFO));
    }

    @Override
    public void debug(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException {
        this.queueService.publish(getWritableMessages(applicationAwareMessage, LogLevel.DEBUG));
    }

    @Override
    public void warn(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException {
        this.queueService.publish(getWritableMessages(applicationAwareMessage, LogLevel.WARN));
    }

    @Override
    public void error(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException {
        this.queueService.publish(getWritableMessages(applicationAwareMessage, LogLevel.ERROR));
    }

    private List<WritableMessage> getWritableMessages(ApplicationAwareMessage applicationAwareMessage, LogLevel logLevel) {
        List<WritableMessage> writableMessages = new ArrayList<>();
        for (SupportedLogMedium supportedLogMedium: applicationAwareMessage.getMediums()) {
            writableMessages.add(new WritableMessage(applicationAwareMessage, logLevel, supportedLogMedium));
        }

        return writableMessages;
    }
}
