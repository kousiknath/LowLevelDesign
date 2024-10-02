package com.lld.multithreadedproducerconsumer;

public class SharedVariable {
    private volatile boolean stop;

    public SharedVariable() {
        this.stop = false;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
