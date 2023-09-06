package com.lld.multithreadedlogging.logger;

import com.lld.multithreadedlogging.model.message.ApplicationAwareMessage;
import com.lld.multithreadedlogging.model.message.WritableMessage;

public interface InternalLogger {
    void log(WritableMessage message);
}
