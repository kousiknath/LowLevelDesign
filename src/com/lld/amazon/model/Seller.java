package com.lld.amazon.model;

public class Seller {
    private String name;
    private Address address;

    public Seller(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
