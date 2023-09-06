package com.lld.multithreadedlogging.logger;

import com.lld.multithreadedlogging.model.message.ApplicationAwareMessage;

public interface ApplicationAwareLogger {
    void info(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException;
    void debug(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException;
    void warn(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException;
    void error(ApplicationAwareMessage applicationAwareMessage) throws InterruptedException;
}
