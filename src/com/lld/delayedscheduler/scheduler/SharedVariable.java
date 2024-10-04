package com.lld.delayedscheduler.scheduler;

public class SharedVariable {
    private volatile boolean stop;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
