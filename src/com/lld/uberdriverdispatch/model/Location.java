package com.lld.uberdriverdispatch.model;

public class Location {
    private String id;
    private String address;
    private double lat;
    private double lng;

    public Location(String address, double lat, double lng) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
