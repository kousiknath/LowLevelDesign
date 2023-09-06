package com.lld.multithreadedlogging.logger.impl;

import com.lld.multithreadedlogging.model.message.WritableMessage;
import com.lld.multithreadedlogging.logger.InternalLogger;

public class DBInternalLogger implements InternalLogger {
    private DBInternalLogger() {
        // Initiate connection to DB driver here
    }

    private static DBInternalLogger instance;

    public static DBInternalLogger getInstance() {
        if (instance == null) {
            synchronized (DBInternalLogger.class) {
                if (instance == null) {
                    instance = new DBInternalLogger();
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
