package com.lld.chatapplication.model;

import java.util.UUID;

public abstract class User {
    private String id;
    private String name;
    private final UniqueUserContact uniqueUserContact;

    public User(String name, UniqueUserContact uniqueUserContact) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.uniqueUserContact = uniqueUserContact;
    }

    public String getName() {
        return this.name;
    }

    public UniqueUserContact getUniqueUserContact() {
        return this.uniqueUserContact;
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

        return this.uniqueUserContact.equals(((User) o).uniqueUserContact);
    }
}
