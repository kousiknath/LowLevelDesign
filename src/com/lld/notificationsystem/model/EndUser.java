package com.lld.notificationsystem.model;

import java.util.UUID;

public class EndUser {
    private String id;
    private String name;
    private String email;

    public EndUser(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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

        return this.id.equals(((EndUser) o).id);
    }
}
