package com.lld.uberdriverdispatch.model;

import java.util.UUID;

public class RideRequest {
    private String id;
    private User user;
    private long requestTimestamp;
    private Product product;
    private Location location;

    public RideRequest(User user, Product product, Location location) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.product = product;
        this.location = location;
        this.requestTimestamp = System.nanoTime();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public long getRequestTimestamp() {
        return requestTimestamp;
    }

    public Product getProduct() {
        return product;
    }

    public Location getLocation() {
        return location;
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

        RideRequest other = (RideRequest) o;
        return this.id.equals(other.id);
    }
}
