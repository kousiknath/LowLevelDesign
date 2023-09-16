package com.lld.chatapplication.model;

import java.util.Objects;

public class UniqueUserContact {
    private String phoneNo;
    private String email;

    public UniqueUserContact(String phoneNo, String email) {
        this.phoneNo = phoneNo;
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNo, email);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        UniqueUserContact other = (UniqueUserContact) o;

        return Objects.equals(this.email, other.email)
                && Objects.equals(this.phoneNo, other.phoneNo);
    }
}
