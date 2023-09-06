package com.lld.multithreadedlogging.logger.impl;

import com.lld.multithreadedlogging.model.message.WritableMessage;
import com.lld.multithreadedlogging.logger.InternalLogger;

public class NetworkInternalLogger implements InternalLogger {
    private NetworkInternalLogger() {
        // Initiate connection to network driver / http connection which will
        // write to that particular logger
    }

    private static NetworkInternalLogger instance;

    public static NetworkInternalLogger getInstance() {
        if (instance == null) {
            synchronized (NetworkInternalLogger.class) {
                if (instance == null) {
                    instance = new NetworkInternalLogger();
                }
            }
        }

        return instance;
    }

    @Override
    public void log(WritableMessage message) {
        System.out.println(message);
    }
}
