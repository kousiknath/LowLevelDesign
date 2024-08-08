package com.lld.amazon.model;

import java.util.Objects;

public class Customer {
    private String name;
    private String email;
    private CustomerProfile profile;

    public Customer(String name, String email, CustomerProfile profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public CustomerProfile getProfile() {
        return profile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;
        return this.email.equals(other.email);
    }
}
