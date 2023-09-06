package com.lld.multithreadedlogging.model.message;

import com.lld.multithreadedlogging.constant.LogLevel;
import com.lld.multithreadedlogging.constant.SupportedLogMedium;

import java.time.Instant;
import java.util.List;

/**
 * ApplicationAwareMessage is the message produced by the application.
 */

public class ApplicationAwareMessage {
    private String msg;
    private Instant time;
    private List<SupportedLogMedium> mediums;
    private Thread thread;

    public ApplicationAwareMessage(String msg, List<SupportedLogMedium> mediums, Thread thread) {
        this.msg = msg;
        this.mediums = mediums;
        this.time = Instant.now();
        this.thread = thread;
    }

    public String getMsg() {
        return msg;
    }

    public Instant getTime() {
        return time;
    }

    public List<SupportedLogMedium> getMediums() {
        return mediums;
    }

    public Thread getThread() {
        return thread;
    }
}
