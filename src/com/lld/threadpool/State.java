package com.lld.threadpool;

public enum State {
    AVAILABLE,
    ASSIGNED,
    BUSY,
    TASK_COMPLETE,
    TASK_COMPLETE_WITH_EXCEPTION,
}
