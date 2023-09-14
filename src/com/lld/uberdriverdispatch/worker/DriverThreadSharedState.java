package com.lld.uberdriverdispatch.worker;

public class DriverThreadSharedState {
    private boolean state;
    private long time;

    public synchronized boolean setState() {
        if (!state) {
            state = true;
            time = System.currentTimeMillis();
            return true;
        }

        return false;
    }

    public long getTime() {
        return this.time;
    }
}