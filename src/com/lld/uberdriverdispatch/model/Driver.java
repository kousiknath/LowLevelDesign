package com.lld.uberdriverdispatch.model;

import com.lld.uberdriverdispatch.constant.DriverStatus;

import java.util.UUID;

public class Driver {
    private String id;
    private String name;
    private DriverStatus status;

    public Driver(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.status = DriverStatus.AVAILABLE;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public synchronized void assign() {
        this.status = DriverStatus.ASSIGNED;
    }

    public synchronized void release() {
        this.status = DriverStatus.AVAILABLE;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.id.equals(((Driver)o).id);
    }
}
