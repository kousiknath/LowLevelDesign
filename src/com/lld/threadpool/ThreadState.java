package com.lld.threadpool;

public class ThreadState {
    private State state;
    private long lastUpdated;

    public ThreadState() {
        this(State.AVAILABLE);
    }

    public ThreadState(State state) {
        this.state = state;
        this.lastUpdated = System.currentTimeMillis();
    }

    public State getState() {
        return state;
    }

    public synchronized void setState(State state) {
        this.state = state;
        this.lastUpdated = System.currentTimeMillis();
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return "ThreadState{" +
                "state=" + state +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
