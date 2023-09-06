package com.lld.multithreadedlogging.logger.impl;

import com.lld.multithreadedlogging.model.message.WritableMessage;
import com.lld.multithreadedlogging.logger.InternalLogger;

public class FileInternalLogger implements InternalLogger {
    private FileInternalLogger() {

    }

    private static FileInternalLogger instance;

    public static FileInternalLogger getInstance() {
        if (instance == null) {
            synchronized (FileInternalLogger.class) {
                if (instance == null) {
                    instance = new FileInternalLogger();
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
