package com.lld.uberdriverdispatch.worker;

import com.lld.uberdriverdispatch.model.Driver;

public class DriverAcceptanceResult {
    boolean accepted;
    long timeStamp;
    Driver acceptedBy;

    public DriverAcceptanceResult(boolean status, long ts, Driver acceptedBy) {
        this.accepted = status;
        this.timeStamp = ts;
        this.acceptedBy = acceptedBy;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Driver getAcceptedBy() {
        return acceptedBy;
    }

    @Override
    public String toString() {
        return "Status = " + accepted + " timestamp = " + timeStamp + " accepted by = " + acceptedBy.getName();
    }
}