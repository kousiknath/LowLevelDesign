package com.lld.onlinejudge.model;

public class User {
    private String name;
    private String email;
    private UserRole role;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.email.equals(((User) o).email);
    }
}
