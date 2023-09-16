package com.lld.chatapplication.model;

// User having business account
public class BusinessUser extends User {
    private Business business;
    private String description;

    public BusinessUser(String name, UniqueUserContact identifier, Business business) {
        super(name, identifier);
        this.business = business;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
