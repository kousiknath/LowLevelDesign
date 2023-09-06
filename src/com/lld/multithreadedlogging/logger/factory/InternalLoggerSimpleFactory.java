package com.lld.multithreadedlogging.logger.factory;

import com.lld.multithreadedlogging.constant.SupportedLogMedium;
import com.lld.multithreadedlogging.logger.InternalLogger;
import com.lld.multithreadedlogging.logger.impl.DBInternalLogger;
import com.lld.multithreadedlogging.logger.impl.FileInternalLogger;
import com.lld.multithreadedlogging.logger.impl.NetworkInternalLogger;

public class InternalLoggerSimpleFactory {
    public static InternalLogger getLoggerInstance(SupportedLogMedium medium) {
        switch (medium) {
            case DB:
                return DBInternalLogger.getInstance();
            case FILE:
                return FileInternalLogger.getInstance();
            case NETWORK:
                return NetworkInternalLogger.getInstance();
            default:
                return null;
        }
    }
}
